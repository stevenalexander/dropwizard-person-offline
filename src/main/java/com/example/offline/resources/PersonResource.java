package com.example.offline.resources;

import com.example.offline.dao.PersonDao;
import com.example.offline.model.Person;
import com.example.offline.views.*;
import io.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/")
@Produces({MediaType.TEXT_HTML})
public class PersonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);
    private final PersonDao personDao;

    public PersonResource(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    public View persons(){
        LOGGER.info("Getting persons list");

        List<Person> persons = personDao.getAll();
        return new PersonsView(persons);
    }

    @GET
    @Path("/add")
    public View personsAdd(){
        return new PersonEditView(null, true, null);
    }

    @POST
    @Path("/add")
    public View personsAddSubmit(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        LOGGER.info("Adding person");

        Person person = new Person(firstName, lastName);
        // in real application validation would occur here, api errors caught and return view with errors
        try {
            personDao.insert(person);
        } catch (Exception exception) {
            return new PersonEditView(person, true, new String[] { "Error" });
        }

        throw new WebApplicationException(Response.seeOther(UriBuilder.fromUri("/persons").build()).build());
    }

    @GET
    @Path("/{personId}")
    public View getPerson(@PathParam("personId") int personId) {
        Person person = personDao.findById(personId);

        return new PersonView(person);
    }

    @GET
    @Path("/{personId}/edit")
    public View personEdit(String callerId, @PathParam("personId") int personId) {
        Person person = personDao.findById(personId);

        return new PersonEditView(person, false, null);
    }

    @POST
    @Path("/{personId}/edit")
    public View personEditSubmit(@PathParam("personId") int personId, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        LOGGER.info("Updating person");

        Person person = new Person(firstName, lastName);
        person.setId(personId);
        // in real application validation would occur here, api errors caught and return view with errors
        try {
            personDao.update(person);
        } catch (Exception exception) {
            return new PersonEditView(person, true, new String[] { "Error" });
        }

        throw new WebApplicationException(Response.seeOther(UriBuilder.fromUri("/persons/" + personId).build()).build());
    }
}
