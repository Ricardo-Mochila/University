/*//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Tabuleiro extends  Main{
	Random r;
	int tamanho;
	int[][] tab;
	int cores;
	int[] coordenadas;
	int[] colunas;
	JFrame jframe = new JFrame();
	JButton botao = new JButton();

	Tabuleiro(int tamanho, int cores) {
		this.tamanho = tamanho;
		this.tab = new int[tamanho][tamanho];
		this.cores = cores;
		this.criarTabuleiro();
		this.printTabuleiro();
		this.coordenadas = new int[tamanho * tamanho];
		this.colunas = new int[tamanho];
	}

	public void graphics(int numeros, JButton botao) {
		if (numeros == 1) {
			botao.setBackground(Color.red);
		} else if (numeros == 2) {
			botao.setBackground(Color.green);
		} else if (numeros == 3) {
			botao.setBackground(Color.yellow);
		} else if (numeros == 4) {
			botao.setBackground(Color.blue);
		} else if (numeros == 5) {
			botao.setBackground(Color.pink);
		} else if (numeros == 6) {
			botao.setBackground(Color.orange);
		} else if (numeros == 7) {
			botao.setBackground(Color.magenta);
		} else if (numeros == 8) {
			botao.setBackground(Color.GRAY);
		} else if (numeros == 9) {
			botao.setBackground(Color.BLACK);
		}

	}

	void criarTabuleiro() {
		jframe.setTitle("Fusion");
		jframe.setLayout(new GridLayout(this.tamanho, this.tamanho, 5, 5));
		jframe.setVisible(true);
		jframe.setSize(500, 500);

		for(int i = 0; i < this.tamanho; ++i) {
			for(int j = 0; j < this.tamanho; ++j) {
				int numeros = r.nextInt( cores) + 1;
				tab[i][j] = numeros;
				botao = new JButton(String.valueOf(tab[i][j]));
				botao.setSize(50, 50);
				jframe.add(botao);
				botao.setVisible(true);

			}
		}

	}

	void printTabuleiro() {
		String s = new String();

		for(int i = 0; i < this.tab.length; ++i) {
			for(int j = 0; j < this.tab[0].length; ++j) {
				s = s + this.tab[i][j] + " ";
				System.out.println(s);
			}

			s = s + "\n";
		}

		System.out.println(s);
	}

	public int[][] verifica_vizinhos(int x, int y, int[][] coords) {
		try {
			if (this.tab[y][x - 1] == this.tab[y][x] && !this.verifica(x - 1, y, coords) && this.tab[y][x - 1] != 0) {
				coords = this.insereCoord(x, y, coords);
				coords = this.verifica_vizinhos(x - 1, y, coords);
			}
		} catch (IndexOutOfBoundsException var8) {
			;
		}

		try {
			if (this.tab[y][x + 1] == this.tab[y][x] && !this.verifica(x + 1, y, coords) && this.tab[y][x + 1] != 0) {
				coords = this.insereCoord(x, y, coords);
				coords = this.verifica_vizinhos(x + 1, y, coords);
			}
		} catch (IndexOutOfBoundsException var7) {
			;
		}

		try {
			if (this.tab[y - 1][x] == this.tab[y][x] && !this.verifica(x, y - 1, coords) && this.tab[y - 1][x] != 0) {
				coords = this.insereCoord(x, y, coords);
				coords = this.verifica_vizinhos(x, y - 1, coords);
			}
		} catch (IndexOutOfBoundsException var6) {
			;
		}

		try {
			if (this.tab[y + 1][x] == this.tab[y][x] && !this.verifica(x, y + 1, coords) && this.tab[y + 1][x] != 0) {
				coords = this.insereCoord(x, y, coords);
				coords = this.verifica_vizinhos(x, y + 1, coords);
			}
		} catch (IndexOutOfBoundsException var5) {
			;
		}

		if (!this.verifica(x, y, coords)) {
			this.insereCoord(x, y, coords);
		}

		return coords;
	}

	public boolean verifica(int x, int y, int[][] coords) {
		boolean result = false;

		for(int i = 0; i < coords.length; ++i) {
			if (coords[i][0] == x && coords[i][1] == y) {
				result = true;
			}
		}

		return result;
	}

	public int[][] insereCoord(int x, int y, int[][] coords) {
		for(int i = 0; i < coords.length; ++i) {
			if (coords[i][0] == 33) {
				coords[i][0] = x;
				coords[i][1] = y;
				break;
			}
		}

		return coords;
	}

	public boolean verifica_pos(int x, int y, int[][] coords) {
		coords = this.verifica_vizinhos(x, y, coords);
		return y >= 0 && x >= 0 && y <= this.tamanho - 1 && x <= this.tamanho - 1 && coords[1][0] != 33 && this.tab[y][x] != 0;
	}

	public boolean verificaColunas() {
		for(int x = 0; x < this.tab.length; ++x) {
			boolean var1 = true;

			for(int y = 0; y < this.tab.length; ++y) {
				if (this.tab[y][x] == 0 && y == this.tab.length - 1) {
					for(int i = 0; i < this.colunas.length; ++i) {
						if (this.colunas[i] == x) {
							var1 = false;
						}
					}

					if (var1) {
						return true;
					}
				} else if (this.tab[y][x] != 0) {
					break;
				}
			}
		}

		return false;
	}

	void arrumaTabuleiro(int change) {
		int y;
		int repeat;
		int x;
		int var;
		if (change == 0) {
			for(y = 0; y < this.tab.length; ++y) {
				for(repeat = 0; repeat < this.tab.length; ++repeat) {
					for(x = this.tab.length - 1; x > 0; --x) {
						if (this.tab[y][x] == 0 && this.tab[y][x - 1] != 0) {
							var = this.tab[y][x];
							this.tab[y][x] = this.tab[y][x - 1];
							this.tab[y][x - 1] = var;
						}
					}
				}
			}
		} else {
			for(y = 0; y < this.tab.length; ++y) {
				for(repeat = 0; repeat < this.tab.length; ++repeat) {
					for(x = this.tab.length - 1; x > 0; --x) {
						if (this.tab[x][y] == 0 && this.tab[x - 1][y] != 0) {
							var = this.tab[x][y];
							this.tab[x][y] = this.tab[x - 1][y];
							this.tab[x - 1][y] = var;
						}
					}
				}
			}
		}

	}

	public void jogadas(int[][] coords) {
		for(int i = 0; i < coords.length; ++i) {
			if (coords[i][0] != 33) {
				this.tab[coords[i][1]][coords[i][0]] = 0;
			}
		}

		this.arrumaTabuleiro(1);
		if (this.verificaColunas()) {
			this.arrumaTabuleiro(0);
		}

	}

	public boolean fim_de_jogo() {
		for(int x = 0; x < this.tamanho; ++x) {
			for(int y = 0; y < this.tamanho; ++y) {
				int[][] coords = new int[this.tamanho * this.tamanho][2];

				for(int i = 0; i < coords.length; ++i) {
					coords[i][0] = 33;
				}

				coords = this.verifica_vizinhos(x, y, coords);
				if (coords[1][0] != 33) {
					return false;
				}
			}
		}

		return true;
	}

	public int pontos() {
		int valor = this.tamanho * this.tamanho;

		for(int i = 0; i < this.tab.length; ++i) {
			for(int j = 0; j < this.tab.length; ++j) {
				if (this.tab[i][j] != 0) {
					--valor;
				}
			}
		}

		return valor;
	}
}
*/