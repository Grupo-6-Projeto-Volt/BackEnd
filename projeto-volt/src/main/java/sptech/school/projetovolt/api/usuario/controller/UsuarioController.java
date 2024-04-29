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
import sptech.school.projetovolt.service.usuario.dto.UsuarioAtualizacaoDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioCriacaoDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Responsável pelo gerenciamento dos usuários")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final LoginRepository loginRepository;
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
    public ResponseEntity<UsuarioConsultaDto> criarConta(@RequestBody @Valid UsuarioCriacaoDto usuarioCriado) {

        usuarioCriado.setSenha(passwordEncoder.encode(usuarioCriado.getSenha()));

        Usuario usuarioEntity = UsuarioMapper.toEntity(usuarioCriado);
        Usuario usuarioSalvo = usuarioRepository.save(usuarioEntity);

        Login login = new Login();
        login.setEmail(usuarioCriado.getEmail());
        login.setSenha(usuarioCriado.getSenha());
        login.setUsuario(usuarioSalvo);

        LoginCriacaoDto loginCriacaoDto = LoginMapper.toCadastrarLoginDto(login);
        Login loginEntity = LoginMapper.toLogin(loginCriacaoDto, usuarioSalvo);
        Login loginSalvo = loginRepository.save(loginEntity);

        usuarioSalvo.setLogin(loginSalvo);

        UsuarioConsultaDto usuarioConsultaDto = UsuarioMapper.toUsuarioConsultaDto(usuarioSalvo);
        return ResponseEntity.status(201).body(usuarioConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioConsultaDto>> listarContas() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(UsuarioMapper.toUsuarioConsultaDto(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioConsultaDto> buscarContaPorId(@PathVariable int id) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        return usuarioEncontrado
                .map(usuario -> ResponseEntity.status(200).body(UsuarioMapper.toUsuarioConsultaDto(usuario)))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }
  

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioConsultaDto> atualizarUsuario(@PathVariable int id, @RequestBody @Valid UsuarioAtualizacaoDto novoUsuario) {

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Usuario entity = UsuarioMapper.toEntity(novoUsuario);
        entity.setId(id);
        entity.setLogin(usuarioEncontrado.get().getLogin());

        Usuario usuarioSalvo = usuarioRepository.save(entity);
        return ResponseEntity.status(200).body(UsuarioMapper.toUsuarioConsultaDto(usuarioSalvo));

    }
}
