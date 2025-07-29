package br.edu.ufersa.poo.dudu.exceptions;

public class CadastroException extends RuntimeException{
    public CadastroException(){super("Usuário já cadastrado!");}
}
