package com.example.codicefiscale.controller;

import org.springframework.web.bind.annotation.*;
import com.example.codicefiscale.service.CodiceFiscaleService;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/codice-fiscale")
public class CodiceFiscaleController {
    @Autowired
    private CodiceFiscaleService codiceFiscaleService;

    @GetMapping("/{cf}")
    public Map<String, Object> getInfo(@PathVariable("cf") String codiceFiscale) {
        LocalDate birthDate = codiceFiscaleService.extractBirthDate(codiceFiscale);
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        return Map.of(
            "data_nascita", birthDate.format(DateTimeFormatter.ISO_DATE),
            "eta", age
        );
    }
}
