package com.electricity;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet("/calculate")
public class BillServlet extends HttpServlet {
    private static final BigDecimal FIRST_RATE = new BigDecimal("3.50");
    private static final BigDecimal SECOND_RATE = new BigDecimal("4.00");
    private static final BigDecimal THIRD_RATE = new BigDecimal("5.20");
    private static final BigDecimal FINAL_RATE = new BigDecimal("6.50");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String unitsValue = request.getParameter("units");

        try {
            int units = Integer.parseInt(unitsValue == null ? "" : unitsValue.trim());
            if (units < 0) {
                throw new NumberFormatException();
            }

            BigDecimal bill = calculateBill(units);
            request.setAttribute("units", units);
            request.setAttribute("bill", bill.setScale(2, RoundingMode.HALF_UP));
        } catch (NumberFormatException exception) {
            request.setAttribute("error", "Please enter a valid number of units.");
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private BigDecimal calculateBill(int units) {
        int remaining = units;
        BigDecimal total = BigDecimal.ZERO;

        int firstSlab = Math.min(remaining, 50);
        total = total.add(BigDecimal.valueOf(firstSlab).multiply(FIRST_RATE));
        remaining -= firstSlab;

        int secondSlab = Math.min(remaining, 100);
        total = total.add(BigDecimal.valueOf(secondSlab).multiply(SECOND_RATE));
        remaining -= secondSlab;

        int thirdSlab = Math.min(remaining, 100);
        total = total.add(BigDecimal.valueOf(thirdSlab).multiply(THIRD_RATE));
        remaining -= thirdSlab;

        if (remaining > 0) {
            total = total.add(BigDecimal.valueOf(remaining).multiply(FINAL_RATE));
        }

        return total;
    }
}
