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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AdresseStichwort entity
 *
 * @author Cem Ikta
 */
@Entity
@Table(name = "adresse_stichwort")
@AttributeOverride(name = "id", column = @Column(name = "adresse_stichwort_id", nullable = false))
public class AdresseStichwort extends BaseEntity {

    @JoinColumn(name = "adresse_id", referencedColumnName = "adresse_id")
    @ManyToOne
    private Adresse adresse;

    @JoinColumn(name = "stichwort_id", referencedColumnName = "stichwort_id")
    @ManyToOne
    private Stichwort stichwort;

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Stichwort getStichwort() {
        return stichwort;
    }

    public void setStichwort(Stichwort stichwort) {
        this.stichwort = stichwort;
    }

    @Override
    public String toString() {
        return this.stichwort.getBezeichnung();
    }

}
