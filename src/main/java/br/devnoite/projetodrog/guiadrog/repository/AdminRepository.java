package br.devnoite.projetodrog.guiadrog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.devnoite.projetodrog.guiadrog.model.Administrador;

public interface AdminRepository extends PagingAndSortingRepository<Administrador, Long> {
	
	public Administrador findByEmailAndSenha(String email, String senha);
	
}
