# **A Web Automation Project using ...**

![image](https://github.com/user-attachments/assets/a20a1ec8-1876-4ca3-bf17-2cb61043454d)

This project automates key scenarios on the website [Daily Finance](https://dailyfinance.roadtocareer.net/) using TestNG for testing and follows the Page Object Model (POM) design pattern.

---

## 🖥️ Features

- User Registration and Email Verification
- Password Reset Scenarios (Negative & Positive)
- Login Verification
- Item Addition and Assertion
- Profile Update and Email Validation
- Admin Dashboard Validation

## ⚒️ Tools and Technologies Used

- **Language**: Java
- **Framework**: TestNG
- **Build Tool**: Gradle
- **Design Pattern**: Page Object Model (POM)
- **Reporting**: Allure Reports
- **Version Control**: GitHub

## 🧠 Automation Scenarios : [Recording](https://go.screenpal.com/watch/cTVllJneJQ0)

### 🧪 Scenario 1: User Registration

1. Visit [Daily Finance](https://dailyfinance.roadtocareer.net/).
2. Register a new user (e.g., `yourvalidgmailuser+1@gmail.com`).
3. Assert that a "Congratulations" email is received.

### 🧪 Scenario 2: Password Reset

1. Attempt password reset with invalid data (2 negative test cases).
2. Input the registered Gmail account and request a reset link.
3. Retrieve the password reset email from Gmail.
4. Set a new password and confirm the reset.
5. Login with the new password and ensure success.

### 🧪 Scenario 3: Adding Items

1. Add two items to the list:
    - One with all fields filled.
    - One with only mandatory fields filled.
2. Assert that both items appear in the item list.

### 🧪 Scenario 4: Profile Update

1. Update the registered email to a new Gmail address from the user profile.
2. Logout and login with the updated email.
3. Assert that login with the updated email is successful.
4. Assert that login with the old email fails.

### 🧪 Scenario 5: Admin Dashboard Validation

1. Login as an admin using credentials passed securely through the terminal.
2. Search for the updated email.
3. Assert that the updated email appears in the admin dashboard.

## 📊 Allure Reports

![image](https://github.com/user-attachments/assets/3890fccb-e08a-4149-898c-6f6ef8050267)

![image](https://github.com/user-attachments/assets/3ae7dff1-9b27-4547-87bd-3239a8bdc860)

## 📋 Test Case Documentation

You can find the detailed test cases [here](https://docs.google.com/spreadsheets/d/1Th49lhptVNlXDWJhZjxc_F62TAe7EHzb/edit?gid=1414849447#gid=1414849447).

## 🚀 Setup and Execution

### 🔒 Prerequisites

- Java 8 or higher
- Gradle installed
- Gmail credentials with less secure app access enabled (optional for testing email automation)

### 🗣️ Steps to Run the Tests

1. Clone the repository:
    
    ```bash
    git clone <https://github.com/zfnkml/web-automation-testNG-selenium.git>
    cd web-automation-testNG-selenium
    
    ```
    
2. Install dependencies:
    
    ```bash
    gradle clean build
    
    ```
    
3. Run the test suite:
    
    ```bash
    gradle test
    
    ```
    
4. Generate Allure Report:
    
    ```bash
    allure generate allure-results --clean -output
    allure serve allure-results/
    
    ```
    

## 🏗️ Folder Structure

```
web-automation-testNG-selenium/
├── .gitignore
├── build.gradle
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src/
    └── test/
        ├── java/
        │   └── com/
        │       └── yourcompany/
        │           ├── pages/
        │           │   ├── HomePage.java
        │           │   ├── LoginPage.java
        │           │   ├── RegistrationPage.java
        │           │   └── ProfilePage.java
        │           ├── tests/
        │           │   ├── RegistrationTest.java
        │           │   ├── LoginTest.java
        │           │   ├── PasswordResetTest.java
        │           │   └── ProfileUpdateTest.java
        │           └── utils/
        │               ├── EmailUtils.java
        │               └── TestBase.java
        └── resources/
            ├── testng.xml
            └── config.properties
```

## 📈Future Improvements

- Enhance test coverage with additional edge cases.
- Implement data-driven testing for scalability.
- Automate Gmail interactions using secure OAuth tokens.
