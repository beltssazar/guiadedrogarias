package br.devnoite.projetodrog.guiadrog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.devnoite.projetodrog.guiadrog.annotation.Publico;
import br.devnoite.projetodrog.guiadrog.model.Administrador;
import br.devnoite.projetodrog.guiadrog.repository.AdminRepository;
import br.devnoite.projetodrog.guiadrog.util.HashUtil;

@Controller
public class AdmController {
	
	//variável para persistência dos dados
	@Autowired
	private AdminRepository repository;
	
	//request mapping para o formulário, do tipo GET
	@RequestMapping(value = "cadAdm", method = RequestMethod.GET)
	public String cadastroAdm(){
		return "cad_adm";
	}
	
	//request mapping para salvar o Administrador, do tipo POST
	@RequestMapping(value = "salvarAdmin", method = RequestMethod.POST)
	public String salvarAdmin(@Valid Administrador admin, BindingResult result, RedirectAttributes attr) {
		//verifica se houveram erros na validação
		if (result.hasErrors()) {
			//adiciona uma mensagem de erro
			attr.addFlashAttribute("mensagemErro", "Verifique os campos...");
			//redireciona para o formulário
			return "redirect:cadAdm";
			
		}
		
		//variável para descobrir se está alterando ou inserindo
		boolean alteracao = admin.getId() != null ? true : false;
		//verifica se a senha está vazia
		if(admin.getSenha().equals(HashUtil.hash(""))) {
			if (!alteracao) {
				//retirar a parte antes do @ no email
				String parte = admin.getEmail().substring(0, admin.getEmail().indexOf("@"));
				//setar a parte na senha do admin
				admin.setSenha(parte);
			}else {
				//buscar a senha atual no BD
				String hash = repository.findById(admin.getId()).get().getSenha();
				//setar o hash na senha
				admin.setSenhaComHash(hash);
			}
			
		}
		
		try {
			//salva no bd a entidade
			repository.save(admin);
			//adiciona uma mensagem de sucesso
			attr.addFlashAttribute("mensagemSucesso", "Administrador cadastrado com sucesso. ID:"+admin.getId());
		} catch (Exception e) {
			//adiciona uma mensagem de erro
			attr.addFlashAttribute("mensagemErro", "Houve um erro ao cadastrar:"+e.getMessage());
		}
		
		//redireciona para o formulário
		return "redirect:cadAdm";
	}
	
	
	//reuqet mapping para listar os administradores
	@RequestMapping("listaAdm/{page}")
	public String listaAdm(Model model, @PathVariable("page") int page) {
		//cria um pageable informando os parâmetros da página
		PageRequest pageable = PageRequest.of(page-1, 6, Sort.by(Sort.Direction.ASC, "nome"));
		//cria um Page de administrador através dos parâmetros passados pelo Repository
		Page<Administrador> pagina = repository.findAll(pageable);
		//adiciona à Model a lista com o admins
		model.addAttribute("admins", pagina.getContent());
		//variável para o total de páginas
		int totalPages = pagina.getTotalPages();
		//cria um List de inteiros para armazenar os números das páginas
		List<Integer> numPaginas = new ArrayList<Integer>();
		//preencher o List com as páginas
		for(int i = 1; i <= totalPages; i++) {
			//adiciona a página ao List
			numPaginas.add(i);
		}
		//adiciona os valores à Model
		model.addAttribute("numPaginas", numPaginas);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("pagAtual", page);
		//retorna para o html da lista
		return "lista_adm";
	}

	@RequestMapping("alteraAdm")
	public String alterar(Long id, Model model) {
		Administrador adm = repository.findById(id).get();
		model.addAttribute("adm", adm);
		return "forward:cadAdm";
	}
	
	@RequestMapping("excluiAdm")
	public String excluir(Long id) {
		repository.deleteById(id);
		return "redirect:listaAdm/1";
		
	}
	
	@Publico
	@RequestMapping("login")
	public String login(Administrador admLogin, RedirectAttributes attr, HttpSession session) {
		//busca o adm no BD
		Administrador admin = repository.findByEmailAndSenha(admLogin.getEmail(), admLogin.getSenha());
		//verifica se os dados do adm existem
		if(admin == null) {
			attr.addFlashAttribute("mensagemErro", "Login e/ou senha inválido(s)");
			return "redirect:/";
		}else {
			//salva o adm na sessão
			session.setAttribute("usuarioLogado", admin);
			return "redirect:/listaAdm/1";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		// invalida a sessão
		session.invalidate();
		// voltar para a página inicial
		return "redirect:/";
	}
}
