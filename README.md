# Patient Portal

A Java-based patient registration and search system built as two separate Maven WAR applications: a browser-facing web application and a REST API backend. The frontend application communicates with the backend through a Jersey-based proxy controller, while the backend handles validation, database operations, and patient data retrieval.

## 📌 Overview

The Patient Portal is a small web application designed to:

- Register patient information through a browser-based form
- Search for existing patients
- Store and retrieve patient and address data from MySQL
- Expose REST endpoints using JAX-RS/Jersey
- Separate presentation, business logic, and database-access responsibilities

The project is implemented as **two independent Java web applications**:

```text
Browser UI (WAR)
      │
      ▼
Jersey Proxy Controller
      │
      ▼
REST API Backend (WAR)
      │
      ▼
Service Layer
      │
      ▼
DAO Layer
      │
      ▼
MySQL
```

> **Note:** This project is not a Spring Boot application and does not contain a Java `main()` entry point. The applications are deployed to a servlet container such as Apache Tomcat.

## ✨ Features

- Patient registration
- Patient search
- Address storage
- REST API endpoints
- Input validation for patient names
- JSON request/response handling
- JDBC-based MySQL integration
- Prepared SQL statements
- AngularJS-based browser interface
- Proxy-based communication between frontend and backend
- Maven WAR packaging
- Deployable on Apache Tomcat

## 🛠️ Tech Stack

### Backend

| Technology | Purpose |
|---|---|
| Java 8 compatibility | Application development |
| Jersey 2.26 | JAX-RS REST endpoints |
| Servlet / Apache Tomcat | Web application hosting |
| JDBC | Database access |
| MySQL | Patient and address data storage |
| Gson / JSON-B | JSON serialization and deserialization |
| HK2 | Jersey runtime / injection support |
| Log4j 1.2.17 | Application logging |
| JUnit / Mockito | Testing support |
| Maven | Dependency management and WAR packaging |

### Frontend

| Technology | Purpose |
|---|---|
| AngularJS 1.6 | Browser UI |
| Angular Translate | UI translation |
| jQuery | Browser-side utilities |
| HTML5 | Page structure |
| CSS | Styling |
| JavaScript | Client-side functionality |
| Jersey | Frontend proxy controller |
| Java `HttpURLConnection` | Forwarding requests to backend |

Database operations are implemented directly using SQL and JDBC.

## 🏗️ Project Architecture

The backend follows a layered structure:

```text
HTTP Request
     │
     ▼
Controller
     │
     ▼
Service
     │
     ▼
DAO
     │
     ▼
MySQL
```

### Controller

Receives REST requests through Jersey/JAX-RS annotations such as:

- `@GET`
- `@POST`
- `@Path`

### Service Layer

Contains application/business logic such as validation and workflow handling.

For example, patient registration validates that first and last names are present and contain only letters before passing the data to the DAO.

### DAO Layer

Handles database access using JDBC and SQL queries.

The DAO:

- Inserts patient information
- Inserts address information
- Searches patient records
- Uses prepared statements for database parameters
- Converts database results into Java patient objects

### Patient Model

The `Patient` class is a simple Java data object used to represent patient information.

It is not a JPA entity.

## 📂 Project Structure

### Backend

```text
PatientPortal backend/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── org/cerner/patientportal/
        │       ├── bean/
        │       │   └── Patient.java
        │       ├── controller/
        │       │   └── PatientController.java
        │       ├── dao/
        │       │   └── PatientDAO.java
        │       └── services/
        │           └── PatientServices.java
        │
        ├── resources/
        │   └── jdbc.properties
        │
        └── webapp/
            └── WEB-INF/
                └── web.xml
```

### Frontend

```text
Patient-Portal---Front-End/
└── patientportal/
    ├── pom.xml
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── org/cerner/patientportal/
    │       │       └── controller/
    │       │           └── Controller.java
    │       │
    │       ├── resources/
    │       │   └── serverurl.properties
    │       │
    │       └── webapp/
    │           ├── WEB-INF/
    │           │   └── web.xml
    │           ├── HTML files
    │           ├── JavaScript files
    │           └── CSS files
    │
    └── target/
```

## 🔄 Patient Registration Flow

When a user registers a patient:

```text
1. Browser
   │
   │ POST /webapi/patient/register
   ▼
2. Frontend Jersey Controller
   │
   │ Reads serverurl.properties
   │ Adjusts date format
   │ Forwards JSON
   ▼
3. Backend Jersey Controller
   │
   ▼
4. PatientServices
   │
   │ Validates patient data
   ▼
5. PatientDAO
   │
   │ JDBC + Prepared Statements
   ▼
6. MySQL
```

The backend returns a message key, which the browser-side application translates before sending the response back to the browser.

## 🔎 Patient Search Flow

The search operation follows the same frontend-proxy architecture:

