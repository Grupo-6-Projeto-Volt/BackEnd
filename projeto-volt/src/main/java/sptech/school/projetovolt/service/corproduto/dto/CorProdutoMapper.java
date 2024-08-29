package sptech.school.projetovolt.service.corproduto.dto;

import sptech.school.projetovolt.entity.corProduto.CorProduto;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;

import java.util.List;

public class CorProdutoMapper {

    public static CorProdutoConsultaDTO toDto(CorProduto entity){
        if(entity == null) return null;

        CorProdutoConsultaDTO dto = new CorProdutoConsultaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setHexId(entity.getHexId());
        dto.setProduto(ProdutoMapper.toDto(entity.getProduto()));

        return dto;
    }

    public static List<CorProdutoConsultaDTO> toDto(List<CorProduto> entities){
        return entities.stream().map(CorProdutoMapper::toDto).toList();
    }

    public static CorProduto toEntity(CorProdutoCriacaoDTO dto){
        if(dto == null) return null;

        CorProduto entity = new CorProduto();
        entity.setNome(dto.getNome());
        entity.setHexId(dto.getHexId());

        return entity;
    }


}
