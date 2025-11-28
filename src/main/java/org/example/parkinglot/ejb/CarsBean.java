package org.example.parkinglot.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ejb.EJBException;

import org.example.parkinglot.entities.Car;
import org.example.parkinglot.common.CarDto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;


    // ============================
    // 1️⃣ FIND ALL CARS
    // ============================
    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");

        try {
            TypedQuery<Car> typedQuery = entityManager.createQuery(
                    "SELECT c FROM Car c", Car.class);

            List<Car> cars = typedQuery.getResultList();

            return copyCarsToDto(cars);

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }


    // ============================
    // 2️⃣ COPY ENTITY LIST → DTO LIST
    // ============================
    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> listDto = new ArrayList<>();

        for (Car c : cars) {
            listDto.add(new CarDto(
                    c.getId(),
                    c.getLicensePlate(),
                    c.getParkingSpot(),
                    c.getOwner().getUsername() //Owner Name
            ));
        }

        return listDto;
    }

}
