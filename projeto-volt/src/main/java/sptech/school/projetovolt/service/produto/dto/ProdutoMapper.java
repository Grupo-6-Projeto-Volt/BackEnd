package sptech.school.projetovolt.service.produto.dto;

import sptech.school.projetovolt.entity.imagemproduto.ImagemProduto;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;

import java.util.List;

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
        dto.setDesconto(produto.getDesconto());
        dto.setImagensProduto(toImagemProdutoDto(produto.getImagensProduto()));
        dto.setTagsProduto(toTagProdutoDto(produto.getTags()));

        return dto;
    }

    public static List<ProdutoConsultaDTO> toDto(List<Produto> produtos) {
        if(produtos == null) return null;
        return produtos.stream().map(ProdutoMapper::toDto).toList();
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
        entity.setDesconto(dto.getDesconto());

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
        entity.setDesconto(dto.getDesconto());

        return entity;
    }

    public static ProdutoConsultaDTO.ImagemProduto toImagemProdutoDto(ImagemProduto entity) {
        if (entity == null) return null;

        ProdutoConsultaDTO.ImagemProduto dto = new ProdutoConsultaDTO.ImagemProduto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigoImagem(entity.getCodigoImagem());

        return dto;
    }

    public static List<ProdutoConsultaDTO.ImagemProduto> toImagemProdutoDto(List<ImagemProduto> entities) {
        return entities.stream().map(ProdutoMapper::toImagemProdutoDto).toList();
    }

    public static ProdutoConsultaDTO.TagProduto toTagProdutoDto(TagProduto entity) {
        if (entity == null) return null;

        ProdutoConsultaDTO.TagProduto dto = new ProdutoConsultaDTO.TagProduto();
        dto.setId(entity.getId());
        dto.setTag(entity.getTag());

        return dto;
    }

    public static List<ProdutoConsultaDTO.TagProduto> toTagProdutoDto(List<TagProduto> entities) {
        return entities.stream().map(ProdutoMapper::toTagProdutoDto).toList();
    }
}
