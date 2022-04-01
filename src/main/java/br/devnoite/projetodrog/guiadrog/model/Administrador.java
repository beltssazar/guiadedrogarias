package br.devnoite.projetodrog.guiadrog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.devnoite.projetodrog.guiadrog.util.HashUtil;
import lombok.Data;

//cria os getters e setters
@Data
//mapeia a etidade para o JPA
@Entity
public class Administrador {
	//chave primária e auto-incremento
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nome;
	//define a coluna email com um índice único
	@Column(unique = true)
	@Email
	private String email;
	@NotEmpty
	private String senha;
	
	//método set que aplica o hash na senha
	public void setSenha(String senha) {
		this.senha = HashUtil.hash(senha);
	}
	
	//seta o hash na senha
	public void setSenhaComHash(String hash) {
		this.senha = hash;
	}

}
