import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;
import java.io.PrintWriter;
import java.util.Arrays;

public class DexGui {

    // main menu constructor
    public DexGui(String menuMessage) {
        JFrame frame = new JFrame("Pokedex");
        frame.setSize(1300, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image
        ImageIcon bgIcon = new ImageIcon("try.jpg");

        // Custom panel that paints the background
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // BUTTONS
        // Load button background image (customize your own image)
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));

        // Button labels
        String[] labels = {"POKEMON", "MOVES", "ITEMS", "TRAINERS", "EXIT"};

        // Add solid-color buttons (no image)
        for (String label : labels) {
            ButtonBg button = new ButtonBg(label);
            // Add action listener to each button
            button.addActionListener(e -> {
                switch (label) {
                    case "POKEMON":
                        PokemonManagement();
                        break;
                    case "MOVES":
                        MoveManagement();
                        break;
                    case "ITEMS":
                        ItemManagement();
                        break;
                    case "TRAINERS":
                        TrainerManagement();
                        break;
                    case "EXIT":
                        exit();
                        break;
                }
            });
            mainPanel.add(button);
        }
        Box verticalBox = Box.createVerticalBox();
        verticalBox.setOpaque(false);

        // 🔽 Add spacing above to move buttons lower
        verticalBox.add(Box.createVerticalStrut(155)); // you can increase for more downward shift

        // Add the button panel
        verticalBox.add(mainPanel);

        // Add spacing between buttons and image
        verticalBox.add(Box.createVerticalStrut(10));
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(verticalBox); // verticalBox contains buttons and optional images

