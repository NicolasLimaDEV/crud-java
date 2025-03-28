package com.crud.youtube.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.youtube.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}