package sptech.school.projetovolt.service.produtochamado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sptech.school.projetovolt.entity.exception.ConflitoStatusChamadoException;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;
import sptech.school.projetovolt.entity.produtochamado.repository.ProdutoChamadoRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.produto.ProdutoService;
import sptech.school.projetovolt.service.usuario.UsuarioService;
import sptech.school.projetovolt.utils.StatusChamado;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoChamadoServiceTest {

    @InjectMocks
    private ProdutoChamadoService produtoChamadoService;

    @Mock
    private ProdutoChamadoRepository produtoChamadoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ProdutoService produtoService;

    private Usuario usuario;
    private Produto produto;
    private ProdutoChamado produtoChamado;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);

        produto = new Produto();
        produto.setId(1);

        produtoChamado = new ProdutoChamado();
        produtoChamado.setId(1);
        produtoChamado.setUsuario(usuario);
        produtoChamado.setProduto(produto);
        produtoChamado.setStatusChamado(StatusChamado.EM_ANDAMENTO.getId());
        produtoChamado.setDataHoraAbertura(LocalDateTime.now());
    }

    @Nested
    @DisplayName("Método salvarProdutoChamado")
    class SalvarProdutoChamado {

        @Test
        @DisplayName("Deve salvar um ProdutoChamado com sucesso")
        void deveSalvarProdutoChamadoComSucesso() {
            when(usuarioService.buscarUsuarioPorId(1)).thenReturn(usuario);
            when(produtoService.buscarProdutoPorId(1)).thenReturn(produto);
            when(produtoChamadoRepository.save(any(ProdutoChamado.class))).thenReturn(produtoChamado);

            ProdutoChamado salvo = produtoChamadoService.salvarProdutoChamado(1, 1);

            assertEquals(produtoChamado.getId(), salvo.getId());
            verify(produtoChamadoRepository, times(1)).save(any(ProdutoChamado.class));
        }
    }

    @Nested
    @DisplayName("Método listarProdutoChamados")
    class ListarProdutoChamados {

        @Test
        @DisplayName("Deve listar todos os ProdutoChamados")
        void deveListarTodosProdutoChamados() {
            List<ProdutoChamado> lista = List.of(produtoChamado);
            when(produtoChamadoRepository.findAll()).thenReturn(lista);

            List<ProdutoChamado> resultado = produtoChamadoService.listarProdutoChamados();

            assertEquals(1, resultado.size());
            verify(produtoChamadoRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Método buscarProdutoChamadoPorId")
    class BuscarProdutoChamadoPorId {

        @Test
        @DisplayName("Deve retornar ProdutoChamado ao buscar por ID")
        void deveRetornarProdutoChamadoAoBuscarPorId() {
            when(produtoChamadoRepository.findById(1)).thenReturn(Optional.of(produtoChamado));

            ProdutoChamado encontrado = produtoChamadoService.buscarProdutoChamadoPorId(1);

            assertEquals(produtoChamado.getId(), encontrado.getId());
            verify(produtoChamadoRepository, times(1)).findById(1);
        }

        @Test
        @DisplayName("Deve lançar NotFoundException ao buscar por ID inexistente")
        void deveLancarNotFoundExceptionAoBuscarPorIdInexistente() {
            when(produtoChamadoRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> produtoChamadoService.buscarProdutoChamadoPorId(1));
        }
    }

    @Nested
    @DisplayName("Método cancelarProdutoChamado")
    class CancelarProdutoChamado {

        @Test
        @DisplayName("Deve cancelar ProdutoChamado em andamento")
        void deveCancelarProdutoChamadoEmAndamento() {
            when(produtoChamadoRepository.findById(1)).thenReturn(Optional.of(produtoChamado));
            when(produtoChamadoRepository.save(any(ProdutoChamado.class))).thenReturn(produtoChamado);

            ProdutoChamado cancelado = produtoChamadoService.cancelarProdutoChamado(1);

            assertEquals(StatusChamado.CANCELADA.getId(), cancelado.getStatusChamado());
            verify(produtoChamadoRepository, times(1)).save(any(ProdutoChamado.class));
        }

        @Test
        @DisplayName("Deve lançar ConflitoStatusChamadoException ao cancelar ProdutoChamado não em andamento")
        void deveLancarConflitoStatusChamadoExceptionAoCancelarProdutoChamadoNaoEmAndamento() {
            produtoChamado.setStatusChamado(StatusChamado.CONCLUIDA.getId());
            when(produtoChamadoRepository.findById(1)).thenReturn(Optional.of(produtoChamado));

            assertThrows(ConflitoStatusChamadoException.class, () -> produtoChamadoService.cancelarProdutoChamado(1));
        }
    }

    @Nested
    @DisplayName("Método concluirProdutoChamado")
    class ConcluirProdutoChamado {

        @Test
        @DisplayName("Deve concluir ProdutoChamado em andamento")
        void deveConcluirProdutoChamadoEmAndamento() {
            when(produtoChamadoRepository.findById(1)).thenReturn(Optional.of(produtoChamado));
            when(produtoChamadoRepository.save(any(ProdutoChamado.class))).thenReturn(produtoChamado);

            ProdutoChamado concluido = produtoChamadoService.concluirProdutoChamado(1);

            assertEquals(StatusChamado.CONCLUIDA.getId(), concluido.getStatusChamado());
            verify(produtoChamadoRepository, times(1)).save(any(ProdutoChamado.class));
        }

        @Test
        @DisplayName("Deve lançar ConflitoStatusChamadoException ao concluir ProdutoChamado não em andamento")
        void deveLancarConflitoStatusChamadoExceptionAoConcluirProdutoChamadoNaoEmAndamento() {
            produtoChamado.setStatusChamado(StatusChamado.CANCELADA.getId());
            when(produtoChamadoRepository.findById(1)).thenReturn(Optional.of(produtoChamado));

            assertThrows(ConflitoStatusChamadoException.class, () -> produtoChamadoService.concluirProdutoChamado(1));
        }
    }
}
