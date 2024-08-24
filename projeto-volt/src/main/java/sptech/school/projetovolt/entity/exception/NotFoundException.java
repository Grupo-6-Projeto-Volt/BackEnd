package sptech.school.projetovolt.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String menssagem) {
        super(String.format("Erro 404: %s não encontrado(a)!", menssagem));
    }

}
