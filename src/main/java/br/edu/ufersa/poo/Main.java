package br.edu.ufersa.poo;
import br.edu.ufersa.poo.model.entities.Usuario;
import br.edu.ufersa.poo.model.services.UserService;
import br.edu.ufersa.poo.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.util.JPAUtil;


// classe teste
public class Main {
    public static void main(String[] args) {

        UserService userService = new UsuarioServiceImpl();
        System.out.println(userService.buscarPorId(12).getNomeUsuario());

        JPAUtil.shutdown();
    }
}