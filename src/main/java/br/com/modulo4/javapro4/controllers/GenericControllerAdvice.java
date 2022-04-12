package br.com.modulo4.javapro4.controllers;

import br.com.modulo4.javapro4.exceptions.InvalidNoteException;
import br.com.modulo4.javapro4.exceptions.FilmeNaoExisteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {

    Logger LOGGER = LoggerFactory.getLogger(GenericControllerAdvice.class);

    @ExceptionHandler({FilmeNaoExisteException.class})
    public ResponseEntity<String> handle(final FilmeNaoExisteException e){
        final String naoExiste = "\n" + "Nenhum filme com este nome existe." + "\n";

        LOGGER.error(naoExiste);
        return new ResponseEntity<>(naoExiste, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({InvalidNoteException.class})
    public ResponseEntity<String> handle(final InvalidNoteException e){
        final String invalidNote = "\n" + "Notas apenas de 1 até 5(apenas números inteiros)" + "\n";

        LOGGER.error(invalidNote);
        return new ResponseEntity<>(invalidNote, HttpStatus.CONFLICT);
    }



}
