package com.bloodbank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addStock")
public class AdminServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String group =
                request.getParameter("blood_group");

        String date =
                request.getParameter("expiry_date");

        try {
            int units = Integer.parseInt(
                    request.getParameter("units")
            );

            if (units <= 0) {
                response.sendError(400,
                        "Units must be positive.");
                return;
            }
          String sql = "INSERT INTO inventory " +
    "(blood_group, units, expiry_date) " +
    "VALUES (?, ?, ?)";

try (Connection con = 
        DBConnection.getConnection();
     PreparedStatement ps = 
        con.prepareStatement(sql)) {

    ps.setString(1, group);
    ps.setInt(2, units);
    ps.setDate(3, 
        java.sql.Date.valueOf(date));

    ps.executeUpdate();

    response.getWriter().println(
        "Inventory updated successfully!"
    );
} catch (Exception e) {
    response.setStatus(400);
    response.getWriter().println(
        "Unable to update inventory."
    );
}
        }
            }
