package com.struti.flightreservation.DAO.Repos;

import com.struti.flightreservation.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
