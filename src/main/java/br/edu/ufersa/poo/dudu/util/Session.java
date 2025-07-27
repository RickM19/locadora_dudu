package br.edu.ufersa.poo.dudu.util;

import br.edu.ufersa.poo.dudu.model.entities.Usuario;

import java.time.Instant;

public class Session {
    private Usuario usuarioLogado;
    private static Session sessionInstance;
    private long timestampUltimoLogin; // em milissegundos
    private static final long TEMPO_EXPIRACAO_MS = 4 * 60 * 60 * 1000; // 4 horas

    private Session() {}
    public void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }
    public void encerrarSessao() {
        usuarioLogado = null;
    }

    public Usuario getUsuarioLogado() {
        if (sessaoExpirada()) {
            encerrarSessao();
        }
        return usuarioLogado;
    }

    public static Session getInstance() {
        if(sessionInstance == null) {
            sessionInstance = new Session();
        }

        return sessionInstance;
    }

    public boolean sessaoExpirada() {
        if (usuarioLogado == null) return true;
        long agora = Instant.now().toEpochMilli();
        return (agora - timestampUltimoLogin) > TEMPO_EXPIRACAO_MS;
    }
}
