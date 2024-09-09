package sptech.school.projetovolt.service.clickproduto.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClickProdutoCriacaoDTO {
    private Integer idUsuario;
    private Integer idProduto;
    private LocalDateTime dataHoraClick = LocalDateTime.now();
    private Short possivelCompra = 0;
}
