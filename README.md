[![Switch to English](https://img.shields.io/badge/Switch_to-English-800080?style=for-the-badge)](https://github.com/Vitorialuz229/desafio_bolt_geradores/main/README-EN.md)

# Desafio Bolt - Geradores

## Descrição
Projeto desenvolvido como **desafio técnico**, cujo objetivo é implementar um **serviço backend** responsável por:

- Ingestão periódica de dados públicos da ANEEL;
- Persistência dos dados relevantes em banco de dados relacional;
- Exposição de uma **API REST** para consulta dos **5 maiores geradores de energia do Brasil**.

Os dados são obtidos a partir do arquivo **`ralie-usina.csv`**, disponibilizado no portal de dados abertos da ANEEL.

---

## Tecnologias Utilizadas

- **Kotlin**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Flyway** (versionamento do banco de dados)
- **Gradle (Kotlin DSL)**

---

## Fonte de Dados

- **Arquivo:** `ralie-usina.csv`
- **Origem:** ANEEL – Dados Abertos
- **URL:**  
  https://dadosabertos.aneel.gov.br/dataset/ralie-relatorio-de-acompanhamento-da-expansao-da-oferta-de-geracao-de-energia-eletrica

---

## Requisitos do Desafio

### ✔ Requisito 1 — Job de Extração
Implementar um serviço agendado que executa periodicamente para:
- Baixar o arquivo `ralie-usina.csv`;
- Processar e extrair as informações relevantes.

### ✔ Requisito 2 — Persistência
- Normalizar os dados extraídos;
- Persistir as informações em uma **tabela de banco de dados relacional**.

### ✔ Requisito 3 — API REST
- Implementar uma API RESTful que retorne:
    - Os **5 maiores geradores de energia do Brasil**, com base nos dados armazenados.

---

## Métodos da API 

Buscar os 5 maiores produtores de energia

1. GET /top-5-produtores
```bash
curl -X GET http://localhost:8081/top-5-produtores``
```

2. Scheduler

   O serviço de ingestão dos dados do RALIE é executado automaticamente a cada 4 horas, conforme configuração abaixo:
```bash
scheduler.ralie-cron=0 0 0/4 * * ?
```

## Como executar o projeto

### Pré-requisitos
- Java 21
- PostgreSQL em execução
- Gradle Wrapper

### Build do projeto
```bash
./gradlew.bat build
```

### Autora 
Vitória Luz Alves D' Abadia