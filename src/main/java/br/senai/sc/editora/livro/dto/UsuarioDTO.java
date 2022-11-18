package br.senai.sc.editora.livro.dto;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class UsuarioDTO {
    @NonNull
    private String email;

    @NonNull
    private String senha;
}
