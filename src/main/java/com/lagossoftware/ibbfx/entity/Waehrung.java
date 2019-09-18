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
 * Waehrung entity 
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "waehrung", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code"}),
    @UniqueConstraint(columnNames = {"bezeichnung"})})
@NamedQueries({
    @NamedQuery(name = Waehrung.FIND_ALL,
            query = "SELECT w FROM Waehrung w ORDER BY w.code, w.bezeichnung"),
    @NamedQuery(name = Waehrung.FIND_BY_CODE_AND_BEZEICHNUNG,
            query = "SELECT w FROM Waehrung w WHERE LOWER(w.code) "
            + "LIKE LOWER(:bezeichnung) OR LOWER(w.bezeichnung) LIKE LOWER(:bezeichnung) "
                    + "ORDER BY w.bezeichnung")
})
@AttributeOverride(name = "id", column = @Column(name = "waehrung_id", nullable = false))
public class Waehrung extends BaseEntity {
    
    public static final String FIND_ALL = "Waehrung.findAll";
    public static final String FIND_BY_CODE_AND_BEZEICHNUNG = "Waehrung.findByCodeAndBezeichnung";
    
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 3)
    private String code;
    
    @Basic(optional = false)
    @Column(name = "bezeichnung", nullable = false, length = 100)
    private String bezeichnung;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "waehrung")
    private List<Eingangsrechnung> eingansrechnungList;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public List<Eingangsrechnung> getEingansrechnungList() {
        return eingansrechnungList;
    }

    public void setEingansrechnungList(List<Eingangsrechnung> eingansrechnungList) {
        this.eingansrechnungList = eingansrechnungList;
    }

    @Override
    public String toString() {
        return this.code;
    }
    
}
