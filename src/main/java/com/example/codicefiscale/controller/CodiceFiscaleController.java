package com.example.codicefiscale.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codicefiscale.service.CodiceFiscaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/codice-fiscale")
public class CodiceFiscaleController {
    @Autowired
    private CodiceFiscaleService codiceFiscaleService;

    @Operation(
        summary = "Estrae data di nascita ed età da codice fiscale",
        description = "Restituisce la data di nascita e l'età calcolata a partire dal codice fiscale italiano."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(example = "{\"data_nascita\": \"1985-01-01\", \"eta\": 40}"))),
        @ApiResponse(responseCode = "400", description = "Codice fiscale non valido",
            content = @Content(schema = @Schema(example = "{\"errorCode\": \"CF_INVALID_FORMAT\", \"errorInfo\": \"Codice fiscale non valido: deve rispettare il formato standard.\"}")))
    })
    @GetMapping("/{cf}")
    public Map<String, Object> getInfo(
        @Parameter(description = "Codice fiscale da analizzare", example = "RSSMRA85A01H501Z")
        @PathVariable("cf") String codiceFiscale) {
        LocalDate birthDate = codiceFiscaleService.extractBirthDate(codiceFiscale);
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        return Map.of(
            "data_nascita", birthDate.format(DateTimeFormatter.ISO_DATE),
            "eta", age
        );
    }
}
