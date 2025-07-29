package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.entities.Cliente;

public interface ClienteService extends GenericService<Cliente> {
    Cliente buscarPorCpf(Cliente c);
    Cliente buscarPorNome(Cliente c);
    void excluir(Cliente c);
    void atualizar(Cliente c);
}
