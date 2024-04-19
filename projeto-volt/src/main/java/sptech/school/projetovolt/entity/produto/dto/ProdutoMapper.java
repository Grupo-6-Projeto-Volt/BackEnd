package sptech.school.projetovolt.entity.produto.dto;

import sptech.school.projetovolt.entity.produto.Produto;

public class ProdutoMapper {

    public static ProdutoConsultaDTO toDto(Produto produto){
        if(produto == null) return null;

        ProdutoConsultaDTO dto = new ProdutoConsultaDTO();
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        dto.setQtdEstoque(produto.getQtdEstoque());
        dto.setEstadoGeral(produto.getEstadoGeral());

        return dto;
    }

    public static Produto toEntity(ProdutoCriacaoDTO dto){
        if(dto == null) return null;

        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setCategoria(dto.getCategoria());
        entity.setQtdEstoque(dto.getQtdEstoque());
        entity.setEstadoGeral(dto.getEstadoGeral());

        return entity;
    }

    public static Produto fromDtoAlteracaoToEntity(ProdutoAlteracaoDto dto){
        if(dto == null) return null;

        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setCategoria(dto.getCategoria());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setQtdEstoque(dto.getQtdEstoque());
        entity.setEstadoGeral(dto.getEstadoGeral());

        return entity;
    }
}
