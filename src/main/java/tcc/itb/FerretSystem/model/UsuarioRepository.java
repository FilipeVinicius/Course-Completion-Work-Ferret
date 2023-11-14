package tcc.itb.FerretSystem.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select i from Usuario i where i.email = :email")
	public Usuario findByEmail(String email);
	
	@Query("select j from Usuario j where j.email = :email and j.senha = :senha")
	public Usuario findLogin(String email, String senha);
	
}
