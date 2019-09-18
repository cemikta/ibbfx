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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Planung entity
 *
 * @author Cem Ikta
 */
@Entity
@Table(name = "planung")
@NamedQueries({
    @NamedQuery(name = Planung.FIND_ALL,
            query = "SELECT p FROM Planung p ORDER BY p.planungNr"),
    @NamedQuery(name = Planung.FIND_BY_FIELDS,
            query = "SELECT p FROM Planung p " +
                    "WHERE " +
                    "str(p.planungNr) LIKE LOWER(:search) OR " +
                    "LOWER(p.zusatz) LIKE LOWER(:search) OR " +
                    "LOWER(p.titel) LIKE LOWER(:search) OR " +
                    "LOWER(p.veranstaltungOrt) LIKE LOWER(:search) OR " +
                    "str(p.veranstaltungBeginn) LIKE LOWER(:search) OR " +
                    "str(p.veranstaltungEnde) LIKE LOWER(:search) " +
                    "ORDER BY p.planungNr")
})
@AttributeOverride(name = "id", column = @Column(name = "planung_id", nullable = false))
public class Planung extends BaseEntity {

    public static final String FIND_ALL = "Planung.findAll";
    public static final String FIND_BY_FIELDS = "Planung.findByFields";

    @Basic(optional = false)
    @Column(name = "planung_nr", nullable = false)
    private Long planungNr;

    @Basic(optional = false)
    @Column(name = "zusatz", nullable = false, length = 50)
    private String zusatz;

    @Basic(optional = false)
    @Column(name = "titel", nullable = false, length = 100)
    private String titel;

    @Basic(optional = false)
    @Column(name = "veranstaltung_ort", nullable = false, length = 100)
    private String veranstaltungOrt;

