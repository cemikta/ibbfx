/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import javafx.scene.layout.VBox;

/**
 * Page header control
 *
 * @author Cem Ikta
 */
public class BoxPanel extends VBox {

    private BoxPanelType boxPanelType;

    public BoxPanel() {
        this(BoxPanelType.DEFAULT);
    }

    public BoxPanel(BoxPanelType boxPanelType) {
        this.boxPanelType = boxPanelType;
        setId(boxPanelType.getCssClass());
    }

}
