package org.immregistries.mqe.validator.engine.codes;

import org.immregistries.codebase.client.generated.CodeStatus;
import org.immregistries.codebase.client.reference.CodesetType;

public class CodeReceived {

  private CodesetType codeset = null;
  private CodeStatus status;
  private String value;

  public CodesetType getCodeset() {
    return codeset;
  }

  public void setCodeset(CodesetType codeset) {
    this.codeset = codeset;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public CodeStatus getStatus() {
    return status;
  }

  public void setStatus(CodeStatus status) {
    this.status = status;
  }

}
