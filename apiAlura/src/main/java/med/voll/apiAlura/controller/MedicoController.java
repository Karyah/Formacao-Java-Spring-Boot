package med.voll.apiAlura.controller;

import java.util.List;

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
import med.voll.apiAlura.model.*;
import med.voll.apiAlura.dto.DadosAtualizacaoMedico;
import med.voll.apiAlura.dto.DadosCadastroMedico;
import med.voll.apiAlura.dto.DadosDetalhamentoMedico;
import med.voll.apiAlura.dto.DadosListagemMedico;
import med.voll.apiAlura.repositories.MedicoRepository;
import med.voll.apiAlura.services.MedicoService;


@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@Autowired 
	private MedicoService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@Valid @RequestBody DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		repository.save(medico);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		
		var medico = repository.getReferenceById(id);
		
		if(this.service.isMedicoAtivo(id)) {
		  return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
		}
		return ResponseEntity.notFound().build();
			
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@Valid @RequestBody DadosAtualizacaoMedico dados) {
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizar(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	
	/* Exclusão normal
	@DeleteMapping("/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}
	*/
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();
		
		return ResponseEntity.noContent().build();
	}
	
}
