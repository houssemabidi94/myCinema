package com.shanks.cinema;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.isNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.shanks.cinema.dao.FilmRepository;
import com.shanks.cinema.dao.SalleRepository;
import com.shanks.cinema.entities.Film;
import com.shanks.cinema.entities.Salle;
import com.shanks.cinema.web.FilmController;
import com.shanks.cinema.web.SalleController;

@ExtendWith(MockitoExtension.class)
public class CinemaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	@Mock
	private FilmRepository filmRepository;
	@Mock
	private SalleRepository salleRepository;
	@InjectMocks
	private FilmController filmController;
	@InjectMocks
	private SalleController salleController;

	Film film_1 = new Film(1L, "titre 1", "desc 1", " real 1", new Date(13 / 05 / 1994), 0, "", null, null);
	Film film_2 = new Film(2, "titre 2", "desc 2", " real 2", new Date(13 / 05 / 1994), 0, null, null, null);

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(filmController, salleController).build();
	}

	@Test
	@DisplayName("get all films")
	public void getAllFilms() throws Exception {
		List<Film> films = new ArrayList<>(Arrays.asList(film_1, film_2));
		Mockito.when(filmRepository.findAll()).thenReturn(films);

		mockMvc.perform(MockMvcRequestBuilders.get("/films").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].titre", is("titre 2")));

	}

	Salle salle1 = new Salle(1L, "Gorjeni", 5000, null, null, null);
	Salle salle2 = new Salle(2L, "Manzeh", 4000, null, null, null);

	@Test
	@DisplayName("get all salles")
	public void getAllSalles() throws Exception {
		List<Salle> salles = new ArrayList<>(Arrays.asList(salle1, salle2));
		Mockito.when(salleRepository.findAll()).thenReturn(salles);

		mockMvc.perform(MockMvcRequestBuilders.get("/salles")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Gorjeni")));
	}

	@Test
	@DisplayName("get book by ID")
	public void getBookByID() throws Exception {
		Mockito.when(salleRepository.findById(salle1.getId())).thenReturn(Optional.of(salle1));

		mockMvc.perform(MockMvcRequestBuilders
				.get("/salles/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("Gorjeni")));

	}
	
	@Test
	@DisplayName("creating new salle")
	public void createSalle() throws Exception{
		Salle salle = new Salle(1L, "newSalle", 500, null, null, null);
		
		Mockito.when(salleRepository.save(salle)).thenReturn(salle);
		
		String content = objectWriter.writeValueAsString(salle);
		System.out.println("content:" +content);
		System.out.println("salle"+salle);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/salles")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(content);
		
		mockMvc.perform(mockRequest)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",notNullValue()))
		.andExpect(jsonPath("$.name",is("newSalle")));
		
	}
	
	@Test
	@DisplayName("update salle")
	public void updateSalle() throws Exception{
		Salle updatedSalle = new Salle(1L, "Carthage", 13000, null, null, null);
		
		Mockito.when(salleRepository.findById(salle1.getId())).thenReturn(Optional.of(salle1));
		Mockito.when(salleRepository.save(updatedSalle)).thenReturn(updatedSalle);
		
		String salleJson = objectWriter.writeValueAsString(updatedSalle);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/salles")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(salleJson);
		
		mockMvc.perform(mockRequest)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",notNullValue()))
		.andExpect(jsonPath("$.name",is("Carthage")));
	}
	
	@Test
	@DisplayName("delete salle")
	public void deleteSalle() throws Exception{
		
		Mockito.when(salleRepository.findById(salle2.getId())).thenReturn(Optional.of(salle2));
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/salles/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
