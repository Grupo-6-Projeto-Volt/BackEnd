package sptech.school.projetovolt.service.graficoskpis.dto;

import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.vwcategoriasacessos.VwCategoriasAcessos;
import sptech.school.projetovolt.entity.vwchamadosgraficos.VwChamadosGraficos;
import sptech.school.projetovolt.entity.vwprodutosmaisacessados.VwProdutosMaisAcessados;
import sptech.school.projetovolt.entity.vwtaxaretorno.VwTaxaRetorno;
import sptech.school.projetovolt.entity.vwultimosacessossetedias.VwUltimosAcessosSeteDias;

import java.util.ArrayList;
import java.util.List;

public class GraficoKpisMapper {

    public static List<ChamadosGraficosDto> toChamadosGraficosDto(List<VwChamadosGraficos> entities){
        if(entities.isEmpty()) return null;
        List<ChamadosGraficosDto> dtos = new ArrayList<>();
        for (VwChamadosGraficos entity : entities) {
            ChamadosGraficosDto dto = new ChamadosGraficosDto();
            dto.setQuantidade(entity.getQtd());
            dto.setDia(entity.getDia());
            dto.setMes(entity.getMes());
            dto.setStatus(entity.getStatus());
            dtos.add(dto);
        }
        return dtos;
    }
    public static List<CategoriasGraficosDto> toCategoriasGraficosDto(List<VwCategoriasAcessos> entities){
        if(entities.isEmpty()) return null;
        List<CategoriasGraficosDto> dtos = new ArrayList<>();
        for (VwCategoriasAcessos entity : entities) {
            CategoriasGraficosDto dto = new CategoriasGraficosDto();
            dto.setAcessos(entity.getAcessos());
            dto.setCategoria(entity.getCategoria());
            dtos.add(dto);
        }
        return dtos;
    }

    public static List<ProdutosAcessadosDto> toProdutosAcessadosDto(List<VwProdutosMaisAcessados> entities){
        if(entities.isEmpty()) return null;
        List<ProdutosAcessadosDto> dtos = new ArrayList<>();
        for (VwProdutosMaisAcessados entity : entities) {
            ProdutosAcessadosDto dto = new ProdutosAcessadosDto();
            dto.setId(entity.getId());
            dto.setNome(entity.getNome());
            dto.setAcessos(entity.getAcessos());
            dto.setQuantidade(entity.getQtd());
            dto.setUrl(entity.getImg());
            dtos.add(dto);
        }
        return dtos;
    }

    public static UltimosAcessosDto toUltimosAcessosDto(List<VwUltimosAcessosSeteDias> entities){
        if(entities.isEmpty()) return null;
        UltimosAcessosDto dto = new UltimosAcessosDto();
        int acessosTotais = 0;
        for (VwUltimosAcessosSeteDias entity : entities) {
            acessosTotais = acessosTotais + entity.getQtd();
        }
        dto.setAcessos(acessosTotais);
        return dto;
    }

    public static TaxaRetornoDto toTaxaRetornoDto(List<VwTaxaRetorno> entities, List<Usuario> usuarios){
        if(entities.isEmpty() || usuarios.isEmpty()) return null;
        TaxaRetornoDto dto = new TaxaRetornoDto();
        double cliquesTotais = 0.0;
        for (VwTaxaRetorno entity : entities) {
            cliquesTotais = cliquesTotais + entity.getCliques();
        }

        double mediaCliquesUsuario = cliquesTotais/ usuarios.size();
        dto.setTaxaRetorno(mediaCliquesUsuario);
        return dto;
    }
    public static FaturamentoDto toFaturamentoDto(Double entity){
        if(entity == null) return null;
        FaturamentoDto dto = new FaturamentoDto();
        dto.setFaturamento(entity);
        return dto;
    }
}
