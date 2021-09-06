package com.ti2cc;
import java.util.Scanner;
public class Principal {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		DAO dao = new DAO();
		
		Jogo jogo = new Jogo(6, "league of legends", "moba","multiplayer", 90);
		
		Jogo[] jogos;
		
		dao.conectar();
		
		
		int opcao = 0;
		
		while (opcao != 6) {
			System.out.println("Escolha a ação que deseja realizar:");
			System.out.println("1 - Listar");
			System.out.println("2 - Inserir");
			System.out.println("3 - Excluir");
			System.out.println("4 - Atualizar:");
			System.out.println("5 - Listar jogos multiplayer");
			System.out.println("6 - Sair");
			opcao = scan.nextInt();
			switch (opcao) {
			case 1: 
				//Mostrar jogos
				jogos = dao.getJogos();
				System.out.println("==== Mostrar jogos === ");		
				for(int i = 0; i < jogos.length; i++) {
					System.out.println(jogos[i].toString());
				} 
				break;
			case 2:
				//Inserir um elemento na tabela
				if(dao.inserirJogo(jogo) == true) {
					System.out.println("Inserção com sucesso -> " + jogo.toString());
				}
				break;
			case 3:
				//Excluir jogo
				dao.excluirJogo(jogo.getCodigo());
				System.out.println("Jogo excluido");
				break;
			case 4: 
				//Atualizar jogo
				jogo.setNota(95);
				dao.atualizarJogo(jogo);
				System.out.println("Nota do jogo atualizada para 95");;
				break;
			case 5: 
				//Mostrar jogos multiplayer		
				System.out.println("==== Mostrar jogos multiplayer === ");
				jogos = dao.getJogosMultiplayer();
				for(int i = 0; i < jogos.length; i++) {
					System.out.println(jogos[i].toString());
				}
				break;
			case 6: 
				dao.close();
				System.out.println("Programa finalizado");
				break;
			}
			
		}
		
	}
	
}