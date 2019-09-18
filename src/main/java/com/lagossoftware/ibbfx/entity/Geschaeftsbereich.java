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
package com.lagossoftware.ibbfx.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Geschaeftsbereich entity 
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "geschaeftsbereich", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
    @NamedQuery(name = Geschaeftsbereich.FIND_ALL,
            query = "SELECT g FROM Geschaeftsbereich g ORDER BY g.name"),
    @NamedQuery(name = Geschaeftsbereich.FIND_BY_NAME,
            query = "SELECT g FROM Geschaeftsbereich g WHERE LOWER(g.name) "
            + "LIKE LOWER(:name) ORDER BY g.name")
})
@AttributeOverride(name = "id", column = @Column(name = "geschaeftsbereich_id", nullable = false))
public class Geschaeftsbereich extends BaseEntity {

    public static final String FIND_ALL = "Geschaeftsbereich.findAll";
    public static final String FIND_BY_NAME = "Geschaeftsbereich.findByName";

    @Basic(optional = false)
    @Column(name = "abkuerzung", nullable = false, length = 10)
    private String abkuerzung;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geschaeftsbereich")
    private List<Planung> planungList;

    public String getAbkuerzung() {
        return abkuerzung;
    }

    public void setAbkuerzung(String abkuerzung) {
        this.abkuerzung = abkuerzung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Planung> getPlanungList() {
        return planungList;
    }

    public void setPlanungList(List<Planung> planungList) {
        this.planungList = planungList;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
