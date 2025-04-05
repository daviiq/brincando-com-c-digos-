//Importação de bibliotecas necessárias:
import java.util.Scanner;
import java.util.Random;

// Classe principal do jogo de adivinhação
public class Jogo {

  // Ideia principal do jogo:
  public static void main(String[] args) {

    Random random = new Random();
    int numeroAleatorio = random.nextInt(2) + 1; // Número aleatório entre 1 e 90
    Scanner scanner = new Scanner(System.in);
    int chute = 0;
    int tentativas = 7;

    // Mensagens de boas-vindas e instruções:
    System.out.println("Bem-vindo ao jogo de adivinhação!");
    System.out.println("Tente adivinhar o número entre 1 e 90.");
    System.out.println("Você tem " + tentativas + " tentativas.");
    System.out.println("Digite 100 para sair do jogo.");

    // Palpite do usuário:
    while (chute != numeroAleatorio && tentativas > 0) {
      System.out.print("Digite seu palpite: ");
      chute = scanner.nextInt();

      // Verifica se o usuário deseja sair do jogo:
      if (chute == 100) {
        System.out.println("Você saiu do jogo.");
        break;
      }

      // Verifica se o palpite está dentro do intervalo permitido:
      if (chute < 1 || chute > 90) {
        System.out.println("Por favor, digite um número entre 1 e 90.");
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
      } else if (diferenca <= 10) {
        System.out.println("Morno! Você está perto.");
      } else if (diferenca <= 20) {
        System.out.println("Já esteve mais longe.");
      } else {
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
