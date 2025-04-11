import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Jogo extends JFrame {

    private int maxNumero;
    private int tentativas;
    private int numeroAleatorio;
    private int dificuldade;

    private JTextField inputPalpite;
    private JTextArea areaMensagens;
    private JButton botaoPalpite;
    private JButton botaoNovoJogo;

    public Jogo() {
        setTitle("Jogo de Adivinhação");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
        escolherDificuldade();
        novoJogo();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        areaMensagens = new JTextArea();
        areaMensagens.setEditable(false);
        areaMensagens.setLineWrap(true);
        areaMensagens.setWrapStyleWord(true);
        add(new JScrollPane(areaMensagens), BorderLayout.CENTER);

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout());

        inputPalpite = new JTextField(10);
        botaoPalpite = new JButton("Chutar");
        botaoNovoJogo = new JButton("Novo Jogo");

        painelInferior.add(new JLabel("Seu palpite:"));
        painelInferior.add(inputPalpite);
        painelInferior.add(botaoPalpite);
        painelInferior.add(botaoNovoJogo);

        add(painelInferior, BorderLayout.SOUTH);

        botaoPalpite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarPalpite();
            }
        });

        botaoNovoJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                escolherDificuldade();
                novoJogo();
            }
        });
    }

    private void escolherDificuldade() {
        String[] opcoes = {
            "1. Fácil (1 a 50, 10 tentativas)",
            "2. Médio (1 a 90, 7 tentativas)",
            "3. Difícil (1 a 150, 5 tentativas)",
            "4. Nível Passos (1 a 200, 7 tentativas)",
            "5. Boa Sorte (1 a 1000, 12 tentativas)"
        };
        String escolha = (String) JOptionPane.showInputDialog(
            this,
            "Escolha a dificuldade:",
            "Dificuldade",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[1]
        );

        if (escolha == null) System.exit(0);

        dificuldade = Integer.parseInt(escolha.substring(0, 1));

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
                tentativas = 7;
                break;
            case 5:
                maxNumero = 1000;
                tentativas = 12;
                break;
            default:
                maxNumero = 90;
                tentativas = 7;
                break;
        }
    }

    private void novoJogo() {
        numeroAleatorio = new Random().nextInt(maxNumero) + 1;
        areaMensagens.setText("Adivinhe um número entre 1 e " + maxNumero + ".\n");
        areaMensagens.append("Você tem " + tentativas + " tentativas.\n");
        inputPalpite.setEditable(true);
        botaoPalpite.setEnabled(true);
    }

    private void verificarPalpite() {
        String entrada = inputPalpite.getText();
        try {
            int chute = Integer.parseInt(entrada);

            if (chute == 2000) {
                JOptionPane.showMessageDialog(this, "Você saiu do jogo.");
                System.exit(0);
            }

            if (chute < 1 || chute > maxNumero) {
                areaMensagens.append("Digite um número entre 1 e " + maxNumero + ".\n");
                return;
            }

            tentativas--;

            if (chute == numeroAleatorio) {
                areaMensagens.append("Parabéns! Você acertou o número!\n");
                inputPalpite.setEditable(false);
                botaoPalpite.setEnabled(false);
                return;
            }

            int diferenca = Math.abs(chute - numeroAleatorio);

            if (diferenca <= 5) {
                areaMensagens.append("Quente! Está muito perto.\n");
            } else if (diferenca <= 10) {
                areaMensagens.append("Morno! Está perto.\n");
            } else if (diferenca <= 20) {
                areaMensagens.append("Já esteve mais longe.\n");
            } else if (dificuldade == 5 && diferenca <= 50) {
                areaMensagens.append("50! Ainda longe.\n");
            } else {
                areaMensagens.append("Frio! Está longe.\n");
            }

            areaMensagens.append("Tentativas restantes: " + tentativas + "\n");

            if (tentativas == 0) {
                areaMensagens.append("Você perdeu! O número era: " + numeroAleatorio + "\n");
                inputPalpite.setEditable(false);
                botaoPalpite.setEnabled(false);
            }

        } catch (NumberFormatException e) {
            areaMensagens.append("Entrada inválida. Digite um número.\n");
        } finally {
            inputPalpite.setText("");
            inputPalpite.requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Jogo().setVisible(true);
            }
        });
    }
}
