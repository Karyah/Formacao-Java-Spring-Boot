package med.voll.apiAlura.dto;

import med.voll.apiAlura.model.Endereco;
import med.voll.apiAlura.model.Paciente;

public record DadosDetalhamentoPaciente(
		Long id, 
		String nome, 
		String email,
		String rg,
		Endereco endereco
		) {
	
	public DadosDetalhamentoPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(),
			paciente.getRg(), paciente.getEndereco());
	}

}
