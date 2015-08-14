package com.example.offline.views;

import com.example.offline.model.Person;
import io.dropwizard.views.View;

import java.util.List;

public class PersonsView extends View {

    List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public PersonsView(List<Person> persons) {
        super("/templates/partials/persons.ftl");
        this.persons = persons;
    }
}
