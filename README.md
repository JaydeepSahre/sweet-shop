# Sweet Shop Management System

**Kata:** Sweet Shop Management System  
**Assessment Round:** Incubyte

## Project Overview

A small management system for a sweet shop, implementing core operations:

- **Backend:** Java (Spring Boot)
    - TDD with JUnit 5
    - SOLID principles
    - RESTful APIs under `/api/sweets`
- **Frontend:** Static HTML/CSS + Bootstrap
    - Served from `src/main/resources/static`
    - Uses `fetch()` to call Java APIs

## Prerequisites

- Java 11+ (JDK)
- Maven 3.6+
- Git 2.47.1+
- IntelliJ IDEA (or VS Code)

## Setup

```bash
# clone your repo (once pushed)
git clone https://github.com/<your‑username>/sweet-shop.git
cd sweet-shop

# to build & run backend
mvn spring-boot:run

# to view frontend
open http://localhost:8080/index.html