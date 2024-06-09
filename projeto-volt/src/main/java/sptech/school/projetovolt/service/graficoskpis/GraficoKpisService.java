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

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraficoKpisService {
    private final VwChamadosGraficosRepository vwChamadosGraficosRepository;
    private final VwCategoriasAcessosRepository vwCategoriasAcessosRepository;
    private final VwProdutosMaisAcessadosRepository vwProdutosMaisAcessadosRepository;
    private final VwUltimosAcessosRepository vwUltimosAcessosRepository;
    private final VwTaxaRetornoRepository vwTaxaRetornoRepository;
    private final UsuarioService usuarioService;

    public List<VwChamadosGraficos> capturarChamadosCanceladosConcluidos(){
        return vwChamadosGraficosRepository.chamadosCanceladosConcluidos();
    }
    public List<VwCategoriasAcessos> capturarCategoriasMaisAcessadas(){
        return vwCategoriasAcessosRepository.categoriasMaisAcessadas();
    }
    public List<VwProdutosMaisAcessados> capturarProdutosMaisAcessados(){
        return vwProdutosMaisAcessadosRepository.produtosMaisAcessados();
    }
    public List<VwUltimosAcessosSeteDias> capturarAcessosUltimosSeteDias(){
        return vwUltimosAcessosRepository.ultimosAcessosNosSeteDias();
    }

    public List<VwTaxaRetorno> capturarTaxaDeRetorno(){
        return vwTaxaRetornoRepository.taxaDeRetorno();
    }
}
