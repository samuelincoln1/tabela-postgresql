package com.ti2cc;

public class Jogo {
	private int codigo;
	private String nome;
	private String genero;
	private String tipo;
	private int nota;
	
	public Jogo() {
		this.codigo = -1;
		this.nome = "";
		this.genero = "";
		this.tipo = "";
		this.nota = -1;
		
	}
	
	public Jogo(int codigo, String nome, String genero, String tipo, int nota) {
		this.codigo = codigo;
		this.nome = nome;
		this.genero = genero;
		this.tipo = tipo;
		this.nota = nota;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getNota() {
		return nota;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Jogo [codigo=" + codigo + ", nome=" + nome + ", genero=" + genero + ", tipo=" + tipo +", nota=" + nota +  "]";
	}	
}