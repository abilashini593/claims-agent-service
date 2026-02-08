# Claims Agent Service

A Spring Boot application that processes FNOL (First Notice of Loss) text, extracts key claim details, identifies missing information, and recommends an appropriate claim routing path based on simple business rules.

---

The goal is to demonstrate backend logic implementation using Java and Spring Boot.

---

## ✨ Features Implemented

- Accepts FNOL free-text input
- Extracts key claim details:
  - Policy Number
  - Estimated Damage
- Identifies missing required fields
- Applies routing logic based on business rules
- Returns a structured claim processing result

---

## 🧠 Business Rules

1. **Policy Number Extraction**
   - Extracted from text containing the keyword `Policy`
   - Example: `Policy P123`

2. **Estimated Damage Extraction**
   - Extracted from text containing `Estimated Damage`
   - Example: `Estimated Damage: 12000`

3. **Routing Logic**
   - If policy number is missing → `Manual Review`
   - If estimated damage < 25,000 and no missing fields → `Fast-track`
   - Otherwise → `Manual Review`

---

## 📦 Sample Input
Accident occurred near highway. Policy P123. Estimated Damage: 12000

---

## 📤 Sample Output

```json
{
  "status": "RECEIVED",
  "message": "FNOL processed successfully",
  "extractedFields": {
    "rawText": "Accident occurred near highway. Policy P123. Estimated Damage: 12000",
    "policyNumber": "P123",
    "estimatedDamage": 12000
  },
  "missingFields": [],
  "recommendedRoute": "Fast-track",
  "reasoning": "Estimated damage below 25,000"
}

---

## 🛠 Tech Stack

-Java

-Spring Boot

-Maven

---


