package br.devnoite.projetodrog.guiadrog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.devnoite.projetodrog.guiadrog.model.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

}
