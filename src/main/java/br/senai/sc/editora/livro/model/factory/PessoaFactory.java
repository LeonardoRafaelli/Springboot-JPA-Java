package br.senai.sc.editora.livro.model.factory;


import br.senai.sc.editora.livro.model.entities.Autor;
import br.senai.sc.editora.livro.model.entities.Diretor;
import br.senai.sc.editora.livro.model.entities.Pessoa;
import br.senai.sc.editora.livro.model.entities.Revisor;

public class PessoaFactory {

    public static Pessoa getDType(int tipoPessoa) {
        switch (tipoPessoa){
            case 1 -> {
                return new Autor();
            }
            case 2 -> {
                return new Revisor();
            }
            case 3 -> {
                return new Diretor();
            }
            default -> {
                return new Pessoa();
            }
        }
    }
}
