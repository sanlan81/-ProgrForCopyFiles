/**
 * Created by Sasha on 09.08.2016.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class FileCopy {
    FileCopy fileCopy2;

    JFrame frame;
    JPanel jPanel1 = new JPanel();

    JButton buttonCopy = new JButton("Copy");
    JButton buttonBrowseFrom = new JButton("Browse");
    JButton buttonBrowseTo = new JButton("Browse");

    JLabel labelfrom = new JLabel("Copy from :");
    JLabel labelto = new JLabel("Copy to :");
    JLabel label = new JLabel("");

    JTextField jTextfrom = new JTextField();
    JTextField jTextto = new JTextField();


    public static void main(String[] args) {

        FileCopy fileCopy = new FileCopy();
        fileCopy.go();

    	/*  int numberOfArgs = args.length;
           for (int i = 0; i < numberOfArgs; i++) {
               System.out.println("I've got an arg " + args[i]);}*/
    }

    void go() {
        // TODO Auto-generated method stub

        buttonBrowseFrom.addActionListener(new BrowseFrom());
        buttonBrowseTo.addActionListener(new BrowseTo());
        buttonCopy.addActionListener(new CopyProcess());

        frame = new JFrame("FileCopy");
        frame.getDefaultCloseOperation();


        jPanel1.setLayout(new GridLayout(3, 3));
        // jPanel2.setLayout(new GridLayout(1,1));

        jPanel1.add(labelfrom);
        jPanel1.add(jTextfrom);
        jPanel1.add(buttonBrowseFrom);
        jPanel1.add(labelto);
        jPanel1.add(jTextto);
        jPanel1.add(buttonBrowseTo);
        jPanel1.add(label);
        jPanel1.add(buttonCopy);

        frame.getContentPane().add(BorderLayout.NORTH, jPanel1);
        //frame.getContentPane().add(BorderLayout.SOUTH,jPanel2);


        frame.setSize(400, 117);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //frame.pack();

    }


    class BrowseFrom implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();

                jTextfrom.setText(String.valueOf(file));

            }
        }
    }

    class BrowseTo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();

                jTextto.setText(String.valueOf(file));

            }
        }
    }


    class CopyProcess implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CopyProcess copyProcess = new CopyProcess();

            copyProcess.go2();

            // System.out.println(jTextto.getText());

        }

        private void go2() {

            if (!jTextto.getText().equals("") && !jTextfrom.getText().equals("")) {
                //System.out.println(jTextto.getText());
                FileInputStream myFile = null;
                BufferedInputStream myBuf = null;

                FileOutputStream fileW = null;
                BufferedOutputStream buffW = null;
                try {

                    // myFile = new FileInputStream("" + args[0]);
                    myFile = new FileInputStream(jTextfrom.getText());

                    myBuf = new BufferedInputStream(myFile, 5000);
                    //fileW = new FileOutputStream("" + args[1]);
                    fileW = new FileOutputStream(jTextto.getText());
                    buffW = new BufferedOutputStream(fileW);

                    while (true) {
                        int ValueOfByte = myBuf.read();
                        //System.out.println(" " + ValueOfByte);
                        if (ValueOfByte == -1) {

                            break;
                        }
                        buffW.write(ValueOfByte);
                        buffW.flush();

                    }
                } catch (IOException e) {
                    System.out.println("Невозможно прочитать  файл " + e.toString());

                } finally {

                    try {
                        myFile.close();

                        buffW.close();

                    } catch (Exception e1) {
                        e1.printStackTrace();

                    }

                    System.out.println("Чтение файло завершено успешно");
                }
                //System.out.println("hey");

            }
        }

    }
}


