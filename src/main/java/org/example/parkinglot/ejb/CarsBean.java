package org.example.parkinglot.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ejb.EJBException;

import org.example.parkinglot.entities.Car;
import org.example.parkinglot.entities.User;
import org.example.parkinglot.common.CarDto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Collection;


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
    // 2️⃣ CREATE CAR
    // ============================
    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User owner = entityManager.find(User.class, userId);
        owner.getCars().add(car);

        car.setOwner(owner);

        entityManager.persist(car);
    }

    // ============================
    // 3️⃣ FIND CAR BY ID (NECESAR LA EDIT)
    // ============================
    public CarDto findById(Long carId) {
        LOG.info("findById " + carId);

        Car car = entityManager.find(Car.class, carId);
        if (car == null) return null;

        return new CarDto(
                car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot(),
                car.getOwner().getUsername()
        );
    }

    // ============================
    // 4️⃣ UPDATE CAR (SALVARE MODIFICĂRI)
    // ============================
    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long ownerId) {
        LOG.info("updateCar " + carId);

        Car car = entityManager.find(Car.class, carId);
        if (car == null) throw new EJBException("Car not found!");

        // Update basic fields
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        // Remove from old owner
        User oldOwner = car.getOwner();
        oldOwner.getCars().remove(car);

        // Add to new owner
        User newOwner = entityManager.find(User.class, ownerId);
        newOwner.getCars().add(car);

        // Set owner
        car.setOwner(newOwner);
    }




    // ============================
    // 5️⃣ COPY ENTITY LIST → DTO LIST
    // ============================
    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> listDto = new ArrayList<>();

        for (Car c : cars) {
            listDto.add(new CarDto(
                    c.getId(),
                    c.getLicensePlate(),
                    c.getParkingSpot(),
                    c.getOwner().getUsername()
            ));
        }

        return listDto;
    }

    public void deleteCarsByIds(Collection<Long> carIds) {
        LOG.info("deleteCarsByIds");

        for (Long carId : carIds) {
            Car car = entityManager.find(Car.class, carId);

            if (car != null) {
                entityManager.remove(car);
            }
        }
    }

}
