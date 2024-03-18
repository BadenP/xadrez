package model;

public class Cavalo extends Peca {

    public Cavalo(boolean cor) {
        super(cor, Tipo.CAVALO);
    }

    @Override
    public boolean Mover(Tabuleiro t, Celula origem, Celula destino) {
        Peca p = t.getCelula(destino.getX(), destino.getY()).getPeca();
        if (p!=null && p.getCor()==this.getCor()) return false;
        if (origem.equals(destino)) return false;
        int x = Math.abs(origem.getX() - destino.getX());
        int y = Math.abs(origem.getY() - destino.getY());
        return x * y == 2;
    }

}

