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
package com.lagossoftware.ibbfx.planung;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.ibbfx.entity.Zuschuss;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.List;
import java.util.Optional;

/**
 * Planung Zuschuss presenter
 *
 * @author Cem Ikta
 */
public class PlanungZuschussPresenter extends AbstractPresenter<PlanungZuschussView> {

    private Planung planung;
    private PlanungZuschussDialog planungZuschussDialog;

    @Inject
    public PlanungZuschussPresenter(final NavigationManager navigationManager,
                                    final PlanungZuschussView view) {
        super(navigationManager, view);
    }

    @Override
    public void start(BorderPane container) {
        bind();
    }

    @Override
    public void bind() {
        view.getBtnAddNewZuschuss().setOnAction((ActionEvent event) -> {
            onAddNewZuschuss();
        });

        view.getBtnEditZuschuss().setOnAction((ActionEvent event) -> {
            onEditZuschuss();
        });

        view.getZuschussListView().setCellFactory(
                list -> new PlanungZuschussListCell((Zuschuss zuschuss) -> {
                    planung.getZuschussList().remove(zuschuss);
                    view.getZuschussListView().getItems().remove(zuschuss);
                })
        );

        view.getZuschussListView().setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEditZuschuss();
            }
        });
    }

    private void onAddNewZuschuss() {
        planungZuschussDialog = new PlanungZuschussDialog(new Zuschuss());
        planungZuschussDialog.pop();
        Optional<Zuschuss> result = planungZuschussDialog.showAndWait();

        result.ifPresent(zuschuss -> {
            zuschuss.setPlanung(planung);
            planung.getZuschussList().add(zuschuss);
            view.getZuschussListView().getItems().add(zuschuss);
        });
    }

    private void onEditZuschuss() {
        if (view.getZuschussListView().getSelectionModel().getSelectedItem() != null) {
            planungZuschussDialog = new PlanungZuschussDialog(
                    view.getZuschussListView().getSelectionModel().getSelectedItem());
            planungZuschussDialog.pop();
            Optional<Zuschuss> result = planungZuschussDialog.showAndWait();

            result.ifPresent(zuschuss -> {
                view.getZuschussListView().getItems().set(
                        view.getZuschussListView().getSelectionModel().getSelectedIndex(), zuschuss);
            });
        }
    }

    public void popFields(Planung entity) {
        this.planung = entity;

        view.getZuschussListView().getItems().clear();

        if (entity.getZuschussList() != null) {
            view.getZuschussListView().getItems().addAll(entity.getZuschussList());
            view.getZuschussListView().getSelectionModel().selectFirst();
        }
    }

    public void pushFields(Planung entity) {
        // zuschuesse already in the list
        // TODO if not save clicked, entity lists saved automaticly???
    }

}
