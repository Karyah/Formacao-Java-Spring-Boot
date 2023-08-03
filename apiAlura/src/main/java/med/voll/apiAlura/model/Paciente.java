package med.voll.apiAlura.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.apiAlura.dto.DadosAtualizacaoPaciente;
import med.voll.apiAlura.dto.DadosCadastroPaciente;


@Table(name="pacientes")
@Entity(name="paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String rg;
	private Boolean ativo;
	
	@Embedded
	private Endereco endereco;

	public Paciente(DadosCadastroPaciente dados) {
		super();
		this.ativo = true;
		this.nome = dados.nome();
		this.email = dados.email();
		this.rg = dados.rg();
		this.endereco = new Endereco(dados.endereco());	
	}
	
	public void atualizar(DadosAtualizacaoPaciente dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.email() != null) {
			this.email = dados.email();
		}
		if(dados.rg() != null) {
			this.rg = dados.rg();
		}
		if(dados.endereco() != null) {
			this.endereco.atualizar(dados.endereco());
		}
	}
	
	public void excluir() {
		this.ativo = false;
	}
	
	
}
