package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.entities.Cliente;

public interface ClienteService extends GenericService<Cliente> {
    Cliente buscarPorCpf(String cpf);
    Cliente buscarPorNome(String nome);
    void excluir(String cpf);
}
