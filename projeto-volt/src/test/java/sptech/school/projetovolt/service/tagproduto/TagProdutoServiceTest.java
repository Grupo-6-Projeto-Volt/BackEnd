package sptech.school.projetovolt.service.tagproduto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sptech.school.projetovolt.entity.tagProduto.TagProduto;
import sptech.school.projetovolt.entity.tagProduto.repository.TagProdutoRepository;
import sptech.school.projetovolt.service.tagProduto.TagProdutoService;

@DisplayName("Ao utilizar a Tag Produto Service")
@ExtendWith(MockitoExtension.class)
public class TagProdutoServiceTest {

    @InjectMocks
    private TagProdutoService service;

    @Mock
    private TagProdutoRepository repository;

    @Nested
    @DisplayName("Método Criar Tag")
    public class CriarTag {

        @Nested
        @DisplayName("Com os dados Corretos")
        public class Correto {

            @Test
            @DisplayName("Criação de Tag Produto Completa")
            void criarTagProdutoCorreto() {
                // GIVEN
                TagProduto entity = new TagProduto();
                entity.setTag("Celular");

                // WHEN
                Mockito.when(repository.save(entity)).thenReturn(entity);

                // THEN
                TagProduto resposta = service.criarTag(entity);

                // ASSERT
                Assertions.assertEquals(entity.getTag(), resposta.getTag());


            }
        }
    }
}
