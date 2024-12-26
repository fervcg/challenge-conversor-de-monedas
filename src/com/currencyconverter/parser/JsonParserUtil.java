package com.currencyconverter.parser;

import com.currencyconverter.model.CurrencyRate;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtil {
    public static CurrencyRate parseExchangeRate(String jsonResponse, String toCurrency) throws Exception {
        // Parsear la respuesta JSON
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        // Verificar si la respuesta contiene las tasas de cambio
        if (!jsonObject.has("conversion_rates")) {
            throw new Exception("El JSON no contiene tasas de cambio.");
        }

        // Extraer las tasas de cambio
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

        // Verificar si existe la moneda destino
        if (!conversionRates.has(toCurrency)) {
            throw new Exception("La moneda destino no se encuentra en las tasas de cambio.");
        }

        // Obtener la tasa específica
        double rate = conversionRates.get(toCurrency).getAsDouble();

        // Crear y devolver el objeto CurrencyRate
        return new CurrencyRate(toCurrency, rate);
    }
}
