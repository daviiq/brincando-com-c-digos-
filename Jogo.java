//Importação das bibliotecas necessárias:

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.text.*;

//Tela inicial: 

 class TelaInicio extends JFrame {
    private JRadioButton temaClaro, temaEscuro;
    private JComboBox<String> comboDificuldade;
    private JButton botaoIniciar;
    private int dificuldadeSelecionada;
    private boolean temaClaroSelecionado = true;

    public TelaInicio() { 
        setTitle("Bem Vindo ao Jogo da Adivinhação");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

     // Escolha do Tema:

     JPanel painelTema = new JPanel();
     painelTema.add(new JLabel("Escolha o Tema:"));
     temaClaro = new JRadioButton("Claro ☀️", true);
     temaEscuro = new JRadioButton("Escuro 🌙");
     ButtonGroup grupoTema = new ButtonGroup();
     grupoTema.add(temaClaro);
     grupoTema.add(temaEscuro);
     painelTema.add(temaClaro);
     painelTema.add(temaEscuro);
     add(painelTema);

     //Seleção de dificuldade:

     JPanel painelDificuldade = new JPanel();
     painelDificuldade.add(new JLabel("Escolha a dificuldade: "));
     String[] opcoes = {
        "1. Fácil (1 a 50, 10 tentativas)",
        "2. Médio (1 a 100, 9 tentativas)",
        "3. Difícil (1 a 200, 8 tentativas)",
        "4. Nível Passos (1 a 400, 7 tentativas)",
        "5. Boa Sorte."
    };
     comboDificuldade = new JComboBox<>(opcoes);
     painelDificuldade.add(comboDificuldade);
     add(painelDificuldade);

     //Botão Iniciar o jogo:

     botaoIniciar = new JButton("Iniciar Jogo");
     botaoIniciar.addActionListener(e -> iniciarJogo());
     JPanel painelBotao = new JPanel();
     painelBotao.add(botaoIniciar);
     add(painelBotao);
     }

     private void iniciarJogo() {
        dificuldadeSelecionada = comboDificuldade.getSelectedIndex() + 1;
        temaClaroSelecionado = temaClaro.isSelected();

        dispose(); //Fecha a tela de inicio
        new Jogo(dificuldadeSelecionada, temaClaroSelecionado).setVisible(true); //Abre o jogo
 
    }
}
 

//Filtro para ler apenas números: 

