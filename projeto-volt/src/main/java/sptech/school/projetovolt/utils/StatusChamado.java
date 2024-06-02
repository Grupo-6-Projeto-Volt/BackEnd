package sptech.school.projetovolt.utils;

import lombok.Getter;
@Getter
public enum StatusChamado {

    EM_ANDAMENTO((short) 0, "Em andamento"),
    CANCELADA((short) 1, "Cancelada"),
    CONCLUIDA((short) 2, "Concluída");

    private Short id;
    private String status;
    StatusChamado(Short id, String status) {
        this.id = id;
        this.status = status;
    }

}
