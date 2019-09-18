/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

/**
 * Module enums
 *
 * @author Cem Ikta
 */
public enum BoxPanelType {

    DEFAULT("box-panel-default"),
    PRIMARY("box-panel-primary"),
    INFO("box-panel-info"),
    DANGER("box-panel-danger"),
    SUCCESS("box-panel-success"),
    WARNING("box-panel-warning"),
    PREVIEW("box-panel-preview");

    private String cssClass;

    BoxPanelType(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return cssClass;
    }

}
