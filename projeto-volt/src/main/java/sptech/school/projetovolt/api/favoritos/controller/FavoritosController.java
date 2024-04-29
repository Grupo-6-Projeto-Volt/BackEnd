package sptech.school.projetovolt.api.favoritos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.favoritos.repository.FavoritosRepository;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    @Autowired
    private FavoritosRepository favoritosRepository;
}
