package tcc.itb.FerretSystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "projeto_usuario")
public class ProjetoUsuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;

    private int status_usuario;
    
	public ProjetoUsuario() {

	}

	public ProjetoUsuario(Projeto projeto, Usuario usuario) {
		super();
		this.projeto = projeto;
		this.usuario = usuario;
		this.status_usuario = 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getStatus_usuario() {
		return status_usuario;
	}

	public void setStatus_usuario(int status_usuario) {
		this.status_usuario = status_usuario;
	}  
 
}



