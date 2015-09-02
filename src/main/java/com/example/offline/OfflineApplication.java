package com.example.offline;

import com.example.offline.configuration.OfflineConfiguration;
import com.example.offline.dao.PersonDao;
import com.example.offline.resources.HeartbeatResource;
import com.example.offline.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;

import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class OfflineApplication extends Application<OfflineConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfflineApplication.class);

    public static void main(String[] args) throws Exception {
        new OfflineApplication().run(args);
    }

    @Override
    public String getName() {
        return "offline-web-application";
    }

    @Override
    public void initialize(Bootstrap<OfflineConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));

        bootstrap.addBundle(new ViewBundle<OfflineConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(OfflineConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(OfflineConfiguration configuration, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

        final PersonDao personDao = jdbi.onDemand(PersonDao.class);

        try {
            personDao.createTable();
        } catch (Exception ex) {
            LOGGER.info("Person table already exists");
        }

        final PersonResource personResource = new PersonResource(personDao);
        final HeartbeatResource heartbeatResource = new HeartbeatResource();

        environment.jersey().register(personResource);
        environment.jersey().register(heartbeatResource);
    }
}
