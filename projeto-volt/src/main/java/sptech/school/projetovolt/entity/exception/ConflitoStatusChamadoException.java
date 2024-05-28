package sptech.school.projetovolt.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflitoStatusChamadoException extends RuntimeException{

    public ConflitoStatusChamadoException() {
        super(String.format("Erro 409: Status já atualizado como Cancelado ou Concluído"));
    }
}
