package model;

public class Rainha extends Peca {

    public Rainha(boolean cor) {
        super(cor, Tipo.RAINHA);

    }

    @Override
    public boolean Mover(Tabuleiro t, Celula origem, Celula destino) {
        int distanciaX = Math.abs(destino.getX() - origem.getX());
        int distanciaY = Math.abs(destino.getY() - origem.getY());

        if (eMovimentoInvalido(distanciaX, distanciaY, origem, destino) || ehMesmaCor(t, destino) || ehMesmaCelula(origem, destino)) {
            return false;
        }

        if (distanciaX == distanciaY) {
            return movimentoDiagonalPossivel(origem, destino, t);
        } else {
            return movimentoAdiantePossivel(origem, destino, t);
        }
    }

    private boolean eMovimentoInvalido(int distanciaX, int distanciaY, Celula origem, Celula destino) {
        return (distanciaX != distanciaY) && (origem.getX() != destino.getX() && origem.getY() != destino.getY());
    }

    private boolean ehMesmaCor(Tabuleiro t, Celula destino) {
        Peca p = t.getCelula(destino.getX(), destino.getY()).getPeca();
        return p != null && p.getCor() == this.getCor();
    }

    private boolean ehMesmaCelula(Celula origem, Celula destino) {
        return origem.equals(destino);
    }

    private boolean movimentoDiagonalPossivel(Celula origem, Celula destino, Tabuleiro t) {
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

    private boolean movimentoAdiantePossivel(Celula origem, Celula destino, Tabuleiro t) {
        if (origem.getX() == destino.getX()) {
            return movimentoVerticalPossivel(origem, destino, t);
        } else {
            return movimentoHorizontalPossivel(origem, destino, t);
        }
    }

    private boolean caminhoLivre(Celula origem, Celula destino, Tabuleiro t, boolean isVertical) {
        int min = Math.min(isVertical ? origem.getY() : origem.getX(), isVertical ? destino.getY() : destino.getX());
        int max = Math.max(isVertical ? origem.getY() : origem.getX(), isVertical ? destino.getY() : destino.getX());

        for (int i = min + 1; i < max; i++) {
            if (isVertical) {
                if (t.getCelula(origem.getX(), i).getPeca() != null) {
                    return false;
                }
            } else {
                if (t.getCelula(i, origem.getY()).getPeca() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean movimentoVerticalPossivel(Celula origem, Celula destino, Tabuleiro t) {
        return caminhoLivre(origem, destino, t, true);
    }

    private boolean movimentoHorizontalPossivel(Celula origem, Celula destino, Tabuleiro t) {
        return caminhoLivre(origem, destino, t, false);
    }

}
