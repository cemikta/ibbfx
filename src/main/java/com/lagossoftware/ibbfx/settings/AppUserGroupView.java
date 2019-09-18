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
package com.lagossoftware.ibbfx.settings;

import com.lagossoftware.ibbfx.entity.AppRight;
import com.lagossoftware.ibbfx.entity.AppUserGroup;
import com.lagossoftware.lagosfx.mvp.BrowserFormView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * App user group view interface
 *
 * @author Cem Ikta
 */
public interface AppUserGroupView extends BrowserFormView<AppUserGroup> {

    Button getBtnCheck();

    Button getBtnUncheck();

    TextField getTfName();

    TableView<AppRightModel> getAppRightTableView();

    TableColumn<AppRightModel, String> getSortColumn();

}
