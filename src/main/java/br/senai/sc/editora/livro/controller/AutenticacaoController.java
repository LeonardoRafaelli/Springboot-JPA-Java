package br.senai.sc.editora.livro.controller;

import br.senai.sc.editora.livro.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class AutenticacaoController {

    @PostMapping
    public ResponseEntity<Object> autenticacao(
        @RequestBody @Valid UsuarioDTO usuarioDTO
    ){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(),usuarioDTO.getSenha()
                );
        if(authToken.isAuthenticated()){
            return ResponseEntity.ok(authToken.getPrincipal());
        }

        return ResponseEntity.badRequest().build();
    }

}
