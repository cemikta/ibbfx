/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.ibbfx.app.AppPlaces;

/**
 * Place builder with parameter objects.
 *
 * @author Cem Ikta
 */
public class PlaceBuilder {

    private Place place;

    public PlaceBuilder(AppPlaces name) {
        this.place = new Place(name);
    }

    public PlaceBuilder parameter(String name, Object value) {
        this.place.getParameters().put(name, value);
        return this;
    }

    public Place build() {
        return place;
    }

}
