import java.util.Scanner;

public class main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    // digitar o número 1:

    System.out.print("Digite n1: ");
    double num1 = scanner.nextDouble();
    
    //Escolher a operação matemática:

    System.out.print("eslconhe a operação (+, -, *, /): ");
    char operacao = scanner.next().charAt(0);
    
    //Digitar o número 2:
    System.out.print("Digite n2: ");
    double num2 = scanner.nextDouble();

    //Resultado:
    double resultado;
    switch (operacao) {
      case '+':
        resultado = num1 + num2;
        break;
      case '-':
        resultado = num1 - num2;
        break;
      case '*':
        resultado = num1 * num2;
        break;
      case '/':
        if (num2 != 0) {
          resultado = num1 / num2;
        } else {
          System.out.println("Erro: Nao sabes cacular em java!");
          return;
        }
        break;
      default:
        System.out.println("vai se foder deu exeçao nao prevista no incontrol!");
        return;
    }
    // Exibir o resultado:
    System.out.println("Resultado: " + resultado);
    scanner.close();
  }
}
