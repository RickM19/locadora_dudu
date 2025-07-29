package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Aluguel;
import br.edu.ufersa.poo.dudu.model.entities.ItemAlugado;

import java.util.List;

public interface ItemAlugadoRepository extends GenericRepository<ItemAlugado> {
    ItemAlugado findById(ItemAlugado item);
    ItemAlugado findByName(ItemAlugado item);
    List<ItemAlugado> findByRental(Aluguel aluguel);
}
