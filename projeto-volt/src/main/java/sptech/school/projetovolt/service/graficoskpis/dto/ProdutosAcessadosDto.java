package sptech.school.projetovolt.service.graficoskpis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutosAcessadosDto {
    private int id;
    private String nome;
    private int quantidade;
    private int acessos;
    private String url;
}
