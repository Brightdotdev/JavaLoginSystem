# JavaFX Login System

Welcome to the **JavaFX Login System**! 🎉  
This project is a simple, user-friendly login and sign-up application developed with JavaFX, Maven, and pure Java. Ideal for learning the basics of JavaFX, handling user authentication, 
and connecting with databases, this project is easy to run and extend.

---

## Features

- **User Registration**: Users can create accounts with their own usernames and passwords.
- **Login**: Secure login process for registered users.
- **JavaFX UI**: Interactive and responsive interface designed using JavaFX.
- **Maven Build**: Simple project setup and dependency management with Maven.
- **MySQL Database**: Stores user data in a MySQL database.
- **Data Validation**: Using regular expressions to validate user inputs before parsing it to the database to avoid redundant false data
- **Proper error handling**: Custom and inbuilt in error handling to help users navigate properly in the application

---

## Prerequisites

- **Java JDK 11+**: [Download here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven**: Ensure Maven is installed and added to your system path. [Installation Guide](https://maven.apache.org/install.html)
- **MySQL Database**: Install MySQL and create a database for the application (e.g., `login_system_db`).

---

  **MySQL Setup**:
    - Install MySQL: [MySQL Installation Guide](https://dev.mysql.com/downloads/installer/)
    - Start MySQL Server and create a database:

  ```sql
  CREATE DATABASE login_system_db;
  ```

- **Configure Database Connection**: Update the database credentials in the application's configuration file (e.g., `db.properties` or `application.properties`) with your MySQL username, password, and database name.

  ```properties
  db.url=jdbc:mysql://localhost:3306/login_system_db
  db.username=yourUsername
  db.password=yourPassword
  ```
  
---

## **Getting Started**

Follow these steps to download, build, and run the application on your local machine.

1. **Clone the repository:**

```bash
git https://github.com/Brightdotdev/JavaLoginSystem.git

cd JavaLoginSystem
```

2. **Build the project:**

Use Maven to compile and package the project:

```bash
mvn clean install
```

3. **Run the Application:**
    
Start the application using the Maven wrapper:

```bash
./mvnw javafx:run   # On Unix/Linux/macOS
mvnw.cmd javafx:run  # On Windows
```

---

##### Usage


1. **Register a New Account**:

Enter a unique email and password.
Click "Sign Up" to register.

2. **Login**:

Enter your registered username and password.
Click "Login" to access your account.

---


### Feel free to explore and modify the code!

---

**Contributing**
Contributions are welcome! Feel free to submit issues or pull requests for enhancements or bug fixes.

License
This project is open-source and available under the MIT License.

