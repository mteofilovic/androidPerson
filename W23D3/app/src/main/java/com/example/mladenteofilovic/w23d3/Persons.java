package com.example.mladenteofilovic.w23d3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by mladen.teofilovic on 21/10/15.
 */
public class Persons {
    private List<Person> persons;

    public Persons() {
        persons = new ArrayList<>();
    }

    public void addPerson(String name, String surname) {
        persons.add(new Person(name, surname));
    }

    public Person getPerson(int index) {
        return persons.get(index);
    }

    public int getSize() {
        return persons.size();
    }

    public void removePerson(UUID id) {
        Iterator<Person> it = persons.iterator();
        while (it.hasNext()) {
            if (it.next().getNumber().equals(id)) {
                it.remove();
                return;
            }
        }
    }
}
