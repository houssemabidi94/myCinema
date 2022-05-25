package com.shanks.cinema.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shanks.cinema.dao.FilmRepository;
import com.shanks.cinema.entities.Film;

@RestController
public class FilmController {

	@Autowired
	private FilmRepository filmRepository;
	
	@GetMapping("/films")
	public List<Film> getAllFilms() {
		return filmRepository.findAll();
	}
}
