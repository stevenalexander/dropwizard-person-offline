package com.example.offline;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class OfflineApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new OfflineApplication().run(args);
    }

    @Override
    public String getName() {
        return "offline-web-application";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        //environment.jersey().setUrlPattern("/api/*");
    }
}
