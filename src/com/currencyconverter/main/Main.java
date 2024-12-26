package com.currencyconverter.main;

import com.currencyconverter.api.ApiService;
import com.currencyconverter.parser.JsonParserUtil;
import com.currencyconverter.model.CurrencyRate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instanciar el servicio API
        ApiService apiService = new ApiService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Conversor de Monedas");
        System.out.println("Seleccione las monedas que desea convertir:");
        System.out.println("1. Dólar (USD) a Peso chileno (CLP)");
        System.out.println("2. Dólar (USD) a Real brasileño (BRL)");
        System.out.println("3. Peso chileno (CLP) a Real brasileño (BRL)");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        String fromCurrency = "";
        String toCurrency = "";

        switch (opcion) {
            case 1 -> {
                fromCurrency = "USD";
                toCurrency = "CLP";
            }
            case 2 -> {
                fromCurrency = "USD";
                toCurrency = "BRL";
            }
            case 3 -> {
                fromCurrency = "CLP";
                toCurrency = "BRL";
            }
            default -> {
                System.out.println("Opción no válida");
                System.exit(0);
            }
        }

        try {
            // Obtener datos de la API
            String jsonResponse = apiService.getExchangeRates(fromCurrency);
            // Parsear datos JSON
            CurrencyRate currencyRate = JsonParserUtil.parseExchangeRate(jsonResponse, toCurrency);

            // Mostrar el resultado
            System.out.println("Ingrese la cantidad en " + fromCurrency + ": ");
            double amount = scanner.nextDouble();
            double convertedAmount = amount * currencyRate.getRate();
            System.out.printf("La cantidad convertida es: %.2f %s%n", convertedAmount, toCurrency);
        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
