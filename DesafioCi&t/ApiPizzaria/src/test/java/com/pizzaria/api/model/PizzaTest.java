package com.pizzaria.api.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PizzaTest {
	
	public Pizza pizza;
	public Pizza pizzaNulo = new Pizza();
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {	
		
		pizza = new Pizza(0L, "Calabreza", "pizza com calabreza", "R$35.00");
	}

	@Test
	@DisplayName("✔ Valida Atributos Não Nulos")
	void testValidaAtributos() {
		
		Set<ConstraintViolation<Pizza>> violacao = validator.validate(pizza);
		
		System.out.println(violacao.toString());
		
		assertTrue(violacao.isEmpty());		
	}
	
	@Test
	@DisplayName("✖ Não Valida Atributos Nulos")
	void testNaoValidaAtributos() {
		
		Set<ConstraintViolation<Pizza>> violacao = validator.validate(pizzaNulo);
		
		System.out.println(violacao.toString());
		
		assertFalse(violacao.isEmpty());
		
	}


}
