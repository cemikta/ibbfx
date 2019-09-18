/*
 * Copyright (C) 2016 IBB Management Project, Cem Ikta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lagossoftware.ibbfx.grundlagen;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.mvp.HasParameters;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.mvp.PlaceBuilder;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

import java.util.Map;

/**
 * Grundlagen presenter
 *
 * @author Cem Ikta
 */
public class GrundlagenPresenter extends AbstractPresenter<GrundlagenView>
        implements HasParameters {

    private Map<String, Object> parameters;

    @Inject
    public GrundlagenPresenter(final NavigationManager navigationManager,
            final GrundlagenView view) {
        super(navigationManager, view);
    }

    @Override
    public void start(BorderPane container) {
        container.setLeft(getView().asNode());
        container.setRight(null);
        container.setTop(null);
        container.setCenter(null);
        container.setBottom(null);
        bind();

        AppPlaces openDefaultPlace = (AppPlaces) parameters.get("openDefaultPlace");

        switch (openDefaultPlace) {
            case ANREDE:
                navigationManager.goTo(new Place(AppPlaces.ANREDE));
                break;
            case ARBEITSAUFWAND:
                navigationManager.goTo(new Place(AppPlaces.ARBEITSAUFWAND));
                break;
            case BUNDESLAND:
                navigationManager.goTo(new Place(AppPlaces.BUNDESLAND));
                break;
            case FACHBEREICH:
                navigationManager.goTo(new Place(AppPlaces.FACHBEREICH));
                break;
            case GESCHAEFTSBEREICH:
                navigationManager.goTo(new Place(AppPlaces.GESCHAEFTSBEREICH));
                break;
            case KONTO:
                navigationManager.goTo(new Place(AppPlaces.KONTO));
                break;
            case LAND:
                navigationManager.goTo(new Place(AppPlaces.LAND));
                break;
            case STICHWORT:
                navigationManager.goTo(new Place(AppPlaces.STICHWORT));
                break;
            case TITEL:
                navigationManager.goTo(new Place(AppPlaces.TITEL));
                break;
            case WAEHRUNG:
                navigationManager.goTo(new Place(AppPlaces.WAEHRUNG));
                break;
            case ZGFAKTOR:
                navigationManager.goTo(new Place(AppPlaces.ZGFAKTOR));
                break;
            default:
                navigationManager.goTo(new Place(AppPlaces.ANREDE));
                break;
        }
    }

    @Override
    public void bind() {
        view.getAnredeLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.ANREDE)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.ANREDE).build());
            }
        });
        view.getArbeitsaufwandLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.ARBEITSAUFWAND)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.ARBEITSAUFWAND).build());
            }
        });
        view.getBundeslandLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.BUNDESLAND)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.BUNDESLAND).build());
            }
        });
        view.getFachbereichLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.FACHBEREICH)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.FACHBEREICH).build());
            }
        });
        view.getGeschaeftsbereichLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.GESCHAEFTSBEREICH)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.GESCHAEFTSBEREICH).build());
            }
        });
        view.getKontenrahmenLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.KONTO)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.KONTO).build());
            }
        });
        view.getLandLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.LAND)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.LAND).build());
            }
        });
        view.getStichwortLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.STICHWORT)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.STICHWORT).build());
            }
        });
        view.getTitelLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.TITEL)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.TITEL).build());
            }
        });
        view.getWaehrungLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.WAEHRUNG)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.WAEHRUNG).build());
            }
        });
        view.getZielgruppenfaktorLink().setOnAction((ActionEvent event) -> {
            if (IbbManagement.get().isReadAllowed(AppPlaces.ZGFAKTOR)) {
                navigationManager.goTo(new PlaceBuilder(AppPlaces.GRUNDLAGEN)
                        .parameter("openDefaultPlace", AppPlaces.ZGFAKTOR).build());
            }
        });
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

}
