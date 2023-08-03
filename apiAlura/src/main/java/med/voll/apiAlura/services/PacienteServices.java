package med.voll.apiAlura.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import med.voll.apiAlura.repositories.PacienteRepository;

@Service
public class PacienteServices {
	
	@Autowired
	private PacienteRepository repository;
	
	public boolean isPacienteAtivo(Long id) {
		var paciente = repository.getReferenceById(id);
		return paciente.getAtivo();
	}
}
