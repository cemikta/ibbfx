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

import javafx.beans.property.*;

/**
 * App Right model for TableView
 *
 * @author Cem Ikta
 */
public class AppRightModel {

    private LongProperty id;
    private StringProperty moduleName;
    private StringProperty objectName1;
    private StringProperty objectName2;
    private StringProperty name;
    private BooleanProperty allowRead;
    private BooleanProperty allowCreate;
    private BooleanProperty allowEdit;
    private BooleanProperty allowDelete;

    public AppRightModel(Long id, String moduleName, String objectName1, String objectName2, String name,
                         Boolean allowRead, Boolean allowCreate, Boolean allowEdit, Boolean allowDelete) {
        this.id = new SimpleLongProperty(id);
        this.moduleName = new SimpleStringProperty(moduleName);
        this.objectName1 = new SimpleStringProperty(objectName1);
        this.objectName2 = new SimpleStringProperty(objectName2);
        this.name = new SimpleStringProperty(name);
        this.allowRead = new SimpleBooleanProperty(allowRead);
        this.allowCreate = new SimpleBooleanProperty(allowCreate);
        this.allowEdit = new SimpleBooleanProperty(allowEdit);
        this.allowDelete = new SimpleBooleanProperty(allowDelete);
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getModuleName() {
        return moduleName.get();
    }

    public void setModuleName(String moduleName) {
        this.moduleName.set(moduleName);
    }

    public String getObjectName1() {
        return objectName1.get();
    }

    public void setObjectName1(String objectName1) {
        this.objectName1.set(objectName1);
    }

    public String getObjectName2() {
        return objectName2.get();
    }

    public void setObjectName2(String objectName2) {
        this.objectName2.set(objectName2);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public boolean isAllowRead() {
        return allowRead.get();
    }

    public void setAllowRead(boolean allowRead) {
        this.allowRead.set(allowRead);
    }

    public boolean isAllowCreate() {
        return allowCreate.get();
    }

    public void setAllowCreate(boolean allowCreate) {
        this.allowCreate.set(allowCreate);
    }

    public boolean isAllowEdit() {
        return allowEdit.get();
    }

    public void setAllowEdit(boolean allowEdit) {
        this.allowEdit.set(allowEdit);
    }

    public boolean isAllowDelete() {
        return allowDelete.get();
    }

    public void setAllowDelete(boolean allowDelete) {
        this.allowDelete.set(allowDelete);
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty moduleNameProperty() {
        return moduleName;
    }

    public StringProperty objectName1Property() {
        return objectName1;
    }

    public StringProperty objectName2Property() {
        return objectName2;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public BooleanProperty allowReadProperty() {
        return allowRead;
    }

    public BooleanProperty allowCreateProperty() {
        return allowCreate;
    }

    public BooleanProperty allowEditProperty() {
        return allowEdit;
    }

    public BooleanProperty allowDeleteProperty() {
        return allowDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppRightModel that = (AppRightModel) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AppRightModel{" +
                "id=" + id +
                ", moduleName=" + moduleName +
                ", objectName1=" + objectName1 +
                ", objectName2=" + objectName2 +
                ", name=" + name +
                ", allowRead=" + allowRead +
                ", allowCreate=" + allowCreate +
                ", allowEdit=" + allowEdit +
                ", allowDelete=" + allowDelete +
                '}';
    }
}
