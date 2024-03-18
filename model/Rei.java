package model;
public class Rei extends Peca {
    private boolean roqueMaiorPermitido;
    private boolean roqueMenorPermitido;
    
    public Rei(boolean cor) {
        super(cor, Tipo.REI);
        roqueMaiorPermitido=false;
        roqueMenorPermitido=false;

    }

    @Override
    public boolean Mover(Tabuleiro t, Celula origem, Celula destino) {
        if (isMesmaCelula(origem, destino)) return false;

        int dx = Math.abs(destino.getX() - origem.getX());
        int dy = Math.abs(destino.getY() - origem.getY());

        if (isMovimentoValido(dx, dy, t, destino)) return true;

        if (!getSaiuCasaInicial()) {
            return isRoque(t, origem, destino);
        }

        return false;
    }

    private boolean isMesmaCelula(Celula origem, Celula destino) {
        return origem.equals(destino);
    }

    private boolean isMovimentoValido(int dx, int dy, Tabuleiro t, Celula destino) {
        if (dx + dy == 1 || (dx == 1 && dy == 1)) {
            Peca p = t.getCelula(destino.getX(), destino.getY()).getPeca();
            return p == null || p.getCor() != this.getCor();
        }
        return false;
    }

    private boolean isRoque(Tabuleiro t, Celula origem, Celula destino) {
        Peca torreEsq = t.getCelula(getCor() ? 7 : 0, 0).getPeca();
        Peca torreDir = t.getCelula(getCor() ? 7 : 0, 7).getPeca();

        if (isTorreValidaParaRoque(torreEsq) && isRoqueMaiorPossivel(t, origem, destino)) {
            roqueMaiorPermitido = true;
            return true;
        }

        if (isTorreValidaParaRoque(torreDir) && isRoqueMenorPossivel(t, origem, destino)) {
            roqueMenorPermitido = true;
            return true;
        }

        return false;
    }

    private boolean isTorreValidaParaRoque(Peca torre) {
        return torre != null && torre.getTipo().equals(Tipo.TORRE) && !torre.getSaiuCasaInicial();
    }

    private boolean isRoqueMaiorPossivel(Tabuleiro t, Celula origem, Celula destino) {
        return destino.getX() == (getCor() ? 7 : 0) && destino.getY() == origem.getY() - 2 && caminhoVazioParaRoque(t, origem, -1);
    }

    private boolean isRoqueMenorPossivel(Tabuleiro t, Celula origem, Celula destino) {
        return destino.getX() == (getCor() ? 7 : 0) && destino.getY() == origem.getY() + 2 && caminhoVazioParaRoque(t, origem, 1);
    }

    private boolean caminhoVazioParaRoque(Tabuleiro t, Celula origem, int direcao) {
        int y = origem.getY() + direcao;
        while (y != (direcao > 0 ? 7 : 0)) {
            if (t.getCelula(origem.getX(), y).getPeca() != null) {
                return false;
            }
            y += direcao;
        }
        return true;
    }


    public boolean getRoqueMaior() {
        return roqueMaiorPermitido;
    }

    public boolean getRoqueMenor() {
        return roqueMenorPermitido;
    }

    public void setFimRoque(){
        roqueMaiorPermitido=false;
        roqueMenorPermitido=false;
    }


}