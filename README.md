# Loan Request Service

## Projektbeschreibung
Dieser Spring Boot Service ermöglicht das Erstellen von Kredit-Requests und das Abrufen des Gesamtbetrags der Kredite eines Kunden.

## Technologien
- Java 17
- Maven
- Spring Boot
- Spring Web
- Spring Data JDBC
- H2 Database
- Validation (Bean Validation with Hibernate validator)


## Installation und Ausführung
1. **Projekt entpacken und importieren**
   ```
   - Lade die Projektdateien als ZIP-Datei herunter.
   - Entpacke die ZIP-Datei.
   - Importiere diese in eine IDE (hier wurde Intellij verwendet).
   
   oder mit git
   - git clone https://github.com/musharaf213/loan.git
   
      ```
2. **Loan-Application direkt ausführen oder mit Maven das Projekt bauen und starten**
   ```sh
   mvn spring-boot:run
   ```

## API Requests
Request wurden mithilfe von Postman (JSON) erstellt und getestet.
### 1. Kredit erstellen
- **POST** `http://localhost:8080/api/loans`
- Id und customerId werden automatisch erzeugt. Deswegen werden nur amount und customerName angegeben.
- **Request Body (JSON):**
  ```json
  {
    "amount": 1000.50,
    "customerName": "Max Mustermann"
  }
  ```
  ```json
  {
    "amount": 2000.50,
    "customerName": "Max Mustermann"
  }
  ```
    ```json
  {
    "amount": 4000.50,
    "customerName": "Thomas Müller"
  }
  ```

- **Antwort:** Die gespeicherten Kredite (siehe **Zusatz: 1. Alle Kredite anzeigen**).

### 2. Gesamten Kreditbetrag eines Kunden abrufen
- **GET** `http://localhost:8080/api/loans/{customerId}`
- **Antwort für Max Mustermann:**
  ```json
  3001.00
  ```

## Datenbank
Die Anwendung nutzt eine embedded H2-Datenbank. Die Konsole ist unter `http://localhost:8080/h2-console` verfügbar mit JDBC-URL: **jdbc:h2:mem:dcbapp**, username: **user** und password: **password**.

## Fehlerbehandlung (Exception)
- Falls der Kreditbetrag nicht zwischen 500 und 12000.50 liegt, gibt die API eine Exception zurück.

---
## Zusatz

Zusätzlich wurden auch PUT-,DELETE- und eine GET-Methode hinzugefügt, die die gesamten Kredite abrufen kann.
### 1. Alle Kredite angzeigen
- **GET** `http://localhost:8080/api/loans`
- **Request Body (JSON):**
    ```json
    [
      {
        "id": 1,
        "amount": 1000.5,
        "customerName": "Max Mustermann",
        "customerId": "19d55a61-518d-4ecf-bc04-e8da57b15ea6"
      },
      {
        "id": 2,
        "amount": 2000.5,
        "customerName": "Max Mustermann",
        "customerId": "19d55a61-518d-4ecf-bc04-e8da57b15ea6"
      },
      {
        "id": 3,
        "amount": 4000.5,
        "customerName": "Thomas Müller",
        "customerId": "8cd23bf1-c6b4-4423-a7ef-636246c76ca6"
      }
    ]

### 2. Einen Kredit ändern
- **PUT** `http://localhost:8080/api/loans/{id}`
- Änderung des Loans mit der ID 3 von 4000.5 auf 5000.5
- Hier muss die unique customerId mit angegeben werden
- **Request Body (JSON):**
    ```json
  {
    "amount": 5000.5,
    "customerName": "Thomas Müller",
    "customerId": "8cd23bf1-c6b4-4423-a7ef-636246c76ca6"
  }

### 3. Einen Kredit löschen
- **DELETE** `http://localhost:8080/api/loans/{id}`
- **Antwort:**
    ```sh
    Loan deleted successfully
    ```


