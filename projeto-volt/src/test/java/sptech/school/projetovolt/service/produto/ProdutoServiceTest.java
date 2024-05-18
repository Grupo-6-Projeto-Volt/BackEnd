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
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ao utilizar a Produto Service")
@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private ProdutoRepository repository;


    @Nested
    @DisplayName("Metódo Cadastrar Produto")
    public class CadastrarProduto{

        @Nested
        @DisplayName("Com os dados Corretos")
        public class Correto{
            @Test
            @DisplayName("Cadastro de Produto Completo")
            void cadastrarProdutoCorreto() {
                // GIVEN
                Produto entity = new Produto();
                entity.setNome("Teclado Mecânico");
                entity.setDescricao("Teclado que concerta carros");
                entity.setCategoria("Informática");
                entity.setPreco(100.0);
                entity.setQtdEstoque(2);
                entity.setEstadoGeral("Novo");
                entity.setDesconto(2);

                // WEN
                Mockito.when(repository.save(entity)).thenReturn(entity);

                // THEN
                Produto resposta = service.cadastrarProduto(entity);

                // ASSERT
                Assertions.assertEquals(entity.getNome(), resposta.getNome());

            }


        }

    }

    @Nested
    @DisplayName("Metódo Alterar Produto Por Id ")
    public class AlterarProdutoPorId{

        @Nested
        @DisplayName("Com os dados corretos")
        public class Correto{

            @Test
            @DisplayName("Alterar o campo nome do Produto")
            public void alterarProdutoPorIdCorreto(){
                // GIVEN
                Produto produtoAntes = new Produto();
                produtoAntes.setNome("Produt0");
                produtoAntes.setId(1);

                Produto produtoDepois = new Produto();
                produtoDepois.setId(1);
                produtoDepois.setNome("Celular Aple");

                // WHEN

                /*
                * TODO: Terminar a implementação do caso de uso.
                *  No aguardo de mais informações do Reis para finalizar
                */

            }
        }
    }


}