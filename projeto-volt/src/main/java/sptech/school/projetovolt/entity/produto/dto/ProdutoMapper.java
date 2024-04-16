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

        return entity;
    }
}
