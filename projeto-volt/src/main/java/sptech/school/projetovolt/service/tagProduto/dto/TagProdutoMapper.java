package sptech.school.projetovolt.service.tagProduto.dto;

import sptech.school.projetovolt.entity.tagProduto.TagProduto;

import java.util.List;

import java.util.ArrayList;
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

    public static List<TagProdutoConsultaDto> toDto(List<TagProduto> listaEntity){
        if(listaEntity == null) return null;

        List<TagProdutoConsultaDto> dtos = new ArrayList<>();
        for (TagProduto tagProduto : listaEntity) {
            dtos.add(TagProdutoMapper.toDto(tagProduto));
        }

        return dtos;
    }
}
