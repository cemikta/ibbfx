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

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Konto entity 
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "konto", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"konto_nr"}),
    @UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
    @NamedQuery(name = Konto.FIND_ALL,
            query = "SELECT k FROM Konto k ORDER BY k.kontoNr, k.name"),
    @NamedQuery(name = Konto.FIND_BY_KONTO_NR_AND_NAME,
            query = "SELECT k FROM Konto k WHERE LOWER(k.kontoNr) "
            + "LIKE LOWER(:name) OR LOWER(k.name) LIKE LOWER(:name) "
                    + "ORDER BY k.kontoNr, k.name")
})
@AttributeOverride(name = "id", column = @Column(name = "konto_id", nullable = false))
public class Konto extends BaseEntity {

    public static final String FIND_ALL = "Konto.findAll";
    public static final String FIND_BY_KONTO_NR_AND_NAME = "Konto.findByKontoNrAndName";
    
    @Basic(optional = false)
    @Column(name = "konto_nr", nullable = false, length = 10)
    private String kontoNr;
    
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "klasse", nullable = false, length = 50)
    private String klasse;
    
    @Column(name = "konto_type")
    @Enumerated(EnumType.ORDINAL)
    private KontoType kontoType;

    public String getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(String kontoNr) {
        this.kontoNr = kontoNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public KontoType getKontoType() {
        return kontoType;
    }

    public void setKontoType(KontoType kontoType) {
        this.kontoType = kontoType;
    }

    @Override
    public String toString() {
        return this.kontoNr + " " + this.name;
    }

}
