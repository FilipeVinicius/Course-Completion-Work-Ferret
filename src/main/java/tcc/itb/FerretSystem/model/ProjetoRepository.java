package tcc.itb.FerretSystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
	
	// select * from projeto where id IN (SELECT projeto_id FROM projeto_usuario where usuario_id = 1)
	
	List<Projeto> findByUsuario(Usuario usuario);

}
