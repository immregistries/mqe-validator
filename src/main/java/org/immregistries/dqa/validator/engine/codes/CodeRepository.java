package org.immregistries.dqa.validator.engine.codes;

import java.util.List;
import org.immregistries.dqa.codebase.client.CodeMap;
import org.immregistries.dqa.codebase.client.CodeMapBuilder;
import org.immregistries.dqa.codebase.client.RelatedCode;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum CodeRepository {
  INSTANCE;

  private static final Logger logger = LoggerFactory.getLogger(CodeRepository.class);

  /**
   * This codemap object will get all the information we know about a code.
   */
  private CodeMap codeMapper = CodeMapBuilder.INSTANCE.getDefaultCodeMap();
  private RelatedCode related;

  CodeRepository() {
    // I want to delay building the codemap. this will speed up unit tests.
  }

  public CodeMap getCodeMap() {
    return this.codeMapper;
  }

  public RelatedCode getRelatedCodes() {
    logger.info("getRelatedCodes");
    if (this.related == null) {
      this.related = new RelatedCode(this.getCodeMap());
    }
    return this.related;
  }

  // TODO: get the data for these items.

  // TODO: X make a way to map values before looking them up in the database. DONE!
  // Check the old code on how this was done.
  // get a value out of the message. Check the DQACM. This is called a "Use Value"
  //

  public Code getCodeFromValue(String code, CodesetType codeType) {
    logger.info("getCodeFromValue");
    // This is calling the XML repository
    Code c = this.getCodeMap().getCodeForCodeset(codeType, code);
    return c;
  }

  public Code getFirstRelatedCodeForCodeIn(CodesetType codeIn, String value, CodesetType codeOut) {
    logger.info("getFirstRelatedCodeForCodeIn");
    return this.getCodeMap().getFirstRelatedCodeForCodeIn(codeIn, value, codeOut);
  }

  public List<Code> getRelatedCodesForCodeIn(CodesetType codeIn, String value, CodesetType codeOut) {
    return this.getCodeMap().getRelatedCodesForCodeIn(codeIn, value, codeOut);
  }

  public String getRelatedValue(Code in, CodesetType desired) {
    return this.getCodeMap().getRelatedValue(in, desired);
  }

  public Code getMfrForCode(String manufacturerCode) {
    // call the new XML based code repository
    Code c =
        this.getCodeMap().getCodeForCodeset(CodesetType.VACCINATION_MANUFACTURER_CODE,
            manufacturerCode);
    return c;
  }

  public Code getVaccineProduct(String vaccineCvx, String vaccineMvx, String adminDate) {
    return this.getCodeMap().getProductFor(vaccineCvx, vaccineMvx, adminDate);
  }

  /**
   * A helper method to compare two codes. The Code object is a generated class, so anything put
   * there would get erased the next time its generated.
   */
  public boolean codeEquals(Code code1, Code code2) {
    return this.getCodeMap().codeEquals(code1, code2);
  }
}
