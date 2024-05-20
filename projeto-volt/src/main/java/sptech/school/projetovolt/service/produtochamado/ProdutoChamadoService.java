package sptech.school.projetovolt.service.produtochamado;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;
import sptech.school.projetovolt.entity.produtochamado.repository.ProdutoChamadoRepository;
import sptech.school.projetovolt.service.produto.ProdutoService;
import sptech.school.projetovolt.service.usuario.UsuarioService;
import sptech.school.projetovolt.utils.StatusChamado;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoChamadoService {

    private final ProdutoChamadoRepository produtoChamadoRepository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    public ProdutoChamado salvarProdutoChamado(Integer idUsuario, Integer idProduto) {

        ProdutoChamado produtoChamado = new ProdutoChamado();
        produtoChamado.setUsuario(usuarioService.buscarUsuarioPorId(idUsuario));
        produtoChamado.setProduto(produtoService.buscarProdutoPorId(idProduto));
        produtoChamado.setStatusChamado(StatusChamado.EM_ANDAMENTO.getId());
        produtoChamado.setDataHoraAbertura(LocalDate.now());

        return produtoChamadoRepository.save(produtoChamado);
    }

    public List<ProdutoChamado> listarProdutoChamados() {
        List<ProdutoChamado> produtoChamados = produtoChamadoRepository.findAll();

        return produtoChamados;
    }

    public ProdutoChamado cancelarProdutoChamado(Integer id) {
        Optional<ProdutoChamado> produtoChamadoOpt = produtoChamadoRepository.findById(id);

        if (produtoChamadoOpt.isEmpty()) {
            throw new NotFoundException("Produto");
        }

        produtoChamadoOpt.get().setStatusChamado(StatusChamado.CANCELADA.getId());
        produtoChamadoOpt.get().setDataHoraAbertura(LocalDate.now());

        return produtoChamadoRepository.save(produtoChamadoOpt.get());
    }

    public ProdutoChamado concluirProdutoChamado(Integer id) {
        Optional<ProdutoChamado> produtoChamadoOpt = produtoChamadoRepository.findById(id);

        if (produtoChamadoOpt.isEmpty()) {
            throw new NotFoundException("Produto");
        }

        produtoChamadoOpt.get().setStatusChamado(StatusChamado.CONCLUIDA.getId());
        produtoChamadoOpt.get().setDataHoraAbertura(LocalDate.now());

        return produtoChamadoRepository.save(produtoChamadoOpt.get());
    }
}
