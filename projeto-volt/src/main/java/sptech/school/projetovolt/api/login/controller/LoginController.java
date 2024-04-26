package sptech.school.projetovolt.api.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.service.login.LoginService;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioLoginDto;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioTokenDto;
import sptech.school.projetovolt.service.login.dto.BuscarLoginDto;
import sptech.school.projetovolt.service.login.dto.LoginMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginRepository loginRepository;
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<UsuarioTokenDto> login (@RequestBody @Valid UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = this.loginService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @GetMapping
    public ResponseEntity<List<BuscarLoginDto>> listar () {
        List<Login> logins = loginRepository.findAll();

        if (logins.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<BuscarLoginDto> loginDtos = LoginMapper.toBuscarLoginDto(logins);
        return ResponseEntity.status(200).body(loginDtos);

    }
}
