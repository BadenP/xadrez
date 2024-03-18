package model;

public abstract class Peca {
    private Tipo tipo;
    private boolean cor;
    private boolean viva;
    private boolean saiuCasaInicial;

    public Peca(boolean c, Tipo t) {
        cor = c;
        viva = true;
        tipo = t;
        saiuCasaInicial=false;
    }

    public boolean getCor() {
        return cor;
    }

    public boolean getViva() {
        return viva;
    }

    public void setViva(boolean s) {
        viva = s;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public abstract boolean Mover(Tabuleiro t, Celula origem, Celula destino);

    public void setSaiuCasaInicial() {
        saiuCasaInicial=true;
    }

    public boolean getSaiuCasaInicial(){
        return saiuCasaInicial;
    }


}

