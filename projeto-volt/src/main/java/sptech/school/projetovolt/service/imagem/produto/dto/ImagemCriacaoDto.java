package sptech.school.projetovolt.service.imagem.produto.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ImagemCriacaoDto {
    private String nome;
    private byte[] codigoImagem;

}
