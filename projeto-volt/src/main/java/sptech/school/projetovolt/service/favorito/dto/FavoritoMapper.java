package sptech.school.projetovolt.service.favorito.dto;

import sptech.school.projetovolt.entity.favoritos.Favoritos;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.service.usuario.dto.UsuarioMapper;

import java.util.List;

public class FavoritoMapper {
    public static FavoritoConsultaDTO toDto(Favoritos entity){
        if(entity == null) return null;

        FavoritoConsultaDTO dto = new FavoritoConsultaDTO();
        dto.setId(entity.getId());
        dto.setUsuario(UsuarioMapper.toUsuarioConsultaDto(entity.getUsuario()));
        dto.setProduto(ProdutoMapper.toDto(entity.getProduto()));
        dto.setDtHoraInsercao(entity.getDtHoraInsercao());
        return dto;
    }
    public static List<FavoritoConsultaDTO> toDto(List<Favoritos> entities){
        return entities.stream().map(FavoritoMapper::toDto).toList();
    }

    public static Favoritos toEntity(FavoritoCriacaoDTO dto, Produto produto, Usuario usuario){
        if(dto == null) return null;

        Favoritos entity = new Favoritos();
        entity.setUsuario(usuario);
        entity.setProduto(produto);
        entity.setDtHoraInsercao(dto.getDtHoraInsercao());

        return entity;
    }
}
