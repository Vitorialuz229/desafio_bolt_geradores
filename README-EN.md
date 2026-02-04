[![Switch to Portuguese](https://img.shields.io/badge/Switch_to-Portuguese-800080?style=for-the-badge)](https://github.com/Vitorialuz229/desafio_bolt_geradores/blob/main/README.md)

# Bolt Challenge – Power Generators

## Description
This project was developed as a **technical challenge**, aiming to implement a **backend service** responsible for:

- Periodic ingestion of public data from ANEEL;
- Persistence of relevant data in a relational database;
- Exposure of a **REST API** to retrieve the **top 5 largest power generators in Brazil**.

The data is obtained from the **`ralie-usina.csv`** file, available on ANEEL’s open data portal.

---

## Access the Frontend

You can view the frontend source code of the project on GitHub by clicking [here](https://github.com/Vitorialuz229/desafio_bolt_geradores_frontend).

Explore the repository to see how the application was built using Angular, TailwindCSS, and RxJS!

## Technologies Used

- **Kotlin**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Flyway** (database versioning)
- **Gradle (Kotlin DSL)**

---

## Data Source

- **File:** `ralie-usina.csv`
- **Source:** ANEEL – Open Data
- **URL:**  
  https://dadosabertos.aneel.gov.br/dataset/ralie-relatorio-de-acompanhamento-da-expansao-da-oferta-de-geracao-de-energia-eletrica

---

## Challenge Requirements

### ✔ Requirement 1 — Extraction Job
Implement a scheduled service that periodically:
- Downloads the `ralie-usina.csv` file;
- Processes and extracts the relevant information.

### ✔ Requirement 2 — Persistence
- Normalize the extracted data;
- Persist the information in a **relational database table**.

### ✔ Requirement 3 — REST API
- Implement a RESTful API that returns:
    - The **top 5 largest power generators in Brazil**, based on the stored data.

---

## API Methods

### Get Top 5 Power Producers

Endpoint responsible for retrieving the **top 5 power producers**, ordered by **authorized power output** in descending order.

#### 1. GET `/top-5-produtores`

```bash
curl -X GET http://localhost:8081/top-5-produtores
```

## How to Run the Project

### Prerequisites
- Java 21
- PostgreSQL running
- Gradle Wrapper

### Build the project
```bash
./gradlew.bat build
```

### Author
Vitória Luz Alves D' Abadia
