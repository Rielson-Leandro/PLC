package JANTARDOSFILOSOFOS;

import javax.swing.JFrame;

public class JantarDosFilosofos extends JFrame{

    public JantarDosFilosofos (){
        // CRIA UMA NOVA GRADE NA TELA
        add(new Grade());

        // DEFINE O TITULO
        setTitle("Jantar dos Filosofos");
        // INFORMA O MÃ‰TODO DE SAÃ�DA
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // SETA O TAMANHO
        setSize(410, 410);
        // SETA A LOCALIZacao
        setLocationRelativeTo(null);
        // SETA A VISIBILIDADE
        setVisible(true);

        setResizable(false);
    }

    public static void main(String[] args){
        new JantarDosFilosofos();
    }

}

