package sptech.school.projetovolt.api.login.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.service.login.LoginService;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioLoginDto;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioTokenDto;
import sptech.school.projetovolt.service.login.dto.BuscarLoginDto;
import sptech.school.projetovolt.service.login.dto.LoginMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Tag(name = "Login", description = "Responsável pelos logins dos usuários e autenticação")
public class LoginController {

    private final LoginService loginService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @Operation(
            summary = "Realiza um login",
            method = "POST",
            description = "Responsável por realizar um login na aplicação e retornar um token de autenticação",
            tags = {"Login"}
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"),
                    headers = @io.swagger.v3.oas.annotations.headers.Header(name = "error", description = "Erro na requisição")
            ),

    })
    public ResponseEntity<UsuarioTokenDto> login (@RequestBody @Valid UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = loginService.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(usuarioToken);
    }

    @GetMapping
    @Operation(hidden = true)
    public ResponseEntity<List<BuscarLoginDto>> listarLogins () {
        List<Login> logins = loginService.listarLogins();
        if (logins.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(LoginMapper.toBuscarLoginDto(logins));
    }

    @PatchMapping("/alterar-senha/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<BuscarLoginDto> alterarSenha(
            @PathVariable String id,
            @RequestParam String novaSenha
    ) {
        return ResponseEntity
                .ok(LoginMapper
                        .toBuscarLoginDto(loginService
                                .alterarSenha(id, passwordEncoder.encode(novaSenha))));
    }

    @PatchMapping("/alterar-email/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<BuscarLoginDto> alterarEmail(
            @PathVariable String id,
            @RequestParam String novoEmail
    ) {
        return ResponseEntity
                .ok(LoginMapper
                        .toBuscarLoginDto(loginService
                                .alterarEmail(id, novoEmail)));
    }

}
