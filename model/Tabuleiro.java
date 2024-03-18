package model;

public class Tabuleiro{
    private final Celula[][] tabuleiro = new Celula[8][8];
    private Celula ultimoMovimento;
    
    public Tabuleiro(){ 

    tabuleiro[0][0] = new Celula(0, 0, new Torre(false));
    tabuleiro[0][1] = new Celula(0, 1, new Cavalo(false));
    tabuleiro[0][2] = new Celula(0, 2, new Bispo(false));
    tabuleiro[0][3] = new Celula(0, 3, new Rainha(false));
    tabuleiro[0][4] = new Celula(0, 4, new Rei(false));
    tabuleiro[0][5] = new Celula(0, 5, new Bispo(false));
    tabuleiro[0][6] = new Celula(0, 6, new Cavalo(false));
    tabuleiro[0][7] = new Celula(0, 7, new Torre(false));

    tabuleiro[1][0] = new Celula(1, 0, new Peao(false));
    tabuleiro[1][1] = new Celula(1, 1, new Peao(false));
    tabuleiro[1][2] = new Celula(1, 2, new Peao(false));
    tabuleiro[1][3] = new Celula(1, 3, new Peao(false));
    tabuleiro[1][4] = new Celula(1, 4, new Peao(false));
    tabuleiro[1][5] = new Celula(1, 5, new Peao(false));
    tabuleiro[1][6] = new Celula(1, 6, new Peao(false));
    tabuleiro[1][7] = new Celula(1, 7, new Peao(false));

    tabuleiro[7][0] = new Celula(7, 0, new Torre(true));
    tabuleiro[7][1] = new Celula(7, 1, new Cavalo(true));
    tabuleiro[7][2] = new Celula(7, 2, new Bispo(true));
    tabuleiro[7][3] = new Celula(7, 3, new Rainha(true));
    tabuleiro[7][4] = new Celula(7, 4, new Rei(true));
    tabuleiro[7][5] = new Celula(7, 5, new Bispo(true));
    tabuleiro[7][6] = new Celula(7, 6, new Cavalo(true));
    tabuleiro[7][7] = new Celula(7, 7, new Torre(true));
  
    tabuleiro[6][0] = new Celula(6, 0, new Peao(true));
    tabuleiro[6][1] = new Celula(6, 1, new Peao(true));
    tabuleiro[6][2] = new Celula(6, 2, new Peao(true));
    tabuleiro[6][3] = new Celula(6, 3, new Peao(true));
    tabuleiro[6][4] = new Celula(6, 4, new Peao(true));
    tabuleiro[6][5] = new Celula(6, 5, new Peao(true));
    tabuleiro[6][6] = new Celula(6, 6, new Peao(true));
    tabuleiro[6][7] = new Celula(6, 7, new Peao(true));

    for (int i = 2; i < 6; i++) {
        for (int j = 0; j < 8; j++) {
            tabuleiro[i][j] = new Celula(i, j, null);
        }
    }

    ultimoMovimento = new Celula(0, 0, null);

    }

    
    public Celula getCelula(int x, int y) {
        return tabuleiro[x][y];
    }


    public Celula getUltimoMovimento(){
        return ultimoMovimento;
    }

    public void setUltimoMovimento(Celula mov){
        ultimoMovimento = mov;
    }
    
}
