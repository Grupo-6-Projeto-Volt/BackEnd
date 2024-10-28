package sptech.school.projetovolt.service.clickproduto.dto;

import lombok.Data;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.service.usuario.dto.UsuarioClickConsultaDTO;

import java.time.LocalDateTime;

@Data
public class ClickProdutoConsultaDTO {
    private UsuarioClickConsultaDTO usuario;
    private ProdutoConsultaDTO produto;
    private LocalDateTime dataHoraClick;
    private Short possivelCompra;
}

