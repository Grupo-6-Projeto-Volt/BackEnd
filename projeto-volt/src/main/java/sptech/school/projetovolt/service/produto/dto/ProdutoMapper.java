package sptech.school.projetovolt.service.produto.dto;

import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.utils.ListaObj;

import java.util.ArrayList;
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

        return dto;
    }

    public static List<ProdutoConsultaDTO> toDto(List<Produto> produtos) {
        if(produtos == null) return null;
        return produtos.stream().map(ProdutoMapper::toDto).toList();
    }

    /*
    * FIXME: Método não necessário, visto que a listaObj não se aplica à nossa API, mas sim à API externa.
    *  Remover esse método após a migração dessa função, se necessário, para a API externa.
    * */

//    public static ListaObj<ProdutoConsultaDTO> toDto(ListaObj<Produto> produtos){
//        if(produtos == null) return null;
//
//        ListaObj<ProdutoConsultaDTO> dtos = new ListaObj<>(produtos
//                .size());
//
//        for (Produto produto : produtos.getArr()) {
//            ProdutoConsultaDTO dto = new ProdutoConsultaDTO();
//            dto.setNome(produto.getNome());
//            dto.setDescricao(produto.getDescricao());
//            dto.setPreco(produto.getPreco());
//            dto.setCategoria(produto.getCategoria());
//            dto.setQtdEstoque(produto.getQtdEstoque());
//            dto.setEstadoGeral(produto.getEstadoGeral());
//            dto.setDesconto(produto.getDesconto());
//
//            dtos.add(dto);
//        }
//
//
//        return dtos;
//    }

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
}
