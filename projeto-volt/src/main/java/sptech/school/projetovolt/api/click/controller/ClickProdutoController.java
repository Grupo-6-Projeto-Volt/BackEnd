package sptech.school.projetovolt.api.click.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.vwcategoriasacessos.VwCategoriasAcessos;
import sptech.school.projetovolt.entity.vwprodutosmaisacessados.VwProdutosMaisAcessados;
import sptech.school.projetovolt.entity.vwtaxaretorno.VwTaxaRetorno;
import sptech.school.projetovolt.service.clickproduto.ClickProdutoService;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoConsultaDTO;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoCriacaoDTO;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoMaisClicadosDTO;
import sptech.school.projetovolt.service.clickproduto.dto.ClickProdutoMapper;
import sptech.school.projetovolt.service.graficoskpis.GraficoKpisService;
import sptech.school.projetovolt.service.graficoskpis.dto.CategoriasGraficosDto;
import sptech.school.projetovolt.service.graficoskpis.dto.GraficoKpisMapper;
import sptech.school.projetovolt.service.graficoskpis.dto.ProdutosAcessadosDto;
import sptech.school.projetovolt.service.graficoskpis.dto.TaxaRetornoDto;
import sptech.school.projetovolt.service.produto.ProdutoService;
import sptech.school.projetovolt.service.produto.dto.ProdutoConsultaDTO;
import sptech.school.projetovolt.service.produto.dto.ProdutoMapper;
import sptech.school.projetovolt.service.usuario.UsuarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clicks-produtos")
@RequiredArgsConstructor
@Slf4j
public class ClickProdutoController {
    private final ClickProdutoService service;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final GraficoKpisService graficoKpisService;


    @GetMapping("/mais-clicados")
    @Operation(summary = "Listar os produtos mais clicados", method = "GET", description = "Responsável por listar os produtos mais clicados", tags = {"Clicks de Produtos"})
    public ResponseEntity<List<ClickProdutoMaisClicadosDTO>> listarMaisClicados() {
        log.debug(service.listarMaisClicados().toString());
        return ResponseEntity.ok(ClickProdutoMapper.vwToDto(service.listarMaisClicados()));
    }

    @GetMapping("/mais-clicados-produtos")
    @Operation(summary = "Listar os produtos mais clicados", method = "GET", description = "Responsável por listar os produtos mais clicados", tags = {"Clicks de Produtos"})
    public ResponseEntity<List<ProdutoConsultaDTO>> listarMaisClicadosProdutos(@RequestParam(required = false) Integer qtd) {
        List<ProdutoConsultaDTO> dtos = service.listarMaisClicadosProdutos(qtd).stream()
                .map(ProdutoMapper::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um click de produto", method = "POST", description = "Responsável por cadastrar um click de produto", tags = {"Clicks de Produtos"})
    public ResponseEntity<ClickProdutoConsultaDTO> criar(@RequestBody @Valid ClickProdutoCriacaoDTO novoClick) {
        if (novoClick.getIdUsuario() != null) {
            return ResponseEntity.created(URI.create("/clicks-produto")).body(
                    ClickProdutoMapper.toDto(
                            service.criar(
                                    ClickProdutoMapper.toEntity(novoClick,
                                            produtoService.buscarProdutoPorId(novoClick.getIdProduto()),
                                            usuarioService.buscarUsuarioPorId(novoClick.getIdUsuario())),
                                    novoClick.getIdUsuario(),
                                    novoClick.getIdProduto())));
        } else {
            return ResponseEntity.created(URI.create("/clicks-produto")).body(ClickProdutoMapper.toDto(service.criar(ClickProdutoMapper.toEntity(novoClick, produtoService.buscarProdutoPorId(novoClick.getIdProduto()), null), null, novoClick.getIdProduto())));
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos os clicks de produtos", method = "GET", description = "Responsável por listar todos os clicks de produtos cadastrados", tags = {"Clicks de Produtos"})
    public ResponseEntity<List<ClickProdutoConsultaDTO>> listarOrdenadoPorData() {
        return ResponseEntity.ok(ClickProdutoMapper.toDto(service.listarOrdenadoPorData()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um click de produto por ID", method = "GET", description = "Responsável por buscar um click de produto por ID", tags = {"Clicks de Produtos"})
    public ResponseEntity<List<ClickProdutoConsultaDTO>> listarClicksPorProduto(@PathVariable int id) {
        return ResponseEntity.ok(ClickProdutoMapper.toDto(service.listarPorProduto(id)));
    }

    @GetMapping("/capturar-dados/categorias")
    @Operation(summary = "Capturar dados de categorias", method = "GET", description = "Responsável por capturar dados de categorias", tags = {"Gráficos KPIs"})
    public ResponseEntity<List<CategoriasGraficosDto>> listarCategoriasMaisAcessadas() {
        List<VwCategoriasAcessos> categoriasAcessos = graficoKpisService.capturarCategoriasMaisAcessadas();

        if (categoriasAcessos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(GraficoKpisMapper.toCategoriasGraficosDto(categoriasAcessos));
    }

    @GetMapping("/capturar-dados/produtos-mais-acessados")
    @Operation(summary = "Capturar dados de produtos mais acessados", method = "GET", description = "Responsável por capturar dados de produtos mais acessados", tags = {"Gráficos KPIs"})
    public ResponseEntity<List<ProdutosAcessadosDto>> listarProdutosMaisAcessados() {
        List<VwProdutosMaisAcessados> produtosMaisAcessados = graficoKpisService.capturarProdutosMaisAcessados();

        if (produtosMaisAcessados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(GraficoKpisMapper.toProdutosAcessadosDto(produtosMaisAcessados));
    }

    @GetMapping("/capturar-dados/taxa-de-retorno")
    @Operation(summary = "Capturar taxa de retorno", method = "GET", description = "Responsável por capturar taxa de retorno", tags = {"Gráficos KPIs"})
    public ResponseEntity<TaxaRetornoDto> capturarTaxaDeRetorno() {
        List<VwTaxaRetorno> taxaRetorno = graficoKpisService.capturarTaxaDeRetorno();
        List<Usuario> qtdUsuario = usuarioService.listarUsuarios();
        if (taxaRetorno.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(GraficoKpisMapper.toTaxaRetornoDto(taxaRetorno, qtdUsuario));
    }
}
