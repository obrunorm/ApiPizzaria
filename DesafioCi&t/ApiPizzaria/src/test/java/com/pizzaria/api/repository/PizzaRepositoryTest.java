package com.pizzaria.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.pizzaria.api.model.Pizza;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PizzaRepositoryTest {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@BeforeAll
	void start(){

		pizzaRepository.save(new Pizza(0L, "Mussarela", "Pizza com mussarela", "R$40.00"));
		
		pizzaRepository.save(new Pizza(0L, "Calabreza", "Pizza com calabreza", "R$35.00"));
		
		pizzaRepository.save(new Pizza(0L, "Portuguesa", "Pizza portuguesa", "R$45.00"));
		

	}
	
	@Test
	@DisplayName("Retorna 1 pizza")
	public void deveRetornarUmaPizza() {

		Optional<Pizza> pizza = pizzaRepository.findByNome("Mussarela");
		assertTrue(pizza.get().getNome().equals("Mussarela"));
	}

	@Test
	@DisplayName("Retorna 3 pizzas")
	public void deveRetornarTresPizzas() {

		List<Pizza> listaDePizzas = pizzaRepository.findAllByDescricaoContainingIgnoreCase("Pizza");
		assertEquals(3, listaDePizzas.size());
	}
}
