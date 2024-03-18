package model;

public class Jogo {
    private Tabuleiro tabuleiro;
    private Jogador jogadorBranco;
    private Jogador jogadorPreto;
    private StatusJogo status;

    public Jogo(){
        tabuleiro = new Tabuleiro();
        jogadorBranco = new Jogador(true);
        jogadorPreto = new Jogador(false);
        status = StatusJogo.BRANCO_TURN;
    }

    public Tabuleiro getTabuleiro(){
        return tabuleiro;
    }

    public StatusJogo getStatus(){
        return status;
    }

    public void setStatus(StatusJogo s){
        status = s;
    }

    public Jogador getJogadorBranco(){
        return jogadorBranco;
    }

    public Jogador getJogadorPreto(){
        return jogadorPreto;
    }

    public void atualizarTabuleiro(Celula origem, Celula destino, Peca p) {
        tabuleiro.getCelula(origem.getX(),origem.getY()).setPeca(null);
        tabuleiro.getCelula(destino.getX(),destino.getY()).setPeca(p);
    }

    
}



    

