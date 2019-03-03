package com.student.entity;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "STUDENT".
 */
@Entity
public class Student {

    @Id
    private Long id;
    private Integer age;
    private String address;
    private String number;

    @Generated
    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    @Generated
    public Student(Long id, Integer age, String address, String number) {
        this.id = id;
        this.age = age;
        this.address = address;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "id = "+ id + "  age = " + age + "  address = " + address + "  number = " + number ;
    }
}
