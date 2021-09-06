package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "samuel";
		String password = "teste";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirJogo(Jogo jogo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO jogos (codigo, nome, genero, tipo, nota) "
					       + "VALUES ("+jogo.getCodigo()+ ", '" + jogo.getNome() + "', '"  
					       + jogo.getGenero() + "', '" + jogo.getTipo() + "', ' " + jogo.getNota() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarJogo(Jogo jogo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE jogos SET nome = '" + jogo.getNome() + "', genero = '"  
				       + jogo.getGenero() + "', tipo = '" + jogo.getTipo() + "', nota = '" + jogo.getNota() + "'"
					   + " WHERE codigo = " + jogo.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirJogo(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM jogos WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Jogo[] getJogos() {
		Jogo[] jogos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM jogos");		
	         if(rs.next()){
	             rs.last();
	             jogos = new Jogo[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                jogos[i] = new Jogo(rs.getInt("codigo"), rs.getString("nome"), 
	                		                  rs.getString("genero"), rs.getString("tipo"), rs.getInt("nota"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogos;
	}

	
	public Jogo[] getJogosMultiplayer() {
		Jogo[] jogos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM jogos WHERE jogos.tipo LIKE 'multiplayer'");		
	         if(rs.next()){
	             rs.last();
	             jogos = new Jogo[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                jogos[i] = new Jogo(rs.getInt("codigo"), rs.getString("nome"), 
      		                  rs.getString("genero"), rs.getString("tipo"), rs.getInt("nota"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogos;
	}
}