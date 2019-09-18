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
import java.math.BigDecimal;

/**
 * FahrtkasseWaehrung entity
 *
 * @author Cem Ikta
 */
@Entity
@Table(name = "fahrtkasse_waehrung")
@AttributeOverride(name = "id", column = @Column(name = "fahrtkasse_waehrung_id", nullable = false))
public class FahrtkasseWaehrung extends BaseEntity {

    @JoinColumn(name = "fahrtkasse_id", referencedColumnName = "fahrtkasse_id", nullable = false)
    @ManyToOne(optional = false)
    private Fahrtkasse fahrtkasse;

    @JoinColumn(name = "waehrung_id", referencedColumnName = "waehrung_id", nullable = false)
    @ManyToOne(optional = false)
    private Waehrung waehrung;

    @Column(name = "kurs", precision = 15, scale = 4, nullable = false)
    private BigDecimal kurs;

    @Column(name = "multiply_eur", length = 10)
    private String multiplyEur;

    public Fahrtkasse getFahrtkasse() {
        return fahrtkasse;
    }

    public void setFahrtkasse(Fahrtkasse fahrtkasse) {
        this.fahrtkasse = fahrtkasse;
    }

    public Waehrung getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(Waehrung waehrung) {
        this.waehrung = waehrung;
    }

    public BigDecimal getKurs() {
        return kurs;
    }

    public void setKurs(BigDecimal kurs) {
        this.kurs = kurs;
    }

    public String getMultiplyEur() {
        return multiplyEur;
    }

    public void setMultiplyEur(String multiplyEur) {
        this.multiplyEur = multiplyEur;
    }

    @Override
    public String toString() {
        return "entity.FahrtkasseWaehrung[ fahrtkasseWaehrungId=" + getId() + " ]";
    }

}
