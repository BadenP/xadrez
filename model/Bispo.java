package model;

public class Bispo extends Peca {

    public Bispo(boolean cor) {
        super(cor, Tipo.BISPO);

    }

    @Override
    public boolean Mover(Tabuleiro t, Celula origem, Celula destino) {
        int distanciaX = Math.abs(destino.getX() - origem.getX());
        int distanciaY = Math.abs(destino.getY() - origem.getY());
        Peca p = t.getCelula(destino.getX(), destino.getY()).getPeca();

        if (p!= null && p.getCor()==this.getCor()) return false;

        if (origem.equals(destino)) return false;
        
        if (distanciaX!=distanciaY) return false;
        
        int xc = (destino.getX() > origem.getX()) ? 1 : -1;
        int yc = (destino.getY() > origem.getY()) ? 1 : -1;

        int x = origem.getX() + xc;
        int y = origem.getY() + yc;
        while (x != destino.getX() && y != destino.getY()) {
            if (t.getCelula(x, y).getPeca() != null) {
                return false;  
            }
            x += xc;
            y += yc;
        }

        return true;
    }

}
