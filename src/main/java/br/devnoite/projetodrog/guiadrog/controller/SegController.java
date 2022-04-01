package br.devnoite.projetodrog.guiadrog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.devnoite.projetodrog.guiadrog.model.Segmento;
import br.devnoite.projetodrog.guiadrog.model.Tipo;
import br.devnoite.projetodrog.guiadrog.repository.SegRepository;

@Controller
public class SegController {
	
	@Autowired
	private SegRepository repository;
	
	//request mapping para o formulário, do tipo GET
	@RequestMapping(value = "cadTipo", method = RequestMethod.GET)
	public String cadastroAdm(Model model){
		model.addAttribute("tipos", Tipo.values());
		return "cad_tipo";
	}
	
	@RequestMapping(value = "salvarTipo", method = RequestMethod.POST)
	public String salvarTipo(Segmento seg) {
		repository.save(seg);
		return "redirect:cadTipo";
	}
	
	@RequestMapping("listaTipo")
	public String listarTipos(Model model) {
		model.addAttribute("tipos", repository.findAll());
		return "lista_tipos";
	}
	
	@RequestMapping("alteraTipo")
	public String alterarTipo(Long id, Model model) {
		Segmento seg = repository.findById(id).get();
		model.addAttribute("seg", seg);
		return "forward:cadTipo";
	}
	
	@RequestMapping("excluiTipo")
	public String excluirTipo(Long id) {
		repository.deleteById(id);
		return "redirect:listaTipo";
	}
	
	@RequestMapping("buscaPalavras")
	public String buscarPorPalavrasChave(String palavra, Model model) {
		List<Segmento> tipos = repository.buscarPorPalavrasChave(palavra);
		model.addAttribute("tipos", tipos);
		return "lista_tipos";
	}
}
