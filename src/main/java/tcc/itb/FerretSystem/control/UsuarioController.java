package tcc.itb.FerretSystem.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import tcc.itb.FerretSystem.model.Usuario;
import tcc.itb.FerretSystem.model.UsuarioRepository;
import tcc.itb.FerretSystem.service.UsuarioService;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
@RequestMapping("/usuario")
public class UsuarioController {
	

	final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService _usuarioService) {
		this.usuarioService = _usuarioService;
	}

	
	//GET
	
	@GetMapping("/list")
	
	public ResponseEntity<List<Usuario>> getAllUsuarios(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(usuarioService.fintAll());
	}
	
	//Insert (POST)
	
	@PostMapping
	public ResponseEntity<Object> saveUsuario(Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.save(usuario));
	}
	

	
	
	
	
}
