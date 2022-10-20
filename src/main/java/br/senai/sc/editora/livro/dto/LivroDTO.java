package br.senai.sc.editora.livro.dto;

import br.senai.sc.editora.livro.model.entities.Autor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class LivroDTO {

        private Long isbn;
        private String titulo;
        private Integer qtdPag;
        private List<Autor> autores;
}
