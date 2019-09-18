/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.app.AppPlaces;

import java.util.HashMap;
import java.util.Map;

/**
 * Place
 *
 * @author Cem Ikta
 */
public class Place {

    private AppPlaces name;
    private Map<String, Object> parameters;

    public Place(AppPlaces name) {
        this.name = name;
        this.parameters = new HashMap<>();
    }

    public AppPlaces getName() {
        return name;
    }

    public void addParameter(String key, Object value) {
        parameters.put(key, value);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

}
