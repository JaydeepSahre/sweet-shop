# 🍬 Sweet Shop Management System

A Test-Driven Development (TDD) based full-stack Java project to manage an inventory of sweets.  
Developed as part of the **Incubyte assessment round**, this app follows **SOLID principles**, RESTful architecture, and uses **Spring Boot + Bootstrap**.

---

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS, Bootstrap 5, JavaScript (Vanilla)
- **Build Tool**: Maven
- **Testing**: JUnit 5 (TDD approach)
- **API Design**: REST (Spring Boot Controllers)
- **In-memory Storage**: Custom repository (No DB used)

---

## 🚀 Features

- ✅ Add new sweets to inventory
- ✅ View all sweets
- ✅ Search sweets by name or category
- ✅ Sort sweets by price and quantity
- ✅ Purchase sweets (decrease quantity)
- ✅ Delete sweets
- ✅ Restock sweets (increase quantity)

---

## 🧪 Test-Driven Development (TDD)

All backend logic is covered using JUnit 5 with clean unit tests for:

- SweetService
- Sweet model behaviors
- Edge cases like purchase failure, invalid search, etc.

Run tests using:

```bash
mvn clean test

## 🖼️ UI Preview

![UI Demo](./assets/ui.png)
