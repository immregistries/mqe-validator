package org.immregistries.mqe.validator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ValidatorProperties {
  INSTANCE;
  private final Logger LOGGER = LoggerFactory.getLogger(ValidatorProperties.class);
  private static final Logger logger = LoggerFactory.getLogger(ValidatorProperties.class);
  Properties prop = new Properties();

  private String ssApiAuthId;
  private String ssApiAuthToken;
  private boolean addressCleanserEnabled;


  ValidatorProperties() {
    loadProps();
    this.ssApiAuthId = prop.getProperty("smartystreets.authId");
    this.ssApiAuthToken = prop.getProperty("smartystreets.authToken");
    this.addressCleanserEnabled = "true".equals(prop.getProperty("addresscleanser.enabled"));
  }

  private void loadProps() {
    try {
      prop.load(getInputStremForProperties());
    } catch (IOException e) {
      LOGGER.error("Error reading validator properties, using defaults");
    }
  }

  public InputStream getInputStremForProperties() {
    InputStream is;
    String fileName = "validator.properties";
    try {
      is = getFileFromRootDirectory(fileName);
      LOGGER.warn("Using " + fileName + " from directory");
    } catch (FileNotFoundException | NullPointerException e) {
      LOGGER.warn(fileName + " not found in directory with jar.  checking classpath");
      is = getFileFromClasspath(fileName);
      if (is != null) {
        LOGGER.warn("Using " + fileName + " from classpath (resources folder in jar)");
      }
    }
    if (is != null) {
      return is;
    } else {
      throw new IllegalArgumentException(
          "You cannot reference " + fileName + " if the input stream is null.  Verify that you are building an input stream from a file that exists. ");
    }
  }

  public InputStream getFileFromClasspath(String resourcePath) {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    return loader.getResourceAsStream(resourcePath);
  }

  public InputStream getFileFromRootDirectory(String resourcePath) throws FileNotFoundException {
    final String dir = System.getProperty("user.dir");
    LOGGER.warn("Current dir: " + dir);
    LOGGER.warn("Looking in: " + dir + "/" + resourcePath + " for file");
    return new FileInputStream(resourcePath);
  }


  public String getSsApiAuthId() {
    return ssApiAuthId; 
  }

  public String getSsApiAuthToken() {
    return ssApiAuthToken; 
  }

  public boolean isAddressCleanserEnabled() {
    return addressCleanserEnabled;
  }
}
