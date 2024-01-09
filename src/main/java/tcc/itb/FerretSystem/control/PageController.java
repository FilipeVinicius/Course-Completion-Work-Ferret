package tcc.itb.FerretSystem.control;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring6.expression.Mvc;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import tcc.itb.FerretSystem.Exceptions.EmailExistsException;
import tcc.itb.FerretSystem.Exceptions.PasswordError;
import tcc.itb.FerretSystem.model.Projeto;
import tcc.itb.FerretSystem.model.ProjetoUsuario;
import tcc.itb.FerretSystem.model.ProjetoUsuarioRepository;
import tcc.itb.FerretSystem.model.Tarefa;
import tcc.itb.FerretSystem.model.TarefaRepository;
import tcc.itb.FerretSystem.model.Usuario;
import tcc.itb.FerretSystem.model.UsuarioRepository;
import tcc.itb.FerretSystem.service.ProjetoService;
import tcc.itb.FerretSystem.service.ProjetoUsuarioService;
import tcc.itb.FerretSystem.service.TarefaService;
import tcc.itb.FerretSystem.service.UsuarioService;

@Controller
@RequestMapping("/ferret")
public class PageController {

	private ProjetoService projetoService;

	private UsuarioService usuarioService;

	private String noImage = "/images/furao_neve_borda.jpg";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProjetoUsuarioRepository projetoUsuarioRepository;

	@Autowired
	private TarefaService tarefaService;

	public PageController(ProjetoService projetoService, UsuarioService usuarioService) {
		super();
		this.projetoService = projetoService;
		this.usuarioService = usuarioService;
	}

	// GetMapping
	@GetMapping("/TelaInicial")
	public ModelAndView TelaInicial() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("html/TelaInicial");
		return mv;
	}

	@GetMapping("/Login")
	public ModelAndView Login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("html/Login");
		return mv;
	}

	@GetMapping("/TelaPrincipal")
	public ModelAndView TelaPrincipal() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("html/TelaPrincipal");
		return mv;
	}

	@GetMapping("/CriarProjeto")
	public ModelAndView criarProjeto() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.addObject("projeto", new tcc.itb.FerretSystem.model.Projeto());
		mv.setViewName("html/CriarProjeto");
		return mv;
	}

//
//	
	@GetMapping("/ListaProjeto")
	public ModelAndView listaProjeto(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		// List<Projeto> projetos = projetoService.findAll(); // Recupera a lista de
		// projetos do banco de dados

		
		
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		 if (usuarioLogado == null) {
		        // Se o usuário não estiver logado, redirecione para a página inicial
		        mv.setViewName("redirect:/ferret/TelaInicial");
		        return mv;
		    }

		List<Projeto> projetos = projetoService.findAllUsuario(usuarioLogado.getId()); // Recupera a lista de projetos
																						// do banco de dados
		 
		
		
		mv.addObject("usuario", new Usuario());
		mv.addObject("projetos", projetos); // Adiciona a lista de projetos ao modelo
		mv.addObject("noImage", noImage);
		mv.setViewName("html/ListaProjeto");
		return mv;
	}

	@GetMapping("/Projeto/{id}")
	public ModelAndView Projeto(@PathVariable("id") long id, @RequestParam(required = false, name = "msg3") String msg3,
			@RequestParam(required = false, name = "msg2") String msg2,
			@RequestParam(required = false, name = "msg1") String msg1
			) {
		ModelAndView mv = new ModelAndView();

		Projeto projeto = projetoService.findById(id);

		List<Tarefa> tarefas = tarefaService.findByProjetoId(id);

		mv.addObject("tarefa", new Tarefa());

		mv.addObject("tarefas", tarefas);
		mv.addObject("projeto", projeto);
	    mv.addObject("usuario", new Usuario());
		mv.addObject("msg1", msg1);
		mv.addObject("msg2", msg2);
		mv.addObject("msg3", msg3);
		mv.addObject("noImage", noImage);
		mv.setViewName("html/Projeto");
		return mv;
	}

	@GetMapping("/Criar-Graficos")
	public ModelAndView CriarGraficos() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("html/Criar-Graficos");
		return mv;
	}

	@GetMapping("/Esquecisenha")
	public ModelAndView EsqueciSenha() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("html/Esquecisenha");
		return mv;
	}

	@GetMapping("/Cronograma")
	public ModelAndView Cronograma() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("html/Cronograma");
		return mv;
	}

	@GetMapping("/SobreNos")
	public ModelAndView SobreNos() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("html/SobreNos");
		return mv;
	}
	
	

