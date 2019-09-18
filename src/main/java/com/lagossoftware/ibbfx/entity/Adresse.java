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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Adresse entity
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "adresse")
@NamedQueries({
    @NamedQuery(name = Adresse.FIND_ALL,
            query = "SELECT a FROM Adresse a ORDER BY a.vorname, a.nachname, a.firma1"),
    @NamedQuery(name = Adresse.FIND_BY_HAUPT_MITARBEITER,
            query = "SELECT a FROM Adresse a, AdresseStichwort asw " +
                "WHERE a.id = asw.adresse.id AND " +
                "asw.stichwort.id = 1 " + 
                "ORDER BY a.vorname, a.nachname"),
    @NamedQuery(name = Adresse.FIND_BY_FREI_MITARBEITER,
            query = "SELECT a FROM Adresse a, AdresseStichwort asw " +
                    "WHERE a.id = asw.adresse.id AND " +
                    "asw.stichwort.id = 2 " +
                    "ORDER BY a.vorname, a.nachname"),
    @NamedQuery(name = Adresse.FIND_BY_PARTNER_MITARBEITER,
            query = "SELECT a FROM Adresse a, AdresseStichwort asw " +
                    "WHERE a.id = asw.adresse.id AND " +
                    "asw.stichwort.id = 3 " +
                    "ORDER BY a.vorname, a.nachname"),
    @NamedQuery(name = Adresse.FIND_BY_HAUPT_AND_FREI_MITARBEITER,
            query = "SELECT a FROM Adresse a, AdresseStichwort asw " +
                    "WHERE a.id = asw.adresse.id AND " +
                    "(asw.stichwort.id = 1 OR asw.stichwort.id = 2) " +
                    "ORDER BY a.vorname, a.nachname"),
    @NamedQuery(name = Adresse.FIND_BY_KOSTENSTELLE,
                query = "SELECT a FROM Adresse a " +
                        "WHERE a.kostenstelle > 0 " +
                        "ORDER BY a.vorname, a.nachname"),
    @NamedQuery(name = Adresse.FIND_BY_FIELDS,
            query = "SELECT a FROM Adresse a " +
                "LEFT JOIN a.anrede an " +
                "LEFT JOIN a.titel t " +
                "LEFT JOIN a.bundesland b " +
                "LEFT JOIN a.land l " +
                "WHERE " +
                "(an.id IS NOT NULL AND LOWER(an.bezeichnung) LIKE LOWER(:search)) OR " + 
                "(t.id IS NOT NULL AND LOWER(t.bezeichnung) LIKE LOWER(:search)) OR " +
                "str(a.adresseNr) LIKE LOWER(:search) OR " +
                "LOWER(a.vorname) LIKE LOWER(:search) OR " +
                "LOWER(a.nachname) LIKE LOWER(:search) OR " + 
                "LOWER(a.firma1) LIKE LOWER(:search) OR " + 
                "LOWER(a.strasse) LIKE LOWER(:search) OR " + 
                "LOWER(a.plz) LIKE LOWER(:search) OR " + 
                "LOWER(a.ort) LIKE LOWER(:search) OR " + 
                "(b.id IS NOT NULL AND LOWER(b.name) LIKE LOWER(:search)) OR " + 
                "(l.id IS NOT NULL AND LOWER(l.name) LIKE LOWER(:search)) OR " + 
                "LOWER(a.postfachPlz) LIKE LOWER(:search) OR " + 
                "LOWER(a.postfach) LIKE LOWER(:search) OR " + 
                "LOWER(a.mobiltelefon) LIKE LOWER(:search) OR " + 
                "LOWER(a.email) LIKE LOWER(:search) OR " + 
                "LOWER(a.homepage) LIKE LOWER(:search) OR " + 
                "LOWER(a.telefonPrivat) LIKE LOWER(:search) OR " + 
                "LOWER(a.telefonDienst) LIKE LOWER(:search) OR " + 
                "LOWER(a.faxPrivat) LIKE LOWER(:search) OR " + 
                "LOWER(a.faxDienst) LIKE LOWER(:search) " + 
                "ORDER BY a.adresseNr, a.vorname, a.nachname, a.firma1")
})
@AttributeOverride(name = "id", column = @Column(name = "adresse_id", nullable = false))
public class Adresse extends BaseEntity {
  
