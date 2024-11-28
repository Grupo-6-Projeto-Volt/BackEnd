package sptech.school.projetovolt.service.graficoskpis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.vwcategoriasacessos.VwCategoriasAcessos;
import sptech.school.projetovolt.entity.vwcategoriasacessos.repository.VwCategoriasAcessosRepository;
import sptech.school.projetovolt.entity.vwchamadosgraficos.VwChamadosGraficos;
import sptech.school.projetovolt.entity.vwchamadosgraficos.repository.VwChamadosGraficosRepository;
import sptech.school.projetovolt.entity.vwprodutosmaisacessados.VwProdutosMaisAcessados;
import sptech.school.projetovolt.entity.vwprodutosmaisacessados.repository.VwProdutosMaisAcessadosRepository;
import sptech.school.projetovolt.entity.vwtaxaretorno.VwTaxaRetorno;
import sptech.school.projetovolt.entity.vwtaxaretorno.repository.VwTaxaRetornoRepository;
import sptech.school.projetovolt.entity.vwultimosacessossetedias.VwUltimosAcessosSeteDias;
import sptech.school.projetovolt.entity.vwultimosacessossetedias.repository.VwUltimosAcessosRepository;
import sptech.school.projetovolt.service.usuario.UsuarioService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GraficoKpisService {
    private final VwChamadosGraficosRepository vwChamadosGraficosRepository;
    private final VwCategoriasAcessosRepository vwCategoriasAcessosRepository;
    private final VwProdutosMaisAcessadosRepository vwProdutosMaisAcessadosRepository;
    private final VwUltimosAcessosRepository vwUltimosAcessosRepository;
    private final VwTaxaRetornoRepository vwTaxaRetornoRepository;

    public List<VwChamadosGraficos> capturarChamadosCanceladosConcluidos(){
        return vwChamadosGraficosRepository.chamadosCanceladosConcluidos();
    }
    public List<VwCategoriasAcessos> capturarCategoriasMaisAcessadas(LocalDate dataInicio, LocalDate dataFim){
        return vwCategoriasAcessosRepository.categoriasMaisAcessadas(dataInicio, dataFim);
    }
    public List<VwProdutosMaisAcessados> capturarProdutosMaisAcessados(LocalDate dataInicio, LocalDate dataFim){
        return vwProdutosMaisAcessadosRepository.produtosMaisAcessados(dataInicio, dataFim);
    }
    public List<VwUltimosAcessosSeteDias> capturarAcessosUltimosSeteDias(LocalDate dataInicio, LocalDate dataFim){
        return vwUltimosAcessosRepository.ultimosAcessosNosSeteDias(dataInicio, dataFim);
    }

    public List<VwTaxaRetorno> capturarTaxaDeRetorno(LocalDate dataInicio, LocalDate dataFim){
        return vwTaxaRetornoRepository.taxaDeRetorno(dataInicio, dataFim);
    }
}
