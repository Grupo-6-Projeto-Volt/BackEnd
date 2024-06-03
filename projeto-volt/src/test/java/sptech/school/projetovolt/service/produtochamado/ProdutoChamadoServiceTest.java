//package sptech.school.projetovolt.service.produtochamado;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sptech.school.projetovolt.entity.login.Login;
//import sptech.school.projetovolt.entity.login.repository.LoginRepository;
//import sptech.school.projetovolt.entity.produto.Produto;
//import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
//import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;
//import sptech.school.projetovolt.entity.produtochamado.repository.ProdutoChamadoRepository;
//import sptech.school.projetovolt.entity.usuario.Usuario;
//import sptech.school.projetovolt.entity.usuario.repository.UsuarioRepository;
//import sptech.school.projetovolt.service.login.LoginService;
//import sptech.school.projetovolt.service.produto.ProdutoService;
//import sptech.school.projetovolt.service.usuario.UsuarioService;
//import sptech.school.projetovolt.utils.StatusChamado;
//
//@DisplayName("Ao utilizar a Produto Chamado Service")
//@ExtendWith(MockitoExtension.class)
//public class ProdutoChamadoServiceTest {
//
//    private static final Logger log = LoggerFactory.getLogger(ProdutoChamadoServiceTest.class);
//    @InjectMocks
//    private ProdutoChamadoService produtoChamadoService;
//    @Mock
//    private ProdutoChamadoRepository produtoChamadoRepository;
//
//    @InjectMocks
//    private ProdutoService produtoService;
//    @Mock
//    private ProdutoRepository produtoRepository;
//
//    @InjectMocks
//    private UsuarioService usuarioService;
//    @Mock
//    private UsuarioRepository usuarioRepository;
//
//    @InjectMocks
//    private LoginService loginService;
//    @Mock
//    private LoginRepository loginRepository;
//
//
//
//    @Nested
//    @DisplayName("Método Salvar Produto Chamado")
//    public class SalvarProdutoChamado {
//
//        @Nested
//        @DisplayName("Com os dados Corretos")
//        public class Correto{
//            @Test
//            @DisplayName("Cadastro de ProdutoChamado Completo")
//            void cadastrarProdutoChamadoCorreto() {
////                // GIVEN
////                Produto produto = new Produto();
////                produto.setNome("Teclado Mecânico");
////                produto.setDescricao("Teclado que concerta carros");
////                produto.setCategoria("Informática");
////                produto.setPreco(100.0);
////                produto.setQtdEstoque(2);
////                produto.setEstadoGeral("Novo");
////                produto.setDesconto(2);
////                produtoService.cadastrarProduto(produto);
////
////                Usuario usuario = new Usuario();
////                usuario.setNome("João Silva");
////                usuario.setEmail("joao@example.com");
////                usuario.setTelefone("123456789");
////                usuario.setCategoria(StatusChamado.EM_ANDAMENTO.getId());
////                usuarioService.criarConta(usuario, "123");
////                loginService.criarLogin(usuario, "123");
////
////                Login login = new Login();
////                login.setEmail(usuario.getEmail());
////                login.setSenha("123");
////                login.setUsuario(usuario);
////
////
////                ProdutoChamado entity = new ProdutoChamado();
////                entity.setProduto(produto);
////                entity.setUsuario(usuario);
////
////
////                // WHEN
////                Mockito.when(produtoChamadoRepository.save(entity)).thenReturn(entity);
////                Mockito.when(produtoRepository.save(produto)).thenReturn(produto);
////                Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
////                Mockito.when(loginRepository.save(login)).thenReturn(login);
////
////                // THEN
////                ProdutoChamado resposta = produtoChamadoService.salvarProdutoChamado(1,1);
////
////                // ASSERT
////                Assertions.assertEquals(entity.getProduto(), resposta.getProduto());
////                Assertions.assertEquals(entity.getUsuario(), resposta.getUsuario());
//            }
//        }
//    }
//}
