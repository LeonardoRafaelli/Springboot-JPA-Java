package br.senai.sc.editora.livro.model.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

@Entity
@Table(name = "arquivos")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String nome;

    @NonNull
    private String tipo;

    @NonNull
    @Lob
    private byte[] dados;
}
