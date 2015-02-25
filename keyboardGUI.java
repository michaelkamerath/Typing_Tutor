import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Created by Michael on 10/20/2014.
 */
public class keyboardGUI extends JFrame implements KeyListener, ActionListener {
    private JTextField typingArea;
    virtualKeyboard VirtualKeyboard = new virtualKeyboard();
    prompts Prompts = new prompts();
    private String promptNames[];
    private JComboBox<String> changePrompts;
    private HashMap<Character, Integer> mistakes;
    private Integer topFiveNumbers[];
    private Character topFiveChars[];
    private JLabel instructions;
    private int cursorPosition = 0;
    private int numMistakes = 0;
    private JLabel displayMistakes;
    private boolean startTimer = false;
    private JLabel showTime;
    private int time = 120;
    private int timePassed;
    private int charactersTyped = 0;
    private Timer timer = new Timer();
    Task task = new Task();


    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int getWpm() {
        return (getCharactersTyped() / 5) * 60 / getTimePassed();
    }

    public void setCharactersTyped(int characters) {
        this.charactersTyped += characters;
    }

    public int getCharactersTyped() {
        return charactersTyped;
    }

    public void incrementTimePassed() {
        timePassed++;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public int decrementTime() {
        this.time--;
        return this.time;
    }

    public void setStartTimer() {
        this.startTimer = true;
    }

    public boolean getStartTimer() {
        return startTimer;
    }

    public void setNumMistakes(int numMistakes) {
        this.numMistakes = numMistakes;
    }

    public int getNumMistakes() {
        return numMistakes;
    }

    public void incrementCursorPosition(int cursorPosition) {
        this.cursorPosition += cursorPosition;
    }

    public void resetCursorPosition() {this.cursorPosition = 0;}

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void showMistakes() {
        displayMistakes.setText("Number of Mistakes: " + getNumMistakes());
    }

    public class Task extends TimerTask {
        @Override
        public void run() {
            incrementTimePassed();
            showTime.setText("Time: " + decrementTime());
        }
    }

    keyboardGUI() {
        mistakes = new HashMap<Character, Integer>();
        topFiveNumbers = new Integer[5];
        topFiveChars = new Character[5];

        promptNames = new String[] {
                "Prompt 1", "Prompt 2", "Prompt 3", "Prompt 4", "Prompt 5", "Prompt 6", "Prompt 7", "Prompt 8", "Prompt 9", "Prompt 10", "Prompt 11"
        };

        showTime = new JLabel("Time: 120");
        showTime.setSize(150, 50);
        showTime.setLocation(840, 0);

        displayMistakes = new JLabel("Number of Mistakes: 0");
        displayMistakes.setSize(150, 50);
        displayMistakes.setLocation(40, 0);

        changePrompts = new JComboBox<String>(promptNames);
        changePrompts.addActionListener(this);
        changePrompts.setFocusable(false);
        changePrompts.setSize(100, 50);
        changePrompts.setLocation(40, 50);

        VirtualKeyboard.setLocation(-10,475);
        VirtualKeyboard.setSize(new Dimension(1000, 250));
        VirtualKeyboard.setFocusable(false);

        Prompts.setLocation(40, 100);
        Prompts.setSize(900, 250);
        Prompts.setFocusable(false);

        instructions = new JLabel("Select a prompt and being typing when you are ready, the prompts are below");
        instructions.setSize(new Dimension(500, 50));
        instructions.setLocation(275, 0);

        typingArea = new JTextField();
        typingArea.setFocusable(false);
        typingArea.setSize(new Dimension(500, 50));
        typingArea.setLocation(240, 400);

        Container pane = getContentPane();
        pane.setLayout(null);
        pane.add(VirtualKeyboard);
        pane.add(typingArea);
        pane.add(instructions);
        pane.add(Prompts);
        pane.add(changePrompts);
        pane.add(displayMistakes);
        pane.add(showTime);

        setFocusable(true);
        requestFocusInWindow();
        setTitle("Keyboard Project");
        setVisible(true);
        setSize(1000, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(this);
        Prompts.setLengthOfPrompt();


    }
    @Override
    public void keyTyped(KeyEvent e) {
        String text = typingArea.getText();
        typingArea.setText(text + e.getKeyChar() + "");
        if (e.getKeyChar() != Prompts.promptPosition(getCursorPosition())) {
            setNumMistakes(numMistakes + 1);
            showMistakes();
            if (mistakes.containsKey(e.getKeyChar())) {
                int value = mistakes.containsKey(e.getKeyChar()) ? mistakes.get(e.getKeyChar()) : 0;
                mistakes.put(e.getKeyChar(), value + 1);
            }
            else {
                int value = 1;
                mistakes.put(e.getKeyChar(), value);
            }
        }
            incrementCursorPosition(1);
    }
    @Override
    public void keyPressed(KeyEvent e) {
       if (e.isShiftDown() == true) {
            VirtualKeyboard.showUpperCard();
        }
       if (!getStartTimer()) {
           setStartTimer();
           timer.schedule(task, 0, 1000);
       }
        setCharactersTyped(1);
        //VirtualKeyboard.findButton(Character.toString(e.getKeyChar()));
        //VirtualKeyboard.lightButton(VirtualKeyboard.findButton(Character.toString(e.getKeyChar())));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        VirtualKeyboard.showLowerCard();
        if (getCursorPosition() == Prompts.getLengthOfPrompt() || getTime() <= 0) {
            if (Prompts.getAtPrompt() == Prompts.getTypingPrompts().length - 1) {
                timer.purge();
                List <Integer> fullList = new ArrayList<Integer>(mistakes.values());
                Collections.sort(fullList, Collections.reverseOrder());
                List<Integer> topFive = fullList.subList(0, 5);
                int i = 0;
                for (Map.Entry<Character, Integer> map : mistakes.entrySet()) {
                    if (topFive.contains(map.getValue())){
                        topFiveChars[i] = map.getKey();
                        topFiveNumbers[i] = map.getValue();
                        ++i;
                        if (i == 5) {
                            break;
                        }
                    }
                }
                Arrays.sort(topFiveChars);
                Arrays.sort(topFiveNumbers);
                JOptionPane.showMessageDialog(this, "You've reached the end of the prompts! You're five most commonly missed keys and the number of times you missed them is " + Arrays.toString(topFiveChars) + Arrays.toString(topFiveNumbers) + "And your words per minute was " + getWpm());

                        Prompts.setAtPrompt(0);
                        Prompts.displayNewPrompt();
                        resetCursorPosition();
                        setNumMistakes(0);
                        setTime(120);
            }
            else {
                Prompts.setAtPrompt(Prompts.getAtPrompt() + 1);
            }
            typingArea.setText("");
            Prompts.displayNewPrompt();
            resetCursorPosition();
            Prompts.setLengthOfPrompt();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        Prompts.setAtPrompt(cb.getSelectedIndex());
        Prompts.displayNewPrompt();
        Prompts.setLengthOfPrompt();
        resetCursorPosition();
        typingArea.setText("");
    }
}
