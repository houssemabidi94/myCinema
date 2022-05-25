package com.shanks.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.shanks.cinema.entities.Cinema;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
