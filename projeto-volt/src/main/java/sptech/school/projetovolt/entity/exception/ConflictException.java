package sptech.school.projetovolt.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException(String menssagem) {
        super(String.format("Erro 409: %s com valor duplicado!", menssagem));
    }

}
