Project Overview
This project is a Java-based application that includes two main components:

Library Management System: A simple system to manage a catalog of books.
Scientific Calculator: A calculator capable of performing scientific calculations with support for various unary functions.
Project Structure
The project is organized into the following Java classes:

Library Management System
Book.class / Book.java: Represents a book in the library, including details like title, author, and ISBN.
LibraryCatalog.class / LibraryCatalog.java: Manages the collection of books in the library, providing methods to add, remove, and search for books.
Main.class / Main.java: The entry point of the Library Management System. It contains the main method to run the application.
Scientific Calculator
Number.class / Number.java: Represents a numeric value and provides methods for basic arithmetic operations.
ScientificCalculator.class / ScientificCalculator.java: Implements the functionality of a scientific calculator, supporting advanced operations like trigonometry, logarithms, and more.
ScientificCalculator$UnaryFunction.class: Represents unary functions that operate on a single numeric value, such as square root, sine, cosine, etc.
Usage
Library Management System
Run the Main class to start the Library Management System.
The system allows you to add new books, remove existing books, and search for books by title or author.
Scientific Calculator
Use the ScientificCalculator class to perform calculations.
The calculator supports both basic arithmetic operations and scientific functions like trigonometric calculations and logarithms.
Requirements
Java Development Kit (JDK) 8 or higher.
Installation
Clone the repository:
bash

git clone https://github.com/your-username/library-scientific-calculator.git
Navigate to the project directory:
bash
Copy code
cd library-scientific-calculator
Compile the Java files:
bash

javac *.java
Run the application:
bash

java Main
License
This project is licensed under the MIT License.
