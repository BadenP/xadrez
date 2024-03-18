package model;


public class Celula{
    private Peca peca;
    private int x,y;

    public Celula(int x, int y, Peca peca){
        this.x = x;
        this.y = y;
        this.peca = peca;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca p) {
        this.peca = p;
    }
}
