package med.voll.apiAlura.infra.exception;

import org.springframework.validation.FieldError;

public record DadosErroValidacao(String campo, String mensagem) {
	public DadosErroValidacao(FieldError fe) {
		this(fe.getField(), fe.getDefaultMessage());
	}
}
