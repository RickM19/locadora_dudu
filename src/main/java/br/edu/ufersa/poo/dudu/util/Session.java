package br.edu.ufersa.poo.dudu.util;

import br.edu.ufersa.poo.dudu.model.entities.Usuario;

public class Session {
    private static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static void encerrarSessao() {
        usuarioLogado = null;
    }
    private Session() {};
}
