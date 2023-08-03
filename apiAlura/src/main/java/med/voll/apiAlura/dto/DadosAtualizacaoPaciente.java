package med.voll.apiAlura.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.apiAlura.model.Endereco;
import med.voll.apiAlura.model.Paciente;

public record DadosAtualizacaoPaciente(
		@NotNull
		Long id, 
		String nome,
		String email,
		String rg,
		DadosEndereco endereco
		) {
	

}
