package com.parking.demo;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.parkinglot.ejb.CarsBean;
import org.example.parkinglot.ejb.UsersBean;
import org.example.parkinglot.common.CarDto;
import org.example.parkinglot.common.UserDto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    UsersBean usersBean;

    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("activePage", "Cars");

        // Toți userii pentru dropdown
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("users", users);

        // ID-ul mașinii
        Long carId = Long.parseLong(request.getParameter("id"));
        CarDto car = carsBean.findById(carId);
        request.setAttribute("car", car);

        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long carId = Long.parseLong(request.getParameter("car_id"));
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        Long ownerId = Long.parseLong(request.getParameter("owner_id"));

        carsBean.updateCar(carId, licensePlate, parkingSpot, ownerId);

        response.sendRedirect(request.getContextPath() + "/Cars");
    }
}
