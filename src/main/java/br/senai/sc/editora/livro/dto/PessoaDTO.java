package br.senai.sc.editora.livro.dto;

import br.senai.sc.editora.livro.model.entities.Genero;
import javax.validation.constraints.NotBlank;

public class PessoaDTO {
    @NotBlank
    private Long CPF;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private Genero genero;

    public Long getCPF() {
        return CPF;
    }

    public String getEmail() {
        return email;
    }
}
