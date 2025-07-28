package br.edu.ufersa.poo.dudu.exceptions;

public class AuthenticationException extends Exception{
    public AuthenticationException() {
        super ("Login ou senha inválidos!");
    }

}