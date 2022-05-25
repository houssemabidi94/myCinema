package com.shanks.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.shanks.cinema.entities.Film;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface FilmRepository extends JpaRepository<Film, Long> {
}
