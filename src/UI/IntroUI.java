package UI;

import core.Booter;
import core.ResearchCenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class IntroUI extends JFrame {
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JButton exitButton, chooseButton, createCenterButton;
    private JComboBox<Object> centerList;
    private String pathName;
    private JanelaListener windowListener;

    public IntroUI(ArrayList<ResearchCenter> researchCenters, String pathName) {
        this.researchCenters = researchCenters;
        this.pathName = pathName;
        Drawer();
    }

    private void Drawer() {
        frame = new JFrame();
        windowListener = new JanelaListener();
        frame.addWindowListener(windowListener);
        ButtonListener listener = new ButtonListener();
        JPanel bottomPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel topPanel = new JPanel();
        exitButton = new JButton("Exit");
        exitButton.addActionListener(listener);
        createCenterButton = new JButton("Adicionar Centro");
        createCenterButton.addActionListener(listener);
        chooseButton = new JButton("Escolher");
        chooseButton.addActionListener(listener);

        centerList = new JComboBox<>();
        JLabel subtitle = new JLabel("Escolha um centro:");
        subtitle.setFont(new Font(subtitle.getFont().getName(), Font.BOLD, 22));
        centerList.addItem("---------------------------------------------");
        for (ResearchCenter r : researchCenters) {
            centerList.addItem(r);
        }
        topPanel.add(subtitle);

        middlePanel.add(centerList);
        bottomPanel.add(chooseButton);
        bottomPanel.add(createCenterButton);

        bottomPanel.add(exitButton);
        frame.setTitle("Project Manager");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == chooseButton) {
                if (centerList.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Por favor escolha um centro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.setVisible(false);
                    frame.dispose();
                    new CenterUI(researchCenters, centerList.getSelectedIndex() - 1, pathName);
                }
            } else if (e.getSource() == exitButton) {
                if (JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION) == 0) {
                    Booter booter = new Booter();
                    if (booter.saveObjectFile(pathName,researchCenters)){
                        System.out.println("Ficheiro Objeto guardado com Sucesso");
                    } else {
                        System.out.println("Erro ao Guardar Ficheiro Objeto");
                    }
                    frame.dispose();
                    System.exit(0);
                }
            } else if (e.getSource() == createCenterButton) {
                String name = JOptionPane.showInputDialog(null, "Indique o nome do centro de investigação:", "Adicionar novo Centro", JOptionPane.QUESTION_MESSAGE);
                if (name != null) {
                    ResearchCenter center = new ResearchCenter(name);
                    researchCenters.add(center);
                    centerList.addItem(center);
                }
            }
        }
    }

    private class JanelaListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            Booter booter = new Booter();
            if (booter.saveObjectFile(pathName,researchCenters)){
                System.out.println("Ficheiro Objeto guardado com Sucesso");
            } else {
                System.out.println("Erro ao Guardar Ficheiro Objeto");
            }
            frame.dispose();
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
