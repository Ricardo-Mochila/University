/*//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
	int tamanho;
	Scanner sc;
	Random r;
	Tabuleiro tab;

	public Main() {
		this.sc = new Scanner(System.in);
		this.r = new Random();
	}

	public void options() {
		int option = 0;
		System.out.println("Escolha a opcao pretendida:");
		System.out.println("1 - Jogo ");
		System.out.println("2 - Creditos");
		System.out.println("3 - Sair do jogo");

		while(option < 1 || option > 3) {
			try {
				option = this.sc.nextInt();
				if (option == 1) {
					int[] lista = new int[2];
					lista = this.tabInputs();
					this.tab = new Tabuleiro(lista[0], lista[1]);
					this.jogo();
				}

				if (option == 2) {
					this.creditos();
					this.options();
				}

				if (option == 3) {
					return;
				}
			} catch (InputMismatchException var3) {
				System.out.println("\nPor favor insira um numero\n");
				this.sc.next();
			}
		}

	}

	void creditos() {
		System.out.println("\nTrabalho realizado por:");
		System.out.println("Maria Correia 39836");
		System.out.println("Mafalda Rosa 40021 \n");
	}

	int[] tabInputs() {
		int tamanho = 0;
		int cores = 0;
		int[] lista = new int[2];

		while(true) {
			try {
				System.out.println("Insira o tamanho do tabuleiro:");
				tamanho = this.sc.nextInt();
				System.out.println("Insira o numero de cores do tabuleiro:");
				cores = this.sc.nextInt();
			} catch (InputMismatchException var5) {
				this.sc.next();
				System.out.println("\nPor favor insira numeros\n");
			}

			if (tamanho >= 5 && tamanho <= 15 && cores <= 9 && cores >= 2 && cores <= tamanho - 1) {
				this.tamanho = tamanho;
				lista[0] = tamanho;
				lista[1] = cores;
				return lista;
			}

			System.out.println("Insira valores validos\n");
		}
	}

	int[] coordenadas() {
		int x = 0;
		int y = 0;
		int[] coordenadas = new int[2];
		boolean var = true;

		while(var) {
			try {
				System.out.println("Insira a posicao x:");
				x = this.sc.nextInt();
				System.out.println("Insira a posicao y:");
				y = this.sc.nextInt();
			} catch (InputMismatchException var6) {
				System.out.println("Por favor insira numeros");
				this.sc.next();
			}

			if (y > -1 && y < this.tamanho && x > -1 && x < this.tamanho) {
				var = false;
			} else {
				System.out.println("Insira coordenadas dentro do tabuleiro");
			}
		}

		coordenadas[0] = x;
		coordenadas[1] = y;
		return coordenadas;
	}

	void jogo() {
		this.tab.printTabuleiro();

		while(!this.tab.fim_de_jogo()) {
			int[] xy = new int[2];
			int[][] coords = new int[this.tamanho * this.tamanho][2];

			int x;
			for(x = 0; x < coords.length; ++x) {
				coords[x][0] = 33;
				coords[x][1] = 33;
			}

			xy = this.coordenadas();
			x = xy[0];
			int y = xy[1];
			coords = this.tab.verifica_vizinhos(x, y, coords);
			if (this.tab.verifica_pos(x, y, coords)) {
				this.tab.jogadas(coords);
				this.tab.printTabuleiro();
			} else {
				System.out.println("A posicao e invalida");
			}
		}

		System.out.println("O jogo terminou");
		System.out.println("A sua pontuacao foi:" + this.tab.pontos());
	}

	public static void main(String[] args) {
		Main menu2 = new Main();
		menu2.options();
		menu2.jogo();
	}
}
*/