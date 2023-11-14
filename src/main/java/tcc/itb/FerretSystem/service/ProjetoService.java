package tcc.itb.FerretSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tcc.itb.FerretSystem.model.Projeto;
import tcc.itb.FerretSystem.model.ProjetoRepository;
import tcc.itb.FerretSystem.model.ProjetoUsuario;
import tcc.itb.FerretSystem.model.ProjetoUsuarioRepository;
import tcc.itb.FerretSystem.model.Usuario;
import tcc.itb.FerretSystem.model.UsuarioRepository;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    
    private final UsuarioRepository usuarioRepository;
    
    private final ProjetoUsuarioRepository projetoUsuarioRepository;

	public ProjetoService(ProjetoRepository projetoRepository, UsuarioRepository usuarioRepository,
			ProjetoUsuarioRepository projetoUsuarioRepository) {
		super();
		this.projetoRepository = projetoRepository;
		this.usuarioRepository = usuarioRepository;
		this.projetoUsuarioRepository = projetoUsuarioRepository;
	}

	public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }
	
	public List<Projeto> findAllUsuario(long id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		return projetoRepository.findByUsuario(usuario);
    }
	
	public boolean estaNoProjeto(Usuario usuario, Projeto projeto) {
		return projetoUsuarioRepository.existsByUsuarioAndProjeto(usuario, projeto);
    }

	public List<Projeto> findAll(long usuario_id) {
		
		Usuario usuario = usuarioRepository.findById(usuario_id).get();
		
        return projetoRepository.findAll();
    }
	
    public Projeto findById(Long id) {
        return projetoRepository.findById(id).orElse(null);
    }
    
   
    @Transactional
    public Projeto save(Projeto projeto) {
        return projetoRepository.save(projeto);
    }
    
    @Transactional
    public Projeto save(Projeto projeto, long usuario_id) {
    	
    	Usuario usuario = usuarioRepository.findById(usuario_id).get();
    	
    	projeto.setUsuario(usuario);
    	    	
    	Projeto p = projetoRepository.save(projeto);
    	
    	ProjetoUsuario pu = new ProjetoUsuario(p, usuario);
    	
    	projetoUsuarioRepository.save(pu);
    	
        return p;
    }

    @Transactional
    public long deleteById(Long id) {
		Projeto projeto = projetoRepository.findById(id).get();

    	projetoRepository.delete(projeto);
        projetoRepository.deleteById(id);
        
        return projeto.getId();
        
    }
}

