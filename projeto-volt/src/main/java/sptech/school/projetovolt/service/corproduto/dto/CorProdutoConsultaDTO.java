package sptech.school.projetovolt.service.corproduto.dto;

import lombok.Data;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;

@Data
public class CorProdutoConsultaDTO {
    private Integer id;
    private String nome;
    private String hexId;
    private ProdutoConsultaDTO produto;

}
