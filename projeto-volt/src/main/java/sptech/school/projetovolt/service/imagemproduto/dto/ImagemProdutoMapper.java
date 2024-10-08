package sptech.school.projetovolt.service.imagemproduto.dto;

import sptech.school.projetovolt.entity.imagemproduto.ImagemProduto;
import sptech.school.projetovolt.entity.produto.Produto;

import java.util.List;

public class ImagemProdutoMapper {

    public static ImagemProduto toEntity(ImagemCriacaoDto dto) {
        if (dto == null) return null;

        ImagemProduto entity = new ImagemProduto();
        entity.setNome(dto.getNome());
        entity.setCodigoImagem(dto.getCodigoImagem());
        entity.setIndiceVt(dto.getIndiceVt());
        return entity;

    }

    public static ImagemProduto toEntity(ImagemAtualizacaoDto dto) {
        if (dto == null) return null;

        ImagemProduto entity = new ImagemProduto();
        entity.setNome(dto.getNome());
        entity.setCodigoImagem(dto.getCodigoImagem());
        entity.setIndiceVt(dto.getIndiceVt());
        return entity;

    }


    public static ImagemConsultaDto toDto(ImagemProduto entity) {
        if (entity == null) return null;

        ImagemConsultaDto dto = new ImagemConsultaDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigoImagem(entity.getCodigoImagem());
        dto.setIndiceVt(entity.getIndiceVt());
        dto.setProduto(toProdutoDto(entity.getProduto()));

        return dto;
    }

    public static List<ImagemConsultaDto> toDto(List<ImagemProduto> entities) {
        return entities.stream().map(ImagemProdutoMapper::toDto).toList();
    }

    public static ImagemConsultaDto.Produto toProdutoDto(Produto entity) {
        if (entity == null) return null;

        ImagemConsultaDto.Produto dto = new ImagemConsultaDto.Produto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCategoria(entity.getCategoria().getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        dto.setQtdEstoque(entity.getQtdEstoque());
        dto.setEstadoGeral(entity.getEstadoGeral());
        dto.setDesconto(entity.getDesconto());
        return dto;
    }

}
