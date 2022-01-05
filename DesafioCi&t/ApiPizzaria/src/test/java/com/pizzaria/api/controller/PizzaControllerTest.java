package com.pizzaria.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pizzaria.api.model.Pizza;
import com.pizzaria.api.repository.PizzaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PizzaControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private PizzaRepository pizzaRepository;

	@Test
	@Order(1)
	@DisplayName("Cadastrar Uma pizza")
	public void deveCriarUmPizza() {

		HttpEntity<Pizza> requisicao = new HttpEntity<Pizza>(new Pizza(0L, 
			"Mussarela", "pizza de mussarela", "R$40.00"));

		ResponseEntity<Pizza> resposta = testRestTemplate
			.exchange("/pizzas", HttpMethod.POST, requisicao, Pizza.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
		assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
	}
	
	@Test
	@Order(2)
	@DisplayName("Listar todas as pizzas")
	public void deveMostrarTodasPizzas() {

		pizzaRepository.save(new Pizza(0L, "Calabreza", "Pizza com calabreza", "R$35.00"));

		pizzaRepository.save(new Pizza(0L, "Mussarela", "Pizza com mussarela", "R$40.00"));

		ResponseEntity<String> resposta = testRestTemplate
				.exchange("/pizzas", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("Alterar uma pizza")
	public void deveAtualizarUmaPizza() {

		Pizza pizzaCreate = pizzaRepository.save(new Pizza(0L, "Calabreza", "Pizza com calabreza", "R$35.00"));

		Pizza pizzaUpdate = new Pizza(pizzaCreate.getId(),
				"Calabreza", "Pizza com calabreza", "R$40.00");

		HttpEntity<Pizza> requisicao = new HttpEntity<Pizza>(pizzaUpdate);

		ResponseEntity<Pizza> resposta = testRestTemplate
				.exchange("/pizzas", HttpMethod.PUT, requisicao, Pizza.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(pizzaUpdate.getDescricao(), resposta.getBody().getDescricao());

	}	
	
	@Test
	@Order(5)
    @DisplayName(" Deletar pizzas!")
	public void deveDeletarPizzas() {
		
		ResponseEntity<String> resposta = testRestTemplate.exchange("/pizzas/1", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}


}
