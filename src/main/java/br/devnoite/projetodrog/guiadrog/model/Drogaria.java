package br.devnoite.projetodrog.guiadrog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Drogaria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private String cep;
	private String endereço;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String formasPagamento;
	private String horario;
	private String site;
	private String telefone;
	private boolean delivery;
	private boolean acessibilidade;
	private boolean estacionamento;
	@ManyToOne
	private Segmento tipo;
	@Column(columnDefinition = "TEXT")
	private String fotos;
	private int preco;
	
}
