# Mutual Fund Backend Service

This backend service provides APIs for calculating investment returns for mutual funds. It is built using **Spring Boot** and integrates with external APIs for financial data like beta values and market return rates.

---

## Features

- **Fetch Beta Values**: Retrieves beta values for specific mutual funds using external APIs.
- **Calculate Investment Returns**: Calculates the future value of an investment and annualized returns.
- **List of Mutual Funds**: Provides a hardcoded list of mutual funds.

---

## Prerequisites

Before running this application, ensure you have:

1. **Java Development Kit (JDK)** 17 or later installed.
2. **Maven** (if using Maven) or **Gradle** (if using Gradle) installed.
3. **Git** installed.
4. Access to the external APIs (ensure API keys are set correctly in the service).

---

## How to Run

### 1. Clone the Repository

```bash
git clone <repository-url>
cd demo
```

### 2. Build the Project

Use Maven to build the project:

```bash
mvn clean install
```

Or, if you're using Gradle:

```bash
gradle build
```

### 3. Run the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

Or for Gradle:

```bash
gradle bootRun
```

The application will start on [http://localhost:8080](http://localhost:8080).

---

## API Endpoints

### 1. Fetch Beta Value
- **URL**: `/api/mutual-funds/calculate`
- **Method**: `GET`
- **Parameters**:
  - `ticker` (String): Mutual fund ticker (e.g., "VFIAX").
  - `amount` (Double): Initial investment amount.
  - `time` (Integer): Duration of the investment in years.
- **Response**:
  - `futureValue`: The future value of the investment.
  - `annualizedReturn`: Annualized return percentage.

**Example**:
```bash
http://localhost:8080/api/mutual-funds/calculate?ticker=VFIAX&amount=1000&time=5
```

---

### 2. List Mutual Funds
- **URL**: `/api/mutual-funds`
- **Method**: `GET`
- **Response**: A list of mutual funds with their tickers and names.

**Example**:
```bash
http://localhost:8080/api/mutual-funds
```

---

## Project Structure

- **Controller**: Handles HTTP requests.
  - `MutualFundController.java`
- **Service**: Contains business logic.
  - `MutualFundService.java`
- **Model**: Represents data structures.
  - `MutualFund.java`

---

## Notes for Collaborators

1. Update the API keys for external services in `MutualFundService.java`:
   - API for beta values: `https://api.newtonanalytics.com/stock-beta`
   - API for market return rate: `https://api.stlouisfed.org/fred/series/observations`
2. Default values are used when API calls fail.

---

## Contribution

to contribute:

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-name`.
3. Commit changes: `git commit -m "Add new feature"`.
4. Push to the branch: `git push origin feature-name`.
5. Create a pull request.
