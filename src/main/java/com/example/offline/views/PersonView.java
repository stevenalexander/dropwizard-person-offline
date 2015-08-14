package com.example.offline.views;

import com.example.offline.model.Person;
import io.dropwizard.views.View;

public class PersonView extends View {

    Person person;

    public Person getPerson() {
        return person;
    }

    public PersonView(Person person) {
        super("/templates/partials/person.ftl");
        this.person = person;
    }
}