```text
Browser
  │
  ▼
Frontend Controller
  │
  ▼
Backend REST Controller
  │
  ▼
Patient Service
  │
  ▼
Patient DAO
  │
  ▼
MySQL
```

The DAO builds the search query from the supplied fields, binds values through prepared-statement parameters, and converts matching database rows into patient objects for JSON output.

## 🌐 API Endpoints

### Frontend Registration Endpoint

```http
POST /webapi/patient/register
```

This endpoint is exposed by the browser-facing application and forwards the request to the backend.

### Backend Registration Endpoint

```http
POST /webapi/patients/register
```

The backend processes validation and database operations for patient registration.

> The exact search endpoint paths should be verified from the controller implementation before documenting additional endpoints.

## 🗄️ Database

The backend uses **MySQL**.

The JDBC configuration is stored in:

```text
src/main/resources/jdbc.properties
```

The current configuration expects:

```text
Database: alien
Host: localhost
Port: 3306
```

The application works with patient and address tables.

### Database Requirement

The repository does not include:

- Database creation scripts
- Schema migration files
- Table creation SQL

Therefore, a compatible MySQL database and the expected tables must be created manually before patient registration and search operations can work correctly.

## ⚙️ Configuration

### Backend

Update:

```text
PatientPortal backend/src/main/resources/jdbc.properties
```

with your local MySQL configuration.

### Frontend

Update:

```text
Patient-Portal---Front-End/patientportal/src/main/resources/serverurl.properties
```

The application currently expects the backend to be available under:

```text
http://localhost:8069/patientPortalSystem
```

## 🚀 Running the Project Locally

### Prerequisites

Install the following:

- Java
- Maven
- MySQL
- Apache Tomcat 9

The project targets older `javax.servlet`-based WAR applications, so **Tomcat 9** is appropriate for local deployment.

Verify Maven:

```powershell
mvn -v
```

Verify Java:

```powershell
java -version
```

### 1. Build the Backend

Open PowerShell inside:

```text
PatientPortal backend
```

Run:

```powershell
mvn clean package
```

This generates:

```text
target/patientPortalSystem.war
```

### 2. Build the Frontend

Open PowerShell inside:

```text
Patient-Portal---Front-End/patientportal
```

Run:

```powershell
mvn clean package
```

This generates:

```text
target/patientportal.war
```

### 3. Configure Tomcat

Install Apache Tomcat 9 and change the HTTP connector port in:

```text
conf/server.xml
```

from:

```text
8080
```

to:

```text
8069
```

This matches the backend URL configured by the frontend application.

### 4. Deploy Both WAR Files

Copy both WAR files into:

```text
apache-tomcat-9/webapps/
```

For example:

```powershell
Copy-Item "PatientPortal backend\target\patientPortalSystem.war" "C:\tools\apache-tomcat-9\webapps\"
Copy-Item "Patient-Portal---Front-End\patientportal\target\patientportal.war" "C:\tools\apache-tomcat-9\webapps\"
```

Start Tomcat:

```powershell
C:\tools\apache-tomcat-9\bin\startup.bat
```

### 5. Open the Application

Frontend:

```text
http://localhost:8069/patientportal/
```

Backend API base:

```text
http://localhost:8069/patientPortalSystem/webapi/
```

The backend database must also be running with the expected `alien` database and compatible patient/address tables.

## 🔐 Security Notes

> **Important:** The current project configuration stores MySQL credentials in plain text inside `jdbc.properties`.

Before using this project outside a local/demo environment:

1. Move database credentials to environment variables or secure configuration.
2. Rotate the password currently stored in the repository.
3. Avoid committing secrets to GitHub.
4. Review and secure all externally accessible endpoints.
5. Add authentication and authorization if the application is deployed for real users.

The inspected codebase does **not** contain a login or authentication flow.

## 🧪 Testing

JUnit and Mockito dependencies are declared in the backend project, providing support for unit testing.

Test coverage and complete automated test suites are not documented in the project report.

## 📦 Deployment Model

Each Maven project generates its own WAR:

```text
patientPortalSystem.war
patientportal.war
```

Both applications are deployed to the same servlet container:

```text
Apache Tomcat
```

The frontend application communicates with the backend through HTTP rather than accessing the database directly.

## 📝 Important Architecture Detail

This project is best understood as a **two-WAR Java web application**:

```text
┌───────────────────────────────────────┐
│           Browser / User              │
└───────────────────┬───────────────────┘
                    │
                    ▼
┌───────────────────────────────────────┐
│      Frontend Web Application         │
│  AngularJS + Jersey Proxy Controller  │
└───────────────────┬───────────────────┘
                    │ HTTP
                    ▼
┌───────────────────────────────────────┐
│         Backend REST API              │
│  Jersey → Service → DAO → JDBC       │
└───────────────────┬───────────────────┘
                    │
                    ▼
┌───────────────────────────────────────┐
│               MySQL                   │
│        Patient + Address Data         │
└───────────────────────────────────────┘
```
