package med.voll.apiAlura.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.apiAlura.model.Especialidade;

public record DadosAtualizacaoMedico(
		@NotNull
		Long id, 
		
		String nome, 
		String email,
		String telefone,
		String crm, 
		Especialidade especialidade,
		DadosEndereco endereco) {

}
