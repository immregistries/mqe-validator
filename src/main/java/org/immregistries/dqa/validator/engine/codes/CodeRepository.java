package org.immregistries.dqa.validator.engine.codes;

import java.util.List;
import java.util.Map;

import org.immregistries.dqa.codebase.client.CodeMap;
import org.immregistries.dqa.codebase.client.CodeMapBuilder;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;

public enum CodeRepository {
	INSTANCE;
	
	/**
	 * This codemap object will get all the information we know about a code.
	 */
	  private CodeMap codeMapper = CodeMapBuilder.INSTANCE.getCodeMapFromClasspathResource("/Compiled.xml");
	  
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

	public Code getMfrForCode(String manufacturerCode) {
		//call the new XML based code repository
		Code c = codeMapper.getCodeForCodeset(CodesetType.VACCINATION_MANUFACTURER_CODE, manufacturerCode);
		return c;
	}

	public Code getVaccineProduct(String vaccineCvx, String vaccineMvx, String adminDate) {
		return codeMapper.getProductFor(vaccineCvx, vaccineMvx, adminDate);
	}

	public List<Code> getRelatedCodesForCodeIn(CodesetType codeTypeIn, String codeIn, CodesetType codeTypeDesired) {
		Map<CodesetType, List<Code>> relatedCodes = codeMapper.getRelatedCodes(codeTypeIn,  codeIn);
		return relatedCodes.get(codeTypeDesired);
	}
	
	public Code getFirstRelatedCodeForCodeIn(CodesetType codeTypeIn, String codeIn, CodesetType codeTypeDesired) {
		List<Code> relatedCodes = getRelatedCodesForCodeIn(codeTypeIn, codeIn, codeTypeDesired);
		if (relatedCodes != null && relatedCodes.size() > 0) {
			return relatedCodes.get(0);
		}
		return null;
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
