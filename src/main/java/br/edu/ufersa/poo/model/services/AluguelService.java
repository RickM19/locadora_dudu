package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.entities.Aluguel;
import br.edu.ufersa.poo.model.entities.Cliente;

import java.time.LocalDate;
import java.util.List;

public interface AluguelService extends GenericService<Aluguel> {
    Aluguel buscarPorId(Aluguel aluguel);
    List<Aluguel> buscarPorCliente(Cliente cliente);
    List<Aluguel> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
    List<Aluguel> buscarAtivos();
    void excluir(Aluguel aluguel);
    void finalizar(Aluguel aluguel);
}
