## TOTP Client Application

### Overview

This project is a Time-based One-Time Password (TOTP) client application inspired by Authy and Google Authenticator. It features a React-based frontend and a Spring Boot backend, with JWT-based authentication for managing users and their associated TOTPs.

The project is a learning exercise to master Spring Framework, Java, and React while building a secure application.

### Technologies Used

React, Spring Boot, Spring JPA, Java, Postgres, JWT

### Functional Requirements

**User Authentication**
- Users can register and log in to the application
- Login is based on secure codes sent to the user's email or phone number. 
- JWT tokens are issued and stored in cookies for authentication. 
- Authentication relies solely on cookies for securing API communication.

**TOTP Management**
- Users can generate and manage TOTPs for various applications. 
- Each TOTP is tied to a specific user and application. 
- The backend supports the generation of valid TOTP codes.

### API Endpoints

**Authentication**
- POST /api/v1/auth/register: Register a new user. 
- POST /api/v1/auth/login: Authenticate a user by sending a secure code to their email or phone number. 
- POST /api/v1/auth/verify: Verify the secure code and return a JWT token.

**Account Management**
- POST /api/v1/accounts: Retreive all accounts related to user.
- GET /api/v1/accounts/{id}/totp: Retrieve current totp for a particular account. 
- DELETE /api/v1/accounts/{id}: Delete an account.

**Device Management** 
- GET /api/v1/devices: Retrieve all devices registered to the authenticated user. 
- DELETE /api/v1/devices/{id}: Remove a device by its ID.

**Public Endpoints**
- GET /: Home page.
- GET /heartbeat: Health check endpoint.