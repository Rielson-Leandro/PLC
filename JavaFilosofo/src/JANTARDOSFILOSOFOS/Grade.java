package JANTARDOSFILOSOFOS;

import javax.swing.JPanel;
import java.awt.*;

public class Grade extends JPanel implements Runnable{

    final int PENSANDO = 0;
    final int FAMINTO  = 1;
    final int COMENDO  = 2;

    String mensagem = "";

    Thread animador;


    public static Semaforo mutex = new Semaforo(1);

    public static Semaforo semaforos[] = new Semaforo[5];

    public static int estado[] = new int[5];

    static Filosofo filosofo[] = new Filosofo[5];

    public Grade (){
        setFocusable(true);

        setSize(400, 400);
        
        setBackground(Color.white);
        
        init();
    }

    public void init (){
        for (int i = 0; i < estado.length; i++){
            estado[i] = 0;
        }

        if(animador == null){
            animador = new Thread(this);
            animador.start();
        }

        Thread.currentThread().setPriority(1);

        // Inicializa todos os filosofos
        filosofo[0] = new Filosofo("Platao", 0);
        filosofo[1] = new Filosofo("Socrates", 1);
        filosofo[2] = new Filosofo("Aristoteles", 2);
        filosofo[3] = new Filosofo("Tales", 3);
        filosofo[4] = new Filosofo("Sofocles", 4);

        // Inicializa todos semaforos
        semaforos[0] = new Semaforo(0);
        semaforos[1] = new Semaforo(0);
        semaforos[2] = new Semaforo(0);
        semaforos[3] = new Semaforo(0);
        semaforos[4] = new Semaforo(0);

        // Inicia o start de todos os dilosofos
        filosofo[0].start();
        filosofo[1].start();
        filosofo[2].start();
        filosofo[3].start();
        filosofo[4].start();
    }

    
    public void paint(Graphics g){
        super.paint(g);
        
        g.setColor(Color.blue);
        g.drawOval(50, 50, 300, 300);

        for(int i = 0; i < 5; i++){            
            if(estado[i] == 0){
                g.setColor(Color.gray);
                mensagem = "PENSANDO";
            }
            if(estado[i] == 1){
                g.setColor(Color.yellow);
                mensagem = "FAMINTO";
            }
            if(estado[i] == 2){
                g.setColor(Color.green);
                mensagem = "COMENDO";
            }
            
            g.fillOval((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 15, 30, 30);
            g.setColor(Color.black);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 5, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 5, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 5, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 5);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 3, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)));
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)), (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)));
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 3, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 3, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 8, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8);
            g.drawString(filosofo[i].getName(), (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 25);
            g.drawString(mensagem, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 40);
        }

        // ATIVA A SINCRONIA
        Toolkit.getDefaultToolkit().sync();
        // PAUSA
        g.dispose();

    }

    public void run (){        
        do{
            // Redesenha a tela
            repaint();

            // Dorme durante um tempo para redesenhar novamente
            try{
                Thread.sleep(1000L);
                
            }catch (InterruptedException ex){
                System.out.println("ERROR>" + ex.getMessage());
            }
        }while (true);
    }

}