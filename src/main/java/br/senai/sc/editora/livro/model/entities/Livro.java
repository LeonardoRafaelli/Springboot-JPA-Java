package br.senai.sc.editora.livro.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "livro")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
public class Livro {

    @Id
    @Column(name = "isbn", length = 13, nullable = false, unique = true)
    private Long isbn;

    @Column(name = "titulo", length = 80, nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "cpf_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "cpf_revisor")
    private Revisor revisor;

    @ManyToOne
    @JoinColumn(name = "cnpj_editora")
    private Editora editora;


    @Column(nullable = false)
    private Integer qtdPag;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

}


