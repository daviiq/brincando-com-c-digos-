//Importação de bibliotecas necessárias:
import java.util.Scanner;
import java.util.Random;

// Classe principal do jogo de adivinhação
public class Jogo {

  // Ideia principal do jogo:
  public static void main(String[] args) {

  // Criação de um objeto Scanner para ler a entrada do usuário:
    Scanner scanner = new Scanner(System.in);

    int maxNumero = 90;
    int tentativas = 7;
    int dificuldade = 0;
    int chute = 0;

    // Loop para garantir que o usuário escolha um nível de dificuldade válido:
    while (true) {

      // Mensagem de boas-vindas e opções de dificuldade:

      System.out.println("Escolha o nível de dificuldade:");
      System.out.println("1. Fácil (1 a 50, 10 tentativas)");
      System.out.println("2. Médio (1 a 90, 7 tentativas)");
      System.out.println("3. Difícil (1 a 150, 7 tentativas)");
      System.out.println("4. Nível Passos (1 a 200, 9 tentativas)");
      System.out.println("Digite sua opção (1-4): ");
      dificuldade = scanner.nextInt();
      if (dificuldade < 1 || dificuldade > 4) {
      break;
      } else {
        System.out.println("Dificuldade escolhida: " + dificuldade);
        break;
      }
    }
    
    // Escolha do nível de dificuldade:
    switch (dificuldade) {
      case 1:
        maxNumero = 50;
        tentativas = 10;
        break;
      case 2:
        maxNumero = 90;
        tentativas = 7;
        break;
      case 3:
        maxNumero = 150;
        tentativas = 5;
        break;
      case 4:
        maxNumero = 200;
        tentativas = 9;
        break;

      // Caso o usuário escolha uma opção inválida, define um padrão:
      default:
        System.out.println("Dificuldade inválida. Usando modo médio.");
        maxNumero = 90;
        tentativas = 7;
        break;
    }

    Random random = new Random();
    int numeroAleatorio = random.nextInt(maxNumero) + 1;
    int tentativasRestantes = tentativas;
    boolean acertou = false;
    chute = 0;

    // Mensagens de boas-vindas e instruções:
    System.out.println("Bem-vindo ao jogo de adivinhação!");
    System.out.println("Tente adivinhar o número entre 1 e 90.");
    System.out.println("Ao chutar uma vez, você receberá dicas sobre a proximidade do seu palpite.");
    System.out.println("Caso a mensagem seja Quente, você está muito perto do número, sendo uma distância de 5 ou menos.");
    System.out.println("Caso a mensagem seja Morno, você está perto do número, sendo uma distância de 6 a 10 do número.");
    System.out.println("Caso a mensagem seja Já esteve mais longe, você está a uma distância de 11 a 20 do número.");
    System.out.println("Caso a mensagem seja Frio, você está longe do número, sendo uma distância de 21 ou mais do número.");
    System.out.println("Você tem " + tentativas + " tentativas.");
    System.out.println("Digite 300 para sair do jogo.");

    // Palpite do usuário:
    while (chute != numeroAleatorio && tentativas > 0) {
      System.out.print("Digite seu palpite: ");
      chute = scanner.nextInt();

      // Verifica se o usuário deseja sair do jogo:
      if (chute == 300) {
        System.out.println("Você saiu do jogo.");
        break;
      }

      // Verifica se o palpite está dentro do intervalo permitido:
      if (chute < 1 || chute > maxNumero) {
        System.out.println("Por favor, digite um número entre 1 e " + maxNumero + ".");
        continue;
      }

      // Verifica se acertou o número:
      if (chute == numeroAleatorio) {
        System.out.println("Parabéns! Você acertou!");
        break;
      }

      // Calcula a diferença entre o palpite e o número aleatório:
      int diferenca = Math.abs(chute - numeroAleatorio);

      // Dá dicas com base na diferença:
      if (diferenca <= 5) {
        System.out.println("Quente! Você está muito perto.");
      } 
      else if (diferenca <= 10) {
        System.out.println("Morno! Você está perto.");
      }
      else if (diferenca <= 20) {
        System.out.println("Já esteve mais longe.");
      }
       else {
        System.out.println("Frio! Você está longe.");
      }

      // Diminui o número de tentativas restantes :
      tentativas--;
      System.out.println("Tentativas restantes: " + tentativas);
    }

    if (tentativas == 0 && chute != numeroAleatorio) {
      System.out.println("Você perdeu! O número era: " + numeroAleatorio);
    }

    // Pergunta se o usuário deseja jogar novamente:
    System.out.println("Deseja jogar novamente? (s/n)");
    String resposta = scanner.next();
    if (resposta.equalsIgnoreCase("s")) {
      main(args); 
    } else {
      System.out.println("Obrigado por jogar!");
    }

    scanner.close();
  }
}
