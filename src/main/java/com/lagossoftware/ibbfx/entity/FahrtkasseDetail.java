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
 * FahrtkasseDetail entity
 *
 * @author Cem Ikta
 */
@Entity
@Table(name = "fahrtkasse_detail")
@AttributeOverride(name = "id", column = @Column(name = "fahrtkasse_detail_id", nullable = false))
public class FahrtkasseDetail extends BaseEntity {

    @JoinColumn(name = "fahrtkasse_id", referencedColumnName = "fahrtkasse_id", nullable = false)
    @ManyToOne(optional = false)
    private Fahrtkasse fahrtkasse;

    @Column(name = "nummer")
    private Integer nummer;

    @Column(name = "empfaenger", length = 100)
    private String empfaenger;

    @Column(name = "zweck", length = 100)
    private String zweck;

    @Column(name = "betrag", precision = 15, scale = 2, nullable = false)
    private BigDecimal betrag;

    @JoinColumn(name = "waehrung_id", referencedColumnName = "waehrung_id", nullable = false)
    @ManyToOne(optional = false)
    private Waehrung waehrung;

    @Column(name = "betrag_in_eur", precision = 15, scale = 2, nullable = false)
    private BigDecimal betragInEur;

    @JoinColumn(name = "konto_id", referencedColumnName = "konto_id", nullable = false)
    @ManyToOne(optional = false)
    private Konto konto;

    public Fahrtkasse getFahrtkasse() {
        return fahrtkasse;
    }

    public void setFahrtkasse(Fahrtkasse fahrtkasse) {
        this.fahrtkasse = fahrtkasse;
    }

    public Integer getNummer() {
        return nummer;
    }

    public void setNummer(Integer nummer) {
        this.nummer = nummer;
    }

    public String getEmpfaenger() {
        return empfaenger;
    }

    public void setEmpfaenger(String empfaenger) {
        this.empfaenger = empfaenger;
    }

    public String getZweck() {
        return zweck;
    }

    public void setZweck(String zweck) {
        this.zweck = zweck;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public Waehrung getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(Waehrung waehrung) {
        this.waehrung = waehrung;
    }

    public BigDecimal getBetragInEur() {
        return betragInEur;
    }

    public void setBetragInEur(BigDecimal betragInEur) {
        this.betragInEur = betragInEur;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    @Override
    public String toString() {
        return "entity.FahrtkasseDetail[ fahrtkasseDetailId=" + getId() + " ]";
    }

}
