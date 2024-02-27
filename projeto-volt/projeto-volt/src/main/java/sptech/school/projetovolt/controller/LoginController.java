package sptech.school.projetovolt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.projetovolt.dto.LoginDto;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ResponseEntity<Object> logarPorEmailSenha(@PathVariable String email, @PathVariable String senha) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/alterar-senha")
    public ResponseEntity<Object> alterarSenha(@RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
