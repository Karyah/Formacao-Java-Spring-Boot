package med.voll.apiAlura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import med.voll.apiAlura.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	UserDetails findByLogin(String login);
}
