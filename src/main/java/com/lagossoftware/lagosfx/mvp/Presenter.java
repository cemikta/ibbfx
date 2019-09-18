/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import javafx.scene.layout.BorderPane;

/**
 * Presenter interface
 * 
 * @param <V> view
 *
 * @author Cem Ikta
 */
public interface Presenter<V extends View> {

    void start(BorderPane container);

    void bind();

    V getView();

}
