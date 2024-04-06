package sptech.school.projetovolt.api.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.school.projetovolt.entity.login.repository.LoginRepository;

@RestController
@RequestMapping("/logins")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;


}
