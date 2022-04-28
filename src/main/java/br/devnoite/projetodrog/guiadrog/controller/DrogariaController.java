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
import br.devnoite.projetodrog.guiadrog.util.FireBaseUtil;

@Controller
public class DrogariaController {
	
	@Autowired
	private SegRepository drogTipo;
	
	@Autowired
	private DrogariaRepository repository;
	
	@Autowired
	private FireBaseUtil fireUtil;
	
	@RequestMapping("cadDrog")
	public String form(Model model) {
		model.addAttribute("tipos", drogTipo.findAllByOrderByOpcaoAsc());
		return "cad_drog";
		
	}
	
	@RequestMapping("salvaDrog")
	public String salvar(Drogaria drogaria, @RequestParam("fileFotos") MultipartFile[] fileFotos) {
		//String para armazenar as URL's
		String fotos = drogaria.getFotos();
		//percorre cada arquivo no vetor
		for(MultipartFile arquivo : fileFotos) {
			//verifica se o arquivo existe
			if(arquivo.getOriginalFilename().isEmpty()) {
				//vai para o próximo arquivo
				continue;
			}
			try {
				//faz o upload e guarda a URL na String fotos
				fotos += fireUtil.upload(arquivo)+";";
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			
		}
		//guarda na variável drogaria as fotos
		drogaria.setFotos(fotos);
		//salva no BD
		repository.save(drogaria);
		//repository.save(drogaria);
		return "redirect:cadDrog";
	}
	
	@RequestMapping("listaDrog")
	public String listarDrogarias(Model model) {
		model.addAttribute("drogs", repository.findAll());
		return "lista_drog";
	}
	@RequestMapping("alteraDrog")
	public String alterarDrogaria(Long id, Model model) {
		Drogaria drog = repository.findById(id).get();
		model.addAttribute("drog", drog);
		return "forward:cadDrog";
	}
	@RequestMapping("excluiDrog")
	public String excluirDrog(Long id) {
		Drogaria drog = repository.findById(id).get();
		if(drog.getFotos().length() > 0) {
			for (String foto : drog.verFotos()) {
				fireUtil.deletar(foto);
			}
		}
		repository.delete(drog);
		return "redirect:listaDrog";
	}
	
	@RequestMapping("excluirFotos")
	public String excluirFotos(Long idDrog, int numFoto, Model model) {
		//busca a drogaria no BD
		Drogaria droga = repository.findById(idDrog).get();
		//busca a URL da foto
		String urlFoto = droga.verFotos()[numFoto];
		//deleta a foto
		fireUtil.deletar(urlFoto);
		//remove a url do atributo fotos
		droga.setFotos(droga.getFotos().replace(urlFoto+";", ""));
		//salva no BD
		repository.save(droga);
		//coloca a drogaria na Model
		model.addAttribute("drog", droga);
		return "forward:cadDrog";
		
	}
	
}


