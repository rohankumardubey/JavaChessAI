package com.coolioasjulio.chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.swing.JPanel;

import com.coolioasjulio.chess.Board;
import com.coolioasjulio.chess.ChessGame;
import com.coolioasjulio.chess.Piece;
import com.coolioasjulio.chess.Square;

public class ChessGameUI extends ChessGame {

    private ChessGamePanel panel;

    public ChessGameUI(int tileSize) {
        super(tileSize);
        panel = new ChessGamePanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void draw() {
        panel.repaint();
    }

    private class ChessGamePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public ChessGamePanel() {
            this.setPreferredSize(new Dimension(tileSize * 8, tileSize * 8));
        }

        private void drawBoard(Graphics g) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int x = i * tileSize;
                    int y = j * tileSize;
                    g.setColor(Board.TAN);
                    if ((i + j) % 2 == 0)
                        g.setColor(Board.BROWN);
                    g.fillRect(x, y, tileSize, tileSize);
                }
            }
        }

        private void drawPieces(Graphics g) throws IOException {
            List<Piece> toDraw = piecesToDraw != null ? piecesToDraw : board.getPieces();
            for (int i = 0; i < toDraw.size(); i++) {
                Piece p = toDraw.get(i);
                Square square = p.getSquare();
                int x = square.getX() * tileSize;
                int y = this.getHeight() - square.getY() * tileSize;
                g.drawImage(Piece.getImage(p), x, y, null);
            }
        }

        private void drawHighlightedSquares(Graphics g) {
            g.setColor(new Color(0, 255, 255, 50));
            for (Square square : highlightedSquares) {
                int x = square.getX() * tileSize;
                int y = getHeight() - square.getY() * tileSize;
                g.fillRect(x, y, tileSize, tileSize);
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                drawBoard(g);
                drawHighlightedSquares(g);
                drawPieces(g);
            } catch (ConcurrentModificationException | IOException e) {
                e.printStackTrace(); // Catch any multithreaded/IO problems
            }
        }
    }

}