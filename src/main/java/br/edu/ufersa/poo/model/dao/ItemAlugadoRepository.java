package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Aluguel;
import br.edu.ufersa.poo.model.entities.ItemAlugado;

import java.util.List;

public interface ItemAlugadoRepository extends GenericRepository<ItemAlugado> {
    ItemAlugado findById(ItemAlugado item);
    List<ItemAlugado> findByRental(Aluguel aluguel);
}
