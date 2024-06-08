package sptech.school.projetovolt.service.produtochamado;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.exception.ConflitoStatusChamadoException;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;
import sptech.school.projetovolt.entity.produtochamado.repository.ProdutoChamadoRepository;
import sptech.school.projetovolt.entity.vwchamadosgraficos.VwChamadosGraficos;
import sptech.school.projetovolt.entity.vwchamadosgraficos.repository.VwChamadosGraficosRepository;
import sptech.school.projetovolt.entity.vwmaisclicados.repository.VwMaisClicadosRepository;
import sptech.school.projetovolt.service.produto.ProdutoService;
import sptech.school.projetovolt.service.usuario.UsuarioService;
import sptech.school.projetovolt.utils.StatusChamado;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoChamadoService {

    private final ProdutoChamadoRepository produtoChamadoRepository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private final VwChamadosGraficosRepository vwChamadosGraficosRepository;
    public ProdutoChamado salvarProdutoChamado(Integer idUsuario, Integer idProduto) {

        ProdutoChamado produtoChamado = new ProdutoChamado();
        produtoChamado.setUsuario(usuarioService.buscarUsuarioPorId(idUsuario));
        produtoChamado.setProduto(produtoService.buscarProdutoPorId(idProduto));
        produtoChamado.setStatusChamado(StatusChamado.EM_ANDAMENTO.getId());
        produtoChamado.setDataHoraAbertura(LocalDateTime.now());

        return produtoChamadoRepository.save(produtoChamado);
    }

    public List<ProdutoChamado> listarProdutoChamados() {
        List<ProdutoChamado> produtoChamados = produtoChamadoRepository.findAll();

        return produtoChamados;
    }

    public ProdutoChamado buscarProdutoChamadoPorId(Integer id) {
        Optional<ProdutoChamado> produtoChamadoOpt = produtoChamadoRepository.findById(id);

        if (produtoChamadoOpt.isEmpty()) {
            throw new NotFoundException("Produto");
        }

        return produtoChamadoOpt.get();
    }

    public ProdutoChamado cancelarProdutoChamado(Integer id) {
        ProdutoChamado produtoChamado = buscarProdutoChamadoPorId(id);

        if (!produtoChamado.getStatusChamado().equals(StatusChamado.EM_ANDAMENTO.getId())) {
            throw new ConflitoStatusChamadoException();
        }

        produtoChamado.setStatusChamado(StatusChamado.CANCELADA.getId());
        produtoChamado.setDataHoraFechamento(LocalDateTime.now());

        return produtoChamadoRepository.save(produtoChamado);
    }

    public ProdutoChamado concluirProdutoChamado(Integer id) {
        ProdutoChamado produtoChamado = buscarProdutoChamadoPorId(id);

        if (!produtoChamado.getStatusChamado().equals(StatusChamado.EM_ANDAMENTO.getId())) {
            throw new ConflitoStatusChamadoException();
        }

        produtoChamado.setStatusChamado(StatusChamado.CONCLUIDA.getId());
        produtoChamado.setDataHoraFechamento(LocalDateTime.now());

        return produtoChamadoRepository.save(produtoChamado);
    }

    public List<ProdutoChamado> listarChamadosOrdenadosPorDataAberturaAsc() {
        return produtoChamadoRepository.findByOrderByDataHoraAberturaAsc();
    }

    public List<ProdutoChamado> listarChamadosOrdenadosPorDataAberturaDesc() {
        return produtoChamadoRepository.findByOrderByDataHoraAberturaDesc();
    }

    public List<VwChamadosGraficos> capturarChamadosCanceladosConcluidos(){
        return vwChamadosGraficosRepository.chamadosGraficoColuna();
    }
}
