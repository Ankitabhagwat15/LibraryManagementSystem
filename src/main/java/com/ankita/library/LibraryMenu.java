
package com.ankita.library;

import java.util.Scanner;

public class LibraryMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        IssueBookDAO issueDAO = new IssueBookDAO();
        ReturnBookDAO returnDAO = new ReturnBookDAO();
        IssuedBookDAO issuedBookDAO = new IssuedBookDAO();

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("   LIBRARY MANAGEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Add Member");
            System.out.println("4. Display Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Issued Books Report");
            System.out.println("8. Search Book");
            System.out.println("9. Exit");
            System.out.println("==================================");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a number from 1 to 9: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();

                    System.out.print("Enter total copies: ");
                    int totalCopies = sc.nextInt();
                    sc.nextLine();

                    if (totalCopies <= 0) {
                        System.out.println(
                                "Copies must be greater than zero!");
                        break;
                    }

                    bookDAO.addBook(
                            title, author, totalCopies, totalCopies);
                    break;

                case 2:
                    bookDAO.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter member name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter member email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter member phone: ");
                    String phone = sc.nextLine();

                    memberDAO.addMember(name, email, phone);
                    break;

                case 4:
                    memberDAO.displayMembers();
                    break;

                case 5:
                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();

                    System.out.print("Enter member ID: ");
                    int memberId = sc.nextInt();
                    sc.nextLine();

                    issueDAO.issueBook(bookId, memberId);
                    break;

                case 6:
                    System.out.print("Enter issue ID to return: ");
                    int issueId = sc.nextInt();
                    sc.nextLine();

                    returnDAO.returnBook(issueId);
                    break;

                case 7:
                    issuedBookDAO.displayIssuedBooks();
                    break;

                case 8:
                    System.out.print("Enter book title to search: ");
                    String searchTitle = sc.nextLine();

                    bookDAO.searchBook(searchTitle);
                    break;

                case 9:
                    System.out.println(
                            "Thank you for using Library Management System!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please select 1 to 9.");
            }

        } while (choice != 9);

        sc.close();
    }
}