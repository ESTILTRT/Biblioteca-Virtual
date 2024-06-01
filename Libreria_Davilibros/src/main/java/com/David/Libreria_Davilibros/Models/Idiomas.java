package com.David.Libreria_Davilibros.Models;

public enum Idiomas {
    ES("es", "Español"),
    IN("en", "Ingles");

    private String idiomaAPI;
    private String idiomaBusqueda;


    Idiomas(String idiomaAPI, String idiomaBusqueda){
        this.idiomaAPI = idiomaAPI;
        this.idiomaBusqueda = idiomaBusqueda ;
    }

    public static Idiomas fromAPI(String text) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.idiomaAPI.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
    public static Idiomas Buscar(String text) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.idiomaBusqueda.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}
