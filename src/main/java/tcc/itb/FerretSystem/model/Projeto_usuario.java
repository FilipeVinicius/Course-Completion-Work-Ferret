package tcc.itb.FerretSystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="projeto_usuario")
public class Projeto_usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

   private long usuario_id;
   @OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
	    name = "projeto_usuario",
	    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id")
	    
	)
	private List<Usuario> usuarios = new ArrayList<>();	
   
   private long projeto_id;
   @OneToMany(fetch = FetchType.EAGER)
  	@JoinTable(
  	    name = "projeto_usuario",
  	    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  	    
  	)
   private List<Projeto> projetos = new ArrayList<>();
   
   private byte[] status_projeto;
   
public long getProjeto_id() {
	return projeto_id;
}
public void setProjeto_id(long projeto_id) {
	this.projeto_id = projeto_id;
}
public byte[] getStatus_projeto() {
	return status_projeto;
}
public void setStatus_projeto(byte[] status_projeto) {
	this.status_projeto = status_projeto;
}
public long getUsuario_id() {
	return usuario_id;
}
public void setUsuario_id(long usuario_id) {
	this.usuario_id = usuario_id;
}

}
