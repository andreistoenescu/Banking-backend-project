# 🏦 Banking System – Design Patterns Implementation  

This project is an implementation of a **simplified banking system**, designed to highlight and apply three fundamental **design patterns**:  

- **Factory Pattern** – for creating accounts and cards.  
- **Command Pattern** – for handling commands received as input.  
- **Builder Pattern** – for building complex transactions.  

The project structure was designed to be **scalable, modular, and easy to maintain**, providing a solid foundation for future development.  

---

## ✨ Design Patterns Used  

### 🔹 Factory Pattern  
Used to create objects of type **Account** and **Card** without exposing the instantiation logic in the main code:  
- Accounts: `ClassicAccount`, `SavingsAccount`.  
- Cards: `ClassicCard`, `OneTimeUseCard`.  

### 🔹 Command Pattern  
Allows interpretation and execution of commands at runtime.  
- All commands implement a `Command` interface with an `execute()` method.  
- The `CommandCenter` interprets the input and runs the appropriate commands.  

### 🔹 Builder Pattern  
Used for **building transactions**, as they may have significantly different structures.  

---

## 📂 Project Structure  

### 1. `account`  
- **`Account` (interface)** – defines methods required for accounts.  
- **`AccountFactory` (class)** – factory for creating accounts.  
- **`ClassicAccount` / `SavingsAccount` (class)** – specific account implementations.  

### 2. `card`  
- **`Card` (interface)** – defines methods required for cards.  
- **`CardFactory` (class)** – factory for creating cards.  
- **`ClassicCard` / `OneTimeUseCard` (class)** – specific card implementations.  

### 3. `commandscenter`  
- **`Commands` (package)** – contains individual command classes, each implementing `execute()`.  
- **`CommandCenter` (class)** – interprets commands from input and executes them.  

### 4. `outputjson`  
- Contains classes dedicated to generating output in JSON format.  
- Results are stored in an `ArrayList<ObjectNode>` and exported at the end to output files.  

### 5. `transactions`  
- Stores transaction history for each user.  
- Provides methods for generating timestamps and serializing transactions into an `ObjectNode`.  

### 6. `Elements` (class)  
- Represents the bank.  
- Stores users (in a `HashMap` keyed by email) and the exchange rate system.  

### 7. `ExchangeRate` (class)  
- Represents exchange rates.  
- Implemented as a **directed graph**, where currency conversions are computed via DFS traversal.  

### 8. `User` (class)  
- Represents a banking user.  
- Stores:  
  - accounts (in a `HashMap` keyed by IBAN),  
  - personal data,  
  - aliases for accounts,  
  - transaction history (`ArrayList`).  

---

## ⚙️ How It Works  

1. In `main`, a `CommandCenter` object is instantiated.  
2. Input commands are provided.  
3. Results are collected in an `ArrayList` of outputs.  
4. At the end, outputs are written to JSON files corresponding to each test.  

---

## 🚀 Benefits of the Implementation  

- **Scalability** – new account types, cards, or transactions can be added easily and independently.  
- **Flexibility** – commands can be extended effortlessly by adding new classes to the `commands` package.  
- **Separation of Concerns** – each package/class has a well-defined role.  
- **Extensibility** – naturally supports new features (e.g., additional exchange rates, new transaction types).  

---

## 🛠️ Technologies Used  

- **Java** (OOP, Collections, JSON handling)  
- **Jackson** – for generating and managing JSON objects  
- **Design Patterns**: Factory, Command, Builder  

---

👉 This project serves as a practical example for learning and applying design patterns in a real-world inspired context (simplified banking system).  
