package sptech.school.projetovolt.api.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.service.login.LoginService;
import sptech.school.projetovolt.service.login.dto.autenticacao.dto.UsuarioLoginDto;
import sptech.school.projetovolt.service.login.dto.autenticacao.dto.UsuarioTokenDto;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<UsuarioTokenDto> login (@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = this.loginService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioToken);
    }


}
