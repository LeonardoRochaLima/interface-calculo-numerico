package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.util.ArrayList;

public class BisceccaoGui {



    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel painel;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JTextField textField7;
    private JList list1;

    public BisceccaoGui() {

        Main biseccaco = new Main();


        button1.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                Float a5 = Float.parseFloat(textField1.getText());
                Float a4 = Float.parseFloat(textField2.getText());
                Float a3 = Float.parseFloat(textField3.getText());
                Float a2 = Float.parseFloat(textField4.getText());
                Float a1 = Float.parseFloat(textField5.getText());
                Float a0 = Float.parseFloat(textField6.getText());
                Integer epsilon = Integer.parseInt(textField7.getText());
                biseccaco.preencherEpsilon(epsilon);
                biseccaco.preencherFuncao(a5,a4,a3,a2,a1,a0);
                ArrayList<Integer[]> intervalos = biseccaco.acharIntervalos();
                //criar lista de raízes
                DefaultListModel listModel = new DefaultListModel();
                list1.setModel(listModel);
                for (int i = 0;i<intervalos.size();i++){
                    listModel.add(i,String.format("[%d,%d]",intervalos.get(i)[0],intervalos.get(i)[1]));
                }



                textPane1.setText(biseccaco.exibirIntervalos());
                //textPane2.setText(biseccaco.exibirRaizes());


            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                ArrayList<Integer[]> intervalos = biseccaco.acharIntervalos();


                ArrayList<Double> raizes_aproximadas = biseccaco.refinarValores1(intervalos.get(list1.getSelectedIndex()));

                StringBuilder tabela = new StringBuilder("k \t x \t f(x) \n");
                int k = 0;

                for(Double i : raizes_aproximadas){
                    tabela.append(String.format("%d \t %f \t %f \n",k++,i,biseccaco.resolverFuncao(i)));

                }

                textPane2.setText(tabela.toString());

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BisceccaoGui");
        frame.setContentPane(new BisceccaoGui().painel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
