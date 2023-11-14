package tcc.itb.FerretSystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "projeto")
public class Projeto {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;	
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(
//	    name = "projeto_usuario",
//	    joinColumns = @JoinColumn(name = "projeto_id", referencedColumnName = "id"),
//	    inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id")
//	)
//	private List<Usuario> usuarios = new ArrayList<>();	
	
	@OneToMany(mappedBy = "projeto")
	private List<ProjetoUsuario> membros = new ArrayList<>();
	
	public List<ProjetoUsuario> getMembros() {
		return membros;
	}
	public void setMembros(List<ProjetoUsuario> membros) {
		this.membros = membros;
	}
	
	private String titulo;
	private int quant_membros;
	private String descricao;
	private LocalDate data_inicio;
	private LocalDate data_fim;
	private String status_projeto;
	private byte[] foto_projeto;	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
//	public List<Usuario> getUsuarios() {
//	    return usuarios;
//	}
//
//	public void setUsuarios(List<Usuario> usuarios) {
//	    this.usuarios = usuarios;
//	}
	public int getQuant_membros() {
		return quant_membros;
	}
	public void setQuant_membros(int quant_membros) {
		this.quant_membros = quant_membros;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(LocalDate data_inicio) {
		this.data_inicio = data_inicio;
	}
	public LocalDate getData_fim() {
		return data_fim;
	}
	public void setData_fim(LocalDate data_fim) {
		this.data_fim = data_fim;
	}
	public String getStatus_projeto() {
		return status_projeto;
	}
	public void setStatus_projeto(String status_projeto) {
		this.status_projeto = status_projeto;
	}
	public byte[] getFoto_projeto() {
		return foto_projeto;
	}
	public void setFoto_projeto(byte[] foto_projeto) {
		this.foto_projeto = foto_projeto;
	}
	
	
	
	
	

}
