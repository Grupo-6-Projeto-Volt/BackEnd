package sptech.school.projetovolt.service.clickproduto.dto;

import sptech.school.projetovolt.entity.clickProduto.ClickProduto;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.vwmaisclicados.VwMaisClicados;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.service.usuario.dto.UsuarioMapper;

import java.util.List;

public class ClickProdutoMapper {
    public static ClickProdutoConsultaDTO toDto(ClickProduto entity) {
        if (entity == null) return null;

        ClickProdutoConsultaDTO dto = new ClickProdutoConsultaDTO();
        dto.setProduto(ProdutoMapper.toDto(entity.getProduto()));
        dto.setUsuario(UsuarioMapper.toUsuarioClickDTO(entity.getUsuario()));
        dto.setDataHoraClick(entity.getDataHoraClick());
        dto.setPossivelCompra(entity.getPossivelCompra());

        return dto;
    }

    public static List<ClickProdutoConsultaDTO> toDto(List<ClickProduto> entities) {
        return entities.stream().map(ClickProdutoMapper::toDto).toList();
    }

    public static ClickProduto toEntity(ClickProdutoCriacaoDTO dto, Produto produto, Usuario usuario) {
        if (dto == null) return null;

        ClickProduto entity = new ClickProduto();
        if (produto != null) {
            entity.setProduto(produto);
        }
        entity.setUsuario(usuario);
        entity.setDataHoraClick(dto.getDataHoraClick());
        entity.setPossivelCompra(dto.getPossivelCompra());

        return entity;
    }

    public static ClickProdutoMaisClicadosDTO vwToDto(VwMaisClicados view) {
        if (view == null) return null;

        ClickProdutoMaisClicadosDTO dto = new ClickProdutoMaisClicadosDTO();
        dto.setQtdClicks(view.getQtdClicks());
        dto.setNomeProduto(view.getNomeProduto());

        return dto;
    }

    public static List<ClickProdutoMaisClicadosDTO> vwToDto(List<VwMaisClicados> view) {
        return view.stream().map(ClickProdutoMapper::vwToDto).toList();
    }


}
