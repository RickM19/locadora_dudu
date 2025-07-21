package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.dao.DiscoRepository;
import br.edu.ufersa.poo.dudu.model.dao.DiscoRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Disco;
import br.edu.ufersa.poo.dudu.model.entities.Usuario;
import br.edu.ufersa.poo.dudu.model.enums.TipoUsuario;
import br.edu.ufersa.poo.dudu.util.Session;

import java.util.List;



public class DiscoServiceImpl implements DiscoService{
   private final DiscoRepository discoRp = new DiscoRepositoryImpl();
    @Override
    public Disco buscarPorNomeBanda(String nomeBanda){
        if(nomeBanda !=null && !nomeBanda.isEmpty())
            return discoRp.findByBandName(nomeBanda);
        return null;
    }

    @Override
    public Disco buscarPorId(long id){
        return discoRp.findById(id);
    }

    @Override
    public List<Disco> listarTodos(){
        return discoRp.findAll();
    }

    @Override
    public Disco buscarPorTitulo(String titulo){
        if(titulo !=null && !titulo.isEmpty())
            return discoRp.findByTitle(titulo);
        return null;
    }

    @Override
    public Disco buscarPorGenero(String genero){
        if(genero !=null && !genero.isEmpty())
            return discoRp.findByGenre(genero);
        return null;
    }

    @Override
    public void cadastrar(Disco disco){
        Usuario usuarioLogado = Session.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Disco discoEncontrado = discoRp.findByTitle(disco.getTitulo());
        if(discoEncontrado != null && discoEncontrado.getTitulo().equals(disco.getTitulo())){
            throw new IllegalArgumentException("Disco já cadastrado!");
        }
        discoRp.save(disco);
    }

    @Override
    public void excluir(long id) {
        Usuario usuarioLogado = Session.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Disco discoEncontrado = discoRp.findById(id);
        if(discoEncontrado == null) {
            throw new IllegalArgumentException("Disco inexistente!");
        }
        discoRp.delete(discoEncontrado);
    }
    @Override
    public void alterarEstoque(long id, int qtd) {
        Usuario usuarioLogado = Session.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Disco discoEncontrado = discoRp.findById(id);
        if(discoEncontrado == null) {
            throw new IllegalArgumentException("Disco inexistente!");
        }
        discoEncontrado.setQtdExemplares(qtd);

    }
    @Override
    public void alugar(long id) {
        Disco discoEncontrado = discoRp.findById(id);
        if(discoEncontrado == null) {
            throw new IllegalArgumentException("Disco inexistente!");
        }
        discoEncontrado.alugar();
        discoRp.update(discoEncontrado);
    }
    @Override
    public void devolver(long id) {
        Disco discoEncontrado = discoRp.findById(id);
        if(discoEncontrado == null) {
            throw new IllegalArgumentException("Disco inexistente!");
        }
        discoEncontrado.devolver();
        discoRp.update(discoEncontrado);
    }

}