class FiltroNumerico extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
    throws BadLocationException {
        if (string.matches("\\d+")){
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
    throws BadLocationException {
    if(text.matches("\\d+")) {
        super.replace(fb, offset, length, text, attrs);
    }
  }
}

//Inicio do código:
public class Jogo extends JFrame {

    //criação das variáveis + botões e áreas de palpite:
    private int maxNumero;
    private int tentativas;
    private int numeroAleatorio;
    private int dificuldade;

    private JTextField inputPalpite;
    private JTextArea areaMensagens;
    private JButton botaoPalpite;
    private JButton botaoNovoJogo;

    //Criação do Front-End do jogo:

    //Criação da posição da página:
    public Jogo(int dificuldadeSelecionada, boolean temaClaro) {
        this.dificuldade = dificuldadeSelecionada;
        setTitle("Jogo de Adivinhação");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
        definirParametrosDificuldade(dificuldadeSelecionada);
        novoJogo();

        if (temaClaro) {
            aplicarTemaClaro();
        }
        else {
            aplicarTemaEscuro();
        }
    }

    //Temas personalizados:
    private void aplicarTemaClaro() {
        getContentPane().setBackground(Color.white);
        areaMensagens.setBackground(Color.WHITE);
        areaMensagens.setForeground(Color.BLACK);
        inputPalpite.setBackground(Color.WHITE);
        inputPalpite.setForeground(Color.BLACK);
        botaoPalpite.setBackground(Color.LIGHT_GRAY);
        botaoPalpite.setForeground(Color.BLACK);
        botaoNovoJogo.setBackground(Color.LIGHT_GRAY);
        botaoNovoJogo.setForeground(Color.BLACK);
    }

    private void aplicarTemaEscuro() {
        getContentPane().setBackground(Color.DARK_GRAY);
        areaMensagens.setBackground(new Color(40,40,40));
        areaMensagens.setForeground(Color.WHITE);
        inputPalpite.setBackground(new Color(70,70,70));
        inputPalpite.setForeground(Color.WHITE);
        botaoPalpite.setBackground(new Color(100,100,100));
        botaoPalpite.setForeground(Color.WHITE);
        botaoNovoJogo.setBackground(new Color(100,100,100));
        botaoNovoJogo.setForeground(Color.WHITE);

    }

    //Criaçao da janela:
    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        //Tornando o texto não editável, e quebras de linha:
        areaMensagens = new JTextArea();
        areaMensagens.setEditable(false);
        areaMensagens.setLineWrap(true);
        areaMensagens.setWrapStyleWord(true);
        add(new JScrollPane(areaMensagens), BorderLayout.CENTER); //Isso faz com que as mensagens fiquem no centro da janela

        //Criação de painel, layout, botões, filtro:

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout());

        inputPalpite = new JTextField(10);
        ((AbstractDocument) inputPalpite.getDocument()).setDocumentFilter(new FiltroNumerico());
        inputPalpite.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botaoPalpite.doClick();
                }
            }
        }
    );

        botaoPalpite = new JButton("Chutar");
        botaoNovoJogo = new JButton("Novo Jogo");

        painelInferior.add(new JLabel("Seu palpite:"));
        painelInferior.add(inputPalpite);
        painelInferior.add(botaoPalpite);
        painelInferior.add(botaoNovoJogo);

        add(painelInferior, BorderLayout.SOUTH);

        //Definição da ação dos botões:

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

         // Menu de Tema:

         JMenuBar menuBar = new JMenuBar();
         JMenu menuTema = new JMenu("Tema");
         JMenuItem itemClaro = new JMenuItem("Claro ☀️");
         JMenuItem itemEscuro = new JMenuItem("Escuro 🌙");

         itemClaro.addActionListener(e -> aplicarTemaClaro());
         itemEscuro.addActionListener(e -> aplicarTemaEscuro());

         menuTema.add(itemEscuro);
         menuTema.add(itemClaro);
         menuBar.add(menuTema);
         setJMenuBar(menuBar);
    }

    //Define a dificuldade do jogo:

    private void escolherDificuldade() {
        String[] opcoes = {
            "1. Fácil (1 a 50, 10 tentativas)",
            "2. Médio (1 a 100, 9 tentativas)",
            "3. Difícil (1 a 200, 8 tentativas)",
            "4. Nível Passos (1 a 400, 7 tentativas)",
            "5. Boa Sorte."
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

        //Se o jogador cancelar, o jogo fecha:

        if (escolha == null) System.exit(0);

        //dificuldade: 

        dificuldade = Integer.parseInt(escolha.substring(0, 1));
    }

        //O que cada dificuldade tem:
        private void definirParametrosDificuldade(int dificuldade) {
        switch (dificuldade) {
            case 1:
                maxNumero = 50;
                tentativas = 10;
                break;
            case 2:
                maxNumero = 100;
                tentativas = 9;
                break;
            case 3:
                maxNumero = 200;
                tentativas = 8;
                break;
            case 4:
                maxNumero = 400;
                tentativas = 7;
                break;
            case 5:
                maxNumero = 1000;
                tentativas = 8;
                break;
             default:
                maxNumero = 90;
                tentativas = 7;
                break;
        }
    }


    //Mostra as regras do jogo: 

    private void mostrarRegras() {
        String regras = "Bem-vindo ao Jogo de Adivinhação!\n\n"
                      + "As mensagens de dica significam:\n"
                      + "🔥 Quente: Você está entre 1 a 5 números de distância.\n"
                      + "🌡️ Morno: Você está entre 6 a 10 números de distância.\n"
                      + "📉 Já esteve mais longe: Você está entre 11 a 20 números de distância.\n"
                      + "❄️ Frio: Você está longe do número. \n\n"
                      + "Boa sorte!\n\n";
        areaMensagens.append(regras);
    }

    //Cria um novo jogo, e se a dificuldade for a 5 não mostra as regras:

    private void novoJogo() {
        definirParametrosDificuldade(dificuldade);
        numeroAleatorio = new Random().nextInt(maxNumero) + 1;
        areaMensagens.setText(""); // Limpa mensagens

        if (dificuldade != 5) {
            mostrarRegras();
        }

        //Mostra as tentativas e o número máximo, além de transformar os botões para serem clicáveis:

        areaMensagens.append("Adivinhe um número entre 1 e " + maxNumero + ".\n");
        areaMensagens.append("Você tem " + tentativas + " tentativas.\n");

        inputPalpite.setEditable(true);
        botaoPalpite.setEnabled(true);
    }

    //Verifica se o palpite é o correto, e se o usuário digitar 2000, sai do jogo:

    private void verificarPalpite() {
        String entrada = inputPalpite.getText();
        try {
            int chute = Integer.parseInt(entrada);

            if (chute == 0) {
                JOptionPane.showMessageDialog(this, "Você saiu do jogo.");
                System.exit(0);
            }

            if (chute < 1 || chute > maxNumero) {
                areaMensagens.append("Digite um número entre 1 e " + maxNumero + ".\n");
                return;
            }

            tentativas--;
            areaMensagens.append("Você chutou: " + chute + "\n");

            if (chute == numeroAleatorio) {
                areaMensagens.append("Parabéns! Você acertou o número!\n");
                inputPalpite.setEditable(false);
                botaoPalpite.setEnabled(false);
                return;
            }

            //Calcula as diferenças para cada Chute, criando assim, as dicas:

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

            //Mensagem de derrota + apresentação do número: 
            if (tentativas == 0) {
                areaMensagens.append("Você perdeu! O número era: " + numeroAleatorio + "\n");
                inputPalpite.setEditable(false);
                botaoPalpite.setEnabled(false);
            }

        //Leitura apenas de números, apresentando a mensagem de inválido caso leia outra coisa: 
        } catch (NumberFormatException e) {
            areaMensagens.append("Entrada inválida. Digite um número.\n");
        } finally {
            inputPalpite.setText("");
            inputPalpite.requestFocus();
        }
    }

    //Fim do código concluindo o argumento e trazendo novamente caso o usuário deseja jogar novamente:
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicio().setVisible(true));
    }
}
