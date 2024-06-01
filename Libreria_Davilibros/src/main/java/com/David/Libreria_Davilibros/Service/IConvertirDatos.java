package com.David.Libreria_Davilibros.Service;

public interface IConvertirDatos {
    <T> T convertirDatos(String json, Class<T> clase);
}
