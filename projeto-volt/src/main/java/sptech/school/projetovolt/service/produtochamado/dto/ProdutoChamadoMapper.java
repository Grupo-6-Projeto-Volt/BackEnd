package sptech.school.projetovolt.service.produtochamado.dto;

import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;

import java.util.List;

public class ProdutoChamadoMapper {

    public static ProdutoChamadoConsultaDto toDto(ProdutoChamado produtoChamado) {
        if (produtoChamado == null) return null;

        ProdutoChamadoConsultaDto dto = new ProdutoChamadoConsultaDto();
        dto.setId(produtoChamado.getId());
        dto.setStatusChamado(produtoChamado.getStatusChamado());
        dto.setDataHoraAbertura(produtoChamado.getDataHoraAbertura());
        dto.setDataHoraFechamento(produtoChamado.getDataHoraFechamento());;

        ProdutoChamadoConsultaDto.UsuarioDto usuarioDto = new ProdutoChamadoConsultaDto.UsuarioDto();
        usuarioDto.setNome(produtoChamado.getUsuario().getNome());
        usuarioDto.setEmail(produtoChamado.getUsuario().getEmail());
        usuarioDto.setTelefone(produtoChamado.getUsuario().getTelefone());
        usuarioDto.setCategoria(produtoChamado.getUsuario().getCategoria());

        ProdutoChamadoConsultaDto.ProdutoDto produtoDto = new ProdutoChamadoConsultaDto.ProdutoDto();
        produtoDto.setNome(produtoChamado.getProduto().getNome());
        produtoDto.setDescricao(produtoChamado.getProduto().getDescricao());
        produtoDto.setCategoria(produtoChamado.getProduto().getCategoria().getNome());
        produtoDto.setPreco(produtoChamado.getProduto().getPreco());
        produtoDto.setQtdEstoque(produtoChamado.getProduto().getQtdEstoque());
        produtoDto.setEstadoGeral(produtoChamado.getProduto().getEstadoGeral());
        produtoDto.setDesconto(produtoChamado.getProduto().getDesconto());

        dto.setUsuarioDto(usuarioDto);
        dto.setProdutoDto(produtoDto);

        return dto;
    }

    public static List<ProdutoChamadoConsultaDto> toDto(List<ProdutoChamado> entities) {
        if (entities == null) return null;

        return entities
                .stream()
                .map(ProdutoChamadoMapper::toDto).toList();
    }
}
