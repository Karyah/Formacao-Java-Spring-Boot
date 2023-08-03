package med.voll.apiAlura.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.apiAlura.model.Medico;
import med.voll.apiAlura.repositories.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository repository;
	
	public boolean isMedicoAtivo(Long id)	 {
		Medico medico = repository.getReferenceById(id);
		return medico.getAtivo();
	}
	
}
