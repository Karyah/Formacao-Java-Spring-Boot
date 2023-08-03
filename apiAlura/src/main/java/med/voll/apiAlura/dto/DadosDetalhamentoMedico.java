package med.voll.apiAlura.dto;

import med.voll.apiAlura.model.Endereco;
import med.voll.apiAlura.model.Especialidade;
import med.voll.apiAlura.model.Medico;

public record DadosDetalhamentoMedico(
		Long id,
		String nome,
		String email,
		String telefone, 
		String crm, 
		Especialidade especialidade,
		Endereco endereco) {
	
	public DadosDetalhamentoMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(),
				medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
	}
}