    @Basic(optional = false)
    @Column(name = "veranstaltung_beginn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date veranstaltungBeginn;

    @Basic(optional = false)
    @Column(name = "veranstaltung_ende", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date veranstaltungEnde;

    @Column(name = "vorbereitungstreffen_ort", length = 50)
    private String vorbereitungstreffenOrt;

    @Column(name = "vorbereitungstreffen_beginn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vorbereitungstreffenBeginn;

    @Column(name = "vorbereitungstreffen_ende")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vorbereitungstreffenEnde;

    @Column(name = "nachbereitungstreffen_ort", length = 50)
    private String nachbereitungstreffenOrt;

    @Column(name = "nachbereitungstreffen_beginn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nachbereitungstreffenBeginn;

    @Column(name = "nachbereitungstreffen_ende")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nachbereitungstreffenEnde;

    @Column(name = "arbeitsaufwand_aufschlag", precision = 5, scale = 2)
    private BigDecimal arbeitsaufwandAufschlag;

    @Basic(optional = false)
    @Column(name = "tage_nach_wbg", nullable = false)
    private Integer tageNachWbg;

    @Column(name = "zgfaktor_aufschlag", precision = 5, scale = 2)
    private BigDecimal zgfaktorAufschlag;

    @Column(name = "fahrtkasse_kostenstelle")
    private Integer fahrtkasseKostenstelle;

    @Column(name = "zahlende_tn_de")
    private Integer zahlendeTnDe;

    @Column(name = "zahlende_tn_ausl")
    private Integer zahlendeTnAusl;

    @Column(name = "nicht_zahlende_tn_de")
    private Integer nichtZahlendeTnDe;

    @Column(name = "nicht_zahlende_tn_ausl")
    private Integer nichtZahlendeTnAusl;

    @Column(name = "teilnehmer_nach_wgb_de")
    private Integer teilnehmerNachWgbDe;

    @Column(name = "teilnehmer_nach_wgb_ausl")
    private Integer teilnehmerNachWgbAusl;

    @Column(name = "hauptberufliche_mitarbeiter")
    private Integer hauptberuflicheMitarbeiter;

    @Column(name = "honorar_mitarbeiter")
    private Integer honorarMitarbeiter;

    @Column(name = "unbezahlte_mitarbeiter")
    private Integer unbezahlteMitarbeiter;

    @Column(name = "fremde_mitarbeiter")
    private Integer fremdeMitarbeiter;

    @Column(name = "zusaetzliche_reise_kosten", precision = 15, scale = 2)
    private BigDecimal zusaetzlicheReiseKosten;

    @Column(name = "zusaetzliche_verpflegungskosten", precision = 15, scale = 2)
    private BigDecimal zusaetzlicheVerpflegungskosten;

    @Column(name = "weitere_ausgaben", precision = 15, scale = 2)
    private BigDecimal weitereAusgaben;

    @Column(name = "ausl_mitarbeiter")
    private Integer auslMitarbeiter;

    @Column(name = "unterkunfstage")
    private Integer unterkunfstage;

    @Column(name = "verpflegungstage", precision = 5, scale = 2)
    private BigDecimal verpflegungstage;

    @Column(name = "honorartage")
    private Integer honorartage;

    @Column(name = "honorarsatz", precision = 15, scale = 2)
    private BigDecimal honorarsatz;

    @Column(name = "reisekosten", precision = 15, scale = 2)
    private BigDecimal reisekosten;

    @Column(name = "veranstaltung_in_de")
    private Boolean veranstaltungInDe;

    @Column(name = "regress_versicherung")
    private Boolean regressVersicherung;

    @Column(name = "reiserecht")
    private Boolean reiserecht;

    @Column(name = "visagebuehr_pro_person", precision = 15, scale = 2)
    private BigDecimal visagebuehrProPerson;

    @Column(name = "visa_nebenkosten", precision = 15, scale = 2)
    private BigDecimal visaNebenkosten;

    @Column(name = "fuehrungen_gesamt", precision = 15, scale = 2)
    private BigDecimal fuehrungenGesamt;

    @Column(name = "externe_referent_gesamt", precision = 15, scale = 2)
    private BigDecimal externeReferentGesamt;

    @Column(name = "externe_dolmetscher_gesamt", precision = 15, scale = 2)
    private BigDecimal externeDolmetscherGesamt;

    @Column(name = "materialkosten_gesamt", precision = 15, scale = 2)
    private BigDecimal materialkostenGesamt;

    @Column(name = "eintrittsgelder_pro_de_tn", precision = 15, scale = 2)
    private BigDecimal eintrittsgelderProDeTn;

    @Column(name = "eintrittsgelder_pro_ausl_tn", precision = 15, scale = 2)
    private BigDecimal eintrittsgelderProAuslTn;

    @Column(name = "sonstigekosten_gesamt", precision = 15, scale = 2)
    private BigDecimal sonstigekostenGesamt;

    @Column(name = "sonstigekosten_pro_tn", precision = 15, scale = 2)
    private BigDecimal sonstigekostenProTn;

    @Column(name = "bahnfahrt_pro_person", precision = 15, scale = 2)
    private BigDecimal bahnfahrtProPerson;

    @Column(name = "flugkosten_pro_person", precision = 15, scale = 2)
    private BigDecimal flugkostenProPerson;

    @Column(name = "bustransfers_gesamt", precision = 15, scale = 2)
    private BigDecimal bustransfersGesamt;

    @Column(name = "weitere_fahrten_im_land_pro_person", precision = 15, scale = 2)
    private BigDecimal weitereFahrtenImLandProPerson;

    @Column(name = "fahrtkosten_fuer_ausl_tn_pro_person", precision = 15, scale = 2)
    private BigDecimal fahrtkostenFuerAuslTnProPerson;

    @Column(name = "uebernachtungskosten_pro_nacht", precision = 15, scale = 2)
    private BigDecimal uebernachtungskostenProNacht;

    @Column(name = "aufpreis_einzelzimmer_pro_nacht", precision = 15, scale = 2)
    private BigDecimal aufpreisEinzelzimmerProNacht;

    @Column(name = "anzahl_naechte_pro_de_tn")
    private Integer anzahlNaechteProDeTn;

    @Column(name = "anzahl_naechte_pro_ausl_tn")
    private Integer anzahlNaechteProAuslTn;

    @Column(name = "verpflegungskosten_pro_tag", precision = 15, scale = 2)
    private BigDecimal verpflegungskostenProTag;

    @Column(name = "verpflegungstage_pro_de_tn", precision = 5, scale = 2)
    private BigDecimal verpflegungstageProDeTn;

    @Column(name = "verpflegungstage_pro_ausl_tn", precision = 5, scale = 2)
    private BigDecimal verpflegungstageProAuslTn;

    @Column(name = "pauschalpreis", precision = 15, scale = 2)
    private BigDecimal pauschalpreis;

    @Column(name = "anmeldung_ibb")
    private Boolean anmeldungIbb;

    @Column(name = "anmeldung_partner")
    private Boolean anmeldungPartner;

    @Column(name = "festpreis", precision = 15, scale = 2)
    private BigDecimal festpreis;

    @Column(name = "bedingung1", length = 50)
    private String bedingung1;

    @Column(name = "bedingung2", length = 50)
    private String bedingung2;

    @Column(name = "bedingung3", length = 50)
    private String bedingung3;

    @Column(name = "bedingung4", length = 50)
    private String bedingung4;

    @Column(name = "bedingung5", length = 50)
    private String bedingung5;

    @Column(name = "bedingung6", length = 50)
    private String bedingung6;

    @Column(name = "preis1", precision = 15, scale = 2)
    private BigDecimal preis1;

    @Column(name = "preis2", precision = 15, scale = 2)
    private BigDecimal preis2;

    @Column(name = "preis3", precision = 15, scale = 2)
    private BigDecimal preis3;

    @Column(name = "preis4", precision = 15, scale = 2)
    private BigDecimal preis4;

    @Column(name = "preis5", precision = 15, scale = 2)
    private BigDecimal preis5;

    @Column(name = "preis6", precision = 15, scale = 2)
    private BigDecimal preis6;

    @Column(name = "wird_durchgefuehrt")
    private Boolean wirdDurchgefuehrt;

    @Column(name = "kurzbeschreibung_eb_plan", length = 2147483647)
    private String kurzbeschreibungEbPlan;

    @Column(name = "notizen", length = 2147483647)
    private String notizen;

    @Column(name = "va_tage")
    private Integer vaTage;

    @JoinColumn(name = "partner_id", referencedColumnName = "adresse_id")
    @ManyToOne
    private Adresse partner;

    @JoinColumn(name = "mitarbeiter1_id", referencedColumnName = "adresse_id")
    @ManyToOne
    private Adresse mitarbeiter1;

    @JoinColumn(name = "mitarbeiter2_id", referencedColumnName = "adresse_id")
    @ManyToOne
    private Adresse mitarbeiter2;

    @JoinColumn(name = "arbeitsaufwand_id", referencedColumnName = "arbeitsaufwand_id", nullable = false)
    @ManyToOne(optional = false)
    private Arbeitsaufwand arbeitsaufwand;

    @JoinColumn(name = "fachbereich_id", referencedColumnName = "fachbereich_id", nullable = false)
    @ManyToOne(optional = false)
    private Fachbereich fachbereich;

    @JoinColumn(name = "geschaeftsbereich_id", referencedColumnName = "geschaeftsbereich_id", nullable = false)
    @ManyToOne(optional = false)
    private Geschaeftsbereich geschaeftsbereich;

    @JoinColumn(name = "zgfaktor_id", referencedColumnName = "zgfaktor_id", nullable = false)
    @ManyToOne(optional = false)
    private Zgfaktor zgfaktor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planung", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Zuschuss> zuschussList = new ArrayList<>();

    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "planung_params_id", referencedColumnName = "planung_params_id", nullable = false)
    private PlanungParams planungParams;

    public Long getPlanungNr() {
        return planungNr;
    }

    public void setPlanungNr(Long planungNr) {
        this.planungNr = planungNr;
    }

    public String getZusatz() {
        return zusatz;
    }

    public void setZusatz(String zusatz) {
        this.zusatz = zusatz;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getVeranstaltungOrt() {
        return veranstaltungOrt;
    }

    public void setVeranstaltungOrt(String veranstaltungOrt) {
        this.veranstaltungOrt = veranstaltungOrt;
    }

    public Date getVeranstaltungBeginn() {
        return veranstaltungBeginn;
    }

    public void setVeranstaltungBeginn(Date veranstaltungBeginn) {
        this.veranstaltungBeginn = veranstaltungBeginn;
    }

    public Date getVeranstaltungEnde() {
        return veranstaltungEnde;
    }

    public void setVeranstaltungEnde(Date veranstaltungEnde) {
        this.veranstaltungEnde = veranstaltungEnde;
    }

    public String getVorbereitungstreffenOrt() {
        return vorbereitungstreffenOrt;
    }

    public void setVorbereitungstreffenOrt(String vorbereitungstreffenOrt) {
        this.vorbereitungstreffenOrt = vorbereitungstreffenOrt;
    }

    public Date getVorbereitungstreffenBeginn() {
        return vorbereitungstreffenBeginn;
    }

    public void setVorbereitungstreffenBeginn(Date vorbereitungstreffenBeginn) {
        this.vorbereitungstreffenBeginn = vorbereitungstreffenBeginn;
    }

    public Date getVorbereitungstreffenEnde() {
        return vorbereitungstreffenEnde;
    }

    public void setVorbereitungstreffenEnde(Date vorbereitungstreffenEnde) {
        this.vorbereitungstreffenEnde = vorbereitungstreffenEnde;
    }

    public String getNachbereitungstreffenOrt() {
        return nachbereitungstreffenOrt;
    }

    public void setNachbereitungstreffenOrt(String nachbereitungstreffenOrt) {
        this.nachbereitungstreffenOrt = nachbereitungstreffenOrt;
    }

    public Date getNachbereitungstreffenBeginn() {
        return nachbereitungstreffenBeginn;
    }

    public void setNachbereitungstreffenBeginn(Date nachbereitungstreffenBeginn) {
        this.nachbereitungstreffenBeginn = nachbereitungstreffenBeginn;
    }

    public Date getNachbereitungstreffenEnde() {
        return nachbereitungstreffenEnde;
    }

    public void setNachbereitungstreffenEnde(Date nachbereitungstreffenEnde) {
        this.nachbereitungstreffenEnde = nachbereitungstreffenEnde;
    }

    public BigDecimal getArbeitsaufwandAufschlag() {
        return arbeitsaufwandAufschlag;
    }

    public void setArbeitsaufwandAufschlag(BigDecimal arbeitsaufwandAufschlag) {
        this.arbeitsaufwandAufschlag = arbeitsaufwandAufschlag;
    }

    public Integer getTageNachWbg() {
        return tageNachWbg;
    }

    public void setTageNachWbg(Integer tageNachWbg) {
        this.tageNachWbg = tageNachWbg;
    }

    public BigDecimal getZgfaktorAufschlag() {
        return zgfaktorAufschlag;
    }

    public void setZgfaktorAufschlag(BigDecimal zgfaktorAufschlag) {
        this.zgfaktorAufschlag = zgfaktorAufschlag;
    }

    public Integer getFahrtkasseKostenstelle() {
        return fahrtkasseKostenstelle;
    }

    public void setFahrtkasseKostenstelle(Integer fahrtkasseKostenstelle) {
        this.fahrtkasseKostenstelle = fahrtkasseKostenstelle;
    }

    public Integer getZahlendeTnDe() {
        return zahlendeTnDe;
    }

    public void setZahlendeTnDe(Integer zahlendeTnDe) {
        this.zahlendeTnDe = zahlendeTnDe;
    }

    public Integer getZahlendeTnAusl() {
        return zahlendeTnAusl;
    }

    public void setZahlendeTnAusl(Integer zahlendeTnAusl) {
        this.zahlendeTnAusl = zahlendeTnAusl;
    }

    public Integer getNichtZahlendeTnDe() {
        return nichtZahlendeTnDe;
    }

    public void setNichtZahlendeTnDe(Integer nichtZahlendeTnDe) {
        this.nichtZahlendeTnDe = nichtZahlendeTnDe;
    }

    public Integer getNichtZahlendeTnAusl() {
        return nichtZahlendeTnAusl;
    }

    public void setNichtZahlendeTnAusl(Integer nichtZahlendeTnAusl) {
        this.nichtZahlendeTnAusl = nichtZahlendeTnAusl;
    }

    public Integer getTeilnehmerNachWgbDe() {
        return teilnehmerNachWgbDe;
    }

    public void setTeilnehmerNachWgbDe(Integer teilnehmerNachWgbDe) {
        this.teilnehmerNachWgbDe = teilnehmerNachWgbDe;
    }

    public Integer getTeilnehmerNachWgbAusl() {
        return teilnehmerNachWgbAusl;
    }

    public void setTeilnehmerNachWgbAusl(Integer teilnehmerNachWgbAusl) {
        this.teilnehmerNachWgbAusl = teilnehmerNachWgbAusl;
    }

    public Integer getHauptberuflicheMitarbeiter() {
        return hauptberuflicheMitarbeiter;
    }

    public void setHauptberuflicheMitarbeiter(Integer hauptberuflicheMitarbeiter) {
        this.hauptberuflicheMitarbeiter = hauptberuflicheMitarbeiter;
    }

    public Integer getHonorarMitarbeiter() {
        return honorarMitarbeiter;
    }

    public void setHonorarMitarbeiter(Integer honorarMitarbeiter) {
        this.honorarMitarbeiter = honorarMitarbeiter;
    }

    public Integer getUnbezahlteMitarbeiter() {
        return unbezahlteMitarbeiter;
    }

    public void setUnbezahlteMitarbeiter(Integer unbezahlteMitarbeiter) {
        this.unbezahlteMitarbeiter = unbezahlteMitarbeiter;
    }

    public Integer getFremdeMitarbeiter() {
        return fremdeMitarbeiter;
    }

    public void setFremdeMitarbeiter(Integer fremdeMitarbeiter) {
        this.fremdeMitarbeiter = fremdeMitarbeiter;
    }

    public BigDecimal getZusaetzlicheReiseKosten() {
        return zusaetzlicheReiseKosten;
    }

    public void setZusaetzlicheReiseKosten(BigDecimal zusaetzlicheReiseKosten) {
        this.zusaetzlicheReiseKosten = zusaetzlicheReiseKosten;
    }

    public BigDecimal getZusaetzlicheVerpflegungskosten() {
        return zusaetzlicheVerpflegungskosten;
    }

    public void setZusaetzlicheVerpflegungskosten(BigDecimal zusaetzlicheVerpflegungskosten) {
        this.zusaetzlicheVerpflegungskosten = zusaetzlicheVerpflegungskosten;
    }

    public BigDecimal getWeitereAusgaben() {
        return weitereAusgaben;
    }

    public void setWeitereAusgaben(BigDecimal weitereAusgaben) {
        this.weitereAusgaben = weitereAusgaben;
    }

    public Integer getAuslMitarbeiter() {
        return auslMitarbeiter;
    }

    public void setAuslMitarbeiter(Integer auslMitarbeiter) {
        this.auslMitarbeiter = auslMitarbeiter;
    }

    public Integer getUnterkunfstage() {
        return unterkunfstage;
    }

    public void setUnterkunfstage(Integer unterkunfstage) {
        this.unterkunfstage = unterkunfstage;
    }

    public BigDecimal getVerpflegungstage() {
        return verpflegungstage;
    }

    public void setVerpflegungstage(BigDecimal verpflegungstage) {
        this.verpflegungstage = verpflegungstage;
    }

    public Integer getHonorartage() {
        return honorartage;
    }

    public void setHonorartage(Integer honorartage) {
        this.honorartage = honorartage;
    }

    public BigDecimal getHonorarsatz() {
        return honorarsatz;
    }

    public void setHonorarsatz(BigDecimal honorarsatz) {
        this.honorarsatz = honorarsatz;
    }

    public BigDecimal getReisekosten() {
        return reisekosten;
    }

    public void setReisekosten(BigDecimal reisekosten) {
        this.reisekosten = reisekosten;
    }

    public Boolean getVeranstaltungInDe() {
        return veranstaltungInDe;
    }

    public void setVeranstaltungInDe(Boolean veranstaltungInDe) {
        this.veranstaltungInDe = veranstaltungInDe;
    }

    public Boolean getRegressVersicherung() {
        return regressVersicherung;
    }

    public void setRegressVersicherung(Boolean regressVersicherung) {
        this.regressVersicherung = regressVersicherung;
    }

    public Boolean getReiserecht() {
        return reiserecht;
    }

    public void setReiserecht(Boolean reiserecht) {
        this.reiserecht = reiserecht;
    }

    public BigDecimal getVisagebuehrProPerson() {
        return visagebuehrProPerson;
    }

    public void setVisagebuehrProPerson(BigDecimal visagebuehrProPerson) {
        this.visagebuehrProPerson = visagebuehrProPerson;
    }

    public BigDecimal getVisaNebenkosten() {
        return visaNebenkosten;
    }

    public void setVisaNebenkosten(BigDecimal visaNebenkosten) {
        this.visaNebenkosten = visaNebenkosten;
    }

    public BigDecimal getFuehrungenGesamt() {
        return fuehrungenGesamt;
    }

    public void setFuehrungenGesamt(BigDecimal fuehrungenGesamt) {
        this.fuehrungenGesamt = fuehrungenGesamt;
    }

    public BigDecimal getExterneReferentGesamt() {
        return externeReferentGesamt;
    }

    public void setExterneReferentGesamt(BigDecimal externeReferentGesamt) {
        this.externeReferentGesamt = externeReferentGesamt;
    }

    public BigDecimal getExterneDolmetscherGesamt() {
        return externeDolmetscherGesamt;
    }

    public void setExterneDolmetscherGesamt(BigDecimal externeDolmetscherGesamt) {
        this.externeDolmetscherGesamt = externeDolmetscherGesamt;
    }

    public BigDecimal getMaterialkostenGesamt() {
        return materialkostenGesamt;
    }

    public void setMaterialkostenGesamt(BigDecimal materialkostenGesamt) {
        this.materialkostenGesamt = materialkostenGesamt;
    }

    public BigDecimal getEintrittsgelderProDeTn() {
        return eintrittsgelderProDeTn;
    }

    public void setEintrittsgelderProDeTn(BigDecimal eintrittsgelderProDeTn) {
        this.eintrittsgelderProDeTn = eintrittsgelderProDeTn;
    }

    public BigDecimal getEintrittsgelderProAuslTn() {
        return eintrittsgelderProAuslTn;
    }

    public void setEintrittsgelderProAuslTn(BigDecimal eintrittsgelderProAuslTn) {
        this.eintrittsgelderProAuslTn = eintrittsgelderProAuslTn;
    }

    public BigDecimal getSonstigekostenGesamt() {
        return sonstigekostenGesamt;
    }

    public void setSonstigekostenGesamt(BigDecimal sonstigekostenGesamt) {
        this.sonstigekostenGesamt = sonstigekostenGesamt;
    }

    public BigDecimal getSonstigekostenProTn() {
        return sonstigekostenProTn;
    }

    public void setSonstigekostenProTn(BigDecimal sonstigekostenProTn) {
        this.sonstigekostenProTn = sonstigekostenProTn;
    }

    public BigDecimal getBahnfahrtProPerson() {
        return bahnfahrtProPerson;
    }

    public void setBahnfahrtProPerson(BigDecimal bahnfahrtProPerson) {
        this.bahnfahrtProPerson = bahnfahrtProPerson;
    }

    public BigDecimal getFlugkostenProPerson() {
        return flugkostenProPerson;
    }

    public void setFlugkostenProPerson(BigDecimal flugkostenProPerson) {
        this.flugkostenProPerson = flugkostenProPerson;
    }

    public BigDecimal getBustransfersGesamt() {
        return bustransfersGesamt;
    }

    public void setBustransfersGesamt(BigDecimal bustransfersGesamt) {
        this.bustransfersGesamt = bustransfersGesamt;
    }

    public BigDecimal getWeitereFahrtenImLandProPerson() {
        return weitereFahrtenImLandProPerson;
    }

    public void setWeitereFahrtenImLandProPerson(BigDecimal weitereFahrtenImLandProPerson) {
        this.weitereFahrtenImLandProPerson = weitereFahrtenImLandProPerson;
    }

    public BigDecimal getFahrtkostenFuerAuslTnProPerson() {
        return fahrtkostenFuerAuslTnProPerson;
    }

    public void setFahrtkostenFuerAuslTnProPerson(BigDecimal fahrtkostenFuerAuslTnProPerson) {
        this.fahrtkostenFuerAuslTnProPerson = fahrtkostenFuerAuslTnProPerson;
    }

    public BigDecimal getUebernachtungskostenProNacht() {
        return uebernachtungskostenProNacht;
    }

    public void setUebernachtungskostenProNacht(BigDecimal uebernachtungskostenProNacht) {
        this.uebernachtungskostenProNacht = uebernachtungskostenProNacht;
    }

    public BigDecimal getAufpreisEinzelzimmerProNacht() {
        return aufpreisEinzelzimmerProNacht;
    }

    public void setAufpreisEinzelzimmerProNacht(BigDecimal aufpreisEinzelzimmerProNacht) {
        this.aufpreisEinzelzimmerProNacht = aufpreisEinzelzimmerProNacht;
    }

    public Integer getAnzahlNaechteProDeTn() {
        return anzahlNaechteProDeTn;
    }

    public void setAnzahlNaechteProDeTn(Integer anzahlNaechteProDeTn) {
        this.anzahlNaechteProDeTn = anzahlNaechteProDeTn;
    }

    public Integer getAnzahlNaechteProAuslTn() {
        return anzahlNaechteProAuslTn;
    }

    public void setAnzahlNaechteProAuslTn(Integer anzahlNaechteProAuslTn) {
        this.anzahlNaechteProAuslTn = anzahlNaechteProAuslTn;
    }

    public BigDecimal getVerpflegungskostenProTag() {
        return verpflegungskostenProTag;
    }

    public void setVerpflegungskostenProTag(BigDecimal verpflegungskostenProTag) {
        this.verpflegungskostenProTag = verpflegungskostenProTag;
    }

    public BigDecimal getVerpflegungstageProDeTn() {
        return verpflegungstageProDeTn;
    }

    public void setVerpflegungstageProDeTn(BigDecimal verpflegungstageProDeTn) {
        this.verpflegungstageProDeTn = verpflegungstageProDeTn;
    }

    public BigDecimal getVerpflegungstageProAuslTn() {
        return verpflegungstageProAuslTn;
    }

    public void setVerpflegungstageProAuslTn(BigDecimal verpflegungstageProAuslTn) {
        this.verpflegungstageProAuslTn = verpflegungstageProAuslTn;
    }

    public BigDecimal getPauschalpreis() {
        return pauschalpreis;
    }

    public void setPauschalpreis(BigDecimal pauschalpreis) {
        this.pauschalpreis = pauschalpreis;
    }

    public Boolean getAnmeldungIbb() {
        return anmeldungIbb;
    }

    public void setAnmeldungIbb(Boolean anmeldungIbb) {
        this.anmeldungIbb = anmeldungIbb;
    }

    public Boolean getAnmeldungPartner() {
        return anmeldungPartner;
    }

    public void setAnmeldungPartner(Boolean anmeldungPartner) {
        this.anmeldungPartner = anmeldungPartner;
    }

    public BigDecimal getFestpreis() {
        return festpreis;
    }

    public void setFestpreis(BigDecimal festpreis) {
        this.festpreis = festpreis;
    }

    public String getBedingung1() {
        return bedingung1;
    }

    public void setBedingung1(String bedingung1) {
        this.bedingung1 = bedingung1;
    }

    public String getBedingung2() {
        return bedingung2;
    }

    public void setBedingung2(String bedingung2) {
        this.bedingung2 = bedingung2;
    }

    public String getBedingung3() {
        return bedingung3;
    }

    public void setBedingung3(String bedingung3) {
        this.bedingung3 = bedingung3;
    }

    public String getBedingung4() {
        return bedingung4;
    }

    public void setBedingung4(String bedingung4) {
        this.bedingung4 = bedingung4;
    }

    public String getBedingung5() {
        return bedingung5;
    }

    public void setBedingung5(String bedingung5) {
        this.bedingung5 = bedingung5;
    }

    public String getBedingung6() {
        return bedingung6;
    }

    public void setBedingung6(String bedingung6) {
        this.bedingung6 = bedingung6;
    }

    public BigDecimal getPreis1() {
        return preis1;
    }

    public void setPreis1(BigDecimal preis1) {
        this.preis1 = preis1;
    }

    public BigDecimal getPreis2() {
        return preis2;
    }

    public void setPreis2(BigDecimal preis2) {
        this.preis2 = preis2;
    }

    public BigDecimal getPreis3() {
        return preis3;
    }

    public void setPreis3(BigDecimal preis3) {
        this.preis3 = preis3;
    }

    public BigDecimal getPreis4() {
        return preis4;
    }

    public void setPreis4(BigDecimal preis4) {
        this.preis4 = preis4;
    }

    public BigDecimal getPreis5() {
        return preis5;
    }

    public void setPreis5(BigDecimal preis5) {
        this.preis5 = preis5;
    }

    public BigDecimal getPreis6() {
        return preis6;
    }

    public void setPreis6(BigDecimal preis6) {
        this.preis6 = preis6;
    }

    public Boolean getWirdDurchgefuehrt() {
        return wirdDurchgefuehrt;
    }

    public void setWirdDurchgefuehrt(Boolean wirdDurchgefuehrt) {
        this.wirdDurchgefuehrt = wirdDurchgefuehrt;
    }

    public String getKurzbeschreibungEbPlan() {
        return kurzbeschreibungEbPlan;
    }

    public void setKurzbeschreibungEbPlan(String kurzbeschreibungEbPlan) {
        this.kurzbeschreibungEbPlan = kurzbeschreibungEbPlan;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public Integer getVaTage() {
        return vaTage;
    }

    public void setVaTage(Integer vaTage) {
        this.vaTage = vaTage;
    }

    public Adresse getPartner() {
        return partner;
    }

    public void setPartner(Adresse partner) {
        this.partner = partner;
    }

    public Adresse getMitarbeiter1() {
        return mitarbeiter1;
    }

    public void setMitarbeiter1(Adresse mitarbeiter1) {
        this.mitarbeiter1 = mitarbeiter1;
    }

    public Adresse getMitarbeiter2() {
        return mitarbeiter2;
    }

    public void setMitarbeiter2(Adresse mitarbeiter2) {
        this.mitarbeiter2 = mitarbeiter2;
    }

    public Arbeitsaufwand getArbeitsaufwand() {
        return arbeitsaufwand;
    }

    public void setArbeitsaufwand(Arbeitsaufwand arbeitsaufwand) {
        this.arbeitsaufwand = arbeitsaufwand;
    }

    public Fachbereich getFachbereich() {
        return fachbereich;
    }

    public void setFachbereich(Fachbereich fachbereich) {
        this.fachbereich = fachbereich;
    }

    public Geschaeftsbereich getGeschaeftsbereich() {
        return geschaeftsbereich;
    }

    public void setGeschaeftsbereich(Geschaeftsbereich geschaeftsbereich) {
        this.geschaeftsbereich = geschaeftsbereich;
    }

    public Zgfaktor getZgfaktor() {
        return zgfaktor;
    }

    public void setZgfaktor(Zgfaktor zgfaktor) {
        this.zgfaktor = zgfaktor;
    }

    public List<Zuschuss> getZuschussList() {
        return zuschussList;
    }

    public void setZuschussList(List<Zuschuss> zuschussList) {
        this.zuschussList = zuschussList;
    }

    public PlanungParams getPlanungParams() {
        return planungParams;
    }

    public void setPlanungParams(PlanungParams planungParams) {
        this.planungParams = planungParams;
    }

    @Override
    public String toString() {
        return planungNr + "-" + zusatz;
    }

}
