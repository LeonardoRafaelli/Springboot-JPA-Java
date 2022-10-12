package br.senai.sc.editora.livro.model.entities;


public enum Status {
    AGUARDANDO_REVISAO("Aguardando revisão"),       //0
    EM_REVISAO("Em revisão"),                       //1
    APROVADO("Aprovado"),                           //2
    AGUARDANDO_EDICAO("Aguardando edição"),         //3
    REPROVADO("Reprovado"),                         //4
    PUBLICADO("Publicado");                         //5

    private String nome;

    Status(String nome) {
        this.nome = nome;
    }


}
