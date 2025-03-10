### Overview

This project is a Time-based One-Time Password (TOTP) client application inspired by Authy and Google Authenticator. It features a React-based frontend and a Spring Boot backend, with JWT-based authentication for managing users and their associated TOTPs.

The project is a learning exercise to master Spring Framework, Java, and React while building a secure application.

### Technologies Used

React, Spring Boot, Spring JPA, Java, Postgres, JWT

### Functional Requirements

**User Authentication**
- Login is based on secure codes sent to either email or phone number.
- JWT tokens are issued and stored in cookies for authentication.
- Remembered devices will have longer sessions.

**TOTP Management**
- Users can add accounts either through a QR code or manually.
- Each TOTP is tied to a specific user and account.
- The backend supports the generation of valid TOTP codes.
- Each account shows the TOTP with a countdown timer

### Technical Requirements

**Authentication**
- Use spring security for securing the API endpoints
- Use a custom filter to intercept cookies and validate JWT
- Exclude public endpoints like register, sign in, verification and UI from authentication
- Set a principal to user_id for further access

**Email & SMS Integration**
- Use AWS SQS and SNS

**Deployments**

**Dev Experience**

### API Endpoints

**Authentication**

| HTTP_METHOD | API                             | DESCRIPTION                                                                  |
|-------------|---------------------------------|------------------------------------------------------------------------------|
| POST        | /api/v1/auth/register           | Register a new user.                                                         |
| POST        | /api/v1/auth/login_token        | Authenticate a user by sending a secure code to their email or phone number. |
| POST        | /api/v1/auth/verify_login_token | Verify the secure code and return a JWT token.                               |

**Users**

| HTTP_METHOD | API                | DESCRIPTION         |
|-------------|--------------------|---------------------|
| PUT         | /api/v1/users/{id} | Update User profile |
| DELETE      | /api/v1/users/{id} | Delete User         |

**Account Management**

| HTTP_METHOD | API                        | DESCRIPTION                                     |
|-------------|----------------------------|-------------------------------------------------|
| POST        | /api/v1/accounts           | Create an account                               |
| GET         | /api/v1/accounts           | Retrieve all accounts for an user               |
| GET         | /api/v1/accounts/{id}/totp | Retrieve current totp for a particular account. |
| DELETE      | /api/v1/accounts/{id}      | Delete an account.                              |

**Device Management**

| HTTP_METHOD | API                  | DESCRIPTION                                                |
|-------------|----------------------|------------------------------------------------------------|
| GET         | /api/v1/devices      | Retrieve all devices registered to the authenticated user. |
| DELETE      | /api/v1/devices/{id} | Remove a device by its ID.                                 |

**Public Endpoints**

| HTTP_METHOD | API        | DESCRIPTION            |
|-------------|------------|------------------------|
| GET         | /          | React Frontend         |
| GET         | /heartbeat | Health check endpoint. |

### Database Schema

![image](https://github.com/user-attachments/assets/970eeb4a-35e0-4746-bfd9-e22af811af46)
