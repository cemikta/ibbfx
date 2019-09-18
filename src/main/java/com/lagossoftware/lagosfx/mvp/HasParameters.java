/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import java.util.Map;

/**
 * Has parameters interface
 *
 * @author Cem Ikta
 */
public interface HasParameters {

    Map<String, Object> getParameters();

    void setParameters(Map<String, Object> parameters);

}
