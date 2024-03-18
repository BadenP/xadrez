package model;

public class Torre extends Peca {

    public Torre(boolean cor) {
        super(cor, Tipo.TORRE);
    }

    @Override

    public boolean Mover(Tabuleiro t, Celula origem, Celula destino) {

        if (origem.equals(destino)) return false;
            
        if (origem.getX() != destino.getX() && origem.getY() != destino.getY()) return false;
   
        if (origem.getX() == destino.getX()) {
            int minY = Math.min(origem.getY(), destino.getY());
            int maxY = Math.max(origem.getY(), destino.getY());
            for (int y = minY + 1; y < maxY; y++) {
                if (t.getCelula(origem.getX(), y).getPeca() != null) {
                    return false;
                }
            }
        } else {
            int minX = Math.min(origem.getX(), destino.getX());
            int maxX = Math.max(origem.getX(), destino.getX());
            for (int x = minX + 1; x < maxX; x++) {
                if (t.getCelula(x,origem.getY()).getPeca() != null) {
                    return false;
                }
            }
        }

        Peca endPeca = t.getCelula(destino.getX(), destino.getY()).getPeca();
        if (endPeca == null || endPeca.getCor() != this.getCor()) return true;
    
        return false;
    }

}
