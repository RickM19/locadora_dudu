package br.edu.ufersa.poo.dudu.util;

import br.edu.ufersa.poo.dudu.model.dao.UsuarioRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Usuario;

public class DatabaseSeeder {

    public static void seedUsuarioDudu() {
        UsuarioRepositoryImpl repo = new UsuarioRepositoryImpl();
        final String userName = "Dudu";
        final String password = "dudu4321";
        final String email = "Dudu@gmail.com";

        Usuario dudu = new Usuario();
        dudu.setNomeUsuario(userName);
        dudu.setEmail(email);
        dudu.setSenha(password);
        dudu.setTipoUsuario(userName);

        Usuario existente = repo.findByUserName(dudu);
        if (existente == null) {
            repo.save(dudu);
            System.out.println("Usuário Dudu criado com sucesso.");
        } else {
            System.out.println("Usuário Dudu já existe no banco.");
        }
    }
}