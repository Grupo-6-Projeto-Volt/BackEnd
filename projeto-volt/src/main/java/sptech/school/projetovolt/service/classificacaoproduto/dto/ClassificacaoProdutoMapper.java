package sptech.school.projetovolt.service.classificacaoproduto.dto;

import sptech.school.projetovolt.entity.classificacaoproduto.ClassificacaoProduto;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;

import java.util.List;

public class ClassificacaoProdutoMapper {

    public static ClassificacaoProdutoConsultaDto toDto(ClassificacaoProduto entity) {
        if (entity == null) return null;

        ClassificacaoProdutoConsultaDto dto = new ClassificacaoProdutoConsultaDto();
        dto.setId(entity.getId());
        dto.setProduto(toProdutoDto(entity.getProduto()));
        dto.setTag(toTagProdutoDto(entity.getTagProduto()));
        return dto;
    }

    public static List<ClassificacaoProdutoConsultaDto> toDto(List<ClassificacaoProduto> entities) {
        return entities.stream().map(ClassificacaoProdutoMapper::toDto).toList();
    }

    public static ClassificacaoProdutoConsultaDto.Produto toProdutoDto(Produto entity) {
        if (entity == null) return null;

        ClassificacaoProdutoConsultaDto.Produto dto = new ClassificacaoProdutoConsultaDto.Produto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCategoria(entity.getCategoria());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        dto.setQtdEstoque(entity.getQtdEstoque());
        dto.setEstadoGeral(entity.getEstadoGeral());
        dto.setDesconto(entity.getDesconto());
        return dto;
    }

    public static ClassificacaoProdutoConsultaDto.TagProduto toTagProdutoDto(TagProduto entity) {
        if (entity == null) return null;

        ClassificacaoProdutoConsultaDto.TagProduto dto = new ClassificacaoProdutoConsultaDto.TagProduto();
        dto.setId(entity.getId());
        dto.setTag(entity.getTag());
        return dto;
    }

}
