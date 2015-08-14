package com.example.offline.views;

import com.example.offline.model.Person;
import io.dropwizard.views.View;

public class PersonEditView extends View {

    Person person;
    boolean isNew;
    String[] errors = null;

    public Person getPerson() {
        return person;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public String[] getErrors() {
        return errors;
    }

    public PersonEditView(Person person, boolean isNew, String[] errors) {
        super("/templates/partials/personEdit.ftl");
        this.person = person;
        this.isNew = isNew;
        this.errors = errors;
    }
}
