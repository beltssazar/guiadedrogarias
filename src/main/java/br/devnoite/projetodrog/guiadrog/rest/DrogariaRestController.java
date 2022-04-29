package br.devnoite.projetodrog.guiadrog.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.devnoite.projetodrog.guiadrog.annotation.Publico;
import br.devnoite.projetodrog.guiadrog.model.Drogaria;
import br.devnoite.projetodrog.guiadrog.repository.DrogariaRepository;

//@CrossOrigin
@RestController
@RequestMapping("/api/drogaria")
public class DrogariaRestController {
	
	@Autowired
	private DrogariaRepository repository;
	
	@Publico
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<Drogaria> getDrogarias(){
		return repository.findAll();
	}
	
	@Publico
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Drogaria> getDrogaria(@PathVariable("id") Long idDrog){
		// tenta buscar a drogaria no repository
		Optional<Drogaria> optional = repository.findById(idDrog);
		// se a drogaria existir
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Publico
	@RequestMapping(value = "/tipo/{id}", method = RequestMethod.GET)
	public Iterable<Drogaria> getTipos(@PathVariable("id") Long tipoId){
		return repository.findByTipoId(tipoId);
	}
}
