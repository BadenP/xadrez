package controller;

import model.*;
import view.*;

/* Classe Controller responsável por intermediar a lógica do Jogo com a interface View  */
public class Controller extends InputControl {

    public Controller() {
        jogo = new Jogo();
        view = new XadrezView(jogo.getTabuleiro());
    }

    /*
     * Método para resetar o jogo após um jogador vencer
     */
    public static void resetJogo(){
        view.fimJogo();
        jogo = new Jogo();
        view = new XadrezView(jogo.getTabuleiro());
    }

    /*
     * Método que verifica se a peça selecionada pertence ao jogador da vez
     */
    public static boolean verificaPermissao(Celula selecionada) {
        Peca p = jogo.getTabuleiro().getCelula(selecionada.getX(), selecionada.getY()).getPeca();
        if(p.getCor()&& jogo.getStatus().equals(StatusJogo.BRANCO_TURN)) return true;
        return !p.getCor() && jogo.getStatus().equals(StatusJogo.PRETO_TURN);
    }

    /*
     * Método que realiza a atualizaçao da promoçao de peao
     */
    public static void getPromocao(Peca pecaSelecionada, int x, int y) {
        jogo.getTabuleiro().getCelula(x,y).setPeca(null);
        jogo.getTabuleiro().getCelula(x,y).setPeca(pecaSelecionada);
        view.getLabels()[x][y].setIcon(null);
        view.getLabels()[x][y].setIcon(icones.getIconPeca(pecaSelecionada));
    }


}