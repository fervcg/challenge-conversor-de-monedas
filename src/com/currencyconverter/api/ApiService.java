package com.currencyconverter.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private static final String API_KEY = "eaa4fb006ca17b5f4b34048d"; // Tu clave API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final HttpClient client = HttpClient.newHttpClient(); // Cliente HTTP reutilizable

    public String getExchangeRates(String baseCurrency) throws IOException, InterruptedException {
        // Construcción de la URL para obtener las tasas
        String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;

        // Configuración del request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Realizar la solicitud y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejo de respuestas HTTP
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Error al consumir la API: " + response.statusCode() + " - " + response.body());
        }
    }
}
