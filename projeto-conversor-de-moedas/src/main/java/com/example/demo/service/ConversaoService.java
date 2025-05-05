package com.example.demo.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ConversaoService {

    private static final String API_KEY = "";  // Substitua pela sua chave da API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";  // USD como base

    public double converterMoeda(String moedaOrigem, double valor, String moedaDestino) {
        try {
            String urlString = API_URL;  // Usando USD como base
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");

            // Lê a resposta da API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse da resposta JSON com Gson
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");

            // Conversão em duas etapas:
            // 1. Converter moedaOrigem para USD
            double taxaOrigemParaUSD = rates.get(moedaOrigem).getAsDouble();
            double valorEmUSD = valor / taxaOrigemParaUSD;

            // 2. Converter o valor em USD para a moeda de destino
            double taxaUSDFinalParaDestino = rates.get(moedaDestino).getAsDouble();
            return valorEmUSD * taxaUSDFinalParaDestino;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar a conversão de moedas", e);
        }
    }
}