    public static final String FIND_ALL = "Adresse.findAll";
    public static final String FIND_BY_HAUPT_MITARBEITER = "Adresse.findByHauptMitarbeiter";
    public static final String FIND_BY_FREI_MITARBEITER = "Adresse.findByFreiMitarbeiter";
    public static final String FIND_BY_PARTNER_MITARBEITER = "Adresse.findByPartnerMitarbeiter";
    public static final String FIND_BY_HAUPT_AND_FREI_MITARBEITER = "Adresse.findByHauptAndFreiMitarbeiter";
    public static final String FIND_BY_KOSTENSTELLE = "Adresse.findByKostenstelle";
    public static final String FIND_BY_FIELDS  = "Adresse.findByFields";

    @Column(name = "adresse_nr")
    private Long adresseNr;

    @Column(name = "vorname", length = 50)
    private String vorname;
    
    @Column(name = "nachname", length = 50)
    private String nachname;
    
    @Column(name = "firma1", length = 50)
    private String firma1;
    
    @Column(name = "firma2", length = 50)
    private String firma2;
    
    @Column(name = "firma3", length = 50)
    private String firma3;
    
    @Column(name = "firma4", length = 50)
    private String firma4;
    
    @Column(name = "kennzeichen1", length = 10)
    private String kennzeichen1;
    
    @Column(name = "kennzeichen2", length = 50)
    private String kennzeichen2;
    
    @Column(name = "postfach_plz", length = 10)
    private String postfachPlz;
    
    @Column(name = "postfach", length = 50)
    private String postfach;
    
    @Column(name = "strasse", length = 50)
    private String strasse;
    
    @Column(name = "plz", length = 10)
    private String plz;
    
    @Column(name = "ort", length = 50)
    private String ort;
    
    @Column(name = "anrede_brief", length = 100)
    private String anredeBrief;
    
    @Column(name = "mobiltelefon", length = 50)
    private String mobiltelefon;
    
    @Column(name = "telefon_privat", length = 50)
    private String telefonPrivat;
    
    @Column(name = "telefon_dienst", length = 50)
    private String telefonDienst;
    
    @Column(name = "fax_privat", length = 50)
    private String faxPrivat;
    
