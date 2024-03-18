package view;

import javax.swing.*;
import controller.*;
import model.*;

import java.awt.*;
import java.awt.event.*;

public class XadrezView extends JFrame {
  private JPanel tabuleiroPanel;
  private JLabel statusLabel;
  private Tabuleiro tabuleiro;
  private JLabel[][] label;
  private int antX, antY;
  private boolean ehFirstClick = true;
  private ImageIcon ponto = new ImageIcon("images/ponto.png");
  private static IconesPecas icones = new IconesPecas();

  public XadrezView(Tabuleiro tab) {
    inicializaView(tab);
  }
  private void inicializaView(Tabuleiro tab) {
    label = new JLabel[8][8];
    setTitle("Xadrez");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 800);

    tabuleiroPanel = new JPanel(new GridLayout(8, 8));
    tabuleiro = tab;
    inicializaLabels();

    add(tabuleiroPanel, BorderLayout.CENTER);
    configureStatusLabel();
    setVisible(true);
  }

  private void inicializaLabels() {
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        inicializaLabel(x, y);
      }
    }
  }

  private void inicializaLabel(int x, int y) {
    label[x][y] = new JLabel();
    label[x][y].setOpaque(true);
    label[x][y].setBackground((x + y) % 2 == 0 ? new Color(230,235,224) : new Color(133,117,110));
    setIconePeca(x, y);
    label[x][y].addMouseListener(createMouseListener());
    tabuleiroPanel.add(label[x][y]);
  }

  private void setIconePeca(int x, int y) {
    Peca Peca = tabuleiro.getCelula(x,y).getPeca();
    if (Peca != null) {
      ImageIcon icon = icones.getIconPeca(Peca);
      label[x][y].setIcon(icon);
      label[x][y].setHorizontalAlignment(SwingConstants.CENTER);
      label[x][y].setVerticalAlignment(SwingConstants.CENTER);
    }
  }

  private MouseAdapter createMouseListener() {
    return new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        resetCoresLabels();
        JLabel casaSelecionada = (JLabel) e.getSource();
        int atualX = tabuleiroPanel.getComponentZOrder(casaSelecionada) / 8;
        int atualY = tabuleiroPanel.getComponentZOrder(casaSelecionada) % 8;

        if (ehFirstClick) {
          primeiroClick(casaSelecionada, atualX, atualY);
        } else {
          segundoClick(atualX, atualY);
        }
      }
    };
  }

  private void primeiroClick(JLabel casaSelecionada, int atualX, int atualY) {
    if (casaSelecionada.getIcon() != null && Controller.verificaPermissao(tabuleiro.getCelula(atualX,atualY))){
      casaSelecionada.setBackground(new Color(85,76,71));
      mostreJogadasPossiveis(atualX,atualY,tabuleiro);
      antX = atualX;
      antY = atualY;
      ehFirstClick = false;
    }
  }

  private void segundoClick(int atualX, int atualY) {
    Controller.gerenciarInput(tabuleiro.getCelula(antX, antY),tabuleiro.getCelula(atualX,atualY),label);
    ehFirstClick = true;
  }

  private void configureStatusLabel() {
    statusLabel = new JLabel("Aguardando jogada: Jogador Branco");
    statusLabel.setFont(new Font ("Latha",Font.PLAIN,18));
    statusLabel.setPreferredSize(new Dimension(80, 30));
    statusLabel.setBackground(new Color(220,225,214));
    statusLabel.setOpaque(true);
    statusLabel.repaint();
    add(statusLabel, BorderLayout.SOUTH);
  }

  public void atualizarStatusLabel(String texto){
    statusLabel.setText(texto);
  }

  public void setFimdeJogo(String texto){
    JOptionPane.showMessageDialog(tabuleiroPanel,texto);
  }

  private void resetCoresLabels() {
    for(int i=0; i<8; i++){
      for(int j=0; j<8; j++){
        label[i][j].setBackground((i + j) % 2 == 0 ? new Color(230,235,224) : new Color(133,117,110));
        if (label[i][j].getIcon()==null || label[i][j].getIcon().equals(ponto)) label[i][j].setIcon(null);
        else label[i][j].setBorder(null);
      }
    }
  }

  public void mostreJogadasPossiveis(int x, int y, Tabuleiro t) {
    for(int i=0; i<8; i++){
      for(int j=0; j<8; j++){
        if(t.getCelula(x,y).getPeca().Mover(t,t.getCelula(x, y), t.getCelula(i, j))){
          if (label[i][j].getIcon()==null){
              label[i][j].setIcon(ponto);
              label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
              label[i][j].setVerticalAlignment(SwingConstants.CENTER);
          }
          else label[i][j].setBorder(BorderFactory.createLineBorder(new Color(85,76,71), 8, true));
        }
      }
    }
  }

  public JLabel[][] getLabels() {
    return label;
  }

  public void atualizarLabel(Celula origem, Celula destino, ImageIcon origemIcon, ImageIcon destinoIcon){
    if(origem!=null) label[origem.getX()][origem.getY()].setIcon(origemIcon);
    if(destino!=null) label[destino.getX()][destino.getY()].setIcon(destinoIcon);
  }

  public void fimJogo() {
    dispose();
  }

  public void promoverPeao(boolean cor,int x, int y) {
    JButton[] botoes = new JButton[4];
    Peca[] pecasPromocao = new Peca[4];
    pecasPromocao[0] = new Torre(cor);
    pecasPromocao[1] = new Cavalo(cor);
    pecasPromocao[2] = new Bispo(cor);
    pecasPromocao[3] = new Rainha(cor);
    for (int i=0; i<4; i++){
      botoes[i] = new JButton();
      botoes[i].setIcon(icones.getIconPeca(pecasPromocao[i]));
      botoes[i].addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e) {
          JButton botaoSelecionado = new JButton();
          botaoSelecionado = (JButton) e.getSource();
          Peca pecaSelecionada = encontrarPecaBotao(botaoSelecionado);
          Controller.getPromocao(pecaSelecionada,x,y);
          ((Window) SwingUtilities.getRoot(botaoSelecionado)).dispose();
        }

        private Peca encontrarPecaBotao(JButton botaoSelecionado) {
          for(int i=0; i<4; i++){
            if(botoes[i].equals(botaoSelecionado)) return pecasPromocao[i];
          }
          return null;
        }});
    }

    JOptionPane.showOptionDialog(
          this,
          "Selecione uma Peca para promover seu peão",
          "Promoção de peão",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.PLAIN_MESSAGE,
          null,
          botoes,
          botoes[0]
      );
  }
}