package br.com.modulo4.javapro4.services;

import br.com.modulo4.javapro4.entities.Filmes;

import java.util.List;

public interface IFilmesService {

    List<Filmes> procuraFilme();
    List<Filmes> procurarNomeFilme(String nome);
    List<Filmes> diretorFilme(String diretor);
    Filmes validaFilme(final  Filmes filme);
    void editFilme(final Filmes filme);
    void deleteFilme(Integer id);
}
