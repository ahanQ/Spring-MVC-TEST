package com.teemlink.person.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 实体类
 * 
 * @author ahan
 *
 */
public class Person {

  public Person() {
  }

  public Person(Long id, String name, Integer age) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
  }

  private Long id;
  private String name;
  private Integer age;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(obj, this);
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
