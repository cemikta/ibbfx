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

/**
 * Zgfaktor bedingung1 type enum
 *
 * @see Zgfaktor
 *
 * @author Cem Ikta
 */
public enum ZgfaktorBedingung1Type {

    JUGEND_UND_SCHULE("Fachbereich ist Jugend und Schule"),
    NICHT_JUGEND_UND_SCHULE("Fachbereich ist nicht Jugend und Schule");
    
    private final String name;

    ZgfaktorBedingung1Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
