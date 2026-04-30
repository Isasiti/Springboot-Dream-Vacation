package com.dreamvacation.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Service
public class CountryInfoService {

    private static final String REST_COUNTRIES_BASE_URL = "https://restcountries.com/v3.1/name/";
    private final RestTemplate restTemplate = new RestTemplate();

    public CountryData obtenerDatosPais(String pais) {
        String paisCodificado = UriUtils.encodePathSegment(pais, StandardCharsets.UTF_8);
        String url = REST_COUNTRIES_BASE_URL + paisCodificado + "?fields=name,capital,population";

        try {
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            if (response == null || !response.isArray() || response.isEmpty()) {
                throw new IllegalArgumentException("No se encontraron datos para el pais: " + pais);
            }

            JsonNode countryNode = response.get(0);
            JsonNode capitalNode = countryNode.path("capital");
            String capital = (capitalNode.isArray() && !capitalNode.isEmpty())
                    ? capitalNode.get(0).asText("Capital no disponible")
                    : "Capital no disponible";
            long poblacion = countryNode.path("population").asLong(0L);

            return new CountryData(capital, poblacion);
        } catch (Exception ex) {
            throw new IllegalArgumentException("No se pudieron consultar datos de Rest Countries para: " + pais);
        }
    }

    public record CountryData(String capital, long poblacion) {
    }
}
