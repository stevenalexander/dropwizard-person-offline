package com.example.offline.views;

import io.dropwizard.views.View;

public class IndexView extends View {

    public IndexView() {
        super("/templates/partials/index.ftl");
    }
}
