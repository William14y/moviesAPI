package br.com.modulo4.javapro4.services;


import br.com.modulo4.javapro4.entities.Filmes;
import br.com.modulo4.javapro4.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FilmeService implements IFilmesService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filmes> procuraFilme(){
       return filmeRepository.procuraFilme();
    }
    public List<Filmes> procurarNomeFilme(String nome) {
        return filmeRepository.procuraNomeFilme(nome);
    }
    public List<Filmes> diretorFilme(String diretor){
        return filmeRepository.diretorFilme(diretor);
    }
    public Filmes validaFilme(final Filmes filme){
        return filmeRepository.validaFilme(filme);
    }
    public void editFilme(final Filmes filme){
        filmeRepository.editFilme(filme);
    }
    public void deleteFilme(@PathVariable("id") Integer id){
        filmeRepository.deleteFilme(id);
    }
}
