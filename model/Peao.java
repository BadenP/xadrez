package model;
public class Peao extends Peca {
    private Peca possivelEnPassant;
    private boolean enPassant;

    public Peao(boolean cor) {
        super(cor, Tipo.PEAO);
        enPassant = false;
    }

    @Override

    public boolean Mover(Tabuleiro t, Celula origem, Celula destino) {
        int distanciaX = ((destino.getX() - origem.getX()));
        int distanciaY = ((destino.getY() - origem.getY()));
        Peca p = t.getCelula(destino.getX(), destino.getY()).getPeca();

        if (p!= null && p.getCor()==this.getCor()) return false;
        if (origem.equals(destino)) return false;

        if (!origem.getPeca().getCor()) {
            return moverPeaoPreto(origem, destino, distanciaX, distanciaY, t);
        } else {
            return moverPeaoBranco(origem, destino, distanciaX, distanciaY, t);
        }
    }

    private boolean moverPeaoPreto(Celula origem, Celula destino, int distanciaX, int distanciaY, Tabuleiro t) {
        if (moverInicial(origem, destino, distanciaX, distanciaY, t, true)) {
            return true;
        }

        if (moverUmaCasa(destino, distanciaX, distanciaY, true) || captura(destino, distanciaX, distanciaY, true)) {
            return true;
        }

        return enPassantPreto(origem, destino, distanciaX, distanciaY, t);
    }

    private boolean moverPeaoBranco(Celula origem, Celula destino, int distanciaX, int distanciaY, Tabuleiro t) {
        if (moverInicial(origem, destino, distanciaX, distanciaY, t, false)) {
            return true;
        }

        if (moverUmaCasa(destino, distanciaX, distanciaY, false) || captura(destino, distanciaX, distanciaY, false)) {
            return true;
        }

        return enPassantBranco(origem, destino, distanciaX, distanciaY, t);
    }

    private boolean moverInicial(Celula origem, Celula destino, int distanciaX, int distanciaY, Tabuleiro t, boolean ehPreta) {
        int linhaInicial = ehPreta ? 1 : 6;
        int direcaoMovimento = ehPreta ? 2 : -2;
        return origem.getX() == linhaInicial && distanciaX == direcaoMovimento && distanciaY == 0 && destino.getPeca() == null && t.getCelula(origem.getX() + direcaoMovimento / 2, origem.getY()).getPeca() == null;
    }

    private boolean moverUmaCasa(Celula destino, int distanciaX, int distanciaY, boolean ehPreta) {
        int direcaoMovimento = ehPreta ? 1 : -1;
        return distanciaX == direcaoMovimento && distanciaY == 0 && destino.getPeca() == null;
    }

    private boolean captura(Celula destino, int distanciaX, int distanciaY, boolean ehPreta) {
        int direcaoMovimento = ehPreta ? 1 : -1;
        return Math.abs(distanciaY) == 1 && distanciaX == direcaoMovimento && destino.getPeca() != null;
    }

    private boolean enPassantPreto(Celula origem, Celula destino, int distanciaX, int distanciaY, Tabuleiro t) {
        if (distanciaY == 1 && distanciaX == distanciaY && destino.getPeca()==null){
            possivelEnPassant = t.getCelula(4, origem.getY()+1).getPeca();
            if (t.getUltimoMovimento().equals(t.getCelula(destino.getX()-1, destino.getY())) && origem.getX()==4 && possivelEnPassant!= null && possivelEnPassant.getTipo().equals(Tipo.PEAO) && possivelEnPassant.getCor()){
                enPassant=true;
                return true;
            }
        }
        return false;
    }

    private boolean enPassantBranco(Celula origem, Celula destino, int distanciaX, int distanciaY, Tabuleiro t) {
        if (distanciaY == 1 && Math.abs(distanciaX)==Math.abs(distanciaY) && distanciaX==(-1) && destino.getPeca()==null){
            possivelEnPassant = t.getCelula(3, origem.getY()+1).getPeca();
            if (t.getUltimoMovimento().equals(t.getCelula(destino.getX()+1, destino.getY())) && origem.getX()==3 && possivelEnPassant!= null && possivelEnPassant.getTipo().equals(Tipo.PEAO) && !possivelEnPassant.getCor()){
                enPassant=true;
                return true;
            }
        }
        return false;
    }

    public boolean getEnPassant(){
        return enPassant;
    }

  }


