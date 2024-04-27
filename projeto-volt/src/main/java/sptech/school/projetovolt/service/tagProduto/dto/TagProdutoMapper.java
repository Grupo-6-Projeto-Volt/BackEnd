package sptech.school.projetovolt.service.tagProduto.dto;

import sptech.school.projetovolt.entity.tagProduto.TagProduto;

import java.util.List;

public class TagProdutoMapper {
    public static TagProdutoConsultaDto toDto(TagProduto entity){
        if(entity == null) return null;

        TagProdutoConsultaDto dto = new TagProdutoConsultaDto();
        dto.setTag(entity.getTag());

        return dto;
    }

    public static TagProduto toEntity(TagProdutoCriacaoDto dto){
        if(dto == null) return null;

        TagProduto entity = new TagProduto();
        entity.setTag(dto.getTag());

        return entity;
    }

    public static List<TagProdutoConsultaDto> toDto(List<TagProduto> entities){
        if (entities.isEmpty()) return null;

        return entities
                .stream()
                .map(TagProdutoMapper::toDto).toList();
    }
}
