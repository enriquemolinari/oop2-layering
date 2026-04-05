package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    private NuevaVentaView ventaView;
    private ListaVentasView listaVentasView;

    public MainView() {
        this.ventaView = new NuevaVentaView();
        this.listaVentasView = new ListaVentasView();
    }

    public void launch() {
        JFrame mainFrame = new JFrame("Estacion de Servicio");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 100);

        JButton nuevaVentabutton = new JButton("Nueva Venta");
        JButton listarVentasbutton = new JButton("Listas Ventas");
        var self = this;

        nuevaVentabutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.ventaView.createAndShowUI();
            }
        });

        listarVentasbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.listaVentasView.createAndShowUI();
            }
        });

        JPanel panel = new JPanel();
        panel.add(nuevaVentabutton);
        panel.add(listarVentasbutton);
        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}

