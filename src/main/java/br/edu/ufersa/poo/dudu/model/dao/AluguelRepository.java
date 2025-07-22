package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Aluguel;
import br.edu.ufersa.poo.dudu.model.entities.Cliente;

import java.time.LocalDate;
import java.util.List;

public interface AluguelRepository extends GenericRepository<Aluguel> {
    Aluguel findById(Aluguel aluguel);
    List<Aluguel> findByClient(Cliente cliente);
    List<Aluguel> findByPeriod(LocalDate dataInicio, LocalDate dataFim);
    List<Aluguel> findActive();
}
