package br.edu.ufersa.poo.model.entities;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Aluguel {
	//Atributos
	private int idAluguel;
	private int idCliente;
	private String dataInicio;
	private String dataFim;
	private double valorTotal;
	private boolean finalizado;
	
	//Getters
	public int getIdAluguel() {
		return this.idAluguel;
	}
	public int getIdCliente() {
		return this.idCliente;
	}
	public String getDataInicio() {
		return this.dataInicio;
	}
	public String getDataFim() {
		return this.dataFim;
	}
	public double getValorTotal() {
		return this.valorTotal;
	}
	public boolean getFinalizado() {
		return this.finalizado;
	}
	
	//Setters
	public void setIdAluguel(int idAluguel) {
		if (idAluguel >= 0) this.idAluguel = idAluguel;
	}
	public void setIdCliente(int idCliente) {
		if (idCliente >= 0) this.idCliente = idCliente;
	}
	public void setDataInicio(String dataInicio) {
		if (dataInicio != null) this.dataInicio = dataInicio;
	}
	public void setDataFim(String dataFim) {
		if (dataFim != null) this.dataFim = dataFim;
	}
	public void setValorTotal(double valorTotal) {
		if (valorTotal >= 0) this.valorTotal = valorTotal;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	//Construtores
	public Aluguel(int idAluguel, int idCliente, String dataInicio, String dataFim, double valorTotal, boolean finalizado) {
		setIdAluguel(idAluguel);
		setIdCliente(idCliente);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setValorTotal(valorTotal);
		setFinalizado(finalizado);
	}
	
	//Métodos
	public void cadastrarAluguel(int idAluguel, int idCliente, String dataInicio, String dataFim, double valorTotal) {
		for (Aluguel a : BancoSimulado.alugueis) {
			if (a.getIdAluguel() == idAluguel && a.getIdCliente() == idCliente) {
				System.out.println("Esse aluguel já está cadastrado.");
				return;
			}
				
		}
		Aluguel novoAluguel = new Aluguel(idAluguel, idCliente, dataInicio, dataFim, valorTotal, false);
		BancoSimulado.alugueis.add(novoAluguel);
		System.out.println("Novo aluguel cadastrado com sucesso!");
	}
	
	public void calcularValorTotal(double valorAluguel) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inicio = LocalDate.parse(dataInicio, formatter);
        LocalDate fim = LocalDate.parse(dataFim, formatter);

        long dias = ChronoUnit.DAYS.between(inicio, fim);
        if (dias <= 0) {
            throw new IllegalArgumentException("A data de fim deve ser depois da data de início.");
        }
        this.valorTotal = dias * valorAluguel;
    }
	
	public void finalizar(int id) {
		if (this.idAluguel == id) {
            this.finalizado = true;
            System.out.println("Aluguel " + id + " finalizado.");
        }
		else {
            System.out.println("ID do aluguel não confere.");
        }
	}	
}
