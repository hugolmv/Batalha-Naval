import java.util.Random;
import java.util.Scanner;

//Construtor:Inicializa o jogo
//Cria e inicializa os tabuleiros oculto e visível, mostra o tabuleiro visível, insere os navios e determina o resultado do jogo (vitória ou derrota)
public class BatalhaNaval {
    public BatalhaNaval() {
        String[][] tabuleiroOculto = new String[8][8];
        String[][] tabuleiroVisivel = new String[8][8];
        inicializacao(tabuleiroVisivel, tabuleiroOculto);
        mostrarTabuleiro(tabuleiroVisivel);
        int resultado = inserirNavios(tabuleiroOculto, tabuleiroVisivel);

        if (resultado == 1) {
            System.out.println();
            mostrarTabuleiroFinal(tabuleiroOculto);
            System.out.println("Você ganhou o jogo.");

        } else {
            System.out.println();
            mostrarTabuleiroFinal(tabuleiroOculto);
            System.out.println("Você perdeu o jogo.");
        }

    }

    // Gerencia a inserção dos navios e ataques durante o jogo
    // Solicita ao jogador as coordenadas dos ataques, atualiza os tabuleiros de
    // acordo com os ataques e verifica se o jogador ganhou ou perdeu o jogo.
    // String[][] tabuleiroOculto: Tabuleiro com a posição dos navios escondidos
    // String[][] tabuleiroVisivel: Tabuleiro que mostra ao jogador os ataques
    // realizados
    public int inserirNavios(String[][] tabuleiroOculto, String[][] tabuleiroVisivel) {
        // Declaração de Variáveis:
        Scanner scan = new Scanner(System.in);
        int jogadas = 0;
        int naviosRestantes = 10;

        // Entrada do Usuário:
        while (jogadas < 30 && naviosRestantes != 0) {
            System.out.println("Digite as coordenadas para atacar (linha e coluna, separadas por espaço):");
            int p = scan.nextInt();
            int q = scan.nextInt();
            p--;
            q--;

            // Verificação de Coordenadas Válidas e Verificação de Posição Já Atacada:
            if (p >= 0 && p < 8 && q >= 0 && q < 8) {
                if (tabuleiroOculto[p][q] == "O" || tabuleiroOculto[p][q] == "X") {
                    System.out.println("Posição já jogada.");
                    jogadas--;
                } else {
                    // Realização do Ataque:
                    System.out.println("Ataque realizado.");
                    jogadas++;

                    // Verificação de Acerto ou Erro:
                    if (tabuleiroOculto[p][q] == "N") {
                        System.out.println("Navio destruído.");
                        tabuleiroVisivel[p][q] = "X";
                        tabuleiroOculto[p][q] = "X";
                        naviosRestantes--;
                    } else {
                        System.out.println("Você errou.");
                        tabuleiroVisivel[p][q] = "O";
                        tabuleiroOculto[p][q] = "O";
                    }
                }
                // Impressão do Tabuleiro Atualizado:
                mostrarTabuleiro(tabuleiroVisivel);
            } else {
                // Coordenadas Inválidas:
                System.out.println("Posição inválida");
                System.out.println("Os números precisam estar no intervalo (1 - 8)");
                jogadas--;
            }

        }
        scan.close();

        // Fim do jogo:
        if (naviosRestantes == 0) {
            return 1; // Ganhou
        } else {
            return -1; // Perdeu
        }
    }

    // Inicializa os tabuleiros e posiciona os navios aleatoriamente, define todos
    // os elementos dos tabuleiro e posiciona 10 navios (N) aleatoriamente no
    // tabuleiro oculto.
    // String[][] tabuleiroVisivel: Tabuleiro que será mostrado ao jogador
    // String[][] tabuleiroOculto: Tabuleiro com a posição dos navios escondidos
    public void inicializacao(String[][] tabuleiroVisivel, String[][] tabuleiroOculto) {
        Random aleatorio = new Random();
        // Inicialização dos Tabuleiros:
        for (int i = 0; i < tabuleiroOculto.length; i++) {
            for (int j = 0; j < tabuleiroOculto[i].length; j++) {
                tabuleiroOculto[i][j] = "~";
                tabuleiroVisivel[i][j] = "~";
            }
        }

        // Posicionamento dos Navios:
        for (int h = 0; h < 10; h++) {
            int a = aleatorio.nextInt(8);
            int b = aleatorio.nextInt(8);

            if (tabuleiroOculto[a][b] != "N") {
                tabuleiroOculto[a][b] = "N";
            } else {
                h--;
            }
        }
    }

    // Mostra o estado atual do tabuleiro visível e imprime o tabuleiro visível na
    // tela, mostrando a posição dos ataques realizados
    // String[][] tabuleiroVisivel: Tabuleiro que será mostrado ao jogador
    public void mostrarTabuleiro(String[][] tabuleiroVisivel) {
        // Impressao dos numeros das colunas:
        System.out.print("  ");
        for (int i = 0; i < tabuleiroVisivel.length; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();

        // Impressao das Linhas e do conteudo do tabuleiro:
        for (int i = 0; i < tabuleiroVisivel.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < tabuleiroVisivel[i].length; j++) {
                System.out.print(tabuleiroVisivel[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Mostra o estado final do tabuleiro oculto
    // Imprime o tabuleiro oculto na tela, mostrando todas as posições dos navios,
    // tanto os que foram atacados e os que não foram
    // String[][] tabuleiroOculto: Tabuleiro com a posição dos navios escondidos
    public void mostrarTabuleiroFinal(String[][] tabuleiroOculto) {
        // Impressão dos números das colunas:
        System.out.print("  ");
        for (int i = 0; i < tabuleiroOculto.length; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();

        // Impressão das linhas e do conteúdo do tabuleiro:
        for (int i = 0; i < tabuleiroOculto.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < tabuleiroOculto[i].length; j++) {
                System.out.print(tabuleiroOculto[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Ponto de entrada do programa.
    public static void main(String[] args) {
        new BatalhaNaval();
    }
}