package sptech.school.projetovolt.entity.tag.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TagProdutoCriacaoDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String tag;
}
