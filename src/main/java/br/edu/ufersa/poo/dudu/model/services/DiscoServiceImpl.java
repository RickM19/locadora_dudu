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
    private final Session sessionInstance = Session.getInstance();
    @Override
    public Disco buscarPorNomeBanda(Disco d){
        if(d.getNomeBanda() !=null && !d.getNomeBanda().isEmpty())
            return discoRp.findByBandName(d);
        return null;
    }

    @Override
    public Disco buscarPorId(Disco d){
        return discoRp.findById(d);
    }

    @Override
    public List<Disco> listarTodos(){
        return discoRp.findAll();
    }

    @Override
    public Disco buscarPorTitulo(Disco d){
        if(d.getTitulo() !=null && !d.getTitulo().isEmpty())
            return discoRp.findByTitle(d);
        return null;
    }

    @Override
    public Disco buscarPorGenero(Disco d){
        if(d.getCategoria() !=null && !d.getCategoria().isEmpty())
            return discoRp.findByGenre(d);
        return null;
    }

    @Override
    public void cadastrar(Disco disco){
        Usuario usuarioLogado = sessionInstance.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Disco discoEncontrado = discoRp.findByTitle(disco);
        if(discoEncontrado != null && discoEncontrado.getTitulo().equals(disco.getTitulo())){
            throw new IllegalArgumentException("Disco já cadastrado!");
        }
        discoRp.save(disco);
    }

    @Override
    public void excluir(Disco d) {
        Usuario usuarioLogado = sessionInstance.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Disco discoEncontrado = discoRp.findById(d);
        if(discoEncontrado == null) {
            throw new IllegalArgumentException("Disco inexistente!");
        }
        discoRp.delete(discoEncontrado);
    }
    @Override
    public void alterarEstoque(Disco d) {
        Usuario usuarioLogado = sessionInstance.getUsuarioLogado();
        if (usuarioLogado.getTipoUsuario() != TipoUsuario.ADMIN) {
            throw new SecurityException("Acesso negado!");
        }
        Disco discoEncontrado = discoRp.findById(d);
        if(discoEncontrado == null) {
            throw new IllegalArgumentException("Disco inexistente!");
        }
        discoEncontrado.setQtdExemplares(d.getExemplares());
        discoRp.update(discoEncontrado);

    }
    @Override
    public void alugar(Disco d) {
        Disco discoEncontrado = discoRp.findById(d);
        if(discoEncontrado == null) {
            throw new IllegalArgumentException("Disco inexistente!");
        }
        discoEncontrado.alugar();
        discoRp.update(discoEncontrado);
    }
    @Override
    public void devolver(Disco d) {
        Disco discoEncontrado = discoRp.findById(d);
        if(discoEncontrado == null) {
            throw new IllegalArgumentException("Disco inexistente!");
        }
        discoEncontrado.devolver();
        discoRp.update(discoEncontrado);
    }

}
