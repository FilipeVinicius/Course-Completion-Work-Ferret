package tcc.itb.FerretSystem.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.pattern.Util;
import jakarta.transaction.Transactional;
import tcc.itb.FerretSystem.Exceptions.EmailExistsException;
import tcc.itb.FerretSystem.Exceptions.PasswordError;
import tcc.itb.FerretSystem.model.Usuario;
import tcc.itb.FerretSystem.model.UsuarioRepository;

@Service
public class UsuarioService {

	//objeto repository
	
	final UsuarioRepository usuarioRepository;
	
	
	//injeção de dependencia
	public UsuarioService(UsuarioRepository _usuarioRepository) {
		this.usuarioRepository = _usuarioRepository;
	}
	
	//select * from usuario
	public List<Usuario> fintAll(){
		return usuarioRepository.findAll();
	}
	
	
	//insert into produto
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	
	
	@Autowired
	private UsuarioRepository userRepository;
	
	public void salvarUsuario(Usuario usuario) throws Exception {
	    try {
	        if (userRepository.findByEmail(usuario.getEmail()) != null) {
	            throw new EmailExistsException("Email já cadastrado!");
	        }
	        usuario.setSenha((usuario.getSenha()));    
	    } catch (Exception e) {
	        throw new PasswordError("Usuário já cadastrado!");
	    }

	    userRepository.save(usuario);
	}
	
	public Usuario loginUser(String email, String senha) throws ServiceExc{
		
		Usuario usuarioLogin = userRepository.findLogin(email, senha);
		return usuarioLogin;
		
	}

	public Usuario findById(long id) {
		
		return usuarioRepository.findById(id).get();
	}
	
	public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
	
	public boolean existeUsuarioComEmail(String email) {
	    Usuario usuarioExistente =	usuarioRepository.findByEmail(email);
		return usuarioExistente != null;
	}
	
	public void update(Usuario usuario) {
		usuario = usuarioRepository.findById(usuario.getId()).get();
		
		usuario.setFoto(usuario.getFoto());
		usuario.setNome_usuario(usuario.getNome_usuario());		
		usuario.setSenha(usuario.getSenha());
		usuario.setTelefone(usuario.getTelefone());
		usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void update(MultipartFile file, Usuario usuario, byte[] foto) {
		
		if (file.getSize() == 0 && foto.length == 0) {
			usuario.setFoto(null);
		} 
		
		if (file.getSize() == 0 && foto.length > 0) {
			usuario.setFoto(foto);
		} 

		if (file != null && !file.isEmpty()) {
	        try {
	            foto = file.getBytes();
	            usuario.setFoto(foto);
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle the IOException (log it or return an error response)
	        }
	    }

	
		usuarioRepository.save(usuario);
	} 

}
