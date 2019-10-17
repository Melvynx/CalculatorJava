import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Window extends JFrame {
    //Result
    private JLabel labelResult = new JLabel("Entre ton calcule !");
    //Button Number
    private JButton but0 = new JButton("0");
    private JButton but1 = new JButton("1");
    private JButton but2 = new JButton("2");
    private JButton but3 = new JButton("3");
    private JButton but4 = new JButton("4");
    private JButton but5 = new JButton("5");
    private JButton but6 = new JButton("6");
    private JButton but7 = new JButton("7");
    private JButton but8 = new JButton("8");
    private JButton but9 = new JButton("9");
    //Button other
    private JButton butPoint = new JButton(".");
    private JButton butReset = new JButton("C");
    //Button operation
    private JButton butSum = new JButton("+");
    private JButton butSub = new JButton("-");
    private JButton butMult = new JButton("X");
    private JButton butDiv = new JButton("/");
    private JButton butResult = new JButton("=");
    //Settings
    private String numberWrite = "";
    private Double calc1 = 0.0;
    private Double resultT = 0.0;
    private Operation operation = Operation.DEFAULT;
    private boolean operationChoose = false;
    private boolean wantResult = false;
    private boolean error = false;

    Window() {
        this.setTitle("Calculette");
        this.setSize(350, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Style des Button et Label

        Font police = new Font("Kefa", Font.BOLD, 22);
        Color redReset = new Color(222,39,89);
        labelResult.setFont(police);but1.setFont(police);but2.setFont(police);but3.setFont(police);but4.setFont(police);but5.setFont(police);but6.setFont(police);but7.setFont(police);but8.setFont(police);but9.setFont(police);but0.setFont(police);butSub.setFont(police);butSum.setFont(police);butDiv.setFont(police);butReset.setFont(police);butResult.setFont(police);butPoint.setFont(police);
        butReset.setForeground(redReset);
        Color colorBorder = new Color(76,58,86);
        Border border = BorderFactory.createLineBorder(colorBorder, 3);
        labelResult.setBorder(border);
        labelResult.setPreferredSize(new Dimension(300,50));
        labelResult.setHorizontalAlignment(JLabel.CENTER);

        //Mise en place du result

        //Settings
        JPanel contain = new JPanel();
        contain.setLayout(new BorderLayout());
        contain.add(labelResult, BorderLayout.NORTH);

        //Mise en place des nombres

        GridLayout gridLayoutNumber = new GridLayout(4,3);
        gridLayoutNumber.setHgap(5);
        gridLayoutNumber.setVgap(5);
        JPanel containNumber = new JPanel();
        containNumber.setLayout(gridLayoutNumber);
        containNumber.add(but1);
        containNumber.add(but2);
        containNumber.add(but3);
        containNumber.add(but4);
        containNumber.add(but5);
        containNumber.add(but6);
        containNumber.add(but7);
        containNumber.add(but8);
        containNumber.add(but9);
        containNumber.add(but0);
        containNumber.add(butPoint);
        containNumber.add(butResult);
        contain.add(containNumber, BorderLayout.CENTER);

        //Mise en place des operation

        GridLayout gridLayoutOperation = new GridLayout(5,1);
        gridLayoutNumber.setHgap(5);
        gridLayoutNumber.setVgap(5);
        JPanel containOperation = new JPanel();
        containOperation.setLayout(gridLayoutOperation);
        containOperation.add(butReset);
        containOperation.add(butSum);
        containOperation.add(butSub);
        containOperation.add(butMult);
        containOperation.add(butDiv);
        contain.add(containOperation, BorderLayout.EAST);

        //Mise en place des variables
        but0.addActionListener(new ButtonNumber());
        but1.addActionListener(new ButtonNumber());
        but2.addActionListener(new ButtonNumber());
        but3.addActionListener(new ButtonNumber());
        but4.addActionListener(new ButtonNumber());
        but5.addActionListener(new ButtonNumber());
        but6.addActionListener(new ButtonNumber());
        but7.addActionListener(new ButtonNumber());
        but8.addActionListener(new ButtonNumber());
        but9.addActionListener(new ButtonNumber());
        butPoint.addActionListener(new ButtonNumber());

        butSum.addActionListener(new ButtonOperation());
        butDiv.addActionListener(new ButtonOperation());
        butSub.addActionListener(new ButtonOperation());
        butMult.addActionListener(new ButtonOperation());

        butReset.addActionListener(new ButtonRes());
        butResult.addActionListener(new ButtonRes());

        //Intégration à la page

        this.setContentPane(contain);
        this.setVisible(true);


    }
    class ButtonNumber implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            if (operationChoose) {
                numberWrite = "";
                operationChoose = false;
            }
            if (numberWrite.equals("0")) {
                numberWrite = "";
            }
            if (numberWrite.length() < 16 && !wantResult) {
                if (event.getSource() == but0) {
                    numberWrite += 0;
                }
                if (event.getSource() == but1) {
                    numberWrite += 1;
                }
                if (event.getSource() == but2) {
                    numberWrite += 2;
                }
                if (event.getSource() == but3) {
                    numberWrite += 3;
                }
                if (event.getSource() == but4) {
                    numberWrite += 4;
                }
                if (event.getSource() == but5) {
                    numberWrite += 5;
                }
                if (event.getSource() == but6) {
                    numberWrite += 6;
                }
                if (event.getSource() == but7) {
                    numberWrite += 7;
                }
                if (event.getSource() == but8) {
                    numberWrite += 8;
                }
                if (event.getSource() == but9) {
                    numberWrite += 9;
                }
                if (event.getSource() == butPoint) {
                    numberWrite += ".";
                }
            }
            if (!numberWrite.equals("Error")) {
                labelResult.setText(numberWrite);
            }
            System.out.println(numberWrite);
        }
    }
    class ButtonOperation implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            if (!wantResult) {
                Double calc2 = 0.0;
                try {
                    calc2 = Double.parseDouble(numberWrite);
                } catch (NumberFormatException | NullPointerException e) {
                    e.printStackTrace();
                    numberWrite = "0.0";
                }
                if (calc1 != 0.0 && calc2 != 0.0) {

                    if (operation == Operation.ADD) {
                        resultT = calc1 + calc2;
                    }
                    if (operation == Operation.SOUS) {
                        resultT = calc1 - calc2;
                    }
                    if (operation == Operation.MULT) {
                        resultT = calc1 * calc2;
                    }
                    if (operation == Operation.DIV) {
                        if (calc2 != 0) {
                            resultT = calc1 / calc2;
                        }else {
                            resultT = 0.0;
                            error = true;
                            labelResult.setText("Error");
                        }
                    }
                    if (!error) {
                        System.out.println("---ON OPERATION---");
                        System.out.println("Cal1 : " + calc1 + "\n Operation :" + operation + "\n Cal2 :" + calc2 + "\n Result :" + resultT);
                        calc1 = resultT;
                        labelResult.setText(resultT.toString());
                        numberWrite = resultT.toString();
                    }
                    error = false;
                }
            }
            wantResult = false;
            if (event.getSource() == butSum) {
                operation = Operation.ADD;
            }
            if (event.getSource() == butSub) {
                operation = Operation.SOUS;
            }
            if (event.getSource() == butMult) {
                operation = Operation.MULT;
            }
            if (event.getSource() == butDiv) {
                operation = Operation.DIV;
            }
            if (!operationChoose) {
                calc1 = Double.parseDouble(numberWrite);
                numberWrite = "";
                operationChoose = true;
            }
            System.out.println(operation);
        }
    }
    class ButtonRes implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == butReset) {
                numberWrite = "0";
                operation = Operation.DEFAULT;
                calc1 = 0.0;
                resultT = 0.0;
                labelResult.setText(numberWrite);
                wantResult = false;


            }
            if (event.getSource() == butResult) {
                wantResult = true;
                Double calc2 = 0.0;
                try {
                    calc2 = Double.parseDouble(numberWrite);
                } catch (NumberFormatException | NullPointerException e) {
                    e.printStackTrace();
                    numberWrite = "0.0";
                }
                System.out.println("égal");
                if (operation == Operation.ADD) {
                    resultT = calc1 + calc2;
                }
                if (operation == Operation.SOUS) {
                    resultT = calc1 - calc2;
                }
                if (operation == Operation.MULT) {
                    resultT = calc1 * calc2;
                }
                if (operation == Operation.DIV) {
                    if (calc2 != 0) {
                        resultT = calc1 / calc2;
                        System.out.println("error");
                    }else {
                        resultT = 0.0;
                        error = true;
                        labelResult.setText("Error");
                        calc1 = 0.0;
                        numberWrite = "";
                        System.out.println("error");
                        wantResult = false;
                    }
                }
                if (!error) {
                    System.out.println("Cal1 : " + calc1 + "\n Operation :" + operation + "\n Cal2 :" + calc2 + "\n Result :" + resultT);
                    calc1 = resultT;
                    labelResult.setText(resultT.toString());
                    numberWrite = resultT.toString();
                }
                error = false;
            }
        }
    }
}
