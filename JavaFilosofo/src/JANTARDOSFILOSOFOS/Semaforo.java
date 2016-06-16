package JANTARDOSFILOSOFOS;

public class Semaforo{

    protected int contador;

    public Semaforo (){
        this.contador = 0;
    }

    // contador
    public Semaforo (int valor){
        this.contador = valor;
    }

    public synchronized void decrementar (){

        // Enquanto o contador for igual a 0, ele aguarda e trata a execusão
        while (this.contador == 0){
            try{
                // Espera uma nova solicitação
                wait();
            }
            catch (InterruptedException ex){
                // Exibe uma mensagem de controle de erro
                System.out.println("ERROR>" + ex.getMessage());
            }
        }

        // Caso tenha saido do while acima, entao decrementa o
        // contador da classe
        this.contador--;

    }

    // Metodo de sincronizacao da classe onde sera¡ incrementado o contador
    public synchronized void incrementar (){
        // Incrementa o contador da classe
        this.contador++;
        // Notifica que a solicitacao ja foi executada
        notify();
    }

}
