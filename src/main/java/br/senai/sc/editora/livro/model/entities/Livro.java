package br.senai.sc.editora.livro.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ManyToMany
    @JoinTable(name = "livro_autor",
            joinColumns =
            @JoinColumn(name = "isbn_livro", nullable = false),
            inverseJoinColumns =
            @JoinColumn(name = "cpf_autor", nullable = false)
    )
    private List<Autor> autores;

    @ManyToOne
    @JoinColumn(name = "cpf_revisor")
    private Revisor revisor;

    @ManyToOne
    private Editora editora;


    @Column(nullable = false)
    private Integer qtdPag;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Arquivo file;

    public void setFile (MultipartFile file){
        try{
            this.file = new Arquivo(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}


