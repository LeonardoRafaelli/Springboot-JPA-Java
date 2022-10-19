package br.senai.sc.editora.livro.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "editora")
@AllArgsConstructor
@NoArgsConstructor
@ToString @EqualsAndHashCode
@Getter @Setter
public class Editora {
    @Id
    @Column(name = "cnpj", length = 14, nullable = false, unique = true)
    private Long CNPJ;
    @Column(name = "nome", nullable = false)
    private String nome;

}
