package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.dao.ItemAlugadoRepository;
import br.edu.ufersa.poo.dudu.model.dao.ItemAlugadoRepositoryImpl;
import br.edu.ufersa.poo.dudu.model.entities.Aluguel;
import br.edu.ufersa.poo.dudu.model.entities.ItemAlugado;

import java.util.List;

public class ItemAlugadoServiceImpl implements ItemAlugadoService {
    private final ItemAlugadoRepository itemRepo = new ItemAlugadoRepositoryImpl();

    @Override
    public ItemAlugado buscarPorId(ItemAlugado item) {
        if (item != null)
            return itemRepo.findById(item);
        return null;
    }

    @Override
    public List<ItemAlugado> buscarPorAluguel(Aluguel aluguel) {
        if (aluguel != null)
            return itemRepo.findByRental(aluguel);
        return null;
    }

    @Override
    public void excluir(ItemAlugado item) {
        if(item != null && itemRepo.findById(item) != null)
            itemRepo.delete(item);
        else
            throw new IllegalArgumentException("Esse item não existe.");
    }

    @Override
    public List<ItemAlugado> listarTodos() { return itemRepo.findAll(); }

    @Override
    public void cadastrar(ItemAlugado item) {
        if(item != null) {
            if (itemRepo.findById(item) != null)
                throw new IllegalArgumentException("Esse item já está cadastrado.");
            else
                itemRepo.save(item);
        } else
            throw new IllegalArgumentException("Esse item não existe.");
    }
}
