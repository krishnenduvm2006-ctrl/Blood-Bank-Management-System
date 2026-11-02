package com.bloodbank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerDonor")
public class DonorServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String bloodGroup = 
                request.getParameter("blood_group");

        String sql = "INSERT INTO donors " +
                "(name, email, phone, blood_group) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = 
                     con.prepareStatement(sql)) {
          ps.setString(1, name);
ps.setString(2, email);
ps.setString(3, phone);
ps.setString(4, bloodGroup);

ps.executeUpdate();
response.getWriter().println(
    "Donor registered successfully!"
);

} catch (Exception e) {
    response.setStatus(
        HttpServletResponse.SC_INTERNAL_SERVER_ERROR
    );
    response.getWriter().println(
        "Registration failed. Check the details."
    );
}
}
}
