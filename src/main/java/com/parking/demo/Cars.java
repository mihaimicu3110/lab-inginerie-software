package com.parking.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Cars", value = "/Cars")
public class Cars extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("numberOfFreeParkingSpots", 10);
        request.setAttribute("activePage", "Cars"); // <-- AICI era lipsa

        request.getRequestDispatcher("/WEB-INF/pages/cars.jsp")
                .forward(request, response);
    }
}

