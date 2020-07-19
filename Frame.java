import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import javax.swing.Action;

import java.awt.*;
import java.awt.event.*;


public class Frame extends JFrame{
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Frame() {
        super();
        JButton startButton = new JButton("Next");
        JButton resetButton = new JButton("Reset");

        Grid g = new Grid(20);

        this.add(startButton, BorderLayout.EAST);
        this.add(resetButton, BorderLayout.WEST);
        this.add(g, BorderLayout.CENTER);

        this.setSize((int) (screenSize.width * 0.5), (int) (screenSize.width * 0.5));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g.updateCellsState();

            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             g.reset();

            }
        });
    }

    static class Grid extends JPanel {
        int cellpixels = 15;

        int gridSize = cellpixels * 20;

        int topMargin = 0;
        int leftMargin = 0;


        private static CellMatrix mat;

        public Grid(int s) {
            super();
            setBorder(BorderFactory.createLineBorder(Color.black));
            gridSize = cellpixels * s;
            mat = new CellMatrix(s, s);

        }

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);

            
            leftMargin = (getSize().width - gridSize) / 2;
            topMargin = (getSize().height - gridSize) / 2;

            Cell[][] m = mat.getMat();
            
            int top = topMargin;
            for (int i = 0; i < m.length; i++) {
                int left = leftMargin;
                for (int j = 0; j < m[0].length; j++) {

                    if(m[i][j].getNextState() == 'd'){
                        g.setColor(Color.black);
                    }else{
                        g.setColor(Color.gray);

                    }
                    g.fillRect(left, top, cellpixels, cellpixels);
                    g.setColor(Color.red);
                    g.drawRect(left, top, cellpixels, cellpixels);
                    left += cellpixels;
                }
                top += cellpixels;
            }
        }

        public void updateCellsState() {
    
                mat.iteration();
                mat.printMat();


                this.repaint();

        }

       

        public void reset(){
            mat.init();

            repaint();
        }

        

    }
}