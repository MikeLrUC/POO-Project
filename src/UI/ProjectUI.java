package UI;

import core.ResearchCenter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjectUI {
    private int centerIndex, projectIndex;
    private ArrayList<ResearchCenter> researchCenters;
    private JFrame frame;
    private JDialog createTaskDialog;
    private ButtonListener buttonListener;
    private ListListener listListener;
    private JButton createTaskButton, removeTaskButton, listTaskButton, updateTaskButton, addDocenteButton, addBolseiroButton, changeRespButton, totalCostButton, endButton, backButton;
    private JButton backCreateTaskButton, trueCreateTaskButton;
    private JTextField diaInicioCreateTaskTextField, mesInicioCreateTaskTextField, anoInicioCreateTaskTextField,diaFimCreateTaskTextField, mesFimCreateTaskTextField, anoFimCreateTaskTextField ;
    private JComboBox typeCreateTaskBox;
    private JList<Object> peopleCreateTaskList;

    public ProjectUI(ArrayList<ResearchCenter> researchCenters, int centerIndex, int projectIndex) {
        this.researchCenters = researchCenters;
        this.centerIndex = centerIndex;
        this.projectIndex = projectIndex;
        drawer();
    }

    private void drawer() {
        frame = new JFrame();
        buttonListener = new ButtonListener();
        listListener = new ListListener();

        frame.setTitle("Project Manager");
        frame.setSize(650, 350);
        frame.setLayout(new GridLayout(4,1));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel titlePanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel projectLabel = new JLabel("Projeto " + researchCenters.get(centerIndex).getProjects().get(projectIndex).getNome());
        projectLabel.setFont(new Font(projectLabel.getFont().getName(), Font.BOLD,28));
        JLabel tarefasLabel = new JLabel("Tarefas:");
        JLabel pessoasLabel = new JLabel("Pessoas:");
        JLabel othersLabel = new JLabel("Outros:");

        //Tasks related Buttons
        createTaskButton = new JButton("Criar");
        createTaskButton.addActionListener(buttonListener);
        removeTaskButton = new JButton("Eliminar");
        listTaskButton = new JButton("Listar");
        updateTaskButton = new JButton("Atualizar");
        //People related Buttons
        addDocenteButton = new JButton("Associar Docente ao Projeto");
        addBolseiroButton = new JButton("Associar Bolseiro ao Projeto");
        changeRespButton = new JButton("Atribuir");
        //Other Buttons
        totalCostButton = new JButton("Custo Total");
        endButton = new JButton("TERMINAR!");
        backButton = new JButton("Voltar");
        backButton.addActionListener(buttonListener);

        titlePanel.add(projectLabel);

        topPanel.add(tarefasLabel);
        topPanel.add(createTaskButton);
        topPanel.add(removeTaskButton);
        topPanel.add(listTaskButton);
        topPanel.add(updateTaskButton);
        topPanel.add(changeRespButton);

        middlePanel.add(pessoasLabel);
        middlePanel.add(addDocenteButton);
        middlePanel.add(addBolseiroButton);


        bottomPanel.add(othersLabel);
        bottomPanel.add(totalCostButton);
        bottomPanel.add(endButton);
        bottomPanel.add(backButton);

        frame.add(titlePanel);
        frame.add(topPanel);
        frame.add(middlePanel);
        frame.add(bottomPanel);

        frame.setVisible(true);
    }

    private void createTaskDrawer(){
        createTaskDialog = new JDialog();
        createTaskDialog.setModal(true);
        createTaskDialog.setSize(550, 400);
        createTaskDialog.setLocationRelativeTo(null);
        createTaskDialog.setLayout(new GridLayout(1, 2));
        createTaskDialog.setTitle("Criar Tarefa");

        /*Panels*/
        //Left Panels
        JPanel leftCreateTaskPanel = new JPanel(new GridLayout(5,1));
        JPanel titleCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel fieldFillCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel inicioCreateTaskPanel = new JPanel(new GridLayout(2,1));
        JPanel topInicioCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel bottomInicioCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel fimCreateTaskPanel = new JPanel(new GridLayout(2,1));
        JPanel topFimCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel bottomFimCreateTaskPanel = new JPanel(new FlowLayout());
        JPanel typeCreateTaskPanel = new JPanel(new FlowLayout());

        //Right Panels
        JPanel rightCreateTaskPanel = new JPanel(new BorderLayout());
        JPanel buttonerCreateTaskPanel = new JPanel();

        /*Labels*/
        //Left Labels
        JLabel titleCreateTaskLabel = new JLabel("Criar Tarefa");
        titleCreateTaskLabel.setFont(new Font(titleCreateTaskLabel.getFont().getName(), Font.BOLD,30));
        JLabel fieldFillCreateTaskLabel = new JLabel("Preencha os seguintes campos:");
        fieldFillCreateTaskLabel.setFont(new Font(fieldFillCreateTaskLabel.getFont().getName(), Font.BOLD,15));
        JLabel inicioCreateTaskLabel = new JLabel("Data de Início:");
        JLabel diaInicioCreateTaskLabel = new JLabel("Dia:");
        JLabel mesInicioCreateTaskLabel = new JLabel("Mês:");
        JLabel anoInicioCreateTaskLabel = new JLabel("Ano:");
        JLabel etcCreateTaskLabel = new JLabel("Data estimada de Conclusão:");
        JLabel diaFimCreateTaskLabel = new JLabel("Dia:");
        JLabel mesFimCreateTaskLabel = new JLabel("Mês:");
        JLabel anoFimCreateTaskLabel = new JLabel("Ano:");
        JLabel typeCreateTaskLabel = new JLabel("Tipo:");

        //Right Labels
        JLabel choosePersonCreateTaskLabel = new JLabel("Escolha uma Pessoa:");

        /*Buttons*/
        backCreateTaskButton = new JButton("Voltar");
        backCreateTaskButton.addActionListener(buttonListener);
        trueCreateTaskButton = new JButton("Criar Tarefa");
        trueCreateTaskButton.addActionListener(buttonListener);
        trueCreateTaskButton.setEnabled(false);

        /*TextFields*/
        diaInicioCreateTaskTextField = new JTextField(2);
        mesInicioCreateTaskTextField = new JTextField(2);
        anoInicioCreateTaskTextField = new JTextField(2);

        diaFimCreateTaskTextField = new JTextField(2);
        mesFimCreateTaskTextField = new JTextField(2);
        anoFimCreateTaskTextField = new JTextField(2);

        /*Combo box*/
        typeCreateTaskBox = new JComboBox();
        typeCreateTaskBox.addItem("Documentação");
        typeCreateTaskBox.addItem("Design");
        typeCreateTaskBox.addItem("Desenvolvimento");

        /*List*/
        DefaultListModel<Object> peopleCreateTaskObjs = new DefaultListModel<Object>();
        peopleCreateTaskObjs.addAll(researchCenters.get(centerIndex).getProjects().get(projectIndex).getPessoas());
        peopleCreateTaskList = new JList<Object>(peopleCreateTaskObjs);
        peopleCreateTaskList.addListSelectionListener(listListener);

        /*Setting up left side of the menu*/
        //Title and information
        titleCreateTaskPanel.add(titleCreateTaskLabel);
        fieldFillCreateTaskPanel.add(fieldFillCreateTaskLabel);

        //adding inicioLabels and TextFields
        topInicioCreateTaskPanel.add(inicioCreateTaskLabel);
        bottomInicioCreateTaskPanel.add(diaInicioCreateTaskLabel);
        bottomInicioCreateTaskPanel.add(diaInicioCreateTaskTextField);
        bottomInicioCreateTaskPanel.add(mesInicioCreateTaskLabel);
        bottomInicioCreateTaskPanel.add(mesInicioCreateTaskTextField);
        bottomInicioCreateTaskPanel.add(anoInicioCreateTaskLabel);
        bottomInicioCreateTaskPanel.add(anoInicioCreateTaskTextField);

        inicioCreateTaskPanel.add(topInicioCreateTaskPanel);
        inicioCreateTaskPanel.add(bottomInicioCreateTaskPanel);

        //adding fim Labels and TextFields
        topFimCreateTaskPanel.add(etcCreateTaskLabel);
        bottomFimCreateTaskPanel.add(diaFimCreateTaskLabel);
        bottomFimCreateTaskPanel.add(diaFimCreateTaskTextField);
        bottomFimCreateTaskPanel.add(mesFimCreateTaskLabel);
        bottomFimCreateTaskPanel.add(mesFimCreateTaskTextField);
        bottomFimCreateTaskPanel.add(anoFimCreateTaskLabel);
        bottomFimCreateTaskPanel.add(anoFimCreateTaskTextField);

        fimCreateTaskPanel.add(topFimCreateTaskPanel);
        fimCreateTaskPanel.add(bottomFimCreateTaskPanel);

        //adding type task ComboBox
        typeCreateTaskPanel.add(typeCreateTaskLabel);
        typeCreateTaskPanel.add(typeCreateTaskBox);

        //adding the left subpanels to the main left panel
        leftCreateTaskPanel.add(titleCreateTaskPanel);
        leftCreateTaskPanel.add(fieldFillCreateTaskPanel);
        leftCreateTaskPanel.add(inicioCreateTaskPanel);
        leftCreateTaskPanel.add(fimCreateTaskPanel);
        leftCreateTaskPanel.add(typeCreateTaskPanel);

        /*Setting up right side of the menu*/
        //adding buttons to the buttoner
        buttonerCreateTaskPanel.add(backCreateTaskButton);
        buttonerCreateTaskPanel.add(trueCreateTaskButton);

        //adding the right components to the main right panel
        rightCreateTaskPanel.add(choosePersonCreateTaskLabel,BorderLayout.NORTH);
        rightCreateTaskPanel.add(peopleCreateTaskList,BorderLayout.CENTER);
        rightCreateTaskPanel.add(buttonerCreateTaskPanel,BorderLayout.SOUTH);

        /*Adding left and right panels to the main JDialog*/
        createTaskDialog.add(leftCreateTaskPanel);
        createTaskDialog.add(rightCreateTaskPanel);

        createTaskDialog.setVisible(true);
    }

    private void taskCreater(){
        int diaInicio, mesInicio, anoInicio;
        int diaFim, mesFim, anoFim;
        if (( 1 <= diaInicio && diaInicio <= 31 ) && ( 1 <= diaInicio && diaInicio <= 31 ))
    }

    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == backButton){
                frame.setVisible(false);
                frame.dispose();
                new CenterUI(researchCenters,centerIndex);
            } else if (e.getSource() == createTaskButton) {
                createTaskDrawer();
            } else if (e.getSource() == trueCreateTaskButton){
                createTaskDialog.setVisible(false);
                createTaskDialog.dispose();
                taskCreater();
            } else if (e.getSource() == backCreateTaskButton){

            }
        }
    }

    private class ListListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == peopleCreateTaskList){
                if (peopleCreateTaskList.getSelectedIndex() == -1){
                    trueCreateTaskButton.setEnabled(false);
                } else {
                    trueCreateTaskButton.setEnabled(true);
                }
            }
        }
    }
}
