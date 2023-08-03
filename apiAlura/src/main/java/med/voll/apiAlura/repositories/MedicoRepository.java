package med.voll.apiAlura.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.apiAlura.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
	Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
