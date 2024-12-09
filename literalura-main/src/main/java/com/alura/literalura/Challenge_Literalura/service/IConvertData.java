package com.alura.literalura.Challenge_Literalura.service;

public interface IConvertData {
    <T> T obtenerDatos(String json, Class<T> clase);
}
