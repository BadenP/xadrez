package controller;

import model.*;
import view.IconesPecas;
import view.XadrezView;

import javax.swing.*;

public class InputControl {
    protected static Jogo jogo;
    protected static XadrezView view;
    protected static IconesPecas icones = new IconesPecas();

    public static void gerenciarInput(Celula origem, Celula destino, JLabel[][] label) {
        Peca p = origem.getPeca();

        if (Controller.verificaPermissao(origem) && p.Mover(jogo.getTabuleiro(), origem, destino)) {
            if (destino.getPeca() == null) {
                handleDestinoVazio(origem, destino, label, p);
            } else {
                handleDestinoOcupado(origem, destino, label, p);
            }
            jogo.getTabuleiro().setUltimoMovimento(jogo.getTabuleiro().getCelula(destino.getX(), destino.getY()));
        }
    }

    private static void handleDestinoVazio(Celula origem, Celula destino, JLabel[][] label, Peca p) {
        jogo.atualizarTabuleiro(origem, destino, p);
        view.atualizarLabel(origem, destino, null, icones.getIconPeca(p));
        p.setSaiuCasaInicial();

        if (p.getTipo().equals(Tipo.PEAO)) {
            handlePeao(origem, destino, label, (Peao) p);
        }

        if (p.getTipo().equals(Tipo.REI)) {
            handleRei(origem, destino, label, (Rei) p);
        }

        atualizaStatusJogo(p);
    }

    private static void handleDestinoOcupado(Celula origem, Celula destino, JLabel[][] label, Peca p) {
        if (destino.getPeca().getTipo().equals(Tipo.REI)) {
            handleCapturaRei(destino);
        } else {
            handleNaoCapturaRei(origem, destino, label, p);
        }
    }

    private static void handlePeao(Celula origem, Celula destino, JLabel[][] label, Peao pe) {
        int i = pe.getCor() ? 1 : -1;
        if (pe.getEnPassant() && jogo.getTabuleiro().getUltimoMovimento().equals(jogo.getTabuleiro().getCelula(destino.getX() + i, destino.getY()))) {
            jogo.getTabuleiro().getCelula(destino.getX() + i, destino.getY()).setPeca(null);
            label[destino.getX() + i][destino.getY()].setIcon(null);
        }

        if ((pe.getCor() && destino.getX() == 0) || (!pe.getCor() && destino.getX() == 7)) {
            if (pe.getCor()) view.promoverPeao(true, destino.getX(), destino.getY());
            else view.promoverPeao(false, destino.getX(), destino.getY());
        }
    }

    private static void handleRei(Celula origem, Celula destino, JLabel[][] label, Rei r) {
        if (r.getRoqueMaior() && destino.getY() == 2) {
            handleRoqueMaior(origem, destino, label, r);
        }
        if (r.getRoqueMenor() && destino.getY() == 6) {
            handleRoqueMenor(origem, destino, label, r);
        }
    }

    private static void handleRoqueMaior(Celula origem, Celula destino, JLabel[][] label, Rei r) {
        Torre esq = (Torre) jogo.getTabuleiro().getCelula(origem.getX(), 0).getPeca();
        jogo.getTabuleiro().getCelula(destino.getX(), destino.getY() + 1).setPeca(esq);
        label[destino.getX()][0].setIcon(null);
        label[destino.getX()][destino.getY() + 1].setIcon(icones.getIconPeca(esq));
        r.setFimRoque();
        esq.setSaiuCasaInicial();
    }

    private static void handleRoqueMenor(Celula origem, Celula destino, JLabel[][] label, Rei r) {
        Torre dir = (Torre) jogo.getTabuleiro().getCelula(origem.getX(), 7).getPeca();
        jogo.getTabuleiro().getCelula(destino.getX(), destino.getY() - 1).setPeca(dir);
        label[destino.getX()][7].setIcon(null);
        label[destino.getX()][destino.getY() - 1].setIcon(icones.getIconPeca(dir));
        r.setFimRoque();
        dir.setSaiuCasaInicial();
    }

    private static void atualizaStatusJogo(Peca p) {
        if (jogo.getStatus().equals(StatusJogo.BRANCO_TURN)) {
            jogo.setStatus(StatusJogo.PRETO_TURN);
            view.atualizarStatusLabel("Aguardando jogada: Jogador Preto");
        } else if (jogo.getStatus().equals(StatusJogo.PRETO_TURN)) {
            jogo.setStatus(StatusJogo.BRANCO_TURN);
            view.atualizarStatusLabel("Aguardando jogada: Jogador Branco");
        }
    }

    private static void handleCapturaRei(Celula destino) {
        if (destino.getPeca().getCor()) {
            jogo.setStatus(StatusJogo.PRETO_VENCEU);
            view.atualizarStatusLabel("Fim de jogo: Jogador Preto venceu");
            view.setFimdeJogo("Fim de jogo! Preto venceu");
        } else {
            jogo.setStatus(StatusJogo.BRANCO_VENCEU);
            view.atualizarStatusLabel("Fim de jogo: Jogador Branco venceu");
            view.setFimdeJogo("Fim de jogo! Branco venceu");
        }
        Controller.resetJogo();
    }

    private static void handleNaoCapturaRei(Celula origem, Celula destino, JLabel[][] label, Peca p) {
        if (jogo.getStatus().equals(StatusJogo.BRANCO_TURN)) {
            jogo.setStatus(StatusJogo.PRETO_TURN);
            view.atualizarStatusLabel("Aguardando jogada: Jogador Preto");
        } else if (jogo.getStatus().equals(StatusJogo.PRETO_TURN)) {
            jogo.setStatus(StatusJogo.BRANCO_TURN);
            view.atualizarStatusLabel("Aguardando jogada: Jogador Branco");
        }

        if (p.getCor()) jogo.getJogadorBranco().capturaPeca(destino.getPeca());
        else jogo.getJogadorPreto().capturaPeca(destino.getPeca());

        destino.getPeca().setViva(false);
        jogo.atualizarTabuleiro(origem, destino, p);
        view.atualizarLabel(origem, destino, null, icones.getIconPeca(p));
        p.setSaiuCasaInicial();
    }
}
