package tcc.itb.FerretSystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

	@Repository
	public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

		List<Tarefa> findByProjetoOrderByIdDesc(Projeto projeto);
		
		Projeto findByProjeto(Projeto projeto);

}
