# Reserveringsplatform voor Kunststudenten

Dit project is een proof-of-concept webapplicatie voor een kunstopleiding, waarmee studenten materiaal kunnen reserveren en huren voor hun projecten en eindwerken. De applicatie is ontwikkeld in een Java Spring omgeving en biedt een catalogus van diverse toestellen en accessoires.

## Functionaliteiten

### Core Functionaliteiten
- **Catalogus:** Alle producten worden weergegeven in een overzichtelijke catalogus.
- **Categorie Filter:** Mogelijkheid om te filteren op categorieën zoals kabels, belichting, en controlepanelen.
- **Winkelmandje:** Producten kunnen worden toegevoegd aan een winkelmandje.
- **Checkout:** Gebruikers kunnen hun reservering bevestigen en een bevestigingspagina zien.
- **Registratie:** Een registratiesysteem voor gebruikers.
- **Login Systeem:** Een veilig login systeem met salting en bcrypt voor wachtwoordbeveiliging.

### Extra Functionaliteiten
- Een admin beheer mogelijkheid, admins kunne items crud doen, users admin rechte geven en reservering bekijken en deleten eens dat product terug binnen is.
- Automatische database-updates bij aanpassingen aan modellen dankzij Hibernate.

## Technische Specificaties

### Front-end
- Vrije keuze, geen specifieke eisen.

### Back-end
- Gebouwd in **Java Spring**.
- MySQL gebruikt als database.

### Beveiliging
- Wachtwoorden worden veilig opgeslagen met behulp van **bcrypt** en **salting**.

## Vereisten om de Applicatie te Runnen

1. **Database:** Maak een nieuwe database in phpMyAdmin.
2. **Configuratie:** Voeg de volgende instellingen toe aan de `application.properties` file:

```properties
spring.application.name=WebSite
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/(Naam van je database)
spring.datasource.username=root
spring.datasource.password=
spring.thymeleaf.cache=false
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
- Zorg ervoor dat `spring.datasource.username` en `spring.datasource.password` correct zijn ingesteld.

3. **Dependencies:** Installeer de benodigde dependencies via Maven.

## Gebruikte Hulpbronnen

### AI Chat Logs
- [Chat Log 1](https://chatgpt.com/share/676e8a0e-ee04-8009-a975-2b28fb52d9c4)
- [Chat Log 2](https://chatgpt.com/share/676e8a51-79a4-8009-a8af-ac2760d25daa)
- [Chat Log 3](https://chatgpt.com/share/67685eba-fc60-8009-a92b-77c7f1977e77)
- [Chat Log 4](https://chatgpt.com/share/676e8bc1-e494-8009-a707-113ac5b5b6cb)

### Tutorials en Documentatie
- [YouTube Login Tutorial](https://www.youtube.com/watch?v=X7pGCmVxx10)

### College Sessies
- Instructies en begeleiding van **David Van Steertegem** tijdens de sessies van het vak *Enterprise Applications*.

## Projectstructuur

- **Backend:**
  - Gebruikt Spring Boot voor eenvoudige setup.
  - Hibernate ORM voor database interacties.
- **Frontend:**
  - Thymeleaf voor server-side rendering.
- **Database:**
  - MySQL met automatische schema-updates.


## Licentie
Dit project heeft geen specifieke licentie en is bedoeld als proof-of-concept voor educatieve doeleinden.


