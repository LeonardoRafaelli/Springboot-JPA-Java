package br.senai.sc.editora.livro.model.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
public class Pessoa {
    @Id
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private Long CPF;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 100, nullable = false)
    private String sobrenome;

    @Column(name = "email", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 30, nullable = false)
    private String senha;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 50, nullable = false)
    private Genero genero;

}
