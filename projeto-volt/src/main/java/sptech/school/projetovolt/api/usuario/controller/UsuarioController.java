package sptech.school.projetovolt.api.usuario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.usuario.repository.UsuarioRepository;
import sptech.school.projetovolt.service.login.dto.LoginCriacaoDto;
import sptech.school.projetovolt.service.login.dto.LoginMapper;
import sptech.school.projetovolt.service.usuario.UsuarioService;
import sptech.school.projetovolt.service.usuario.dto.UsuarioAtualizacaoDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioCriacaoDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioMapper;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Responsável pelo gerenciamento dos usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @Operation(
            summary = "Cria uma conta",
            method = "POST",
            description = "Responsável por criar uma conta de usuário comum no sistema",
            tags = {"Usuários"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "201",
                description = "Conta criada com sucesso"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Erro na requisição",
                content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Erro na requisição")
            )
    })
    public ResponseEntity<UsuarioConsultaDto> criarConta(@RequestBody @Valid UsuarioCriacaoDto novoUsuario) {

        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));

        Usuario usuarioCriado = usuarioService.criarConta(UsuarioMapper.toEntity(novoUsuario), novoUsuario.getSenha());
        return ResponseEntity
                .created(URI.create("/usuarios/" + usuarioCriado.getId()))
                .body(UsuarioMapper.toUsuarioConsultaDto(usuarioCriado));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioConsultaDto>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(UsuarioMapper.toUsuarioConsultaDto(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioConsultaDto> buscarUsuarioPorId(@PathVariable int id) {
        return ResponseEntity
                .ok(UsuarioMapper
                        .toUsuarioConsultaDto(usuarioService.buscarUsuarioPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioConsultaDto> atualizarUsuario(
            @PathVariable int id,
            @RequestBody @Valid UsuarioAtualizacaoDto novoUsuario)
    {
        return ResponseEntity
                .ok(UsuarioMapper
                        .toUsuarioConsultaDto(usuarioService
                                .atualizarUsuario(id, UsuarioMapper.toEntity(novoUsuario))));
    }
}
