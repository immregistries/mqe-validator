package org.immregistries.mqe.validator.report.codes;

import java.util.Objects;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.vxu.VxuField;

public class CollectionBucket {

  private String typeCode;
  private String typeName;
  private String source;
  private String attribute;
  private String value;
  private int count;
  private String status;
  private String label;

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CollectionBucket(VxuField field, String attribute, String value) {
    this(field.toString(), attribute, value);
  }

  public CollectionBucket(String type, String attribute, String value) {
    this.typeCode = type;
    this.attribute = attribute;
    this.value = value;
  }

  public CollectionBucket(String type, String attribute, String value, int count) {
    this.typeCode = type;
    this.attribute = attribute;
    this.value = value;
    this.count = count;
  }

  @Override
  public String toString() {
    return "CollectionBucket{type:" + typeCode + " attribute:" + getAttribute() + " /value: "
        + getValue() + " /count: " + getCount() + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollectionBucket that = (CollectionBucket) o;

    return Objects.equals(this.typeCode, that.typeCode)
        && Objects.equals(this.attribute, that.attribute)
        && Objects.equals(this.source, that.source)
        && Objects.equals(this.value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeCode, source, attribute, value);
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String type) {
    this.typeCode = type;
  }

  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }
}
