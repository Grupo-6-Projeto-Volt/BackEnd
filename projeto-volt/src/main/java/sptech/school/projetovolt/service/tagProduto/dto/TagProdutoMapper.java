package sptech.school.projetovolt.service.tagProduto.dto;

import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.utils.ListaObj;

import java.util.List;

import java.util.ArrayList;

public class TagProdutoMapper {
    public static TagProdutoConsultaDto toDto(TagProduto entity){
        if(entity == null) return null;

        TagProdutoConsultaDto dto = new TagProdutoConsultaDto();
        dto.setId(entity.getId());
        dto.setTag(entity.getTag());

        return dto;
    }

    public static TagProduto toEntity(TagProdutoCriacaoDto dto){
        if(dto == null) return null;

        TagProduto entity = new TagProduto();
        entity.setTag(dto.getTag());

        return entity;
    }

    public static List<TagProdutoConsultaDto> toDto(List<TagProduto> listaEntity){
        if(listaEntity == null) return null;

        return listaEntity.stream().map(TagProdutoMapper::toDto).toList();
    }
}
