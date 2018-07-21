package org.immregistries.mqe.validator.report.codes;

import java.util.Objects;

public class VaccineBucket {

  private String code;
  private int age;
  private int count;
  private boolean administered = true;/* default to true. */

  public VaccineBucket(VaccineBucket vb) {
	this(vb.code, vb.age, vb.administered, vb.count);
  }

  public VaccineBucket(String cvx, Integer age, boolean administered) {
	this(cvx, age, administered, 1);
  }

  public VaccineBucket(String cvx, Integer age, boolean administered, Integer count) {
    this.code = cvx;
    this.age = age;
    this.count = count;
    this.administered = administered;
  }

  public boolean isAdministered() {
    return administered;
  }

  public void setAdministered(boolean administered) {
    this.administered = administered;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VaccineBucket that = (VaccineBucket) o;
    return age == that.age && administered == that.administered && Objects.equals(code, that.code);
  }

  @Override
  public int hashCode() {

    return Objects.hash(code, age, administered);
  }

  @Override
  public String toString() {
    return "VaccineBucket{" + "code='" + code + '\'' + ", age=" + age + ", count=" + count
        + ", administered=" + administered + '}';
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void increment() {
    this.incrementBy(1);
  }

  public void incrementBy(int count) {
    this.count += count;
  }

}
