package com.shanks.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.shanks.cinema.entities.Ville;

@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface VilleRepository extends JpaRepository<Ville, Long> {
}
