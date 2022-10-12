//package br.senai.sc.editora.livro.model.entities;
//
//import lombok.*;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//
//@Entity
//@Table(name = "livro")
//@AllArgsConstructor
//@Getter @Setter @ToString @EqualsAndHashCode
//public class Livro {
//
//    @Id
//    @Column(name = "isbn", length = 13, nullable = false, unique = true)
//    private Integer isbn;
//
//    @Column
//    private Editora editora;
//
////    @Column(nullable = false)
////    private Autor autor;
//
//    @Column(name = "titulo", length = 100, nullable = false)
//    private String titulo;
//
//    @Column(nullable = false)
//    private Integer status;
//
//    @Column(nullable = false)
//    private Integer qtdPag;
//
//    @Column(nullable = false)
//    private Status statusLivro;
//}
//
//
