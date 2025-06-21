package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Cliente;
import java.util.List;

public interface ClienteRepository {
    void salve(Cliente cliente);
    void update(Cliente cliente);
    void delete(Cliente cliente);
    Cliente findByCpf(String cpf);
    List<Cliente> findAll();
}
