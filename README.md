# Library Management System

## Project Overview
The Library Management System is a Java-based application developed using JDBC and MySQL to manage books, members, and book transactions.

## Technologies Used
- Java
- JDBC
- MySQL
- Maven
- Eclipse IDE

## Features
- Add and display books
- Search books by title
- Register and display library members
- Issue books to members
- Return issued books
- View issued books report
- Update available book copies in the database

## Database
MySQL database: `library_db`

Main tables:
- `books`
- `members`
- `issued_books`

## How to Run
1. Clone or download the repository.
2. Import the project into Eclipse as an existing Maven project.
3. Create the `library_db` database and required tables in MySQL.
4. Configure your database connection in `DBConnection.java`.
5. Run `LibraryMenu.java`.

## Author
Ankita Bhagwat
