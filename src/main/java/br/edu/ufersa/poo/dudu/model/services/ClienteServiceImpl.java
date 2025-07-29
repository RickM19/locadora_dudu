package br.edu.ufersa.poo.dudu.model.services;


import br.edu.ufersa.poo.dudu.model.dao.ClienteRepository;
import br.edu.ufersa.poo.dudu.model.dao.ClienteRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Cliente;

import java.util.List;

public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository clienteRp = new ClienteRepositoryImpl();

    @Override
    public Cliente buscarPorNome(Cliente c){
        if(c.getNome() !=null && !c.getNome().isEmpty())
            return clienteRp.findByName(c);
        return null;
    }

    @Override
    public Cliente buscarPorCpf(Cliente c){
        if(c.getCpf() !=null && !c.getCpf().isEmpty())
            return clienteRp.findByCpf(c);
        return null;
    }

    @Override
    public List<Cliente> listarTodos(){
        return clienteRp.findAll();
    }

    @Override
    public void cadastrar(Cliente cliente){
        Cliente clienteEncontrado = clienteRp.findByCpf(cliente);
        if(clienteEncontrado != null && clienteEncontrado.getCpf().equals(cliente.getCpf())){
            throw new IllegalArgumentException("Cliente já cadastrado!");
        }
        clienteRp.save(cliente);
    }

    @Override
    public void excluir(Cliente c){
        Cliente clienteEncontrado = clienteRp.findByCpf(c);
        if(clienteEncontrado==null)
            throw new IllegalArgumentException("Cliente inexistente!");
        clienteRp.delete(clienteEncontrado);
    }

    @Override
    public void atualizar(Cliente c){
        Cliente clienteEncontrado = clienteRp.findByCpf(c);
        if(clienteEncontrado==null)
            throw new IllegalArgumentException("Cliente inexistente!");
        clienteRp.update(clienteEncontrado);
    }
}