    @Column(name = "fax_dienst", length = 50)
    private String faxDienst;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "homepage", length = 100)
    private String homepage;

    @Column(name = "skype", length = 100)
    private String skype;

    @Column(name = "facebook", length = 100)
    private String facebook;

    @Column(name = "twitter", length = 100)
    private String twitter;
    
    @Column(name = "xing", length = 100)
    private String xing;
  
    @Column(name = "linkedin", length = 100)
    private String linkedin;
    
    @Column(name = "google_plus", length = 100)
    private String googlePlus;
    
    @Column(name = "beruf", length = 50)
    private String beruf;
    
    @Column(name = "geburtsdatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date geburtsdatum;
    
    @Column(name = "geburtsort", length = 50)
    private String geburtsort;
    
    @Column(name = "staatsangehoerigkeit", length = 50)
    private String staatsangehoerigkeit;
    
    @Column(name = "pass_nr", length = 50)
    private String passNr;
    
    @Column(name = "ausstellungsdatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ausstellungsdatum;
    
    @Column(name = "ausstellungsort", length = 50)
    private String ausstellungsort;
    
    @Column(name = "kostenstelle")
    private Integer kostenstelle;
    
    @Column(name = "konto_inhaber", length = 50)
    private String kontoInhaber;
    
    @Column(name = "iban", length = 50)
    private String iban;
    
    @Column(name = "bic", length = 50)
    private String bic;
    
    @Column(name = "bank", length = 50)
    private String bank;
    
    @Column(name = "konto_nr", length = 50)
    private String kontoNr;
    
    @Column(name = "blz", length = 50)
    private String blz;
    
    @Column(name = "letzte_spende_am")
    @Temporal(TemporalType.TIMESTAMP)
    private Date letzteSpendeAm;
    
    @Column(name = "betrag", precision = 15, scale = 2)
    private BigDecimal betrag;
    
    @Column(name = "spendensumme", precision = 15, scale = 2)
    private BigDecimal spendensumme;
    
    @Column(name = "freies_feld1", length = 50)
    private String freiesFeld1;
    
    @Column(name = "freies_feld2", length = 50)
    private String freiesFeld2;
    
    @Column(name = "freies_feld3", length = 50)
    private String freiesFeld3;
    
    @Column(name = "notizen", length = 2147483647)
    private String notizen;
    
    @JoinColumn(name = "anrede_id", referencedColumnName = "anrede_id", nullable = false)
    @ManyToOne(optional = false)
    private Anrede anrede;
    
    @JoinColumn(name = "bundesland_id", referencedColumnName = "bundesland_id")
    @ManyToOne
    private Bundesland bundesland;
    
    @JoinColumn(name = "land_id", referencedColumnName = "land_id", nullable = false)
    @ManyToOne(optional = false)
    private Land land;
    
    @JoinColumn(name = "titel_id", referencedColumnName = "titel_id")
    @ManyToOne
    private Titel titel;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adresse", orphanRemoval = true)
    private List<AdresseStichwort> adresseStichwortList = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adresse")
    private List<Eingangsrechnung> eingansrechnungList;

    @OneToMany(mappedBy = "partner")
    private List<Planung> planungPartnerList;

    @OneToMany(mappedBy = "mitarbeiter1")
    private List<Planung> planungMitarbeiter1List;

    @OneToMany(mappedBy = "mitarbeiter2")
    private List<Planung> planungMitarbeiter2List;

    public Long getAdresseNr() {
        return adresseNr;
    }

    public void setAdresseNr(Long adresseNr) {
        this.adresseNr = adresseNr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getFirma1() {
        return firma1;
    }

    public void setFirma1(String firma1) {
        this.firma1 = firma1;
    }

    public String getFirma2() {
        return firma2;
    }

    public void setFirma2(String firma2) {
        this.firma2 = firma2;
    }

    public String getFirma3() {
        return firma3;
    }

    public void setFirma3(String firma3) {
        this.firma3 = firma3;
    }

    public String getFirma4() {
        return firma4;
    }

    public void setFirma4(String firma4) {
        this.firma4 = firma4;
    }

    public String getKennzeichen1() {
        return kennzeichen1;
    }

    public void setKennzeichen1(String kennzeichen1) {
        this.kennzeichen1 = kennzeichen1;
    }

    public String getKennzeichen2() {
        return kennzeichen2;
    }

    public void setKennzeichen2(String kennzeichen2) {
        this.kennzeichen2 = kennzeichen2;
    }

    public String getPostfachPlz() {
        return postfachPlz;
    }

    public void setPostfachPlz(String postfachPlz) {
        this.postfachPlz = postfachPlz;
    }

    public String getPostfach() {
        return postfach;
    }

    public void setPostfach(String postfach) {
        this.postfach = postfach;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getAnredeBrief() {
        return anredeBrief;
    }

    public void setAnredeBrief(String anredeBrief) {
        this.anredeBrief = anredeBrief;
    }
    
    public String getMobiltelefon() {
        return mobiltelefon;
    }

    public void setMobiltelefon(String mobiltelefon) {
        this.mobiltelefon = mobiltelefon;
    }

    public String getTelefonPrivat() {
        return telefonPrivat;
    }

    public void setTelefonPrivat(String telefonPrivat) {
        this.telefonPrivat = telefonPrivat;
    }

    public String getTelefonDienst() {
        return telefonDienst;
    }

    public void setTelefonDienst(String telefonDienst) {
        this.telefonDienst = telefonDienst;
    }

    public String getFaxPrivat() {
        return faxPrivat;
    }

    public void setFaxPrivat(String faxPrivat) {
        this.faxPrivat = faxPrivat;
    }

    public String getFaxDienst() {
        return faxDienst;
    }

    public void setFaxDienst(String faxDienst) {
        this.faxDienst = faxDienst;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getXing() {
        return xing;
    }

    public void setXing(String xing) {
        this.xing = xing;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGooglePlus() {
        return googlePlus;
    }

    public void setGooglePlus(String googlePlus) {
        this.googlePlus = googlePlus;
    }

    public String getBeruf() {
        return beruf;
    }

    public void setBeruf(String beruf) {
        this.beruf = beruf;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getGeburtsort() {
        return geburtsort;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    public String getStaatsangehoerigkeit() {
        return staatsangehoerigkeit;
    }

    public void setStaatsangehoerigkeit(String staatsangehoerigkeit) {
        this.staatsangehoerigkeit = staatsangehoerigkeit;
    }

    public String getPassNr() {
        return passNr;
    }

    public void setPassNr(String passNr) {
        this.passNr = passNr;
    }

    public Date getAusstellungsdatum() {
        return ausstellungsdatum;
    }

    public void setAusstellungsdatum(Date ausstellungsdatum) {
        this.ausstellungsdatum = ausstellungsdatum;
    }

    public String getAusstellungsort() {
        return ausstellungsort;
    }

    public void setAusstellungsort(String ausstellungsort) {
        this.ausstellungsort = ausstellungsort;
    }

    public Integer getKostenstelle() {
        return kostenstelle;
    }

    public void setKostenstelle(Integer kostenstelle) {
        this.kostenstelle = kostenstelle;
    }
    
    public String getKontoInhaber() {
        return kontoInhaber;
    }

    public void setKontoInhaber(String kontoInhaber) {
        this.kontoInhaber = kontoInhaber;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(String kontoNr) {
        this.kontoNr = kontoNr;
    }

    public String getBlz() {
        return blz;
    }

    public void setBlz(String blz) {
        this.blz = blz;
    }

    public Date getLetzteSpendeAm() {
        return letzteSpendeAm;
    }

    public void setLetzteSpendeAm(Date letzteSpendeAm) {
        this.letzteSpendeAm = letzteSpendeAm;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public BigDecimal getSpendensumme() {
        return spendensumme;
    }

    public void setSpendensumme(BigDecimal spendensumme) {
        this.spendensumme = spendensumme;
    }

    public String getFreiesFeld1() {
        return freiesFeld1;
    }

    public void setFreiesFeld1(String freiesFeld1) {
        this.freiesFeld1 = freiesFeld1;
    }

    public String getFreiesFeld2() {
        return freiesFeld2;
    }

    public void setFreiesFeld2(String freiesFeld2) {
        this.freiesFeld2 = freiesFeld2;
    }

    public String getFreiesFeld3() {
        return freiesFeld3;
    }

    public void setFreiesFeld3(String freiesFeld3) {
        this.freiesFeld3 = freiesFeld3;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public Anrede getAnrede() {
        return anrede;
    }

    public void setAnrede(Anrede anrede) {
        this.anrede = anrede;
    }

    public Bundesland getBundesland() {
        return bundesland;
    }

    public void setBundesland(Bundesland bundesland) {
        this.bundesland = bundesland;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Titel getTitel() {
        return titel;
    }

    public void setTitel(Titel titel) {
        this.titel = titel;
    }

    public List<AdresseStichwort> getAdresseStichwortList() {
        return adresseStichwortList;
    }

    public void setAdresseStichwortList(List<AdresseStichwort> adresseStichwortList) {
        this.adresseStichwortList = adresseStichwortList;
    }

    public List<Eingangsrechnung> getEingansrechnungList() {
        return eingansrechnungList;
    }

    public void setEingansrechnungList(List<Eingangsrechnung> eingansrechnungList) {
        this.eingansrechnungList = eingansrechnungList;
    }

    public List<Planung> getPlanungPartnerList() {
        return planungPartnerList;
    }

    public void setPlanungPartnerList(List<Planung> planungPartnerList) {
        this.planungPartnerList = planungPartnerList;
    }

    public List<Planung> getPlanungMitarbeiter1List() {
        return planungMitarbeiter1List;
    }

    public void setPlanungMitarbeiter1List(List<Planung> planungMitarbeiter1List) {
        this.planungMitarbeiter1List = planungMitarbeiter1List;
    }

    public List<Planung> getPlanungMitarbeiter2List() {
        return planungMitarbeiter2List;
    }

    public void setPlanungMitarbeiter2List(List<Planung> planungMitarbeiter2List) {
        this.planungMitarbeiter2List = planungMitarbeiter2List;
    }

    @Override
    public String toString() {
        return this.vorname + " " + this.nachname;
    }
    
}
