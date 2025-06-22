package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.entities.Cliente;

public interface ClienteService extends GenericService<Cliente> {
    Cliente buscarPorCpf(String cpf);
    Cliente buscarPorNome(String nome);
    void excluir(String cpf);
}
