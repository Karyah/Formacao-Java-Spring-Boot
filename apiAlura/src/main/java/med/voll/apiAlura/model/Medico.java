package med.voll.apiAlura.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.apiAlura.dto.DadosAtualizacaoMedico;
import med.voll.apiAlura.dto.DadosCadastroMedico;

@Table(name="medicos")
@Entity(name="medico")	
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;
    
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    
    @Embedded
    private Endereco endereco;

	public Medico(DadosCadastroMedico dados) {
		super();
		this.ativo = true;
		this.nome = dados.nome();
		this.email =dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
	}
    
	public void atualizar(DadosAtualizacaoMedico dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		if(dados.email() != null) {
			this.email = dados.email();
		}
		if(dados.crm() != null) {
			this.crm = dados.crm();
		}
		if(dados.endereco() != null) {
			this.endereco.atualizar(dados.endereco());
		}
	}
	
	public void excluir() {
		this.ativo = false;
	}
    
}
