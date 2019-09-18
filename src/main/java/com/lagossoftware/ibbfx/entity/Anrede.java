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
 * Anrede entity
 *
 * @author Cem Ikta
 */
@Entity
@Table(name = "anrede", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bezeichnung"})
})
@NamedQueries({
    @NamedQuery(name = Anrede.FIND_ALL,
            query = "SELECT a FROM Anrede a ORDER BY a.bezeichnung"),
    @NamedQuery(name = Anrede.FIND_BY_BEZEICHNUNG,
            query = "SELECT a FROM Anrede a WHERE LOWER(a.bezeichnung) "
            + "LIKE LOWER(:bezeichnung) ORDER BY a.bezeichnung")
})
@AttributeOverride(name = "id", column = @Column(name = "anrede_id", nullable = false))
public class Anrede extends BaseEntity {

    public static final String FIND_ALL = "Anrede.findAll";
    public static final String FIND_BY_BEZEICHNUNG = "Anrede.findByBezeichnung";

    @Basic(optional = false)
    @Column(name = "bezeichnung", nullable = false, length = 100)
    private String bezeichnung;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anrede")
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
