package sptech.school.projetovolt.service.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaConsultaDTO {

    private Integer id;

    private String nome;
}
