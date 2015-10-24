package com.example.mladenteofilovic.w23d3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mladen.teofilovic on 21/10/15.
 */
public class Person implements Serializable{

    public String name;
    public String surname;
    public Date newDate;
    public UUID number;


    public Person(String name, String surname){
        number = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        newDate = Calendar.getInstance().getTime();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getNewDate() {
        return newDate;
    }

    public UUID getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    public void setNumber(UUID number) {
        this.number = number;
    }
}
