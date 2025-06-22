package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Cliente;
import java.util.List;

public interface ClienteRepository extends GenericRepository<Cliente> {
    Cliente findByCpf(String cpf);
    Cliente findByName(String nome);
}
