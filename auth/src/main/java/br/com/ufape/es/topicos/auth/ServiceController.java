package br.com.ufape.es.topicos.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServiceController {

    @GetMapping("/gerente/dados")
    public String dadosGerente() {
        System.out.println("Acesso permitido somente para gerentes");
        return "Acesso permitido somente para gerentes";
    }

    @GetMapping("/usuario/dados")
    public String dadosUsuario() {
        System.out.println("Acesso permitido para usuários e gerentes");
        return "Acesso permitido para usuários e gerentes";
    }

    @GetMapping("/livre")
    public String livre() {
        System.out.println("Acesso livre");
        return "Acesso permitido para todos";
    }
}