package br.edu.ufersa.poo.dudu.exceptions;

public class SessaoExpiradaException extends Exception {
    public SessaoExpiradaException() {
        super ("O tempo de sessão esgotou! Entre novamente!");
    }
}
