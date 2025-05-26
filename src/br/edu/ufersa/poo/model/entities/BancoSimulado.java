package br.edu.ufersa.poo.model.entities;

import java.util.List;
import java.util.ArrayList;

public class BancoSimulado {
	public static int idGenerator = 0;
	
	public static List<Livro> livros = new ArrayList<>();
	public static List<Disco> discos = new ArrayList<>();
	public static List<Cliente> clientes = new ArrayList<>();
	public static List<Aluguel> alugueis = new ArrayList<>();
	public static List<ItemAlugado> itensAlugados = new ArrayList<>();
	public static List<Usuario> usuarios = new ArrayList<>();

}
