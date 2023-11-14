package tcc.itb.FerretSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcc.itb.FerretSystem.model.ProjetoUsuario;
import tcc.itb.FerretSystem.model.ProjetoUsuarioRepository;

@Service
public class ProjetoUsuarioService {
	 private final ProjetoUsuarioRepository projetoUsuarioRepository;

	    @Autowired
	    public ProjetoUsuarioService(ProjetoUsuarioRepository projetoUsuarioRepository) {
	        this.projetoUsuarioRepository = projetoUsuarioRepository;
	    }

	    public ProjetoUsuario save(ProjetoUsuario projetoUsuario) {
	        return projetoUsuarioRepository.save(projetoUsuario);
	    }
}
