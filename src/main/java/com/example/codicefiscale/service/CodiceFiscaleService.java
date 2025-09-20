package com.example.codicefiscale.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CodiceFiscaleService {
    public LocalDate extractBirthDate(String cf) {
        if (cf == null || cf.trim().isEmpty()) {
            throw new com.example.codicefiscale.exception.InvalidCodiceFiscaleException(
                "CF_NULL_OR_EMPTY",
                "Codice fiscale nullo o vuoto."
            );
        }
        String regex = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
        if (!cf.toUpperCase().matches(regex)) {
            throw new com.example.codicefiscale.exception.InvalidCodiceFiscaleException(
                "CF_INVALID_FORMAT",
                "Codice fiscale non valido: deve rispettare il formato standard."
            );
        }

        String year = cf.substring(6, 8);
        char monthChar = Character.toUpperCase(cf.charAt(8));
        String day = cf.substring(9, 11);

        int anno = Integer.parseInt(year);
        int currentYear = LocalDate.now().getYear() % 100;
        // NOTA: Dal solo codice fiscale non è possibile discriminare tra nati nel 1900-1999 e 2000-2099
        // per persone che hanno più di 100 anni. Questa logica assume che chi ha "anno" > anno corrente sia nato nel 1900.
        if (anno > currentYear) {
            anno += 1900;
        } else {
            anno += 2000;
        }

        int mese;
        switch (monthChar) {
            case 'A': mese = 1; break;
            case 'B': mese = 2; break;
            case 'C': mese = 3; break;
            case 'D': mese = 4; break;
            case 'E': mese = 5; break;
            case 'H': mese = 6; break;
            case 'L': mese = 7; break;
            case 'M': mese = 8; break;
            case 'P': mese = 9; break;
            case 'R': mese = 10; break;
            case 'S': mese = 11; break;
            case 'T': mese = 12; break;
            default: throw new IllegalArgumentException("Mese non valido nel codice fiscale: " + monthChar);
        }

        int giorno = Integer.parseInt(day);
        if (giorno > 40) giorno -= 40; // Sesso femminile: +40

        return LocalDate.of(anno, mese, giorno);
    }
}
