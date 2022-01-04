package com.pizzaria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaria.api.model.Pizza;
import com.pizzaria.api.repository.PizzaRepository;

@RestController
@RequestMapping("/pizzas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepository;

	@GetMapping
	public ResponseEntity<List<Pizza>> getAll() {
		return ResponseEntity.ok(pizzaRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pizza> getById(@PathVariable long id) {
		return pizzaRepository.findById(id)
		.map(resp -> ResponseEntity.ok(resp))
		.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Pizza>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(pizzaRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Pizza> postTema(@RequestBody Pizza pizza) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pizzaRepository.save(pizza));
	}

	@PutMapping
	public ResponseEntity<Pizza> putPizza(@RequestBody Pizza pizza) {
		return ResponseEntity.status(HttpStatus.OK).body(pizzaRepository.save(pizza));

	}

	@DeleteMapping("/{id}")
	public void deletePizza(@PathVariable long id) {
		pizzaRepository.deleteById(id);
	}
	}
