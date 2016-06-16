package JANTARDOSFILOSOFOS;

public class Filosofo extends Thread{

    private int ID;

    final int PENSANDO = 0;
    final int FAMINTO  = 1;
    final int COMENDO  = 2;

    public Filosofo (String nome, int ID){
        super(nome);
        this.ID = ID;
    }

    public void ComFome (){
        Grade.estado[this.ID] = 1;
        System.out.println("O Filosofo " + getName() + " esta FAMINTO!");
    }

    public void Come (){
        Grade.estado[this.ID] = 2;
        System.out.println("O Filosofo " + getName() + " esta COMENDO!");

        try{
            Thread.sleep(1000L);
        }catch (InterruptedException ex){
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void Pensa (){
        Grade.estado[this.ID] = 0;
        System.out.println("O Filosofo " + getName() + " esta PENSANDO!");

        try{
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex){
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void LargarGarfo (){
        Grade.mutex.decrementar();

        Pensa();

        Grade.filosofo[VizinhoEsquerda()].TentarObterGarfos();
        Grade.filosofo[VizinhoDireita()].TentarObterGarfos();

        Grade.mutex.incrementar();
    }

    public void PegarGarfo (){
        Grade.mutex.decrementar();

        ComFome();

        TentarObterGarfos();

        Grade.mutex.incrementar();
        Grade.semaforos[this.ID].decrementar();
    }

    public void TentarObterGarfos(){

        if (Grade.estado[this.ID] == 1 &&
            Grade.estado[VizinhoEsquerda()] != 2 && 
            Grade.estado[VizinhoDireita()] != 2){
            
        	Come();
            Grade.semaforos[this.ID].incrementar();
        }
    }

    public void run (){

        try{
            Pensa();

            do{
                PegarGarfo();
                Thread.sleep(1000L);
                LargarGarfo();
            }while (true);
        }
        catch (InterruptedException ex){
            System.out.println("ERROR>" + ex.getMessage());
            return;
        }

    }

    public int VizinhoDireita (){
        return (this.ID + 1) % 5;
    }

    public int VizinhoEsquerda (){
        if (this.ID == 0){
            return 4;
            
        }else{
            return (this.ID - 1) % 5;
        }
    }

}

