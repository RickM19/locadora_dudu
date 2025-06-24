package br.edu.ufersa.poo.model.services;

import br.edu.ufersa.poo.model.dao.AluguelRepository;
import br.edu.ufersa.poo.model.dao.AluguelRepositoryImpl;
import br.edu.ufersa.poo.model.entities.Aluguel;
import br.edu.ufersa.poo.model.entities.Cliente;

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
        if(dataInicio != null && dataFim != null)
            return aluguelRepo.findByPeriod(dataInicio, dataFim);
        return null;
    }

    @Override
    public List<Aluguel> buscarAtivos() { return aluguelRepo.findActive(); }

    @Override
    public void excluir(Aluguel aluguel) {
        if(aluguel != null)
            aluguelRepo.delete(aluguel);
        else
            throw new IllegalArgumentException("Esse aluguel não existe.");
    }

    @Override
    public void finalizar(Aluguel aluguel) {
        if(aluguel != null)
            aluguel.setFinalizado(true);
        else
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
