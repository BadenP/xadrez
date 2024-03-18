package model;
import java.util.ArrayList;


public class Jogador {
    private boolean cor;
    private ArrayList<Peca> PecasCapturadas;

    public Jogador(boolean c){
        cor = c;
        PecasCapturadas = new ArrayList<Peca>();
    }

    public boolean getCor(){
        return cor;
    }

    public void capturaPeca(Peca p){
        PecasCapturadas.add(p);
    }

}


    


