package com.bloodbank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT blood_group, SUM(units) AS total " +
                     "FROM inventory WHERE expiry_date >= CURRENT_DATE " +
                     "GROUP BY blood_group ORDER BY blood_group")) {

            var out = response.getWriter();

            out.println("<html><body>");
            out.println("<h2>Available Blood Stock</h2>");
            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr><th>Blood Group</th>" +
                        "<th>Available Units</th></tr>");
          while (rs.next()) {
            out.println("<tr><td>" +
                rs.getString("blood_group") +
                "</td><td>" +
                rs.getInt("total") +
                "</td></tr>");
        }

        out.println("</table></body></html>");

    } catch (Exception e) {
        response.setStatus(500);
        response.getWriter().println(
            "Unable to load inventory."
        );
    }
}
}
}
