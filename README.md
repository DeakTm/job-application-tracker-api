# Job Application Tracker API

API REST sviluppata con Spring Boot per tenere traccia delle candidature di lavoro. Ogni utente può gestire le proprie candidature in modo sicuro tramite autenticazione JWT.

## Tecnologie utilizzate

- Java 21
- Spring Boot 4.0
- Spring Security + JWT (JJWT 0.12)
- Spring Data JPA + Hibernate
- MySQL
- Springdoc OpenAPI (Swagger UI)

## Funzionalità

- Registrazione e login utente con hashing della password tramite BCrypt
- Autenticazione stateless basata su JWT
- CRUD completo per le candidature di lavoro
- Ogni utente può accedere solo alle proprie candidature
- Validazione degli input con Jakarta Validation
- Gestione globale delle eccezioni

## Come avviare il progetto

### Prerequisiti

- Java 21
- Maven
- MySQL

### Configurazione

1. Clona il repository:
   ```bash
   git clone https://github.com/DeakTm/job-application-tracker-api.git
   ```

2. Crea il database MySQL:
   ```sql
   CREATE DATABASE jobApplicationTracker;
   ```

3. Configura `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/jobApplicationTracker
   spring.datasource.username=tuo_username
   spring.datasource.password=tua_password
   jwt.secret=tua_chiave_segreta_minimo_32_caratteri
   ```

4. Avvia l'applicazione:
   ```bash
   mvn spring-boot:run
   ```

5. Apri Swagger UI:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

## Endpoint

### Autenticazione
| Metodo | Endpoint | Descrizione | Autenticazione |
|--------|----------|-------------|----------------|
| POST | `/api/auth/register` | Registra un nuovo utente | No |
| POST | `/api/auth/login` | Login e ottenimento token JWT | No |

### Candidature
| Metodo | Endpoint | Descrizione | Autenticazione |
|--------|----------|-------------|----------------|
| GET | `/api/applications` | Ottieni tutte le candidature dell'utente loggato | Sì |
| GET | `/api/applications/{id}` | Ottieni una candidatura per ID | Sì |
| POST | `/api/applications` | Crea una nuova candidatura | Sì |
| PUT | `/api/applications/{id}` | Aggiorna una candidatura | Sì |
| DELETE | `/api/applications/{id}` | Elimina una candidatura | Sì |

## Valori stato candidatura

```
INVIATA, COLLOQUIO, TEST_TECNICO, OFFERTA, RIFIUTATA
```

## Autenticazione

Dopo il login, includi il token JWT nell'header di ogni richiesta:

```
Authorization: Bearer <il_tuo_token>
```

Su Swagger UI, clicca il pulsante **Authorize** 🔒 e incolla il token.
