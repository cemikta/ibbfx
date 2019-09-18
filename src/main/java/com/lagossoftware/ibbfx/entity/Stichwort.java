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

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Stichwort entity
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "stichwort", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bezeichnung"})})
@NamedQueries({
    @NamedQuery(name = Stichwort.FIND_ALL,
            query = "SELECT s FROM Stichwort s ORDER BY s.bezeichnung"),
    @NamedQuery(name = Stichwort.FIND_BY_BEZEICHNUNG,
            query = "SELECT s FROM Stichwort s WHERE LOWER(s.bezeichnung) "
            + "LIKE LOWER(:bezeichnung) ORDER BY s.bezeichnung")
})
@AttributeOverride(name = "id", column = @Column(name = "stichwort_id", nullable = false))
public class Stichwort extends BaseEntity {

    public static final String FIND_ALL = "Stichwort.findAll";
    public static final String FIND_BY_BEZEICHNUNG = "Stichwort.findByBezeichnung";
    
    @Basic(optional = false)
    @Column(name = "bezeichnung", nullable = false, length = 100)
    private String bezeichnung;
    
    @OneToMany(mappedBy = "stichwort")
    private List<AdresseStichwort> adresseStichwortList;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public List<AdresseStichwort> getAdresseStichwortList() {
        return adresseStichwortList;
    }

    public void setAdresseStichwortList(List<AdresseStichwort> adresseStichwortList) {
        this.adresseStichwortList = adresseStichwortList;
    }
    
    @Override
    public String toString() {
        return this.bezeichnung;
    }

}
