package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


// Main class for TicTacToe App that extends JFrame and implements ActionListener
public class TicTacToeApp extends JFrame implements ActionListener {
    // Mendeklarasikan komponen untuk permainan
    private JButton[][] buttons;
    private char currentPlayer = 'X';
    private boolean gameOver = false;
    private int rows, cols;
    private JLabel statusLabel;
    private boolean playWithBot = false;
    private String botDifficulty;

    // Konstruktor untuk window
    public TicTacToeApp() {
        setTitle("Tic Tac Toe");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initGameModePage();
    }

    // Menginisialisasi halaman untuk memilih mode permainan
    private void initGameModePage() {
        getContentPane().setBackground(new Color(255, 204, 255));
        getContentPane().removeAll();

        JPanel modePanelAtas = new JPanel();
        modePanelAtas.setPreferredSize(new Dimension(100, 50));
        modePanelAtas.setBackground(new Color(255, 204, 255));
        add(modePanelAtas, BorderLayout.NORTH);

        JPanel modePanelKanan = new JPanel();
        modePanelKanan.setPreferredSize(new Dimension(200, 100));
        modePanelKanan.setBackground(new Color(255, 204, 255));
        add(modePanelKanan, BorderLayout.EAST);

        JPanel modePanelKiri = new JPanel();
        modePanelKiri.setPreferredSize(new Dimension(200, 100));
        modePanelKiri.setBackground(new Color(255, 204, 255));
        add(modePanelKiri, BorderLayout.WEST);

        JPanel modePanelBawah = new JPanel();
        modePanelBawah.setPreferredSize(new Dimension(100, 100));
        modePanelBawah.setBackground(new Color(255, 204, 255));
        add(modePanelBawah, BorderLayout.SOUTH);

        JPanel modePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        modePanel.setBackground(new Color(255, 204, 255));

        JLabel modeLabel = new JLabel("TicTacToe!", SwingConstants.CENTER);
        modeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));

        JLabel chooseLabel = new JLabel("Choose Game Mode:", SwingConstants.CENTER);
        chooseLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        JButton friendButton = new JButton("Play with Friend");
        friendButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        friendButton.setBackground(Color.BLACK);
        friendButton.setForeground(Color.WHITE);
        friendButton.setFocusPainted(false);
        friendButton.addActionListener(e -> {
            playWithBot = false;
            initWelcomePage();
        });

        JButton botButton = new JButton("Play with Bot");
        botButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        botButton.setBackground(Color.BLACK);
        botButton.setForeground(Color.WHITE);
        botButton.setFocusPainted(false);
        botButton.addActionListener(e -> {
            playWithBot = true;
            initBotDifficultyPage();
        });

        modePanel.add(modeLabel);
        modePanel.add(chooseLabel);
        modePanel.add(friendButton);
        modePanel.add(botButton);

        add(modePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    // Menginisialisasi halaman untuk memilih ukuran papan
    private void initWelcomePage() {
        getContentPane().removeAll();
        getContentPane().setBackground(new Color(255, 204, 255));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(255, 204, 255));

        JLabel rowLabel = new JLabel("Select Board:", SwingConstants.CENTER);
        rowLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        JComboBox<String> sizeComboBox = new JComboBox<>(new String[]{"3 x 3", "4 x 4", "5 x 5"});
        sizeComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        sizeComboBox.setBackground(Color.BLACK);
        sizeComboBox.setForeground(Color.WHITE);
        sizeComboBox.setPreferredSize(new Dimension(200, 40));

        JButton startButton = new JButton("START!");
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        startButton.setBackground(Color.RED);
        startButton.setForeground(Color.BLACK);
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(200, 40));
        startButton.addActionListener(e -> {
            String selectedSize = (String) sizeComboBox.getSelectedItem();
            if (selectedSize != null) {
                String[] dimensions = selectedSize.split("x");
                rows = Integer.parseInt(dimensions[0].trim());
                cols = Integer.parseInt(dimensions[1].trim());
                startGame();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(rowLabel, gbc);

        gbc.gridy = 1;
        inputPanel.add(sizeComboBox, gbc);

        gbc.gridy = 2;
        inputPanel.add(startButton, gbc);

        add(inputPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Halaman untuk memilih tingkat kesulitan bot
    private void initBotDifficultyPage() {
        getContentPane().removeAll();
        getContentPane().setBackground(new Color(255, 204, 255));

        JPanel difficultyPanel = new JPanel(new GridBagLayout());
        difficultyPanel.setBackground(new Color(255, 204, 255));

        JLabel difficultyLabel = new JLabel("Choose Difficulty:", SwingConstants.CENTER);
        difficultyLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        JButton easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        easyButton.setBackground(Color.BLACK);
        easyButton.setForeground(Color.WHITE);
        easyButton.setFocusPainted(false);
        easyButton.setPreferredSize(new Dimension(200, 40));
        easyButton.addActionListener(e -> {
            botDifficulty = "Easy";
            initWelcomePage();
        });
        
        JButton mediumButton = new JButton("Medium");
        mediumButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        mediumButton.setBackground(Color.BLACK);
        mediumButton.setForeground(Color.WHITE);
        mediumButton.setFocusPainted(false);
        mediumButton.setPreferredSize(new Dimension(200, 40));
        mediumButton.addActionListener(e -> {
            botDifficulty = "Medium";
            initWelcomePage();
        });
        
        JButton hardButton = new JButton("Hard");
        hardButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        hardButton.setBackground(Color.BLACK);
        hardButton.setForeground(Color.WHITE);
        hardButton.setFocusPainted(false);
        hardButton.setPreferredSize(new Dimension(200, 40));
        hardButton.addActionListener(e -> {
            botDifficulty = "Hard";
            initWelcomePage();
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        difficultyPanel.add(difficultyLabel, gbc);
        
        gbc.gridy = 1;
        difficultyPanel.add(easyButton, gbc);
        
        gbc.gridy = 2;
        difficultyPanel.add(mediumButton, gbc);
        
        gbc.gridy = 3;
        difficultyPanel.add(hardButton, gbc);
        
        add(difficultyPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Start game
    private void startGame() {
        getContentPane().removeAll();
        getContentPane().setBackground(new Color(255, 204, 255));

        gameOver = false;
        currentPlayer = 'X';

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(new Color(255, 204, 255));
        JLabel sizeLabel = new JLabel(rows + "x" + cols, SwingConstants.LEFT);
        sizeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn", SwingConstants.RIGHT);
        statusLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        statusPanel.add(sizeLabel, BorderLayout.WEST);
        statusPanel.add(statusLabel, BorderLayout.EAST);
        add(statusPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(rows, cols));
        boardPanel.setBackground(new Color(255, 204, 255));
        buttons = new JButton[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setBackground(Color.BLACK);
                buttons[row][col].addActionListener(this);
                boardPanel.add(buttons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(new Color(255, 204, 255));

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        restartButton.setBackground(Color.BLACK);
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setPreferredSize(new Dimension(200, 40));
        restartButton.addActionListener(e -> {
            UIManager.put("Panel.background", new Color(255, 204, 255)); 
            UIManager.put("OptionPane.background", new Color(255, 204, 255)); 
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to restart?", "Confirm Restart", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                startGame();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFocusPainted(false);
        quitButton.setPreferredSize(new Dimension(200, 40));
        quitButton.addActionListener(e -> {
            UIManager.put("Panel.background", new Color(255, 204, 255)); 
            UIManager.put("OptionPane.background", new Color(255, 204, 255)); 
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                resetToWelcomePage();
            }
        });

        controlPanel.add(restartButton);
        controlPanel.add(quitButton);
        add(controlPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
    
        if (!buttonClicked.getText().equals("") || gameOver) {
            return;
        }
    
        
        buttonClicked.setText(String.valueOf(currentPlayer));
        buttonClicked.setForeground(currentPlayer == 'X' ? new Color(135, 206, 250) : Color.RED);
    
        
        if (checkForWinner(true)) {
            showCustomDialog("Player " + currentPlayer + " wins!");
            gameOver = true;
            return;
        }
    
        
        if (isBoardFull()) {
            showCustomDialog("It's a draw!");
            gameOver = true;
            return;
        }
    
        
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    
        
        if (playWithBot && currentPlayer == 'O') {
            makeBotMove();
        }
    }
    
    //  Pop up dengan tambahan UI
    private void showCustomDialog(String message) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 255));
        panel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        messageLabel.setForeground(Color.BLACK);
        panel.add(messageLabel, BorderLayout.CENTER);

        UIManager.put("OptionPane.background", new Color(255, 204, 255));
        UIManager.put("Panel.background", new Color(255, 204, 255));
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);

        JOptionPane.showMessageDialog(this, panel, "Game Result", JOptionPane.PLAIN_MESSAGE);
    }

    private void makeBotMove() {
        if (gameOver) return;
    
        if (botDifficulty.equals("Easy")) {
            easyBotMove();
        } else if (botDifficulty.equals("Medium")) {
            mediumBotMove();
        } else if (botDifficulty.equals("Hard")) {
            hardBotMove();
        }
    }
    
    private void easyBotMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(rows);
            col = random.nextInt(cols);
        } while (!buttons[row][col].getText().equals(""));
    
        buttons[row][col].setText("O");
        buttons[row][col].setForeground(Color.RED);
    
        checkGameState();
    }
    
    private void mediumBotMove() {
        
        int[] blockMove = findBlockingMove();
        if (blockMove != null) {
        
            buttons[blockMove[0]][blockMove[1]].setText("O");
            buttons[blockMove[0]][blockMove[1]].setForeground(Color.RED);
        } else {
        
            easyBotMove();
        }
        checkGameState();
    }
    
    private int[] findBlockingMove() {
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buttons[i][j].getText().equals("")) {
        
                    buttons[i][j].setText("X");  
                    if (checkForWinner(true)) {
        
                        buttons[i][j].setText("");
                        return new int[] {i, j};  
                    }
                    buttons[i][j].setText("");  
                }
            }
        }
        return null; 
    }
    
    private void hardBotMove() {
        int[] bestMove = minimax();
        buttons[bestMove[0]][bestMove[1]].setText("O");
        buttons[bestMove[0]][bestMove[1]].setForeground(Color.RED);
    
        checkGameState();
    }
    
    private int[] minimax() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText("O");  
                    int score = minimaxScore(false); 
                    buttons[i][j].setText(""); 
    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }
    
    private int minimaxScore(boolean isMaximizing) {
        if (checkForWinner(true)) {
            return isMaximizing ? -1 : 1;
        }
        if (isBoardFull()) {
            return 0;
        }
    
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText(isMaximizing ? "O" : "X");
                    int score = minimaxScore(!isMaximizing);
                    buttons[i][j].setText("");
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
    
        return bestScore;
    }
    
    
    private void checkGameState() {
        if (checkForWinner(true)) {
            showCustomDialog("Bot wins!");
            gameOver = true;
        } else if (isBoardFull()) {
            showCustomDialog("It's a draw!");
            gameOver = true;
        } else {
            currentPlayer = 'X';
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }
    

    private void resetToWelcomePage() {
        initGameModePage();
        revalidate();
        repaint();
    }

    private boolean checkForWinner(boolean forEvaluation) {
        for (int i = 0; i < rows; i++) {
            if (isRowWin(i, forEvaluation)) return true;
        }
        for (int i = 0; i < cols; i++) {
            if (isColumnWin(i, forEvaluation)) return true;
        }
        return isDiagonalWin(forEvaluation); 
    }
    
    

    private boolean isRowWin(int row, boolean forEvaluation) {
        String firstCell = buttons[row][0].getText();
        if (firstCell.equals("") || !firstCell.equals(String.valueOf(currentPlayer))) return false;
        for (int col = 1; col < cols; col++) {
            if (!buttons[row][col].getText().equals(firstCell)) {
                return false;
            }
        }
        if (!forEvaluation) highlightWinningRow(row);
        return true;
    }
    
    private boolean isColumnWin(int col, boolean forEvaluation) {
        String firstCell = buttons[0][col].getText();
        if (firstCell.equals("") || !firstCell.equals(String.valueOf(currentPlayer))) return false;
        for (int row = 1; row < rows; row++) {
            if (!buttons[row][col].getText().equals(firstCell)) {
                return false;
            }
        }
        if (!forEvaluation) highlightWinningColumn(col);
        return true;
    }
    
    private boolean isDiagonalWin(boolean forEvaluation) {
        // Cek diagonal utama
        String firstCell = buttons[0][0].getText();
        boolean diagonal1 = !firstCell.equals("") && firstCell.equals(String.valueOf(currentPlayer));
        for (int i = 1; i < rows; i++) {
            if (!buttons[i][i].getText().equals(firstCell)) {
                diagonal1 = false;
                break;
            }
        }
        
        // Cek diagonal sekunder
        firstCell = buttons[0][cols - 1].getText();
        boolean diagonal2 = !firstCell.equals("") && firstCell.equals(String.valueOf(currentPlayer));
        for (int i = 1; i < rows; i++) {
            if (!buttons[i][cols - i - 1].getText().equals(firstCell)) {
                diagonal2 = false;
                break;
            }
        }
    
        if (!forEvaluation) {
            if (diagonal1) highlightWinningDiagonal(true);
            if (diagonal2) highlightWinningDiagonal(false);
        }
        return diagonal1 || diagonal2;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (buttons[row][col].getText().equals("")) return false;
            }
        }
        return true;
    }

    private void highlightWinningRow(int row) {
        for (int col = 0; col < cols; col++) {
            buttons[row][col].setBackground(Color.YELLOW);
        }
    }

    private void highlightWinningColumn(int col) {
        for (int row = 0; row < rows; row++) {
            buttons[row][col].setBackground(Color.YELLOW);
        }
    }

    private void highlightWinningDiagonal(boolean mainDiagonal) {
        for (int i = 0; i < rows; i++) {
            if (mainDiagonal) {
                buttons[i][i].setBackground(Color.YELLOW);
            } else {
                buttons[i][cols - i - 1].setBackground(Color.YELLOW);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeApp app = new TicTacToeApp();
            app.setVisible(true);
        });
    }
}




