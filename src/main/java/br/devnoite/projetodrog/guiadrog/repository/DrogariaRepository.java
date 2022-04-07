package br.devnoite.projetodrog.guiadrog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.devnoite.projetodrog.guiadrog.model.Drogaria;

public interface DrogariaRepository extends PagingAndSortingRepository<Drogaria, Long> {
	
}
