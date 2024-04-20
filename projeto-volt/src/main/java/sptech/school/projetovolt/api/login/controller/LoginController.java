package sptech.school.projetovolt.api.login.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.entity.login.Login;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.login.LoginService;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioLoginDto;
import sptech.school.projetovolt.service.login.autenticacao.dto.UsuarioTokenDto;
import sptech.school.projetovolt.service.login.dto.BuscarLoginDto;
import sptech.school.projetovolt.service.login.dto.LoginCriacaoDto;
import sptech.school.projetovolt.service.login.dto.LoginMapper;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    LoginService loginService;

//    @PostMapping
//    @SecurityRequirement(name = "Bearer")
//    public ResponseEntity<Void> inserir (@RequestBody @Valid LoginCriacaoDto loginCriacaoDto) {
//
//    }

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
