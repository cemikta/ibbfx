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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Titel entity
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "titel", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bezeichnung"})})
@NamedQueries({
    @NamedQuery(name = Titel.FIND_ALL,
            query = "SELECT t FROM Titel t ORDER BY t.bezeichnung"),
    @NamedQuery(name = Titel.FIND_BY_BEZEICHNUNG,
            query = "SELECT t FROM Titel t WHERE LOWER(t.bezeichnung) "
            + "LIKE LOWER(:bezeichnung) ORDER BY t.bezeichnung")
})
@AttributeOverride(name = "id", column = @Column(name = "titel_id", nullable = false))
public class Titel extends BaseEntity {
    
    public static final String FIND_ALL = "Titel.findAll";
    public static final String FIND_BY_BEZEICHNUNG = "Titel.findByBezeichnung";
    
    @Basic(optional = false)
    @Column(name = "bezeichnung", nullable = false, length = 100)
    private String bezeichnung;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titel")
    private List<Adresse> adresseList;
    
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    
    public List<Adresse> getAdresseList() {
        return adresseList;
    }

    public void setAdresseList(List<Adresse> adresseList) {
        this.adresseList = adresseList;
    }

    @Override
    public String toString() {
        return this.bezeichnung;
    }

}
