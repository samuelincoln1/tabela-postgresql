package com.ti2cc;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		//Inserir um elemento na tabela
		Jogo jogo = new Jogo(6, "league of legends", "moba","multiplayer", 90);
		if(dao.inserirJogo(jogo) == true) {
			System.out.println("Inserção com sucesso -> " + jogo.toString());
		}
		
		//Mostrar jogos multiplayer		
		System.out.println("==== Mostrar jogos multiplayer === ");
		Jogo[] jogos = dao.getJogosMultiplayer();
		for(int i = 0; i < jogos.length; i++) {
			System.out.println(jogos[i].toString());
		}

		//Atualizar jogo
		jogo.setNota(95);
		dao.atualizarJogo(jogo);

		//Mostrar jogos multiplayer		
		System.out.println("==== Mostrar jogos multiplayer === ");
		jogos = dao.getJogosMultiplayer();
		for(int i = 0; i < jogos.length; i++) {
			System.out.println(jogos[i].toString());
		}
		
		//Excluir jogo
		dao.excluirJogo(jogo.getCodigo());
		
		//Mostrar jogos
		jogos = dao.getJogos();
		System.out.println("==== Mostrar jogos === ");		
		for(int i = 0; i < jogos.length; i++) {
			System.out.println(jogos[i].toString());
		}
		
		dao.close();
	}
}