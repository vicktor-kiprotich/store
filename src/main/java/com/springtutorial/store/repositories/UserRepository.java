package com.springtutorial.store.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springtutorial.store.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