//	@PostMapping("/salvarProjeto")
//	public ModelAndView salvarProjeto(@Validated @ModelAttribute("projeto") tcc.itb.FerretSystem.model.Projeto projeto, BindingResult result, HttpSession session) {
//	    // Verificar se há erros de validação
//	    if (result.hasErrors()) {
//	        // Tratar os erros de validação
//	        ModelAndView mv = new ModelAndView("html/CriarProjeto");
//	        mv.addObject("usuario", new Usuario());
//	        mv.addObject("projeto", projeto);
//	        return mv;
//	    }
//
//	    projeto.setStatus_projeto("ativo");
//	    Usuario usuarioLogado = null;
//	    
//	    if (projeto.getUsuarios() != null && !projeto.getUsuarios().isEmpty()) {
//	        List<Usuario> usuarios = projeto.getUsuarios();
//	        List<Usuario> usuariosCompletos = new ArrayList<>();
//	        for (Usuario usuario : usuarios) {
//	            Usuario usuarioCompleto = usuarioRepository.findById(usuario.getId()).orElse(null);
//	            if (usuarioCompleto != null) {
//	                usuariosCompletos.add(usuarioCompleto);
//	            }
//	        }
//
//	        // Obter o usuário logado a partir da sessão
//	        usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
//
//	        // Verificar se o usuário logado está disponível
//	        if (usuarioLogado != null) {
//	            // Buscar o usuário logado a partir do repositório utilizando o ID
//	        	Usuario usuarioLogadoCompleto = usuarioRepository.findById(usuarioLogado.getId()).orElse(null);
//
//	            // Verificar se o usuário logado foi encontrado
//	            if (usuarioLogadoCompleto != null) {
//	                usuariosCompletos.add(usuarioLogadoCompleto); // Adicionar o usuário logado à lista de usuários do projeto
//	            }
//	        }
//
//	        projeto.setUsuarios(usuariosCompletos); // Associar os usuários ao projeto
//	    }
//
//	   
//		tcc.itb.FerretSystem.model.Projeto projetoSalvo = projetoService.save(projeto, usuarioLogado.getId());
//
//	    ModelAndView mv = new ModelAndView("redirect:/ferret/ListaProjeto");
//	    mv.addObject("projetoAtivo", projetoSalvo);
//	    mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
//	    return mv;
//	}
//	
	@PostMapping("/salvarProj")
	public ModelAndView salvarProj(@RequestParam(value = "file", required = false) MultipartFile file,
			@Validated @ModelAttribute("projeto") Projeto projeto, BindingResult result, HttpSession session) {

		// Caso arquivo seja muito grande
		if (file.getSize() > 1048576) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("msg2", "Arquivo muito grande");
			mv.setViewName("html/CriarProjeto");
			return mv;
		}

		// Só aceitar imagem
		if (file.getSize() > 0 && !file.getContentType().startsWith("image/")) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("msg1", "Nesse campo só é aceito imagem");
			mv.setViewName("html/CriarProjeto");
			return mv;
		}

		// Verificar se há erros de validação

		if (result.hasErrors()) {
			// Tratar os erros de validação
			ModelAndView mv = new ModelAndView("redirect:/ferret/CriarProjeto");
			return mv;
		}

		// Setar como nulo no banco de dados
		if (file != null && !file.isEmpty()) {
			try {
				projeto.setFoto_projeto(file.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			projeto.setFoto_projeto(null);
		}

		projeto.setData_fim(null);
		projeto.setStatus_projeto("ativo");

		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

		Projeto projetoSalvo = projetoService.save(projeto, usuarioLogado.getId());

		ModelAndView mv = new ModelAndView("redirect:/ferret/ListaProjeto");
		mv.addObject("projetoAtivo", projetoSalvo);
		mv.addObject("usuarioLogado", session.getAttribute("usuarioLogado"));
		mv.setViewName("redirect:/ferret/ListaProjeto");
		return mv;

	}

	@PostMapping("/adicionarMembro")
	public String adicionarMembro(@RequestParam("email") String email, @RequestParam("projetoId") long projetoId) {
		ModelAndView mv = new ModelAndView();

		Projeto projeto_ = projetoService.findById(projetoId);
		int numeroMaximoMembros = projeto_.getQuant_membros();

		if (projeto_.getMembros().size() >= numeroMaximoMembros) {
			// O número máximo de membros foi atingido, então não adicione o novo membro
		      return "redirect:/ferret/Projeto/" + projetoId + "?msg1=O numero maximo de membros foi atingido";

		} else {

			try {
				// Consulte o banco de dados para obter o usuário com base no email
				Usuario usuario = usuarioService.findByEmail(email);
				
				if (usuario == null) {
				      return "redirect:/ferret/Projeto/" + projetoId + "?msg3=Usuario nao encontrado";

				}

				if (usuario.getNivel_acesso().equals("Membro")) {
					// Consulte o banco de dados para obter o projeto pelo ID
					Projeto projeto = projetoService.findById(projetoId);

					if (!projetoService.estaNoProjeto(usuario, projeto)) {
						// Crie uma nova instância de ProjetoUsuario
						ProjetoUsuario projetoUsuario = new ProjetoUsuario(projeto, usuario);

						// Salve a instância de ProjetoUsuario no banco de dados
						projetoUsuarioRepository.save(projetoUsuario);
						projeto.getMembros().add(projetoUsuario);
						projetoService.save(projeto);
					}

					return "redirect:/ferret/Projeto/" + projetoId; // Redireciona de volta ao formulário

				} else {
				      return "redirect:/ferret/Projeto/" + projetoId + "?msg2=Só é possível adicionar usuários cadastrados no App";

				}
			}

			catch (Exception e) {
				// Trate outros erros aqui, se necessário
				return "redirect:/ferret/Projeto/adicionarMembro?error=unknown";
			}
		}

	}

	@PostMapping("addTarefa")
	public String SalvarTarefa(@RequestParam("titulo") String titulo, @RequestParam("projetoId") long projetoId)
			throws Exception {

		Projeto projeto = projetoService.findById(projetoId);

		System.out.println("///// " + titulo);

		tarefaService.save(titulo, projeto);

		return "redirect:/ferret/Projeto/" + projetoId;

	}

	// Excluir tarefa
	@GetMapping("/excluir/{id}")
	public String ExcluirTarefa(Tarefa tarefa, @PathVariable("id") long id) {

		tarefa.setStatus_tarefa("Inativo");
		long projetoId = tarefaService.removerTarefa(id);

		return "redirect:/ferret/Projeto/" + projetoId;
	}

	@GetMapping("/editar/{id}")
	public String editarTarefa(@PathVariable Long id, Model model) {
		ModelAndView mv = new ModelAndView();

		Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
		mv.addObject("tarefa", tarefa);
		return "modalEditarTarefa"; // Página de edição da tarefa
	}

	// Editar tarefa
	@PostMapping("/editTarefa/{id}")
	public String SalvarTarefaEditada(@ModelAttribute("tarefa") Tarefa tarefa, @PathVariable("id") long id,
			@RequestParam("projetoId") long projetoId) {

		tarefaService.editarTarefa(tarefa);
		return "redirect:/ferret/Projeto/" + projetoId; // Redirecionar para a página principal

	}

	// Editar perfil do usuário
	/*
	 * @PostMapping("/editar/{id}") public String Editar(
	 * 
	 * @RequestParam(value = "file", required = false) MultipartFile file,
	 * 
	 * @PathVariable("id") long id, @ModelAttribute("usuario") Usuario usuario) {
	 * 
	 * 
	 * 
	 * 
	 * usuarioService.update(usuario);
	 * 
	 * 
	 * 
	 * return "redirect:/ferret/TelaPrincipal"; }
	 */

	@GetMapping("/encerrar/{id}")
	public String Encerrar(@PathVariable("id") long id, Projeto projeto) {
		ModelAndView mv = new ModelAndView();
		projeto = projetoService.findById(id);

		projeto.setStatus_projeto("inativo");
		projeto.setData_fim(LocalDate.now());
		projetoService.save(projeto);

		return "redirect:/ferret/ListaProjeto";
	}

	@PostMapping("salvarUsuario")
	public ModelAndView TelaInicial(Usuario usuario, BindingResult br) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			usuario.setStatus_usuario("ativo");
			usuario.setNivel_acesso("Gerente");
			usuarioService.salvarUsuario(usuario);
			mv.setViewName("redirect:/ferret/Login");
		} catch (EmailExistsException e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("html/TelaInicial");
		} catch (PasswordError e) {
			mv.addObject("msg", "Usuário já cadastrado!");
			mv.setViewName("html/TelaInicial");
		}

		return mv;
	}

	@PostMapping("/Login")
	public ModelAndView Login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());

		if (br.hasErrors()) {
			mv.setViewName("html/Login");
		}

		Usuario usuarioLogin = usuarioService.loginUser(usuario.getEmail(), usuario.getSenha());

		if (usuarioLogin == null) {
			mv.addObject("msg", "Email e/ou senha incorreto");
			mv.setViewName("html/Login");
		} else {
			session.setAttribute("usuarioLogado", usuarioLogin);
			return TelaPrincipal();
		}

		return mv;
	}

	@PostMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return TelaInicial();
	}

	@GetMapping("/showImage/{id}")
	@ResponseBody
	public void showImage(@PathVariable("id") long id, HttpServletResponse response, Projeto projeto)
			throws ServletException, IOException {

		projeto = projetoService.findById(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if (projeto.getFoto_projeto() != null) {
			response.getOutputStream().write(projeto.getFoto_projeto());
		} else {
			response.getOutputStream().write(null);
		}

		response.getOutputStream().close();
	}

}
