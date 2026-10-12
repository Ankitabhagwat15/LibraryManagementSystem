

package com.ankita.library;

public class Main {

    public static void main(String[] args) {

        BookDAO bookDAO = new BookDAO();

        bookDAO.addBook(
                "Java Advanced",
                "Herbert Schildt",
                4,
                4
        );

        bookDAO.displayBooks();
    }
}

