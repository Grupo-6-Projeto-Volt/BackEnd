package sptech.school.projetovolt.service.produto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sptech.school.projetovolt.entity.exception.NotFoundException;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ao utilizar a Produto Service")
@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private ProdutoRepository repository;


    @Nested
    @DisplayName("Método Cadastrar Produto")
    public class CadastrarProduto {

        @Nested
        @DisplayName("Com os dados Corretos")
        public class Correto {
            @Test
            @DisplayName("Cadastro de Produto Completo")
            void cadastrarProdutoCorreto() {
                // GIVEN
                Produto entity = new Produto();
                entity.setNome("Teclado Mecânico");
                entity.setDescricao("Teclado que concerta carros");
                // entity.setCategoria("Informática");
                entity.setPreco(100.0);
                entity.setQtdEstoque(2);
                entity.setEstadoGeral("Novo");
                entity.setDesconto(2);

                // WHEN
                Mockito.when(repository.save(entity)).thenReturn(entity);

                // THEN
                Produto resposta = service.cadastrarProduto(entity);

                // ASSERT
                Assertions.assertEquals(entity.getNome(), resposta.getNome());
                Assertions.assertEquals(entity.getId(), resposta.getId());
                Mockito.verify(repository, Mockito.times(1)).save(entity);
            }
        }

    }

    @Nested
    @DisplayName("Método Alterar Produto Por Id")
    public class AlterarProdutoPorId {

        @Nested
        @DisplayName("Com os dados corretos")
        public class Correto {

            @Test
            @DisplayName("Alterar o campo nome do Produto")
            public void alterarProdutoPorIdCorreto() {
                // GIVEN
                Produto produtoAntes = new Produto();
                produtoAntes.setNome("Produto");
                produtoAntes.setId(1);

                Produto produtoDepois = new Produto();
                produtoDepois.setId(1);
                produtoDepois.setNome("Celular Apple");

                // WHEN
                Mockito.when(repository.findById(1)).thenReturn(Optional.of(produtoAntes));
                Mockito.when(repository.save(produtoDepois)).thenReturn(produtoDepois);

                // THEN
                Produto resposta = service.alterarProdutoPorId(1, produtoDepois);

                // ASSERT
                Assertions.assertEquals(produtoDepois.getNome(), resposta.getNome());
                Mockito.verify(repository, Mockito.times(1)).findById(1);
                Mockito.verify(repository, Mockito.times(1)).save(produtoDepois);
            }
        }
    }

    @Nested
    @DisplayName("Método Listar Produtos")
    public class ListarProdutos {

        @Test
        @DisplayName("Listar todos os produtos quando não há texto de busca")
        void listarTodosProdutos() {
            // GIVEN
            Produto produto = new Produto();
            produto.setNome("Produto");

            // WHEN
            Mockito.when(repository.findAll()).thenReturn(List.of(produto));

            // THEN
            List<Produto> resposta = service.listarProdutos(null, 10);

            // ASSERT
            Assertions.assertFalse(resposta.isEmpty());
            Assertions.assertEquals(1, resposta.size());
            Assertions.assertEquals(produto.getNome(), resposta.get(0).getNome());
            Mockito.verify(repository, Mockito.times(1)).findAll();
        }

        @Test
        @DisplayName("Listar produtos filtrados pelo nome")
        void listarProdutosComTextoBusca() {
            // GIVEN
            String textoBusca = "Produto";
            Produto produto = new Produto();
            produto.setNome("Produto");

            // WHEN
            Mockito.when(repository.findAllByNome(textoBusca)).thenReturn(List.of(produto));

            // THEN
            List<Produto> resposta = service.listarProdutos(textoBusca, 10);

            // ASSERT
            Assertions.assertFalse(resposta.isEmpty());
            Assertions.assertEquals(1, resposta.size());
            Assertions.assertTrue(resposta.get(0).getNome().contains(textoBusca));
            Mockito.verify(repository, Mockito.times(1)).findAllByNome(textoBusca);
        }
    }

    @Nested
    @DisplayName("Método Buscar Produto Por Id")
    public class BuscarProdutoPorId {

        @Test
        @DisplayName("Buscar produto por ID existente")
        void buscarProdutoPorIdExistente() {
            // GIVEN
            int id = 1;
            Produto produto = new Produto();
            produto.setId(id);

            // WHEN
            Mockito.when(repository.findById(id)).thenReturn(Optional.of(produto));

            // THEN
            Produto resposta = service.buscarProdutoPorId(id);

            // ASSERT
            Assertions.assertNotNull(resposta);
            Assertions.assertEquals(id, resposta.getId());
            Mockito.verify(repository, Mockito.times(1)).findById(id);
        }

        @Test
        @DisplayName("Buscar produto por ID inexistente")
        void buscarProdutoPorIdInexistente() {
            // GIVEN
            int id = 1;

            // WHEN
            Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

            // THEN
            NotFoundException exception = assertThrows(NotFoundException.class, () -> {
                service.buscarProdutoPorId(id);
            });

            // ASSERT
            Assertions.assertEquals("Erro 404: Produto %d não encontrado!".formatted(id), exception.getMessage());
            Mockito.verify(repository, Mockito.times(1)).findById(id);
        }
    }

    @Nested
    @DisplayName("Método Deletar Produto Por Id")
    public class DeletarProdutoPorId {

        @Test
        @DisplayName("Deletar produto por ID existente")
        void deletarProdutoPorIdExistente() {
            // GIVEN
            int id = 1;
            Produto produto = new Produto();
            produto.setId(id);

            // WHEN
            Mockito.when(repository.findById(id)).thenReturn(Optional.of(produto));

            // THEN
            service.deletarProdutoPorId(id);

            // ASSERT
            Mockito.verify(repository, Mockito.times(1)).findById(id);
            Mockito.verify(repository, Mockito.times(1)).deleteById(id);
        }
    }

    @Nested
    @DisplayName("Método Filtrar Por Preço")
    public class FiltrarPorPreco {

        @Test
        @DisplayName("Filtrar por preço em ordem ascendente")
        void filtrarPorPrecoAsc() {
            // GIVEN
            Produto produto = new Produto();
            produto.setPreco(100.0);

            // WHEN
            Mockito.when(repository.findByOrderByPreco()).thenReturn(List.of(produto));

            // THEN
            List<Produto> resposta = service.filtrarPorPreco("asc");

            // ASSERT
            Assertions.assertFalse(resposta.isEmpty());
            Assertions.assertEquals(1, resposta.size());
            Mockito.verify(repository, Mockito.times(1)).findByOrderByPreco();
        }

        @Test
        @DisplayName("Filtrar por preço em ordem descendente")
        void filtrarPorPrecoDesc() {
            // GIVEN
            Produto produto = new Produto();
            produto.setPreco(100.0);

            // WHEN
            Mockito.when(repository.findByOrderByPrecoDesc()).thenReturn(List.of(produto));

            // THEN
            List<Produto> resposta = service.filtrarPorPreco("desc");

            // ASSERT
            Assertions.assertFalse(resposta.isEmpty());
            Assertions.assertEquals(1, resposta.size());
            Mockito.verify(repository, Mockito.times(1)).findByOrderByPrecoDesc();
        }
    }
}
