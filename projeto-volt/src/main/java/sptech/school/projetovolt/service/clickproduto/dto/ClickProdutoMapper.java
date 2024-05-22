package sptech.school.projetovolt.service.clickproduto.dto;

import sptech.school.projetovolt.entity.clickProduto.ClickProduto;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.service.usuario.dto.UsuarioMapper;

public class ClickProdutoMapper {
    public static ClickProdutoConsultaDTO toDto(ClickProduto entity){
        if(entity == null) return null;

        ClickProdutoConsultaDTO dto = new ClickProdutoConsultaDTO();
        dto.setProduto(ProdutoMapper.toDto(entity.getProduto()));
        dto.setUsuario(UsuarioMapper.toUsuarioConsultaDto(entity.getUsuario()));
        dto.setDataHoraClick(entity.getDataHoraClick());
        dto.setPossivelCompra(entity.getPossivelCompra());

        return dto;
    }

    public static ClickProduto toEntity(ClickProdutoCriacaoDTO dto, Produto produto, Usuario usuario){
        if(dto == null) return null;

        ClickProduto entity = new ClickProduto();
        entity.setProduto(produto);
        entity.setUsuario(usuario);
        entity.setDataHoraClick(dto.getDataHoraClick());
        entity.setPossivelCompra(dto.getPossivelCompra());

        return entity;
    }
}
