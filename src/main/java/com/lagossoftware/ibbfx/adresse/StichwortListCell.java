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
package com.lagossoftware.ibbfx.adresse;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.AdresseStichwort;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Removal List Cell
 *
 * @author Cem Ikta
 */
public class StichwortListCell extends ListCell<AdresseStichwort> {

    private HBox contentBox;
    private Label lblItem;
    private Label lblRemove;
    private StichwortListCellCallback stichwortListCellCallback;

    public StichwortListCell(StichwortListCellCallback stichwortListCellCallback) {
        super();
        this.stichwortListCellCallback = stichwortListCellCallback;

        lblItem = new Label();
        lblItem.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(lblItem, Priority.ALWAYS);
        lblRemove = new Label();
        lblRemove.setGraphic(ViewHelpers.createImageView(Images.REMOVE_16));
        lblRemove.setTooltip(new Tooltip(I18n.COMMON.getString("action.remove")));
        lblRemove.setOnMouseClicked((MouseEvent event) -> {
            callRemoveCallback(getItem());
        });

        contentBox = new HBox(4, lblItem, lblRemove);        
        setText(null);
    }

    @Override
    public void updateItem(AdresseStichwort item, boolean empty) {
        super.updateItem(item, empty);
        setEditable(false);
        
        if (item != null) {
            lblItem.setText(item.toString());
            setGraphic(contentBox);
        } else {
            setGraphic(null);
        }
    }
    
    private void callRemoveCallback(AdresseStichwort adresseStichwort) {
        if (stichwortListCellCallback != null) {
            stichwortListCellCallback.onRemove(adresseStichwort);
        }
    }
    
}
