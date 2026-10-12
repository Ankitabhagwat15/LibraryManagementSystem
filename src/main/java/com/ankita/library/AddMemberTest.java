
package com.ankita.library;

public class AddMemberTest {

    public static void main(String[] args) {

        MemberDAO memberDAO = new MemberDAO();

        memberDAO.addMember(
                "Priya",
                "priya@gmail.com",
                "9876543213"
        );
    }
}

