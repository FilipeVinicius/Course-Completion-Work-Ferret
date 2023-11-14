package tcc.itb.FerretSystem.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarefa")
public class Tarefa {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
@ManyToOne
    @JoinColumn(name = "projeto_id")
	private Projeto projeto;

	private String titulo;
	
	private String status_tarefa;

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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getStatus_tarefa() {
		return status_tarefa;
	}

	public void setStatus_tarefa(String status_tarefa) {
		this.status_tarefa = status_tarefa;
	}
	
	
}
