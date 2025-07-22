package br.edu.ufersa.poo.dudu.model.services;

import br.edu.ufersa.poo.dudu.model.entities.Aluguel;
import br.edu.ufersa.poo.dudu.model.entities.ItemAlugado;

import java.util.List;

public interface ItemAlugadoService extends GenericService<ItemAlugado> {
    ItemAlugado buscarPorId(ItemAlugado item);
    List<ItemAlugado> buscarPorAluguel(Aluguel aluguel);
    void excluir(ItemAlugado item);
}
