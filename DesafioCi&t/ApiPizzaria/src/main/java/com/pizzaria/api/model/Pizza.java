package com.pizzaria.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


	@Entity
	@Table(name = "tb_pizzas")
	public class Pizza {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@NotNull(message = "O atributo nome é obrigatório")
		@Size(min = 5, max = 100, message = "O atributo nome deve conter no mínimo 05 e no máximo 100 caracteres")
		private String nome;

		@Size(min = 10, max = 500, message = "O atributo escrição deve conter no mínimo 10 e no máximo 500 caracteres")
		private String descricao;

		private String preco;
		
		@Temporal(TemporalType.TIMESTAMP)
		private Date data = new java.sql.Date(System.currentTimeMillis());

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}


		public Date getData() {
			return data;
		}

		public void setData(Date data) {
			this.data = data;
		}

		public String getPreco() {
			return preco;
		}

		public void setPreco(String preco) {
			this.preco = preco;
		}
	}



