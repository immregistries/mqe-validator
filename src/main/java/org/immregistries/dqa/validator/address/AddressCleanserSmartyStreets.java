package org.immregistries.dqa.validator.address;

import com.smartystreets.api.StaticCredentials;
import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.us_street.Batch;
import com.smartystreets.api.us_street.Candidate;
import com.smartystreets.api.us_street.Client;
import com.smartystreets.api.us_street.ClientBuilder;
import com.smartystreets.api.us_street.Lookup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.ValidatorProperties;
import org.immregistries.dqa.vxu.DqaAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum AddressCleanserSmartyStreets implements AddressCleanser {
  INSTANCE;
  private static final Logger logger = LoggerFactory.getLogger(AddressCleanserSmartyStreets.class);

  private ValidatorProperties appProps = ValidatorProperties.INSTANCE;

  @Override
  public Map<DqaAddress, DqaAddress> cleanThese(DqaAddress ... addresses) {
    Map<DqaAddress, DqaAddress> results = new HashMap<>();
    List<Candidate> candidates = getSSResults(addresses);

    for (Candidate c : candidates) {
      int idx = c.getInputIndex();
      DqaAddress in = addresses[idx];
      DqaAddress out = new DqaAddress(in);
      String code = c.getAnalysis().getDpvMatchCode();
      out.setCleansingAttempted(true);
      if (StringUtils.isBlank(code) || "N".equalsIgnoreCase(code)) {
        out.setClean(false);
        out.setCleansingResultCode(SmartyStreetResponse.combineCodes("", c.getAnalysis().getDpvFootnotes()));
      } else {
        out.setClean(true);
        out.setStreet(c.getDeliveryLine1());
        out.setStreet2(c.getDeliveryLine2());
        out.setCity(c.getComponents().getCityName());
        out.setStateCode(c.getComponents().getState());
        out.setZip(c.getComponents().getZipCode());
        out.setCountyParishCode(c.getMetadata().getCountyFips());
        out.setCountryCode("USA");
        out.setLongitude(c.getMetadata().getLongitude());
        out.setLatitude(c.getMetadata().getLatitude());
        out.setCleansingResultCode(SmartyStreetResponse.combineCodes(c.getAnalysis().getFootnotes(), c.getAnalysis().getDpvFootnotes()));
      }

      results.put(in, out);
    }

    return results;
  }
  /**
   * Calls SmartyStreets and retrieves the raw results from it.
   *
   * @param addressList Input addresses to cleanse
   * @return List of candidate addresses. If batch.includeInvalid = true, then only one address will be returned in the list.
   */
  public List<Candidate> getSSResults(DqaAddress... addressList) {
    /*
    SmartyStreets API Library:
    the number of retries impacts how long the timeout will be--about 20 seconds per attempt, regardless of what you set the timeout to. 1 retry = 40 seconds
    */
    StaticCredentials credentials = new StaticCredentials(
        appProps.getSsApiAuthId(), appProps.getSsApiAuthToken());
    List<Candidate> results = new ArrayList<>();
    Client client = new ClientBuilder(credentials).retryAtMost(1).build();
    Batch batch = new Batch();
    batch.setIncludeInvalid(true);

    try {
      for (DqaAddress addr : addressList) {
        if (addr.getStreet() == null) {
          continue;//if there's no street, SS will throw an exception for the whole batch.
        }
        Lookup lookup = this.createLookup(addr);
        batch.add(lookup);
        if (batch.size() > 99) {
          results.addAll(this.sendBatch(client, batch));
        }
      }
      if (batch.size() > 0) {
        results.addAll(this.sendBatch(client, batch));
      }
    } catch (SmartyException ex) {
      System.out.println(ex.getMessage());
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return results;
  }

  private List<Candidate> sendBatch(Client client, Batch batch)
      throws IOException, SmartyException {
    List<Candidate> results = new ArrayList<>();
    client.send(batch);
    for (Lookup l : batch.getAllLookups()) {
      results.addAll(l.getResult());
    }
    batch.clear();
    return results;
  }

  private Lookup createLookup(DqaAddress address) {
    Lookup lookup = new Lookup();
    lookup.setStreet(address.getStreet());
    lookup.setStreet2(address.getStreet2());
    lookup.setCity(address.getCity());
    lookup.setState(address.getStateCode());
    lookup.setZipCode(address.getZip());
    lookup.setInputId(String.valueOf(address.hashCode()));

    /*
    SmartyStreets throws an error if the primary street is null
    note that even if you set the primary street to a space, SS will still only return an empty result set
    */
    if (address.getStreet() == null) {
      lookup.setStreet(" ");
      if (logger.isDebugEnabled()) {
        logger.debug("Primary street is null; setting to space.");
      }
    }

    return lookup;
  }
}
