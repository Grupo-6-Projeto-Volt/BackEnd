package sptech.school.projetovolt.api.usuario.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.entity.usuario.repository.UsuarioRepository;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioCriacaoDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioMapper;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<UsuarioConsultaDto> cadastrar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriado) {

        Usuario entity = UsuarioMapper.toEntity(usuarioCriado);
        Usuario usuarioSalvo = usuarioRepository.save(entity);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(usuarioSalvo);
        return ResponseEntity.status(201).body(dto);
    }
}
