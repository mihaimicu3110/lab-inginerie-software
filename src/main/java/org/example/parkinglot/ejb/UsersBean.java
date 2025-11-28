package org.example.parkinglot.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.example.parkinglot.entities.User;
import org.example.parkinglot.common.UserDto;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersBean {

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers() {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();

        List<UserDto> dto = new ArrayList<>();
        for (User u : users) {
            dto.add(new UserDto(
                    u.getId(),
                    u.getUsername(),
                    u.getEmail()
            ));
        }

        return dto;
    }
}
