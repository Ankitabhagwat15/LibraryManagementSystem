
package com.ankita.library;

public class ReturnBookTest {

    public static void main(String[] args) {

        ReturnBookDAO dao = new ReturnBookDAO();

        // Replace 1 with an issue_id that has not been returned
        dao.returnBook(1);
    }
}
