package com.example.codicefiscale.service;

import com.example.codicefiscale.exception.InvalidCodiceFiscaleException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CodiceFiscaleServiceTest {
    private final CodiceFiscaleService service = new CodiceFiscaleService();

    @Test
    void testUomoGennaio1985() {
        // RSSMRA85A01H501Z: 01/01/1985 uomo
        LocalDate date = service.extractBirthDate("RSSMRA85A01H501Z");
        assertEquals(LocalDate.of(1985, 1, 1), date);
        int expectedAge = java.time.Period.between(date, LocalDate.now()).getYears();
        assertEquals(expectedAge, java.time.Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    void testDonnaFebbraio1990() {
        // RSSMRA90B41H501Z: 01/02/1990 donna (41 = 1 + 40)
        LocalDate date = service.extractBirthDate("RSSMRA90B41H501Z");
        assertEquals(LocalDate.of(1990, 2, 1), date);
        int expectedAge = java.time.Period.between(date, LocalDate.now()).getYears();
        assertEquals(expectedAge, java.time.Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    void testUomoDicembre1975() {
        // RSSMRA75T15H501Z: 15/12/1975 uomo
        LocalDate date = service.extractBirthDate("RSSMRA75T15H501Z");
        assertEquals(LocalDate.of(1975, 12, 15), date);
        int expectedAge = java.time.Period.between(date, LocalDate.now()).getYears();
        assertEquals(expectedAge, java.time.Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    void testDonnaMarzo2001() {
        // RSSMRA01C55H501Z: 15/03/2001 donna (55 = 15 + 40)
        LocalDate date = service.extractBirthDate("RSSMRA01C55H501Z");
        assertEquals(LocalDate.of(2001, 3, 15), date);
        int expectedAge = java.time.Period.between(date, LocalDate.now()).getYears();
        assertEquals(expectedAge, java.time.Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    void testUomoGiugno1930() {
        // RSSMRA30H20H501Z: 20/06/1930 uomo
        LocalDate date = service.extractBirthDate("RSSMRA30H20H501Z");
        assertEquals(LocalDate.of(1930, 6, 20), date);
        int expectedAge = java.time.Period.between(date, LocalDate.now()).getYears();
        assertEquals(expectedAge, java.time.Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    void testDonnaLuglio2010() {
        // RSSMRA10L45H501Z: 5/07/2010 donna (45 = 5 + 40)
        LocalDate date = service.extractBirthDate("RSSMRA10L45H501Z");
        assertEquals(LocalDate.of(2010, 7, 5), date);
        int expectedAge = java.time.Period.between(date, LocalDate.now()).getYears();
        assertEquals(expectedAge, java.time.Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    void testUomoAgosto2005() {
        // RSSMRA05M31H501Z: 31/08/2005 uomo
        LocalDate date = service.extractBirthDate("RSSMRA05M31H501Z");
        assertEquals(LocalDate.of(2005, 8, 31), date);
        int expectedAge = java.time.Period.between(date, LocalDate.now()).getYears();
        assertEquals(expectedAge, java.time.Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    void testDonnaSettembre1988() {
        // RSSMRA88P50H501Z: 10/09/1988 donna (50 = 10 + 40)
        LocalDate date = service.extractBirthDate("RSSMRA88P50H501Z");
        assertEquals(LocalDate.of(1988, 9, 10), date);
        int expectedAge = java.time.Period.between(date, LocalDate.now()).getYears();
        assertEquals(expectedAge, java.time.Period.between(date, LocalDate.now()).getYears());
    }

    @Test
    void testErroreFormato() {
        Exception ex = assertThrows(InvalidCodiceFiscaleException.class, () -> service.extractBirthDate("RSSMRA85A01H501"));
        assertEquals("CF_INVALID_FORMAT", ((InvalidCodiceFiscaleException) ex).getErrorCode());
    }

    @Test
    void testErroreNullVuoto() {
        Exception ex1 = assertThrows(InvalidCodiceFiscaleException.class, () -> service.extractBirthDate(null));
        assertEquals("CF_NULL_OR_EMPTY", ((InvalidCodiceFiscaleException) ex1).getErrorCode());
        Exception ex2 = assertThrows(InvalidCodiceFiscaleException.class, () -> service.extractBirthDate("   "));
        assertEquals("CF_NULL_OR_EMPTY", ((InvalidCodiceFiscaleException) ex2).getErrorCode());
    }
}
