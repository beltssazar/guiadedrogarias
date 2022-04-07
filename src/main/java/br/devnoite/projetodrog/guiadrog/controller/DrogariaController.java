package br.devnoite.projetodrog.guiadrog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.devnoite.projetodrog.guiadrog.repository.SegRepository;

@Controller
public class DrogariaController {
	
	@Autowired
	private SegRepository drogTipo;
	
	@RequestMapping("cadDrog")
	public String form(Model model) {
		model.addAttribute("tipos", drogTipo.findAllByOrderByOpcaoAsc());
		return "cad_drog";
	}
}
