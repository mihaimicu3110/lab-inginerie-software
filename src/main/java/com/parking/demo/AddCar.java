package com.parking.demo;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.parkinglot.ejb.CarsBean;
import org.example.parkinglot.ejb.UsersBean;
import org.example.parkinglot.common.UserDto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddCar", value = "/AddCar")
public class AddCar extends HttpServlet {

    @Inject
    private CarsBean carsBean;

    @Inject
    private UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("activePage", "Cars");

        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/pages/addCar.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long userId = Long.parseLong(request.getParameter("owner_id"));

        carsBean.createCar(licensePlate, parkingSpot, userId);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}

