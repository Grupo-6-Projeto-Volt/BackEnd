package sptech.school.projetovolt.api.produtochamado.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.produtochamado.ProdutoChamado;
import sptech.school.projetovolt.service.produto.dto.ProdutoCriacaoDTO;
import sptech.school.projetovolt.service.produtochamado.ProdutoChamadoService;
import sptech.school.projetovolt.service.produtochamado.dto.ProdutoChamadoConsultaDto;
import sptech.school.projetovolt.service.produtochamado.dto.ProdutoChamadoCriacaoDto;
import sptech.school.projetovolt.service.produtochamado.dto.ProdutoChamadoMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtochamados")
@Tag(name = "ProdutoChamado", description = "Responsável pelo gerenciamento de chamados de produtos")
public class ProdutoChamadoController {

    private final ProdutoChamadoService produtoChamadoService;

    @PostMapping
    @Operation(
            summary = "Cria um chamado para compra de um produto",
            method = "POST",
            description = "Responsável por criar um chamado para compra de um produto",
            tags = {"ProdutoChamado"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Chamado de produto cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Erro na requisição")
            ),

    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Produto Chamado Criacao DTO",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProdutoChamadoCriacaoDto.class))
    )
    @Parameters(value = {
            @Parameter( name = "idUsuario", description = "ID do usuário relacionado", example = "1", required = true),
            @Parameter( name = "idProduto", description = "ID do produto relacionado", example = "1", required = true)
})
    public ResponseEntity<ProdutoChamadoConsultaDto> criarProdutoChamado(@RequestParam Integer idUsuario, @RequestParam Integer idProduto) {

        ProdutoChamado produtoChamadoSalvo = produtoChamadoService.salvarProdutoChamado(idUsuario, idProduto);

        URI uri = URI.create("/produtochamados" + produtoChamadoSalvo.getId());

        ProdutoChamadoConsultaDto dto = ProdutoChamadoMapper.toDto(produtoChamadoSalvo);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os chamados de produtos da Loja",
            method = "GET",
            description = "Responsável por listar todos os chamados de produtos criados na loja",
            tags = {"ProdutosChamado"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamados de produtos retornados com sucesso",
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Não contém chamados de produtos cadastrados",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Não contém chamados de produtos cadastrados")
            )
    })
    public ResponseEntity<List<ProdutoChamadoConsultaDto>> listarProdutoChamados() {
        List<ProdutoChamado> produtoChamados = produtoChamadoService.listarProdutoChamados();

        if (produtoChamados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProdutoChamadoConsultaDto> dtos = ProdutoChamadoMapper.toDto(produtoChamados);

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Lista um chamado de produto da Loja",
            method = "GET",
            description = "Responsável por buscar por ID o chamado de produto desejado cadastrado na loja",
        tags = {"ProdutosChamados"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamado de produto retornado com sucesso",
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não contém chamado de produto com respectivo ID",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Não contém chamado de produto cadastrado")
            )
    })
    @Parameter(name = "id", description = "Inteiro para busca de um chamado", example = "1", required = true)
    public ResponseEntity<ProdutoChamadoConsultaDto> buscarChamadoProdutoPorId(@PathVariable Integer id) {
        ProdutoChamado produtoChamadoBuscado = produtoChamadoService.buscarProdutoChamadoPorId(id);

        ProdutoChamadoConsultaDto dto = ProdutoChamadoMapper.toDto(produtoChamadoBuscado);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/cancelar/{id}")
    @Operation(
            summary = "Cancela um chamado de produto",
            method = "GET",
            description = "Responsável por alterar status como cancelado de um chamado desejado através de um ID",
            tags = {"ProdutosChamados"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamado de produto cancelado com sucesso",
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não contém chamado de produto com respectivo ID",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Não contém chamado de produto cadastrado")
            )
    })
    @Parameter(name = "id", description = "Inteiro para busca de um chamado", example = "1", required = true)
    public ResponseEntity<ProdutoChamadoConsultaDto> cancelarProdutoChamado(@PathVariable Integer id) {
        ProdutoChamado produtoChamadoCancelado = produtoChamadoService.cancelarProdutoChamado(id);

        ProdutoChamadoConsultaDto dto = ProdutoChamadoMapper.toDto(produtoChamadoCancelado);

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/concluir/{id}")
    @Operation(
            summary = "Conclui um chamado de produto",
            method = "GET",
            description = "Responsável por alterar status como concluido de um chamado desejado através de um ID",
            tags = {"ProdutosChamados"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamado de produto finalizado com sucesso",
                    useReturnTypeSchema = true
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não contém chamado de produto com respectivo ID",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Não contém chamado de produto cadastrado")
            )
    })
    @Parameter(name = "id", description = "Inteiro para busca de um chamado", example = "1", required = true)
    public ResponseEntity<ProdutoChamadoConsultaDto> concluirProdutoChamado(@PathVariable Integer id) {
        ProdutoChamado produtoChamadoConcluido = produtoChamadoService.concluirProdutoChamado(id);

        ProdutoChamadoConsultaDto dto = ProdutoChamadoMapper.toDto(produtoChamadoConcluido);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filtro/buscar-por-data-abertura-asc")
    public ResponseEntity<List<ProdutoChamadoConsultaDto>> listarChamadosOrdenadosPorDataAberturaAsc() {
        List<ProdutoChamado> produtoChamados = produtoChamadoService.listarChamadosOrdenadosPorDataAberturaAsc();

        if (produtoChamados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ProdutoChamadoMapper.toDto(produtoChamados));
    }

    @GetMapping("/filtro/buscar-por-data-abertura-desc")
    public ResponseEntity<List<ProdutoChamadoConsultaDto>> listarChamadosOrdenadosPorDataAberturaDesc() {
        List<ProdutoChamado> produtoChamados = produtoChamadoService.listarChamadosOrdenadosPorDataAberturaDesc();

        if (produtoChamados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ProdutoChamadoMapper.toDto(produtoChamados));
    }
}


