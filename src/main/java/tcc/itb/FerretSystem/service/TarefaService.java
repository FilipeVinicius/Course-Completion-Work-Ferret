package tcc.itb.FerretSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import jakarta.transaction.Transactional;
import tcc.itb.FerretSystem.model.Projeto;
import tcc.itb.FerretSystem.model.ProjetoRepository;
import tcc.itb.FerretSystem.model.Tarefa;
import tcc.itb.FerretSystem.model.TarefaRepository;
import tcc.itb.FerretSystem.model.UsuarioRepository;

@Service
public class TarefaService {
	
	
	private TarefaRepository tarefaRepository;
	
	private ProjetoRepository projetoRepository;
	
	
	
	
	
	public TarefaService(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository) {
		super();
		this.tarefaRepository = tarefaRepository;
		this.projetoRepository = projetoRepository;
	}

	@Transactional
    public void salvarTarefa(Tarefa tarefa) throws Exception {
		ModelAndView mv = new ModelAndView();
        try {
			tarefaRepository.save(tarefa);
		} catch (Exception e) {
			mv.addObject("msg", "erro");
			
		}
    }
	
	@Transactional
	public void save(String titulo, Projeto projeto) {


		System.out.println("\n**** tarefa: " + titulo + "\n");
		
		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo(titulo);
		tarefa.setProjeto(projeto);
		tarefa.setStatus_tarefa("Ativo");
		tarefaRepository.save(tarefa);
		
	}

	public List<Tarefa> findByProjetoId(long id) {
		Projeto projeto = projetoRepository.findById(id).get();
		return tarefaRepository.findByProjetoOrderByIdDesc(projeto);
	}
	
	public Tarefa buscarTarefaPorId(Long id) {
		Optional<Tarefa> opt = tarefaRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			// Aqui trataria com um erro Ex
		System.out.println("Erro");
		}
		return null;		
	}	
	
	public long removerTarefa(Long id){
		
		Tarefa tarefa = buscarTarefaPorId(id);
		
		long projetoId = tarefa.getProjeto().getId();
		
		Projeto projeto = projetoRepository.findById(projetoId).get();
		
		tarefaRepository.delete(tarefa);
		
		return projeto.getId();
	}
	
	public void editarTarefa(Tarefa tarefa) {
		tarefa.setTitulo(tarefa.getTitulo());
		tarefaRepository.save(tarefa);
	}

	
	
	
	

}
