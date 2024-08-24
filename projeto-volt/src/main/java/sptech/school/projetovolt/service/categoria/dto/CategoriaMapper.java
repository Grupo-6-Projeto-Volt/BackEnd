package sptech.school.projetovolt.service.categoria.dto;

import sptech.school.projetovolt.entity.categoria.Categoria;

import java.util.List;

public class CategoriaMapper {

    public static CategoriaConsultaDTO toDto(Categoria entity) {
        if (entity == null) return null;

        CategoriaConsultaDTO dto = new CategoriaConsultaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());

        return dto;
    }

    public static Categoria toEntity(CategoriaCriacaoDTO dto) {
        if (dto == null) return null;

        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());

        return entity;
    }

    public static List<CategoriaConsultaDTO> toDto(List<Categoria> entities) {
        if (entities == null) return null;

        return entities
                .stream()
                .map(CategoriaMapper::toDto).toList();
    }
}