        backgroundPanel.add(centerWrapper, BorderLayout.CENTER);
        // Frame setup
        frame.setContentPane(backgroundPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //exit method
    public static void exit() {
        // Create a full-screen frame (or set specific size if preferred)
        JFrame exitFrame = new JFrame();
        exitFrame.setSize(1300, 700); // Or any size matching your image
        exitFrame.setUndecorated(true); // Removes window borders and title bar
        exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load exit background
        ImageIcon exitBg = new ImageIcon("exitbg.jpg");

        // Custom panel to paint the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(exitBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        exitFrame.setContentPane(backgroundPanel);
        exitFrame.setLocationRelativeTo(null); // Center the window
        exitFrame.setVisible(true);

        // Delay for 5 seconds, then exit
        new javax.swing.Timer(5000, e -> {
            exitFrame.dispose();
            System.exit(0);
        }).start();
    }

    public static void PokemonManagement() {
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon pokBg = new ImageIcon("pokman.jpg");

        // Custom panel to paint background
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Center container for buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridLayout(4, 1, 20, 50)); // 4 rows, 1 column, spacing

        // Button labels
        String[] labels = {"ADD POKEMON", "VIEW POKEMON", "SEARCH POKEMON", "MAIN-MENU"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(220, 50), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "ADD POKEMON":
                        AddPokemon();
                        break;
                    case "VIEW POKEMON":
                        break;
                    case "SEARCH POKEMON":
                        break;
                    case "MAIN-MENU":
                        pokFrame.dispose();
                        new DexGui("Main Menu");
                        break;
                }
            });

            mainPanel.add(subButton);
        }

        // Center mainPanel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(mainPanel, gbc);

        // Final setup
        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    public static void MoveManagement() {
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon pokBg = new ImageIcon("movebg.jpg");

        // Custom panel to paint background
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Center container for buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridLayout(4, 1, 20, 50)); // 4 rows, 1 column, spacing

        // Button labels
        String[] labels = {"ADD MOVE", "VIEW MOVES", "SEARCH MOVE", "MAIN-MENU"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(220, 50), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "ADD MOVE":
                        AddMove();
                        break;
                    case "VIEW MOVES":
                        ViewMoves();
                        break;
                    case "SEARCH MOVE":
                        SearchMove();
                        break;
                    case "MAIN-MENU":
                        pokFrame.dispose();
                        new DexGui("Main Menu");
                        break;
                }
            });
            mainPanel.add(subButton);
        }

        // Center mainPanel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(mainPanel, gbc);

        // Final setup
        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    public static void ViewMoves() {
        JFrame viewFrame = new JFrame("All Moves");
        viewFrame.setSize(1300, 700);
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create a text area to display moves
        JTextArea movesTextArea = new JTextArea();
        movesTextArea.setEditable(false);
        movesTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(movesTextArea);

        // Button to go back
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> viewFrame.dispose());

        // Display all moves
        StringBuilder movesText = new StringBuilder();
        for (Moves move : Moves.moveList) {
            if (move != null) {
                movesText.append("Move: ").append(move.getName()).append("\n");
                movesText.append("Description: ").append(move.getDesc()).append("\n");
                movesText.append("Machine: ").append(move.getMachine()).append("\n");
                movesText.append("Type: ").append(move.getType1());
                if (!move.getType2().contains("0")) {
                    movesText.append("/").append(move.getType2());
                }
                movesText.append("\n\n");
                movesText.append("----------------------------------------\n\n");
            }
        }

        if (Moves.moveCount == 0) {
            movesText.append("No moves found in the database.");
        }

        movesTextArea.setText(movesText.toString());

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        viewFrame.add(mainPanel);
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(true);
    }

    public static void SearchMove() {
        JFrame searchFrame = new JFrame("Search Move");
        searchFrame.setSize(600, 300);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel searchLabel = new JLabel("Enter Move Name:");
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        inputPanel.add(searchLabel);
        inputPanel.add(searchField);
        inputPanel.add(searchButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> searchFrame.dispose());

        searchButton.addActionListener(e -> {
            String moveName = searchField.getText().trim();
            if (moveName.isEmpty()) {
                resultArea.setText("Please enter a move name to search.");
                return;
            }

            Moves foundMove = Moves.getMoveByName(moveName);
            if (foundMove != null) {
                StringBuilder moveInfo = new StringBuilder();
                moveInfo.append("Move: ").append(foundMove.getName()).append("\n");
                moveInfo.append("Description: ").append(foundMove.getDesc()).append("\n");
                moveInfo.append("Machine: ").append(foundMove.getMachine()).append("\n");
                moveInfo.append("Type: ").append(foundMove.getType1());
                if (!foundMove.getType2().contains("0")) {
                    moveInfo.append("/").append(foundMove.getType2());
                }
                resultArea.setText(moveInfo.toString());
            } else {
                resultArea.setText("Move not found: " + moveName);
            }
        });

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        searchFrame.add(mainPanel);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
    }

    public static void AddMove() {
        JFrame addFrame = new JFrame("Add New Move");
        addFrame.setSize(800, 600);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));

        // Form fields
        JLabel nameLabel = new JLabel("Move Name:");
        JTextField nameField = new JTextField();

        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(3, 20);
        JScrollPane descScroll = new JScrollPane(descArea);

        JLabel machineLabel = new JLabel("Machine:");
        JTextField machineField = new JTextField();

        JLabel type1Label = new JLabel("Primary Type:");
        JComboBox<String> type1Combo = new JComboBox<>(new String[]{
                "Normal", "Fire", "Water", "Electric", "Grass", "Ice",
                "Fighting", "Poison", "Ground", "Flying", "Psychic",
                "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy"
        });

        JLabel type2Label = new JLabel("Secondary Type (or None):");
        JComboBox<String> type2Combo = new JComboBox<>(new String[]{
                "None", "Normal", "Fire", "Water", "Electric", "Grass", "Ice",
                "Fighting", "Poison", "Ground", "Flying", "Psychic",
                "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy"
        });

        // Add components to form panel
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descLabel);
        formPanel.add(descScroll);
        formPanel.add(machineLabel);
        formPanel.add(machineField);
        formPanel.add(type1Label);
        formPanel.add(type1Combo);
        formPanel.add(type2Label);
        formPanel.add(type2Combo);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add Move");
        JButton backButton = new JButton("Back");

        // Result area
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScroll = new JScrollPane(resultArea);

        // Add action listeners
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String desc = descArea.getText().trim();
            String machine = machineField.getText().trim();
            String type1 = (String) type1Combo.getSelectedItem();
            String type2 = (String) type2Combo.getSelectedItem();

            if (name.isEmpty() || desc.isEmpty() || machine.isEmpty()) {
                resultArea.setText("Please fill in all required fields.");
                return;
            }

            if (type2.equals("None")) {
                type2 = "0";
            }

            // Check if move already exists
            if (Moves.getMoveByName(name) != null) {
                resultArea.setText("Error: A move with this name already exists.");
                return;
            }

            // Add the move
            Moves.addMovetoDataBase(name, desc, machine, type1, type2);
            resultArea.setText("Move added successfully!\n\n" +
                    "Name: " + name + "\n" +
                    "Description: " + desc + "\n" +
                    "Machine: " + machine + "\n" +
                    "Type: " + type1 + (type2.equals("0") ? "" : "/" + type2));

            // Clear fields for next entry
            nameField.setText("");
            descArea.setText("");
            machineField.setText("");
            type1Combo.setSelectedIndex(0);
            type2Combo.setSelectedIndex(0);
        });

        backButton.addActionListener(e -> addFrame.dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(backButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(resultScroll, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addFrame.add(mainPanel);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }

    public static void ItemManagement() {
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon pokBg = new ImageIcon("itembg.jpg");

        // Custom panel to paint background
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Center container for buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridLayout(4, 1, 20, 50)); // 4 rows, 1 column, spacing

        // Button labels
        String[] labels = {"VIEW ITEM", "SEARCH ITEM" ,"MAIN-MENU"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(220, 50), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "VIEW ITEM":
                        break;
                    case "SEARCH ITEM":
                        break;
                    case "MAIN-MENU":
                        pokFrame.dispose();
                        new DexGui("Main Menu");
                        break;
                }
            });

            mainPanel.add(subButton);
        }

        // Center mainPanel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(100, 0, 0, 0); // top, left, bottom, right (shifts down by 100px)
        backgroundPanel.add(mainPanel, gbc);

        // Final setup
        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    public static void TrainerManagement() {
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon pokBg = new ImageIcon("trainerbg.jpg");

        // Custom panel to paint background
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Center container for buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridLayout(4, 1, 20, 50)); // 4 rows, 1 column, spacing

        // Button labels
        String[] labels = {"ADD TRAINER", "VIEW TRAINER", "SEARCH TRAINER", "MAIN-MENU"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(220, 50), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "ADD TRAINER":
                        break;
                    case "VIEW TRAINER":
                        break;
                    case "SEARCH TRAINER":
                        break;
                    case "MAIN-MENU":
                        pokFrame.dispose();
                        new DexGui("Main Menu");
                        break;
                }
            });

            mainPanel.add(subButton);
        }

        // Center mainPanel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(mainPanel, gbc);

        // Final setup
        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    public static int getValidatedIntInput(String input, int min, int max) {
        if (input == null || input.isEmpty()) return -1;

        if (!input.matches("\\d{1,4}")) return -1; // Must be 1–4 digit number

        try {
            int value = Integer.parseInt(input);
            if (value >= min && value <= max) {
                return value;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }

    public static JPanel DexNum(String labelText, String buttonText, boolean checkDuplicate, Consumer<Integer> onSubmit) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setForeground(Color.BLACK);
        textField.setBackground(new Color(255, 255, 204));
        textField.setCaretColor(Color.BLUE);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton submitButton = new JButton(buttonText);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));

        submitButton.addActionListener(e -> {
            String input = textField.getText().trim();
            int number = getValidatedIntInput(input, 0, 9999);

            if (number == -1) {
                JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a number between 0–9999.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (checkDuplicate) {
                try (BufferedReader br = new BufferedReader(new FileReader("pokedex.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length > 0 && parts[0].trim().equals(String.valueOf(number))) {
                            JOptionPane.showMessageDialog(panel, "Pokedex number already exists. Please enter a unique number.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                            return; // 🛑 Don't proceed
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(panel, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // ✅ Only continue if all checks passed
            onSubmit.accept(number);
        });

        panel.add(label);
        panel.add(textField);
        panel.add(submitButton);
        return panel;
    }

    // di pa maayos input fields for dex numbers
    public static void AddPokemon() {
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("addpok.jpg");

        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 👇 Flag to avoid duplicate panel additions
        final boolean[] namePanelAdded = {false};

        JPanel dexPanel = DexNum("Enter Pokedex Number:", "Submit", true, number -> {
            if (!namePanelAdded[0]) {
                JPanel namePanel = DexNum("Enter Pokemon Name (as number for now):", "Next", false, name -> {
                    System.out.println("Name (input): " + name);
                    // more inputs here...
                });

                mainPanel.add(Box.createVerticalStrut(20));
                mainPanel.add(namePanel);
                mainPanel.revalidate();
                mainPanel.repaint();

                namePanelAdded[0] = true; // ✅ Avoid adding it again
            }
        });

        mainPanel.add(Box.createVerticalStrut(80));
        mainPanel.add(dexPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(120, 20, 0, 50);

        backgroundPanel.add(mainPanel, gbc);

        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }


}