package sptech.school.projetovolt.api.favoritos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.favoritos.repository.FavoritosRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favoritos")
public class FavoritosController {

    private final FavoritosRepository favoritosRepository;
}
