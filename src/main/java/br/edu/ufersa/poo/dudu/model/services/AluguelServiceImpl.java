package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.dao.AluguelRepository;
import br.edu.ufersa.poo.dudu.model.dao.AluguelRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Aluguel;
import br.edu.ufersa.poo.dudu.model.entities.Cliente;

import java.time.LocalDate;
import java.util.List;

public class AluguelServiceImpl implements AluguelService {
    private final AluguelRepository aluguelRepo = new AluguelRepositoryImpl();

    @Override
    public Aluguel buscarPorId(Aluguel aluguel) { return aluguelRepo.findById(aluguel); }

    @Override
    public List<Aluguel> buscarPorCliente(Cliente cliente) {
        if(cliente != null)
            return aluguelRepo.findByClient(cliente);
        return null;
    }

    @Override
    public List<Aluguel> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        if(dataInicio != null && dataFim != null && !dataFim.isBefore(dataInicio))
            return aluguelRepo.findByPeriod(dataInicio, dataFim);
        return null;
    }

    @Override
    public List<Aluguel> buscarAtivos() { return aluguelRepo.findActive(); }

    @Override
    public void excluir(Aluguel aluguel) {
        if(aluguel != null && aluguelRepo.findById(aluguel) != null)
            aluguelRepo.delete(aluguel);
        else
            throw new IllegalArgumentException("Esse aluguel não existe.");
    }

    @Override
    public void atualizar(Aluguel aluguel) {
        Aluguel aluguelEncontrado = aluguelRepo.findById(aluguel);
        if(aluguelEncontrado == null)
            throw new IllegalArgumentException("Aluguel inexistente!");
        aluguelEncontrado.setItem(aluguel.getItem());
        aluguelEncontrado.setDataFim(aluguel.getDataFim());
        aluguelEncontrado.setValorTotal(aluguel.getValorTotal());
        aluguelRepo.update(aluguelEncontrado);
    }

    @Override
    public void finalizar(Aluguel aluguel) {
        if(aluguel != null && aluguelRepo.findById(aluguel) != null) {
            if (!aluguel.getFinalizado()) {
                aluguel.setFinalizado(true);
                aluguelRepo.update(aluguel);
            } else throw new IllegalStateException("Aluguel já está finalizado");
        } else
            throw new IllegalArgumentException("Esse aluguel não existe.");
    }

    @Override
    public List<Aluguel> listarTodos() { return aluguelRepo.findAll(); }

    @Override
    public void cadastrar(Aluguel aluguel) {
        if(aluguel != null) {
            if (aluguelRepo.findById(aluguel) != null)
                throw new IllegalArgumentException("Esse aluguel já está cadastrado.");
            else
                aluguelRepo.save(aluguel);
        } else
            throw new IllegalArgumentException("Esse item não existe.");
    }
}
