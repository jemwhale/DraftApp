package com.jackwhale.multiverse;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {


    JPanel panelDraftBoard = new PanelDraftBoard();
    JPanel panelHeader = new PanelHeader();
    JPanel panelMenu = new PanelMenu();
    JPanel panelFooter= new PanelFooter();
    AppFrame(){

        this.setTitle("com.jackwhale.multiverse.Draft Board");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(1024, 768);
        this.setLayout(new BorderLayout());

//        this.add(panelDraftBoard);
//        this.add(panelHeader);
//        this.add(panelMenu);
//        this.add(panelFooter);


        this.add(panelHeader, BorderLayout.NORTH);
        this.add(panelMenu, BorderLayout.WEST);
        this.add(panelDraftBoard, BorderLayout.CENTER);
        this.add(panelFooter, BorderLayout.SOUTH);

        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());


        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}