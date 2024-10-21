package sptech.school.projetovolt.service.produtochamado.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "Produto Chamado Consulta DTO", description = "DTO para consulta de um chamado de produto")
public class ProdutoChamadoConsultaDto {

    @Schema(description = "Id do chamado")
    private Integer id;

    @Schema(description = "Status do chamado")
    private Short statusChamado;

    @Schema(description = "Momento da abertura do chamado")
    private LocalDateTime dataHoraAbertura;

    @Schema(description = "Momento do fechamento do chamado")
    private LocalDateTime dataHoraFechamento;

    @Schema(description = "Produto relacionado ao chamado")
    private ProdutoDto produtoDto;

    @Schema(description = "Usuário relacionado ao chamado")
    private UsuarioDto usuarioDto;

    @Data
    public static class ProdutoDto {
        private String nome;
        private String descricao;
        private String categoria;
        private Double preco;
        private Integer qtdEstoque;
        private String estadoGeral;
        private Integer desconto;
    }

    @Data
    public static class UsuarioDto {
        private String nome;
        private String email;
        private String telefone;
        private Short categoria;
    }

}
