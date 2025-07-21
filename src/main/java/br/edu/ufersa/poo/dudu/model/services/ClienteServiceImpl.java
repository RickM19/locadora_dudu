package br.edu.ufersa.poo.dudu.model.services;


import br.edu.ufersa.poo.dudu.model.dao.ClienteRepository;
import br.edu.ufersa.poo.dudu.model.dao.ClienteRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Cliente;

import java.util.List;

public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository clienteRp = new ClienteRepositoryImpl();

    @Override
    public Cliente buscarPorNome(String nome){
        if(nome !=null && !nome.isEmpty())
            return clienteRp.findByName(nome);
        return null;
    }

    @Override
    public Cliente buscarPorCpf(String cpf){
        if(cpf !=null && !cpf.isEmpty())
            return clienteRp.findByCpf(cpf);
        return null;
    }

    @Override
    public List<Cliente> listarTodos(){
        return clienteRp.findAll();
    }

    @Override
    public void cadastrar(Cliente cliente){
        Cliente clienteEncontrado = clienteRp.findByCpf(cliente.getCpf());
        if(clienteEncontrado != null && clienteEncontrado.getCpf().equals(cliente.getCpf())){
            throw new IllegalArgumentException("Cliente já cadastrado!");
        }
        clienteRp.save(cliente);
    }

    @Override
    public void excluir(String cpf){
        Cliente clienteEncontrado = clienteRp.findByCpf(cpf);
        if(clienteEncontrado==null)
            throw new IllegalArgumentException("Cliente inexistente!");
        clienteRp.delete(clienteEncontrado);
    }
}
