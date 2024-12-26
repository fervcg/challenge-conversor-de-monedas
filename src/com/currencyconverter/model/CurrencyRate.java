package com.currencyconverter.model;
//Representación simple de una tasa de cambio de una moneda a otra.
//Esta clase es usada para almacenar y manipular datos de tipo de cambio de una aplicación de conversión de monedas.

public class CurrencyRate {
    private String currencyCode;
    private double rate;

    public CurrencyRate(String currencyCode, double rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getRate() {
        return rate;
    }
}
