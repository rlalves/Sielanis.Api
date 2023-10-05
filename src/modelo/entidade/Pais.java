package modelo.entidade;

import java.util.List;

public class Pais {
	private String Nome;
	private String Capital;
	private String Linguas;
	private int Populacao;
	
	public Pais(String nome, String capital, String linguas, int populacao) {
		Nome = nome;
		Capital = capital;
		Linguas = linguas;
		Populacao = populacao;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public String getCapital() {
		return Capital;
	}
	
	public void setCapital(String capital) {
		Capital = capital;
	}
	
	public String getLinguas() {
		return Linguas;
	}
	
	public void setLinguas(String linguas) {
		Linguas = linguas;
	}
	
	public int getPopulacao() {
		return Populacao;
	}
	
	public void setPopulacao(int populacao) {
		Populacao = populacao;
	}
}
