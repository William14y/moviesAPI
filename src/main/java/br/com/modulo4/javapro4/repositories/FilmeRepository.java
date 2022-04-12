package br.com.modulo4.javapro4.repositories;

import br.com.modulo4.javapro4.entities.Filmes;
import br.com.modulo4.javapro4.exceptions.FilmeJaExisteException;
import br.com.modulo4.javapro4.exceptions.FilmeNaoExisteException;
import br.com.modulo4.javapro4.exceptions.InvalidNoteException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FilmeRepository {

    private final List<Filmes> filmeList;

    private FilmeRepository(){
        this.filmeList = new ArrayList<>();
    }

    public List<Filmes> procuraFilme(){
        return filmeList;
    }

    public List<Filmes> procuraNomeFilme(final String nome) {
        List<Filmes> filmeExistente = new ArrayList<>(filmeList.stream()
            .filter(mve -> mve.getNome().contains(nome))
            .collect(Collectors.toList()));

        if(filmeExistente.isEmpty()){
            throw new FilmeNaoExisteException();
        }
        return filmeExistente;
    }
    public List<Filmes> diretorFilme(final String diretor){
        List<Filmes> filmeExistente = new ArrayList<>(filmeList.stream()
                .filter(mve -> mve.getDiretor().contains(diretor))
                .collect(Collectors.toList()));

        if(filmeExistente.isEmpty()){
            throw new FilmeNaoExisteException();
        }
        return filmeExistente;
    }

    public Filmes validaFilme(Filmes filme){
        if(filmeList.stream().anyMatch(mve -> mve.getNome().equalsIgnoreCase(filme.getNome())) &&
            filmeList.stream().anyMatch(mve0 -> mve0.getDiretor().equalsIgnoreCase(filme.getDiretor())) &&
             filmeList.stream().anyMatch(mve1 -> mve1.getAno().equals(filme.getAno())))
        {
            throw new FilmeJaExisteException();
        }
        if(filme.getId()==null){
            filme.setId(filmeList.size()+1);
        }

        if(filme.getNota() < 1 || filme.getNota() > 5) {
                throw new InvalidNoteException();
        }
        this.filmeList.add(filme);
        return filme;
    }
    
    public void editFilme(final Filmes filme){

        filmeList.stream().filter(mve -> mve.getId().equals(filme.getId()))
                .forEach(mve -> mve.setNome(filme.getNome()));

        filmeList.stream().filter(mve -> mve.getId().equals(filme.getId()))
                .forEach(mve -> mve.setDiretor(filme.getDiretor()));

        filmeList.stream().filter(mve -> mve.getId().equals(filme.getId()))
                .forEach(mve -> mve.setAno(filme.getAno()));

        filmeList.stream().filter((mve -> mve.getId().equals(filme.getId())))
                .forEach(mve -> mve.setNota(filme.getNota()));
    }
    public void deleteFilme(Integer id){
        filmeList.removeIf(filme -> filme.getId().equals(id));
    }
    



}

