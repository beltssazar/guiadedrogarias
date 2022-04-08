package br.devnoite.projetodrog.guiadrog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.devnoite.projetodrog.guiadrog.model.Drogaria;
import br.devnoite.projetodrog.guiadrog.repository.DrogariaRepository;
import br.devnoite.projetodrog.guiadrog.repository.SegRepository;

@Controller
public class DrogariaController {
	
	@Autowired
	private SegRepository drogTipo;
	
	@Autowired
	private DrogariaRepository repository;
	
	@RequestMapping("cadDrog")
	public String form(Model model) {
		model.addAttribute("tipos", drogTipo.findAllByOrderByOpcaoAsc());
		return "cad_drog";
		
	}
	
	@RequestMapping("salvaDrog")
	public String salvar(Drogaria drogaria, @RequestParam("fileFotos") MultipartFile[] fileFotos) {
		System.out.println(fileFotos.length);
		//repository.save(drogaria);
		return "redirect:cadDrog";
	}
}


