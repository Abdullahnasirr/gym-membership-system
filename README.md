# Gym gymsystem.Membership System (CPSC 219 Demo 2)

## Demo2 Overview

This project is an object-oriented Gym gymsystem.Membership Management System developed for **CPSC 219 Demo 2**.

The system allows user to:

* Manage members
* Track check-ins and payments
* Generate summaries
* Save and load data using CSV files

This project demonstrates core **Object-Oriented Programming (OOP)** concepts including:

* Encapsulation
* Inheritance
* Abstraction
* Comparable / equals / hashCode
* Separation of concerns (UI vs logic vs data)

---

## System Design

The system follows a **layered design**:

### 1. Core Logic

* `gymsystem.gymsystem` → manages all members and operations
* `gymsystem.Member` → represents a single gym member

### 2. Object-Oriented gymsystem.Data Model

* `gymsystem.Membership` (abstract class)

    * `gymsystem.MonthlyMembership`
    * `gymsystem.QuarterlyMembership`
    * `gymsystem.AnnualMembership`

* `gymsystem.Payment` → represents a payment record

* `gymsystem.CheckIn` → represents a visit record

### 3. User Interface

* `gymsystem.GymConsoleUI` → handles all user interaction (menu, input/output)

### 4. File Management

* `gymsystem.GymFileManager` → handles CSV saving and loading

---

## Features

### gymsystem.Member Management

* Add new members with auto-generated IDs
* Update member information
* Activate / deactivate membership

### Tracking

* Record member check-ins
* Record payments
* Track total visits and total paid

### gymsystem.Summaries

* Total members and active members
* Total revenue
* Top 5 members by visits
* Inactive or zero-visit members
* Average visits by membership type

### File Operations

* Save data to CSV file
* Load data from CSV file

Example CSV header:

```
id,name,contact,address,type,visits,totalPaid,active
```

---

## Testing

JUnit tests are implemented for core logic classes:

* `gymsystem.MemberTest` → tests member behavior (visits, payments, equals, compareTo)
* `gymsystem.GymSystemTest` → tests system operations and summaries
* `gymsystem.MembershipTest` → tests membership types
* `gymsystem.PaymentTest` → tests payment logic
* `gymsystem.CheckInTest` → tests check-in functionality

Tests follow the **AAA pattern (Arrange – Act – Assert)**.

---

## Key OOP Concepts Used

### Encapsulation

* Private fields with getters/setters in `gymsystem.Member` and `gymsystem.gymsystem`

### Inheritance

* `gymsystem.Membership` is an abstract class extended by:

    * Monthly
    * Quarterly
    * Annual

### Polymorphism

* `getMembershipType()` and `getDurationMonths()` overridden in subclasses

### Comparable / equals / hashCode

* `gymsystem.Member` implements `Comparable<gymsystem.Member>` (sorted by memberId)
* `equals()` and `hashCode()` based on unique member ID

---

## How to Run

### Option 1: Run with demo data

```
Run gymsystem.Main.java
```

### Option 2: Run with CSV file

```
java gymsystem.Main DemoCsv.csv
```

If file loading fails, the system will fall back to demo data.

---

## File Structure

```
gymsystem.Main.java
gymsystem.gymsystem.java
gymsystem.Member.java
gymsystem.Membership.java
gymsystem.MonthlyMembership.java
gymsystem.QuarterlyMembership.java
gymsystem.AnnualMembership.java
gymsystem.Payment.java
gymsystem.CheckIn.java
gymsystem.GymConsoleUI.java
gymsystem.GymFileManager.java

Tests:
gymsystem.MemberTest.java
gymsystem.GymSystemTest.java
gymsystem.MembershipTest.java
gymsystem.PaymentTest.java
gymsystem.CheckInTest.java
```

---

## Technologies Used

* Java (JDK 25)
* JUnit 5
* GitLab (version control)
* CSV file handling (java.io)

---

## Authors
* Abdullah Nasir
* Brandong Aung
* Ethan Chiu

---

## Notes

* UI is console-based (System.in / System.out)
* UI classes are not unit tested (as per project guidelines)
* File handling uses CSV format for persistence

---

## Future Improvements

* JavaFX GUI (Demo 3)
* Better validation and error handling
* Advanced search/filtering
* Database integration (unsure)

---
