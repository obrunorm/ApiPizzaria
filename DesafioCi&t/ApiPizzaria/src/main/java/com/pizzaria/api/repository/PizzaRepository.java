package com.pizzaria.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzaria.api.model.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{
    
    public List <Pizza> findAllByNomeContainingIgnoreCase (String nome);
    
    /** 
	 * Método criado para checar se o usuário já existe no banco de dados
	 */ 
	public Optional<Pizza> findByNome(String nome);

	/** 
	 * Método criado para a Sessão de testes
	 */ 
	public List<Pizza> findAllByDescricaoContainingIgnoreCase(String descricao);

}
