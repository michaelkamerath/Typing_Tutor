import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Michael on 10/14/2014.
 */
public class virtualKeyboard extends JPanel {
    private String firstRow[];
    private String secondRow[];
    private String thirdRow[];
    private String fourthRow[];
    private String fifthRow[];
    private String rowsArray[][] = {firstRow, secondRow, thirdRow, fourthRow, fifthRow};

    private String firstShiftRow[];
    private String[] secondShiftRow;
    private String thirdShiftRow[];
    private String fourthShiftRow[];
    private String fifthShiftRow[];
    private String shiftRowsArray[][];

    private JButton row1[];
    private JButton row2[];
    private JButton row3[];
    private JButton row4[];
    private JButton row5[];
    private JButton buttonsArray[][];

    private JPanel lowerKeyboard;
    private JPanel upperKeyboard;
    private JPanel card;

    private CardLayout cl;

    public void addShiftRow(int rowNumber) {
            buttonsArray[rowNumber] = new JButton[shiftRowsArray[rowNumber].length];
            JPanel tempPanel = new JPanel(new GridLayout(1, shiftRowsArray[rowNumber].length));
            buttonsArray[rowNumber] = new JButton[shiftRowsArray[rowNumber].length];
            for (int i = 0; i < shiftRowsArray[rowNumber].length; i++) {
                JButton tempButton = new JButton(shiftRowsArray[rowNumber][i]);
                tempButton.setPreferredSize(new Dimension(50, 100));
                tempButton.setFocusable(false);
                buttonsArray[rowNumber][i] = tempButton;
                tempPanel.add(buttonsArray[rowNumber][i]);
            }
            upperKeyboard.add(tempPanel);
        }

    public void addRow(int rowNumber) {
        buttonsArray[rowNumber] = new JButton[rowsArray[rowNumber].length];
        JPanel tempPanel = new JPanel(new GridLayout(1, rowsArray[rowNumber].length));
        buttonsArray[rowNumber] = new JButton[rowsArray[rowNumber].length];
        for (int i = 0; i < rowsArray[rowNumber].length; i++) {
            JButton tempButton = new JButton(rowsArray[rowNumber][i]);
            tempButton.setPreferredSize(new Dimension(50, 100));
            tempButton.setFocusable(false);
            buttonsArray[rowNumber][i] = tempButton;
            tempPanel.add(buttonsArray[rowNumber][i]);
        }
        lowerKeyboard.add(tempPanel);
    }

    public JButton findButton(String button) {
        JButton returnedButton = null;
        for (int i = 0; i < rowsArray.length; ++i) {
            for (int j = 0; j < rowsArray[j].length; ++j) {
                if (button.equals(rowsArray[i][j])) {
                    returnedButton = buttonsArray[i][j];
                }
                else if (button.equals(shiftRowsArray[i][j])) {
                    returnedButton = buttonsArray[i][j];
                }
            }
        }
        return returnedButton;
    }

    public void lightButton(JButton button) {
        button.setBackground(Color.BLUE);
    }

    public void showUpperCard() {
        cl.last(card);
    }

    public void showLowerCard() {
        cl.first(card);
    }

        virtualKeyboard() {
            firstRow = new String[]{"~", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "="};
            secondRow = new String[]{"TAB", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\"};
            thirdRow = new String[]{"CAPS", "a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'", "ENTER"};
            fourthRow = new String[]{"SHIFT", "z", "x", "c", "v", "b", "n", "m", ",", ".", "/", "SHIFT" };
            fifthRow = new String[]{"SPACE"};
            rowsArray = new String[][]{firstRow, secondRow, thirdRow, fourthRow, fifthRow};

            firstShiftRow = new String[]{"~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+"};
            secondShiftRow = new String[]{"TAB", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "{", "}", "|"};
            thirdShiftRow = new String[]{"CAPS", "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"", "ENTER"};
            fourthShiftRow = new String[]{"SHIFT", "Z", "X", "C", "V", "B", "N", "M", "<", ">", "?", "SHIFT" };
            fifthShiftRow = new String[]{"SPACE"};
            shiftRowsArray = new String[][]{firstShiftRow, secondShiftRow, thirdShiftRow, fourthShiftRow, fifthShiftRow};

            lowerKeyboard = new JPanel();
            upperKeyboard = new JPanel();
            card = new JPanel();

            lowerKeyboard.setLayout(new GridLayout(5, 1));
            upperKeyboard.setLayout(new GridLayout(5, 1));

            card.setLayout(new CardLayout());
            cl = (CardLayout) card.getLayout();
            card.add(lowerKeyboard);
            card.add(upperKeyboard);

            setLayout(new GridLayout(1, 1));

            for (int i = 0; i < rowsArray.length; ++i) {
                addRow(i);
            }

            for (int i = 0; i < rowsArray.length; ++i) {
                addShiftRow(i);
            }

            add(card);
            setFocusable(false);
            setVisible(true);
            }
}

