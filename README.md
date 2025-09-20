# Codice Fiscale API

Questa applicazione Spring Boot espone una API REST che, dato un codice fiscale, restituisce la data di nascita e l'età.

## Requisiti
- Java 17 installato
- Maven installato

## Come avviare il progetto

1. Clona la repository o copia i file su un nuovo computer.
2. Apri un terminale nella cartella del progetto.
3. Esegui il comando:

```sh
mvn clean install
```

4. Avvia l'applicazione:

```sh
mvn spring-boot:run
```

5. Accedi alla documentazione Swagger:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Esempio di chiamata API

```
GET /api/codice-fiscale/{codiceFiscale}
```

Risposta:
```json
{
  "data_nascita": "YYYY-MM-DD",
  "eta": 30
}
```

## Test
Sono presenti test automatici su estrazione data di nascita, età e gestione errori. Per eseguirli:

```sh
mvn test
```

## Note
La logica di estrazione della data di nascita dal codice fiscale è semplificata e va adattata per casi reali.
Dal solo codice fiscale non è possibile discriminare tra nati nel 1900-1999 e 2000-2099 per persone che hanno più di 100 anni.
