package org.immregistries.dqa.validator.engine.codes;

import org.immregistries.dqa.codebase.client.CodeMap;
import org.immregistries.dqa.codebase.client.CodeMapBuilder;
import org.immregistries.dqa.codebase.client.RelatedCode;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;

import java.util.List;

public enum CodeRepository {
	INSTANCE;

	public CodeMap getCodeMap() {
		return codeMapper;
	}

	/**
	 * This codemap object will get all the information we know about a code.
	 */
	  private final CodeMap codeMapper;
	  private final RelatedCode related;

	  CodeRepository() {
	  	this.codeMapper = CodeMapBuilder.INSTANCE.getCodeMap(Thread.currentThread().getContextClassLoader().getResourceAsStream("Compiled.xml"));
	  	this.related = new RelatedCode(codeMapper);
	  }

	  public RelatedCode getRelatedCodes() {
	  	return related;
	  }

	//TODO:  get the data for these items.
	
	//TODO: X make a way to map values before looking them up in the database.  DONE!
	// 		Check the old code on how this was done. 
	//		get a value out of the message.  Check the DQACM.  This is called a "Use Value"  
	//		
	
	public Code getCodeFromValue(String code, CodesetType codeType) {
//		This is calling the XML repository
		Code c = codeMapper.getCodeForCodeset(codeType,  code);
		return c;
	}

	public Code getFirstRelatedCodeForCodeIn(CodesetType codeIn, String value, CodesetType codeOut) {
	  	return this.codeMapper.getFirstRelatedCodeForCodeIn(codeIn, value, codeOut);
	}

	public List<Code> getRelatedCodesForCodeIn(CodesetType codeIn, String value, CodesetType codeOut) {
	  	return this.codeMapper.getRelatedCodesForCodeIn(codeIn, value, codeOut);
	}

	public String getRelatedValue(Code in, CodesetType desired) {
		return this.codeMapper.getRelatedValue(in, desired);
	}

	public Code getMfrForCode(String manufacturerCode) {
		//call the new XML based code repository
		Code c = codeMapper.getCodeForCodeset(CodesetType.VACCINATION_MANUFACTURER_CODE, manufacturerCode);
		return c;
	}

	public Code getVaccineProduct(String vaccineCvx, String vaccineMvx, String adminDate) {
		return codeMapper.getProductFor(vaccineCvx, vaccineMvx, adminDate);
	}

	/**
	 * A helper method to compare two codes.  The Code object is a generated class, so anything put there
	 * would get erased the next time its generated. 
	 * @param code1
	 * @param code2
	 * @return
	 */
	public boolean codeEquals(Code code1, Code code2) {
		return codeMapper.codeEquals(code1, code2);
	}
}
