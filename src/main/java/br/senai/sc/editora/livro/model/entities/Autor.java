package br.senai.sc.editora.livro.model.entities;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "autor")
@AllArgsConstructor
public class Autor extends Pessoa {

}
