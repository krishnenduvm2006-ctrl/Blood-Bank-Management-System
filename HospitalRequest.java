package com.bloodbank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/requestBlood")
public class HospitalServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int hospitalId = Integer.parseInt(
                request.getParameter("hospital_id")
            );

            int units = Integer.parseInt(
                request.getParameter("units")
            );

            String group =
                request.getParameter("blood_group");
            String priority =
                request.getParameter("priority");

            if (units <= 0) {
                response.sendError(400,
                    "Units must be greater than zero.");
                return;
            }
        String sql = "INSERT INTO blood_requests " +
    "(hospital_id, blood_group, units_requested, priority) " +
    "VALUES (?, ?, ?, ?)";

try (Connection con = 
        DBConnection.getConnection();
     PreparedStatement ps = 
        con.prepareStatement(sql)) {

    ps.setInt(1, hospitalId);
    ps.setString(2, group);
    ps.setInt(3, units);
    ps.setString(4, priority);

    ps.executeUpdate();

    response.getWriter().println(
        "Blood request submitted successfully!"
    );

} catch (Exception e) {
    response.setStatus(400);
    response.getWriter().println(
        "Request failed. Check hospital ID and inputs."
    );
}
        }
            }
