package sptech.school.projetovolt.service.clickproduto.dto;

import jakarta.persistence.Column;
import lombok.Data;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;

import java.time.LocalDateTime;

@Data
public class ClickProdutoConsultaDTO {
    private UsuarioConsultaDto usuario;
    private ProdutoConsultaDTO produto;
    private LocalDateTime dataHoraClick;
    private Short possivelCompra;
}

