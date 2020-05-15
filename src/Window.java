import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Window extends JFrame {
    private JLabel labelResult = new JLabel("Entre ton calcule !");
    private JPanel contain = new JPanel();
    private String[] buttonPanels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "="};
    private String[] buttonColumns = {"C", "+", "-", "X", "/"};

    private String currentNumber = "";
    private Double currentResult = 0.0;
    private Double result = 0.0;
    private Operation operation = Operation.DEFAULT;
    private boolean operationChoose = false;
    private boolean wantResult = false;
    private boolean error = false;

    Window() {
        this.setTitle("Calculette");
        this.setSize(350, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // init container
        contain.setLayout(new BorderLayout());
        contain.add(labelResult, BorderLayout.NORTH);
        //Style des Button et Label
        initLabel();

        initButtonPanel();

        initButtonColumn();

        this.setContentPane(contain);
        this.setVisible(true);
    }

    private void initLabel() {
        Font police = new Font("Kefa", Font.BOLD, 22);
        Color colorBorder = new Color(76,58,86);
        Border border = BorderFactory.createLineBorder(colorBorder, 3);
        labelResult.setFont(police);
        labelResult.setBorder(border);
        labelResult.setPreferredSize(new Dimension(300,50));
        labelResult.setHorizontalAlignment(JLabel.RIGHT);
    }

    private void initButtonPanel() {
        GridLayout gridLayoutNumber = new GridLayout(4,3);
        Font police = new Font("Kefa", Font.BOLD, 22);
        JPanel containNumber = new JPanel();

        gridLayoutNumber.setHgap(5);
        gridLayoutNumber.setVgap(5);
        containNumber.setLayout(gridLayoutNumber);

        for (String buttonPanel : buttonPanels) {
            JButton button = new JButton(buttonPanel);
            containNumber.add(button);
            button.setFont(police);
            button.addActionListener(new EventNumbers());
        }

        contain.add(containNumber, BorderLayout.CENTER);
    }

    private void initButtonColumn() {
        GridLayout gridLayoutOperation = new GridLayout(5,1);
        Font police = new Font("Kefa", Font.BOLD, 22);
        JPanel containOperation = new JPanel();

        gridLayoutOperation.setHgap(5);
        gridLayoutOperation.setVgap(5);
        containOperation.setLayout(gridLayoutOperation);

        for (String buttonColumn : buttonColumns) {
            JButton button = new JButton(buttonColumn);
            containOperation.add(button);
            button.addActionListener(new EventColumns());
            button.setFont(police);
        }

        contain.add(containOperation, BorderLayout.EAST);
    }


    class EventNumbers implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            String sourceText = event.getActionCommand();
            if (sourceText.equals("=")) {
                wantResult = true;
                getResult();
                return;
            }
            addNumber(event);
        }
    }

    class EventColumns implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            String sourceText = event.getActionCommand();
            if(sourceText.equals("C")) {
                clear();
                return;
            }
            setOperator(event);
        }
    }

    private void addNumber(ActionEvent event) {
        String sourceText = event.getActionCommand();

        if (operationChoose) {
            currentNumber = "";
            operationChoose = false;
        }
        if (currentNumber.equals("0")) {
            currentNumber = "";
        }

        if (currentNumber.length() < 16 && !wantResult) {
            currentNumber += sourceText;
        }

        if (!currentNumber.equals("Error")) {
            labelResult.setText(currentNumber);
        }
        System.out.println(currentNumber);
    }

    private void clear() {
        currentNumber = "0";
        operation = Operation.DEFAULT;
        currentResult = 0.0;
        result = 0.0;
        labelResult.setText(currentNumber);
        wantResult = false;
    }

    private void setOperator(ActionEvent event) {
        if (!wantResult) {
            getResult();
        }
        wantResult = false;
        String sourceText = event.getActionCommand();

        if (sourceText.equals("+")) {
            operation = Operation.ADD;
        }
        if (sourceText.equals("-")) {
            operation = Operation.SOUS;
        }
        if (sourceText.equals("X")) {
            operation = Operation.MULT;
        }
        if (sourceText.equals("/")) {
            operation = Operation.DIV;
        }

        if (!operationChoose) {
            currentResult = Double.parseDouble(currentNumber);
            currentNumber = "";
            operationChoose = true;
        }
    }

    private void getResult() {
        Double calc2 = 0.0;
        try {
            calc2 = Double.parseDouble(currentNumber);
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
            currentNumber = "0.0";
        }
        if (currentResult != 0.0 && calc2 != 0.0) {

            if (operation == Operation.ADD) {
                result = currentResult + calc2;
            }
            if (operation == Operation.SOUS) {
                result = currentResult - calc2;
            }
            if (operation == Operation.MULT) {
                result = currentResult * calc2;
            }
            if (operation == Operation.DIV) {
                if (calc2 != 0) {
                    result = currentResult / calc2;
                }else {
                    result = 0.0;
                    error = true;
                    labelResult.setText("Error");
                }
            }
            if (!error) {
                System.out.println("---ON OPERATION---");
                System.out.println("Cal1 : " + currentResult + "\n Operation :" + operation + "\n Cal2 :" + calc2 + "\n Result :" + result);
                currentResult = result;
                labelResult.setText(result.toString());
                currentNumber = result.toString();
            }
            error = false;
        }
    }
}
