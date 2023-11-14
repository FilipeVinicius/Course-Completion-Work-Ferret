package tcc.itb.FerretSystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoUsuarioRepository extends JpaRepository<ProjetoUsuario, Long> {
	
	List<ProjetoUsuario> findByUsuario(Usuario usuario);
	
	boolean existsByUsuarioAndProjeto(Usuario usuario, Projeto projeto);

}
