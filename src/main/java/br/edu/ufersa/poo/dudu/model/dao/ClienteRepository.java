package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Cliente;

public interface ClienteRepository extends GenericRepository<Cliente> {
    Cliente findByCpf(String cpf);
    Cliente findByName(String nome);
}
