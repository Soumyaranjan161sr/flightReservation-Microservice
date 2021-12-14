package com.soumya.reserveFlight.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.reserveFlight.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
