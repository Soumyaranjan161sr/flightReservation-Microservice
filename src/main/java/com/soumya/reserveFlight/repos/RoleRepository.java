package com.soumya.reserveFlight.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.reserveFlight.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
