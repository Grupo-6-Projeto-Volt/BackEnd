package sptech.school.projetovolt.api.usuario.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.usuario.repository.UsuarioRepository;
import sptech.school.projetovolt.service.login.dto.LoginCriacaoDto;
import sptech.school.projetovolt.service.login.dto.LoginMapper;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioCriacaoDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioMapper;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
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
        loginRepository.save(loginEntity);

        UsuarioConsultaDto usuarioConsultaDto = UsuarioMapper.toUsuarioConsultaDto(usuarioSalvo);
        return ResponseEntity.status(201).body(usuarioConsultaDto);
    }
}
