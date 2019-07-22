package com.struti.flightreservation.DAO.Repos;

import com.struti.flightreservation.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
