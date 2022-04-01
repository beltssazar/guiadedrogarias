package br.devnoite.projetodrog.guiadrog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.devnoite.projetodrog.guiadrog.model.Segmento;

public interface SegRepository extends PagingAndSortingRepository<Segmento, Long> {
	
	
		//JPQL
		@Query("SELECT s FROM Segmento s WHERE s.palavrasChave LIKE %:p%")
		public List<Segmento> buscarPorPalavrasChave(@Param("p") String palavra);

}
