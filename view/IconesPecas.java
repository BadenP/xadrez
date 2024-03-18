package view;
import javax.swing.ImageIcon;

import model.*;

/*
 * Esta classe auxilia as outras classes na manutençao dos ícones do tabuleiro
 */

public class IconesPecas {

    private static ImageIcon peaoPreto = new ImageIcon("images/peaop.png");
    private static ImageIcon peaoBranco = new ImageIcon("images/peaob.png");
    private static ImageIcon cavaloPreto = new ImageIcon("images/cavalop.png");
    private static ImageIcon cavaloBranco = new ImageIcon("images/cavalob.png");
    private static ImageIcon bispoPreto = new ImageIcon("images/bispop.png");
    private static ImageIcon bispoBranco = new ImageIcon("images/bispob.png");
    private static ImageIcon torrePreta = new ImageIcon("images/torrep.png");
    private static ImageIcon torreBranca = new ImageIcon("images/torreb.png");
    private static ImageIcon rainhaPreta = new ImageIcon("images/rainhap.png");
    private static ImageIcon rainhaBranca = new ImageIcon("images/rainhab.png");
    private static ImageIcon reiPreto = new ImageIcon("images/reip.png");
    private static ImageIcon reiBranco = new ImageIcon("images/reib.png");
    

    public ImageIcon getIconPeca(Peca peca) {
        if (peca instanceof Peao) {
            if (peca.getCor()) return peaoBranco;
            else return peaoPreto;
        } else if (peca instanceof Cavalo) {
            if (peca.getCor()) return cavaloBranco;
            else return cavaloPreto;
        } else if (peca instanceof Bispo) {
            if (peca.getCor()) return bispoBranco;
            else return bispoPreto;
        } else if (peca instanceof Torre) {
            if (peca.getCor()) return torreBranca;
            else return torrePreta;
        } else if (peca instanceof Rainha) {
            if (peca.getCor()) return rainhaBranca;
            else return rainhaPreta;
        } else if (peca instanceof Rei) {
            if (peca.getCor()) return reiBranco;
            else return reiPreto;
        }
        return null;
  }

    
}
