/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.lagossoftware.lagosfx.control.AppHeaderBar;
import javafx.scene.layout.BorderPane;

/**
 * Application layout
 *
 * @author Cem Ikta
 */
public interface AppLayout extends View {

    AppHeaderBar getAppHeader();

    void setAppHeader(boolean isLogin);

    BorderPane getAppContent();

}
