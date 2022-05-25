package com.shanks.cinema.web;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shanks.cinema.dao.SalleRepository;
import com.shanks.cinema.entities.Salle;

import javassist.NotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/salles")
public class SalleController {

	@Autowired
	private SalleRepository salleRepository;

	@GetMapping()
	public List<Salle> getAllSalles() {
		return salleRepository.findAll();
	}

	@GetMapping("/{id}")
	public Salle getSalleById(@PathVariable(value = "id") long salleId) {
		return salleRepository.findById(salleId).get();
	}

	@PostMapping
	public Salle createSalle(@RequestBody @Valid Salle salle) {
		return salleRepository.save(salle);
	}

	@PutMapping
	public Salle updateSalle(@RequestBody @Valid Salle salle) throws NotFoundException {
		if (salle.getId() == null || salle == null) {
			throw new NotFoundException("salle or salle ID must not be null");
		}
		Optional<Salle> optionalSalle = salleRepository.findById(salle.getId());
		if (optionalSalle.isEmpty()) {
			throw new NotFoundException("salle with id " + salle.getId() + " not found");
		}
		Salle existingSalle = optionalSalle.get();
		existingSalle.setName(salle.getName());
		existingSalle.setNombrePlace(salle.getNombrePlace());
		existingSalle.setPlaces(salle.getPlaces());
		
		return salleRepository.save(existingSalle);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSalle(@PathVariable(value = "id") Long salleID) throws Exception{
		if (salleRepository.findById(salleID).isEmpty()) {
			throw new NotFoundException("ID Not found");
		}		
		salleRepository.deleteById(salleID);
	} 
}
