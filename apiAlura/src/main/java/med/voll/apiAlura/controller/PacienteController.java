package med.voll.apiAlura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.apiAlura.dto.DadosAtualizacaoPaciente;
import med.voll.apiAlura.dto.DadosCadastroPaciente;
import med.voll.apiAlura.dto.DadosDetalhamentoPaciente;
import med.voll.apiAlura.dto.DadosListagemPaciente;
import med.voll.apiAlura.model.Paciente;
import med.voll.apiAlura.repositories.PacienteRepository;
import med.voll.apiAlura.services.PacienteServices;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;
	
	@Autowired
	private PacienteServices service;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@Valid @RequestBody DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		repository.save(paciente);
		
		var uri = uriBuilder.path("medicos/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault Pageable paginacao){
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		
		if(this.service.isPacienteAtivo(id)) 
			return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@Valid @RequestBody DadosAtualizacaoPaciente dados) {
		Paciente paciente = repository.getReferenceById(dados.id());
		paciente.atualizar(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
	
	/*
	@DeleteMapping("/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}
	*/
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		paciente.excluir();
		return ResponseEntity.noContent().build();
	}
	
}
