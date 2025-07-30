
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;

public class DexGui {

    public static Pokemon tempPokemon = new Pokemon();
    public static Pokemon[] pokemon = new Pokemon[300];
    public static int pokemonCount = 0;
    private static JLabel moneyLabel;
    private static JLabel itemCountLabel;
    private static JLabel uniqueCountLabel;

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

    public static ImageIcon loadImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
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
                        ViewPokemon();
                        break;
                    case "SEARCH POKEMON":
                        SearchMenu();
                        break;
                    case "MAIN-MENU":
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
                movesText.append("  Move: ").append(move.getName()).append("\n");
                movesText.append("  Description: ").append(move.getDesc()).append("\n");
                movesText.append("  Machine: ").append(move.getMachine()).append("\n");
                movesText.append("  Type: ").append(move.getType1());
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
        JLabel searchLabel = new JLabel("Enter Move Keyword:");
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
            String keyword = searchField.getText().trim().toLowerCase();
            if (keyword.isEmpty()) {
                resultArea.setText("Please enter a move keyword to search.");
                return;
            }

            StringBuilder foundMoves = new StringBuilder();
            for (Moves move : Moves.moveList) {
                if (move != null && (
                        move.getName().toLowerCase().contains(keyword) ||
                                move.getDesc().toLowerCase().contains(keyword) ||
                                move.getMachine().toLowerCase().contains(keyword) || // TM or HM
                                move.getType1().toLowerCase().contains(keyword) ||
                                (!move.getType2().equals("0") && move.getType2().toLowerCase().contains(keyword))
                )) {
                    foundMoves.append("Move: ").append(move.getName()).append("\n");
                    foundMoves.append("Description: ").append(move.getDesc()).append("\n");
                    foundMoves.append("Machine: ").append(move.getMachine()).append("\n");
                    foundMoves.append("Type: ").append(move.getType1());
                    if (!move.getType2().equals("0")) {
                        foundMoves.append("/").append(move.getType2());
                    }
                    foundMoves.append("\n\n----------------------------------------\n\n");
                }
            }

            if (foundMoves.length() == 0) {
                resultArea.setText("No move matches the keyword: " + keyword);
            } else {
                resultArea.setText(foundMoves.toString());
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
        JComboBox<String> machineCombo = new JComboBox<>(new String[]{"HM","TM"});

        JLabel type1Label = new JLabel("Primary Type:");
        JComboBox<String> type1Combo = new JComboBox<>(Pokemon.TYPES);

        JLabel type2Label = new JLabel("Secondary Type (or None):");
        JComboBox<String> type2Combo = new JComboBox<>();
        type2Combo.addItem("None");
        for (String type : Pokemon.TYPES) {
            type2Combo.addItem(type);
        }

        // Add components to form panel
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descLabel);
        formPanel.add(descScroll);
        formPanel.add(machineLabel);
        formPanel.add(machineCombo);
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
            String machine = (String) machineCombo.getSelectedItem();
            String type1 = (String) type1Combo.getSelectedItem();
            String type2 = (String) type2Combo.getSelectedItem();

            // Validation checks
            if (name.isEmpty() || desc.isEmpty() || machine.isEmpty()) {
                resultArea.setText("Please fill in all required fields.");
                return;
            }

            // Check for numbers in move name
            if (name.matches(".*\\d.*")) {
                resultArea.setText("Error: Move names cannot contain numbers.");
                return;
            }

            // Check for special characters (optional)
            if (!name.matches("^[a-zA-Z\\s-]+$")) {
                resultArea.setText("Error: Move names can only contain letters, spaces and hyphens.");
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
            machineCombo.setSelectedIndex(0);
            type1Combo.setSelectedIndex(0);
            type2Combo.setSelectedIndex(0);

            // Save to file
            String data = "\n" + name + "-" + desc + "-" + machine + "-" + type1 + "-" + type2 + "-";
            try {
                FileWriter writer = new FileWriter("moves.txt", true);
                writer.append(data);
                writer.close();
            } catch (IOException f) {
                resultArea.setText("Error saving move to file: " + f.getMessage());
            }
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
                        ViewItems();
                        break;
                    case "SEARCH ITEM":
                        SearchItem();
                        break;
                    case "MAIN-MENU":
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

    public static void ViewItems() {
        JFrame viewFrame = new JFrame("All Items");
        viewFrame.setSize(1300, 700);
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JTextArea itemsTextArea = new JTextArea();
        itemsTextArea.setEditable(false);
        itemsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(itemsTextArea);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> viewFrame.dispose());

        StringBuilder itemsText = new StringBuilder();

        // Available Items section
        itemsText.append("========== AVAILABLE ITEMS ==========\n\n");
        // Loop through all items using itemCount to avoid nulls at the end of the array
        for (int i = 0; i < Items.itemCount; i++) {
            Items item = Items.itemList[i]; // Access item by index
            // Change .equals() to .equalsIgnoreCase() for category comparison
            if (item != null && !item.getitemCategory().equalsIgnoreCase("Evolution Stone")) {
                itemsText.append("    Item ID: ").append(item.getitemID()).append("\n");
                itemsText.append("    Name: ").append(item.getitemName()).append("\n");
                itemsText.append("    Category: ").append(item.getitemCategory()).append("\n");
                itemsText.append("    Description: ").append(item.getitemDesc()).append("\n");
                itemsText.append("    Effects: ").append(item.getitemEffects()).append("\n");
                itemsText.append(String.format("    Buying Price: P %.2f\n", item.getstartBuyingPrice()));
                itemsText.append(String.format("    Selling Price: P %.2f\n", item.getsellingPrice()));
                itemsText.append("\n----------------------------------------\n\n");
            }
        }

        // Evolution Stones section
        itemsText.append("========== EVOLUTION STONES ==========\n\n");
        // Loop through all items using itemCount to avoid nulls at the end of the array
        for (int i = 0; i < Items.itemCount; i++) {
            Items item = Items.itemList[i]; // Access item by index
            // Change .equals() to .equalsIgnoreCase() for category comparison
            if (item != null && item.getitemCategory().equalsIgnoreCase("Evolution Stone")) {
                itemsText.append("    Item ID: ").append(item.getitemID()).append("\n");
                itemsText.append("    Name: ").append(item.getitemName()).append("\n");
                itemsText.append("    Category: ").append(item.getitemCategory()).append("\n");
                itemsText.append("    Description: ").append(item.getitemDesc()).append("\n");
                itemsText.append("    Effects: ").append(item.getitemEffects()).append("\n");
                itemsText.append(String.format("    Buying Price: P %.2f\n", item.getstartBuyingPrice()));
                itemsText.append(String.format("    Selling Price: P %.2f\n", item.getsellingPrice()));
                itemsText.append("\n----------------------------------------\n\n");
            }
        }

        itemsTextArea.setText(itemsText.toString());

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        viewFrame.add(mainPanel);
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(true);
    }

    public static void SearchItem() {
        JFrame searchFrame = new JFrame("Search Item");
        searchFrame.setSize(600, 300);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel searchLabel = new JLabel("Enter Item Name or ID:");
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
        backButton.addActionListener(e -> ItemManagement());

        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText().trim();
            if (searchTerm.isEmpty()) {
                resultArea.setText("Please enter an item name or ID.");
                return;
            }

            StringBuilder foundItems = new StringBuilder();
            boolean isNumericSearch = searchTerm.matches("\\d+"); // Check if search is numeric ID

            for (Items item : Items.itemList) {
                if (item == null) continue;

                boolean matchFound;
                if (isNumericSearch) {
                    // Search by ID
                    matchFound = String.valueOf(item.getitemID()).equals(searchTerm);
                } else {
                    // Search by name/description/effects (case-insensitive)
                    String lowerSearch = searchTerm.toLowerCase();
                    matchFound = item.getitemName().toLowerCase().contains(lowerSearch) ||
                            item.getitemCategory().toLowerCase().contains(lowerSearch) ||
                            item.getitemDesc().toLowerCase().contains(lowerSearch) ||
                            item.getitemEffects().toLowerCase().contains(lowerSearch);
                }

                if (matchFound) {
                    foundItems.append("Item ID: ").append(item.getitemID()).append("\n");
                    foundItems.append("Name: ").append(item.getitemName()).append("\n");
                    foundItems.append("Category: ").append(item.getitemCategory()).append("\n");
                    foundItems.append("Description: ").append(item.getitemDesc()).append("\n");
                    foundItems.append("Effects: ").append(item.getitemEffects()).append("\n");
                    foundItems.append(String.format("Buying Price: P %.2f\n", item.getstartBuyingPrice()));
                    foundItems.append(String.format("Selling Price: P %.2f\n", item.getsellingPrice()));
                    foundItems.append("\n----------------------------------------\n\n");
                }
            }

            if (foundItems.length() == 0) {
                resultArea.setText("No items found matching: " + searchTerm);
            } else {
                resultArea.setText(foundItems.toString());
            }
        });

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        searchFrame.add(mainPanel);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
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
                        addTrainerMenu();
                        break;
                    case "VIEW TRAINER":
                        viewTrainers();
                        break;
                    case "SEARCH TRAINER":
                        SearchTrainer();
                        break;
                    case "MAIN-MENU":
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
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setForeground(Color.BLACK);
        textField.setBackground(new Color(255, 255, 204));
        textField.setCaretColor(Color.BLACK);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton submitButton = new JButton(buttonText);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));

        submitButton.addActionListener(e -> {
            String input = textField.getText().trim();
            int number = getValidatedIntInput(input, 1, 9999);

            if (number == -1) {
                JOptionPane.showMessageDialog(panel, "Invalid input. Please enter a number between 1–9999.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (checkDuplicate) {
                boolean isDuplicate = false;
                try (BufferedReader br = new BufferedReader(new FileReader("pokedex.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.trim().split(" +");
                        if (parts.length > 0) {
                            String fileDex = parts[0].trim();
                            if (fileDex.equals(String.valueOf(number))) {
                                isDuplicate = true;
                                break;
                            }
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(panel, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (isDuplicate) {
                    JOptionPane.showMessageDialog(panel, "Pokedex number already exists. Please enter a unique number.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // ✅ All checks passed — lock input
            textField.setEnabled(false);
            submitButton.setEnabled(false);

            onSubmit.accept(number);
        });

        panel.add(label);
        panel.add(textField);
        panel.add(submitButton);
        return panel;
    }

    public static JPanel PokemonNameInput(String labelText, String buttonText, boolean checkDuplicate, Consumer<String> onSubmit) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setForeground(Color.BLACK);
        textField.setBackground(new Color(255, 255, 204));
        textField.setCaretColor(Color.BLACK);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton submitButton = new JButton(buttonText);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));

        submitButton.addActionListener(e -> {
            String name = textField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Name field cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ❌ Check if duplicate
            if (checkDuplicate) {
                boolean isDuplicate = false;
                try (BufferedReader br = new BufferedReader(new FileReader("pokedex.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.trim().split(" +");
                        if (parts.length > 1) {
                            String fileName = parts[1].trim();
                            if (fileName.equalsIgnoreCase(name)) {
                                isDuplicate = true;
                                break;
                            }
                        }
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(panel, "Error reading file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (isDuplicate) {
                    JOptionPane.showMessageDialog(panel, "Pokémon name already exists. Please enter a unique name.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // ✅ Lock name input once valid
            textField.setEnabled(false);
            submitButton.setEnabled(false);

            onSubmit.accept(name);
        });

        panel.add(label);
        panel.add(textField);
        panel.add(submitButton);
        return panel;
    }

    public static JPanel PokemonTypeInput(String labelText,String buttonText,boolean isType2,String type1Value,Consumer<String> onValidTypeEntered) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setForeground(Color.BLACK);
        textField.setBackground(new Color(255, 255, 204));
        textField.setCaretColor(Color.BLACK);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton submitButton = new JButton(buttonText);
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(label);
        panel.add(textField);
        panel.add(submitButton);
        panel.add(errorLabel);

        submitButton.addActionListener(e -> {
            String input = textField.getText().trim().toLowerCase();

            boolean isValid = false;
            for (String type : Pokemon.TYPES) {
                if (type.equalsIgnoreCase(input)) {
                    isValid = true;
                    break;
                }
            }

            if (!isValid) {
                JOptionPane.showMessageDialog(panel, "Invalid Pokémon Type", "Invalid Type", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (isType2 && input.equalsIgnoreCase(type1Value)) {
                JOptionPane.showMessageDialog(panel, "Duplicate Type", "Duplicate Type", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ All validations passed
            errorLabel.setText(" ");
            textField.setEnabled(false);
            submitButton.setEnabled(false);
            onValidTypeEntered.accept(input);
        });

        return panel;
    }

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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);
        mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Dimension inputSize = new Dimension(500, 60);

        JPanel dexPanelWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        dexPanelWrap.setOpaque(false);
        dexPanelWrap.setAlignmentX(Component.LEFT_ALIGNMENT);
        dexPanelWrap.setPreferredSize(inputSize);
        dexPanelWrap.setMinimumSize(inputSize);
        dexPanelWrap.setMaximumSize(inputSize);

        JPanel namePlaceholder = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        namePlaceholder.setOpaque(false);
        namePlaceholder.setAlignmentX(Component.LEFT_ALIGNMENT);
        namePlaceholder.setPreferredSize(inputSize);
        namePlaceholder.setMinimumSize(inputSize);
        namePlaceholder.setMaximumSize(inputSize);

        JPanel typePlaceholder = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        typePlaceholder.setOpaque(false);
        typePlaceholder.setAlignmentX(Component.LEFT_ALIGNMENT);
        typePlaceholder.setPreferredSize(inputSize);
        typePlaceholder.setMinimumSize(inputSize);
        typePlaceholder.setMaximumSize(inputSize);

        final JPanel type2ContainerForStacking = new JPanel();
        type2ContainerForStacking.setLayout(new BoxLayout(type2ContainerForStacking, BoxLayout.Y_AXIS));
        type2ContainerForStacking.setOpaque(false);
        type2ContainerForStacking.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JPanel type2PromptAndButtonsRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        type2PromptAndButtonsRow.setOpaque(false);

        // Wrapper panel to align type2PromptAndButtonsRow to the right
        JPanel type2PromptWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 90, 0));
        type2PromptWrapper.setOpaque(false);
        type2PromptWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        type2PromptWrapper.add(type2PromptAndButtonsRow);
        type2PromptWrapper.setPreferredSize(new Dimension(600, 40));
        type2PromptWrapper.setMinimumSize(new Dimension(600, 40));
        type2PromptWrapper.setMaximumSize(new Dimension(600, 40));


        JPanel type2DynamicSlot = new JPanel(new BorderLayout());
        type2DynamicSlot.setOpaque(false);
        type2DynamicSlot.setAlignmentX(Component.LEFT_ALIGNMENT);
        type2DynamicSlot.setPreferredSize(new Dimension(600, 100));
        type2DynamicSlot.setMinimumSize(new Dimension(600, 100));
        type2DynamicSlot.setMaximumSize(new Dimension(600, 100));

        JPanel imagePlaceholder = new JPanel();
        imagePlaceholder.setPreferredSize(new Dimension(500, 250));
        imagePlaceholder.setMaximumSize(new Dimension(500, 250));
        imagePlaceholder.setMinimumSize(new Dimension(500, 250));
        imagePlaceholder.setOpaque(false);
        imagePlaceholder.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(dexPanelWrap);
        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(namePlaceholder);
        mainPanel.add(imagePlaceholder);
        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(typePlaceholder);
        mainPanel.add(Box.createVerticalStrut(1));
        mainPanel.add(type2DynamicSlot);

        JPanel dexPanel = DexNum("Enter Pokedex Number:", "Next", true, number -> {
            System.out.println("Dex Number input: " + number);
            tempPokemon.setPokedexNo(number);

            namePlaceholder.removeAll();
            namePlaceholder.add(PokemonNameInput("Enter Pokémon Name:", "Next", true, name -> {
                System.out.println("Entered Name: " + name);
                tempPokemon.setName(name);

                imagePlaceholder.removeAll();
                imagePlaceholder.add(new JLabel(loadImage("types.png", 500, 250)));
                imagePlaceholder.revalidate();
                imagePlaceholder.repaint();

                final String[] finalType1 = new String[1];

                typePlaceholder.removeAll();
                typePlaceholder.add(PokemonTypeInput("Enter Pokémon Type 1:", "Next", false, "", type -> {
                    System.out.println("Valid Type 1: " + type);
                    tempPokemon.setType1(type);
                    finalType1[0] = type;

                    type2ContainerForStacking.removeAll();
                    type2PromptAndButtonsRow.removeAll(); // Clear previous components if any

                    JLabel label = new JLabel("Does Pokémon have a Type 2?");
                    label.setFont(new Font("Arial", Font.BOLD, 18));
                    label.setForeground(Color.BLACK);

                    ButtonBg btn1 = new ButtonBg("YES", new Dimension(100, 25), new Color(0, 153, 76));
                    ButtonBg btn2 = new ButtonBg("NO", new Dimension(100, 25), new Color(255, 0, 0));

                    type2PromptAndButtonsRow.add(label);
                    type2PromptAndButtonsRow.add(Box.createHorizontalStrut(10));
                    type2PromptAndButtonsRow.add(btn1);
                    type2PromptAndButtonsRow.add(btn2);
                    type2PromptAndButtonsRow.revalidate();
                    type2PromptAndButtonsRow.repaint();


                    type2ContainerForStacking.add(type2PromptWrapper); // Add the wrapper panel
                    type2ContainerForStacking.revalidate();
                    type2ContainerForStacking.repaint();

                    type2DynamicSlot.removeAll();
                    type2DynamicSlot.add(type2ContainerForStacking, BorderLayout.NORTH);
                    type2DynamicSlot.revalidate();
                    type2DynamicSlot.repaint();

                    btn1.addActionListener(e1 -> {
                        btn1.setEnabled(false); // Disable YES
                        btn2.setEnabled(false); // Disable NO

                        // Ensure previous input panel is removed if it exists
                        Component[] components = type2ContainerForStacking.getComponents();
                        for (int i = 0; i < components.length; i++) {
                            // Ensure we don't remove the type2PromptWrapper itself
                            if (components[i] instanceof JPanel && components[i] != type2PromptWrapper) {
                                type2ContainerForStacking.remove(components[i]);
                            }
                        }
                        type2ContainerForStacking.revalidate();
                        type2ContainerForStacking.repaint();


                        JPanel type2InputPanel = PokemonTypeInput("Enter Pokémon Type 2:", "Next", true, finalType1[0], type2 -> {
                            System.out.println("Valid Pokémon Type 2: " + type2);
                            tempPokemon.setType2(type2);
                            EvoFrom(); // Move to next screen after getting both types
                        });

                        JPanel leftAdjustedInputPanelWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                        leftAdjustedInputPanelWrapper.setOpaque(false);
                        leftAdjustedInputPanelWrapper.setPreferredSize(inputSize);
                        leftAdjustedInputPanelWrapper.add(Box.createHorizontalStrut(100));
                        leftAdjustedInputPanelWrapper.add(type2InputPanel);
                        leftAdjustedInputPanelWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);


                        type2ContainerForStacking.add(Box.createVerticalStrut(10)); // Add space before new input
                        type2ContainerForStacking.add(leftAdjustedInputPanelWrapper);
                        type2ContainerForStacking.revalidate();
                        type2ContainerForStacking.repaint();
                    });

                    btn2.addActionListener(e -> {
                        btn1.setEnabled(false); // Disable YES
                        btn2.setEnabled(false); // Disable NO
                        String type2 = "0"; // "0" or null to indicate no second type
                        System.out.println("User clicked NO – Type 2 set to: " + type2);

                        // Remove the type2 input field if it was previously added
                        Component[] components = type2ContainerForStacking.getComponents();
                        for (int i = 0; i < components.length; i++) {
                            // Ensure we don't remove the type2PromptWrapper itself
                            if (components[i] instanceof JPanel && components[i] != type2PromptWrapper) {
                                type2ContainerForStacking.remove(components[i]);
                            }
                        }
                        type2ContainerForStacking.revalidate();
                        type2ContainerForStacking.repaint();
                        tempPokemon.setType2(type2); // Set type2 to "0" or null
                        EvoFrom();
                    });

                }));
                typePlaceholder.revalidate();
                typePlaceholder.repaint();
            }));
            namePlaceholder.revalidate();
            namePlaceholder.repaint();
        });

        dexPanelWrap.add(dexPanel);

        // Add a horizontal filler on the left
        GridBagConstraints fillerGbc = new GridBagConstraints();
        fillerGbc.gridx = 0;
        fillerGbc.gridy = 0;
        fillerGbc.weightx = 1.0;
        fillerGbc.weighty = 1.0;
        fillerGbc.fill = GridBagConstraints.BOTH;

        JPanel horizontalFiller = new JPanel();
        horizontalFiller.setOpaque(false);
        backgroundPanel.add(horizontalFiller, fillerGbc);

        // Place mainPanel to the right
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        // 🚨 Main Change: Decrease the right inset value (last parameter) to move it further right.
        // For example, from 80 to 20 or even 0 depending on how far right you want it.
        gbc.insets = new Insets(120, 0, 0, 5); // Moved from 80 to 20 for more right alignment.

        backgroundPanel.add(mainPanel, gbc);
        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    public static JPanel createValidatedIntInputField(String buttonText,
                                                      int min, int max,
                                                      Consumer<Integer> onValidInput) {
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Transparent panel
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack input and error

        JPanel inputGroup = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        inputGroup.setOpaque(false);

        JTextField inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.setForeground(Color.BLACK);
        inputField.setBackground(new Color(255, 255, 204));
        inputField.setCaretColor(Color.BLACK);
        inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton submitButton = new ButtonBg(buttonText, new Dimension(80, 30), new Color(0, 102, 204));
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center in BoxLayout

        inputGroup.add(inputField);
        inputGroup.add(submitButton);

        panel.add(Box.createVerticalStrut(10)); // optional top padding
        panel.add(inputGroup);
        panel.add(Box.createVerticalStrut(5)); // spacing between input and error
        panel.add(errorLabel);

        submitButton.addActionListener(e -> {
            String inputText = inputField.getText().trim();
            int value = -1;
            boolean valid = false;

            try {
                value = Integer.parseInt(inputText);
                if (value >= min && value <= max) {
                    valid = true;
                } else {
                    errorLabel.setText("Enter a number from " + min + " to " + max + ".");
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Please enter a whole number.");
            }

            if (valid) {
                errorLabel.setText(" ");
                inputField.setEnabled(false);
                submitButton.setEnabled(false);
                onValidInput.accept(value);
            }
        });

        inputField.addActionListener(submitButton.getActionListeners()[0]);

        return panel;
    }

    public static void EvoFrom() {
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("evofrom.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Does Pokémon have Evolution from?");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        ButtonBg btn1 = new ButtonBg("YES", new Dimension(100, 30), new Color(0, 153, 76));
        ButtonBg btn2 = new ButtonBg("NO", new Dimension(100, 30), new Color(255, 0, 0));
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);

        JPanel dynamicInputHolder = new JPanel();
        dynamicInputHolder.setOpaque(false);
        dynamicInputHolder.setLayout(new BoxLayout(dynamicInputHolder, BoxLayout.Y_AXIS));

        btn1.addActionListener(e -> {
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            dynamicInputHolder.removeAll();

            JLabel promptLabel = new JLabel("Pokemon Evolves From (1–2000):");
            promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
            promptLabel.setForeground(Color.BLACK);
            promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel inputPanel = createValidatedIntInputField(
                    "Next", 1, 2000, level -> {
                        System.out.println("Evo From: " + level);
                        tempPokemon.setFrom(level);
                        EvoTo();
                    }
            );

            dynamicInputHolder.add(Box.createVerticalStrut(40)); // moved lower
            dynamicInputHolder.add(promptLabel);
            dynamicInputHolder.add(Box.createVerticalStrut(10)); // spacing
            dynamicInputHolder.add(inputPanel);

            dynamicInputHolder.revalidate();
            dynamicInputHolder.repaint();
        });

        btn2.addActionListener(e -> {
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            int evofrom = 0; // "0" or null to indicate no second type
            System.out.println("User clicked NO evofrom:" + evofrom);
            tempPokemon.setFrom(evofrom);
            EvoTo();
        });

        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(label);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(buttonPanel);
        mainPanel.add(dynamicInputHolder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(250, 0, 0, 0);

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static void EvoTo(){
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("evoto.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Does Pokémon have Evolution To?");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        ButtonBg btn1 = new ButtonBg("YES", new Dimension(100, 30), new Color(0, 153, 76));
        ButtonBg btn2 = new ButtonBg("NO", new Dimension(100, 30), new Color(255, 0, 0));
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);

        JPanel dynamicInputHolder = new JPanel();
        dynamicInputHolder.setOpaque(false);
        dynamicInputHolder.setLayout(new BoxLayout(dynamicInputHolder, BoxLayout.Y_AXIS));

        btn1.addActionListener(e -> {
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            dynamicInputHolder.removeAll();

            JLabel promptLabel = new JLabel("Pokemon Evolves To (1–2000):");
            promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
            promptLabel.setForeground(Color.BLACK);
            promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel inputPanel = createValidatedIntInputField(
                    "Next", 1, 2000, level -> {
                        System.out.println("Evo To: " + level);
                        tempPokemon.setTo(level);
                        EvoLevel();
                    }
            );

            dynamicInputHolder.add(Box.createVerticalStrut(40)); // moved lower
            dynamicInputHolder.add(promptLabel);
            dynamicInputHolder.add(Box.createVerticalStrut(10)); // spacing
            dynamicInputHolder.add(inputPanel);

            dynamicInputHolder.revalidate();
            dynamicInputHolder.repaint();
        });

        btn2.addActionListener(e -> {
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            int evoTo = 0; // "0" or null to indicate no second type
            System.out.println("User clicked NO evoto:" + evoTo);
            tempPokemon.setTo(evoTo);
            EvoLevel();
        });

        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(label);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(buttonPanel);
        mainPanel.add(dynamicInputHolder);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(250, 0, 0, 0);

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static void EvoLevel() {
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("evolevel.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 🟨 Prompt Label
        JLabel promptLabel = new JLabel("Enter Pokémon Evolution Level (2–100):");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🟩 Input Panel
        JPanel inputPanel = createValidatedIntInputField(
                "Next", 2, 100, level -> {
                    System.out.println("Evo Level: " + level);
                    tempPokemon.setEvoLevel(level);
                    Hp();
                }
        );

        // Add components to main panel
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(promptLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);

        // Add main panel to background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(200, 0, 0, 0); // Adjust top spacing

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static void Hp(){
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("hp.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 🟨 Prompt Label
        JLabel promptLabel = new JLabel("Enter HP Stats (1–2000):");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🟩 Input Panel
        JPanel inputPanel = createValidatedIntInputField(
                "Next", 1, 2000, level -> {
                    System.out.println("Hp received: " + level);
                    tempPokemon.setHP(level);
                    Attack();
                }
        );

        // Add components to main panel
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(promptLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);

        // Add main panel to background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(200, 0, 0, 0); // Adjust top spacing

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static void Attack(){
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("attack.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 🟨 Prompt Label
        JLabel promptLabel = new JLabel("Enter Attack Stats (1–2000):");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🟩 Input Panel
        JPanel inputPanel = createValidatedIntInputField(
                "Next", 1, 2000, level -> {
                    System.out.println("Attack received: " + level);
                    tempPokemon.setAtk(level);
                    Defense();
                }
        );

        // Add components to main panel
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(promptLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);

        // Add main panel to background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(200, 0, 0, 0); // Adjust top spacing

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static void Defense(){
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("defense.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 🟨 Prompt Label
        JLabel promptLabel = new JLabel("Enter Defense Stats (1–2000):");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🟩 Input Panel
        JPanel inputPanel = createValidatedIntInputField(
                "Next", 1, 2000, level -> {
                    System.out.println("Defense received: " + level);
                    tempPokemon.setDef(level);
                    Speed();
                }
        );

        // Add components to main panel
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(promptLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);

        // Add main panel to background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(200, 0, 0, 0); // Adjust top spacing

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static void saveToFile(Pokemon p) {
        String data = "\n" + p.getPokedexNo() + " "
                + p.getName().replace(" ", "_") + " "
                + p.getType1() + " "
                + p.getType2() + " "
                + p.getBaseLevel() + " "
                + p.getFrom() + " "
                + p.getTo() + " "
                + p.getEvoLevel() + " "
                + p.getHP() + " "
                + p.getAtk() + " "
                + p.getDef() + " "
                + p.getSpd();

        try {
            FileWriter writer = new FileWriter("pokedex.txt", true); // append = true
            writer.append(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Speed() {
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("speed.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel promptLabel = new JLabel("Enter Speed Stats (1–2000):");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel promptLabel2 = new JLabel("Pokemon added successfully!");
        promptLabel2.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel2.setForeground(Color.GREEN);
        promptLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        promptLabel2.setVisible(false); // Hidden initially

        // 🔙 Back button (initially hidden)
        ButtonBg backButton = new ButtonBg("Back to Pokemon Management", new Dimension(500, 30), new Color(128, 128, 128));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setVisible(false); // Hidden initially
        backButton.addActionListener(e -> {
            PokemonManagement(); // Return to management
        });

        // 🟩 Input Panel
        JPanel inputPanel = createValidatedIntInputField(
                "Next", 1, 2000, level -> {
                    System.out.println("Speed received: " + level);
                    tempPokemon.setSpd(level);

                    // ✅ Finalize and save the Pokemon only after Speed is entered
                    pokemon[pokemonCount] = new Pokemon(
                            tempPokemon.getPokedexNo(),
                            tempPokemon.getName(),
                            tempPokemon.getType1(),
                            tempPokemon.getType2(),
                            1,
                            tempPokemon.getFrom(),
                            tempPokemon.getTo(),
                            tempPokemon.getEvoLevel(),
                            tempPokemon.getHP(),
                            tempPokemon.getAtk(),
                            tempPokemon.getDef(),
                            tempPokemon.getSpd()
                    );

                    pokemon[pokemonCount].teachMove("Tackle", false);
                    pokemon[pokemonCount].teachMove("Defend", false);
                    saveToFile(pokemon[pokemonCount]);
                    pokemonCount++;
                    Pokedex.pokemon[Pokedex.pokemonCount] = pokemon[pokemonCount - 1]; // Access the Pokémon just saved
                    Pokedex.pokemonCount++;

                    // 🎉 Feedback
                    promptLabel2.setVisible(true);
                    backButton.setVisible(true);
                }
        );

        // 🧩 Add all to main panel
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(promptLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(promptLabel2);
        mainPanel.add(Box.createVerticalStrut(60));
        mainPanel.add(backButton);

        // 🎨 Add to background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(200, 0, 0, 0);

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static JLabel buildMoveLabel(Pokemon currentPokemon) {
        StringBuilder movesTextBuilder = new StringBuilder();
        movesTextBuilder.append("<html>").append(currentPokemon.getName()).append("'s Moves:<br>");

        if (currentPokemon.getPMoves() == 0) {
            movesTextBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;(No moves known)");
        } else {
            for (int i = 0; i < currentPokemon.getPMoves(); i++) {
                Moves m = currentPokemon.getMoves()[i];
                if (m != null) {
                    movesTextBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;- ")
                            .append(m.getName()).append(": ").append(m.getDesc())
                            .append(" [").append(m.getType1());

                    if (!m.getType2().equals("0") && !m.getType2().isEmpty()) {
                        movesTextBuilder.append("/").append(m.getType2());
                    }

                    movesTextBuilder.append("]");
                    if (m.getMachine() != null && !m.getMachine().isEmpty()) {
                        movesTextBuilder.append(" (Machine: ").append(m.getMachine()).append(")");
                    }
                    movesTextBuilder.append("<br>");
                } else {
                    movesTextBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;(Empty or unassigned move slot)<br>");
                }
            }
        }

        movesTextBuilder.append("</html>");
        return new JLabel(movesTextBuilder.toString());
    }

    public static void ViewPokemon() {
        JFrame frame = new JFrame("Pokédex Viewer");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        try (BufferedReader reader = new BufferedReader(new FileReader("pokedex.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.trim().split("\\s+");
                if (data.length < 12) continue;

                // Parse all fields
                int pokedexNo = Integer.parseInt(data[0]);
                String name = data[1].replace("_", " ");
                String type1 = data[2];
                String type2 = data[3].equals("0") ? "None" : data[3];
                int baseLevel = Integer.parseInt(data[4]);
                int from = Integer.parseInt(data[5]);
                int to = Integer.parseInt(data[6]);
                int evoLevel = Integer.parseInt(data[7]);
                int hp = Integer.parseInt(data[8]);
                int atk = Integer.parseInt(data[9]);
                int def = Integer.parseInt(data[10]);
                int spd = Integer.parseInt(data[11]);

                // Create Pokémon object
                Pokemon currentPokemon = new Pokemon(pokedexNo, name, type1, type2, baseLevel, from, to, evoLevel, hp, atk, def, spd);

                // 🔁 Copy moves from memory if Pokémon was added earlier
                for (int i = 0; i < Pokedex.pokemonCount; i++) {
                    Pokemon original = Pokedex.pokemon[i];
                    if (original != null && original.getPokedexNo() == currentPokemon.getPokedexNo()) {
                        currentPokemon.setMoves(original.getMoves());
                        currentPokemon.setPMoves(original.getPMoves());
                        break;
                    }
                }

                // 🔁 Use the exact layout logic from DisplayPokemonSearch()
                JPanel pokemonCard = new JPanel();
                pokemonCard.setLayout(new BoxLayout(pokemonCard, BoxLayout.Y_AXIS));
                pokemonCard.setBackground(new Color(245, 245, 245));
                pokemonCard.setMaximumSize(new Dimension(800, 250));
                pokemonCard.setPreferredSize(new Dimension(800, 250));
                pokemonCard.setAlignmentX(Component.CENTER_ALIGNMENT);
                pokemonCard.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1),
                        BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));

                pokemonCard.add(new JLabel("Pokédex No: " + String.format("%04d", currentPokemon.getPokedexNo())));
                pokemonCard.add(new JLabel("Name: " + currentPokemon.getName()));
                pokemonCard.add(Box.createVerticalStrut(5));
                pokemonCard.add(new JLabel(currentPokemon.cry()));
                pokemonCard.add(Box.createVerticalStrut(5));
                pokemonCard.add(new JLabel("Type 1: " + currentPokemon.getType1()));
                pokemonCard.add(new JLabel("Type 2: " + (currentPokemon.getType2().equals("0") ? "None" : currentPokemon.getType2())));
                pokemonCard.add(new JLabel("Base Level: " + currentPokemon.getBaseLevel()));
                pokemonCard.add(new JLabel("HP: " + currentPokemon.getHP() + " | ATTACK: " + currentPokemon.getAtk() + " | DEFENSE: " + currentPokemon.getDef() + " | SPEED: " + currentPokemon.getSpd()));

                // Evolution Info
                int fromPokedexNo = currentPokemon.getFrom();
                int toPokedexNo = currentPokemon.getTo();
                String evolvesFromText = "Evolves From: " + (fromPokedexNo > 0 && fromPokedexNo <= Pokedex.pokemonCount
                        ? Pokedex.pokemon[fromPokedexNo - 1].getName() + " at Level " + Pokedex.pokemon[fromPokedexNo - 1].getEvoLevel()
                        : (fromPokedexNo == 0 ? "None" : "Unknown At Unknown Level"));
                String evolvesToText = "Evolves To: " + (toPokedexNo > 0 && toPokedexNo <= Pokedex.pokemonCount
                        ? Pokedex.pokemon[toPokedexNo - 1].getName() + " at Level " + currentPokemon.getEvoLevel()
                        : (toPokedexNo == 0 ? "None" : "Unknown At Level " + currentPokemon.getEvoLevel()));

                pokemonCard.add(new JLabel(evolvesFromText));
                pokemonCard.add(new JLabel(evolvesToText));
                pokemonCard.add(Box.createVerticalStrut(5));
                pokemonCard.add(new JLabel("Held Item: None"));
                pokemonCard.add(Box.createVerticalStrut(5));

                // Moves
                StringBuilder movesTextBuilder = new StringBuilder();
                movesTextBuilder.append("<html>").append(currentPokemon.getName()).append("'s Moves:<br>");
                if (currentPokemon.getPMoves() == 0) {
                    movesTextBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;(No moves known)");
                } else {
                    for (int i = 0; i < currentPokemon.getPMoves(); i++) {
                        Moves m = currentPokemon.getMoves()[i];
                        if (m != null) {
                            movesTextBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;- ")
                                    .append(m.getName()).append(": ").append(m.getDesc())
                                    .append(" [").append(m.getType1());

                            if (!m.getType2().equals("0") && !m.getType2().isEmpty()) {
                                movesTextBuilder.append("/").append(m.getType2());
                            }

                            movesTextBuilder.append("]");
                            if (m.getMachine() != null && !m.getMachine().isEmpty()) {
                                movesTextBuilder.append(" (Machine: ").append(m.getMachine()).append(")");
                            }
                            movesTextBuilder.append("<br>");
                        } else {
                            movesTextBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;(Empty or unassigned move slot)<br>");
                        }
                    }
                }
                movesTextBuilder.append("</html>");
                pokemonCard.add(new JLabel(movesTextBuilder.toString()));

                mainPanel.add(pokemonCard);
                mainPanel.add(Box.createVerticalStrut(10));
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading pokedex.txt: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Add Back Button
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.WHITE);
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> frame.dispose());
        backPanel.add(backButton);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(backPanel);

        // Final ScrollPane Setup
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        frame.add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static JPanel DisplayPokemonSearch(Pokemon currentPokemon) {
        JPanel pokemonCard = new JPanel();
        pokemonCard.setLayout(new BoxLayout(pokemonCard, BoxLayout.Y_AXIS));
        pokemonCard.setBackground(new Color(245, 245, 245));
        pokemonCard.setMaximumSize(new Dimension(800, 250));
        pokemonCard.setPreferredSize(new Dimension(800, 250));
        pokemonCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        pokemonCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        pokemonCard.add(new JLabel("Pokédex No: " + String.format("%04d", currentPokemon.getPokedexNo())));
        pokemonCard.add(new JLabel("Name: " + currentPokemon.getName()));
        pokemonCard.add(Box.createVerticalStrut(5));
        pokemonCard.add(new JLabel(currentPokemon.cry()));
        pokemonCard.add(Box.createVerticalStrut(5));
        pokemonCard.add(new JLabel("Type 1: " + currentPokemon.getType1()));
        pokemonCard.add(new JLabel("Type 2: " + (currentPokemon.getType2().equals("0") ? "None" : currentPokemon.getType2())));
        pokemonCard.add(new JLabel("Base Level: " + currentPokemon.getBaseLevel()));
        pokemonCard.add(new JLabel("HP: " + currentPokemon.getHP() + " | ATTACK: " + currentPokemon.getAtk() + " | DEFENSE: " + currentPokemon.getDef() + " | SPEED: " + currentPokemon.getSpd()));

        // Evolution info
        int fromPokedexNo = currentPokemon.getFrom();
        int toPokedexNo = currentPokemon.getTo();

        String evolvesFromText = "Evolves From: " + (fromPokedexNo > 0 && fromPokedexNo <= Pokedex.pokemonCount
                ? Pokedex.pokemon[fromPokedexNo - 1].getName() + " at Level " + Pokedex.pokemon[fromPokedexNo - 1].getEvoLevel()
                : (fromPokedexNo == 0 ? "None" : "Unknown At Unknown Level"));

        String evolvesToText = "Evolves To: " + (toPokedexNo > 0 && toPokedexNo <= Pokedex.pokemonCount
                ? Pokedex.pokemon[toPokedexNo - 1].getName() + " at Level " + currentPokemon.getEvoLevel()
                : (toPokedexNo == 0 ? "None" : "Unknown At Level " + currentPokemon.getEvoLevel()));

        pokemonCard.add(new JLabel(evolvesFromText));
        pokemonCard.add(new JLabel(evolvesToText));
        pokemonCard.add(Box.createVerticalStrut(5));
        pokemonCard.add(new JLabel("Held Item: None"));
        pokemonCard.add(Box.createVerticalStrut(5));

        // Moves
        pokemonCard.add(buildMoveLabel(currentPokemon));

        return pokemonCard;
    }


    public static void SearchMenu() {
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon pokBg = new ImageIcon("searchmen.png");

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
        mainPanel.setLayout(new GridLayout(4, 1, 20, 30)); // 4 rows, 1 column, spacing

        // Button labels
        String[] labels = {"POKEDEX NUMBER", "POKEMON NAME", "POKEMON TYPE", "BACK"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(220, 50), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "POKEDEX NUMBER":
                        SearchDex(Pokedex.pokemon); // Replace pokemonList with actual array
                        break;
                    case "POKEMON NAME":
                        SearchPokName(Pokedex.pokemon);
                        break;
                    case "POKEMON TYPE":
                        SearchPokType(Pokedex.pokemon);
                        break;
                    case "BACK":
                        PokemonManagement();
                        break;
                }
            });

            mainPanel.add(subButton);
        }

        // Center mainPanel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(150, 0, 0, 0); // Adjust top spacing
        backgroundPanel.add(mainPanel, gbc);

        // Final setup
        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    public static Pokemon searchByPokedexNumber(Pokemon[] pokemonList, int searchID) {
        for (Pokemon p : pokemonList) {
            if (p != null && p.getPokedexNo() == searchID) {
                return p; // Found
            }
        }
        return null; // Not found
    }

    public static void SearchDex(Pokemon[] pokemonList) {
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("searchNum.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 🟨 Prompt Label
        JLabel promptLabel = new JLabel("Search Pokedex Number:");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🟦 Result Panel placeholder
        JPanel resultPanel = new JPanel();
        resultPanel.setOpaque(false);

        // 🟩 Input Panel with validation and search logic
        JPanel inputPanel = createValidatedIntInputField(
                "Next", 1, 9999,
                enteredNumber -> {
                    Pokemon result = searchByPokedexNumber(pokemonList, enteredNumber);
                    resultPanel.removeAll(); // clear previous results

                    if (result != null) {
                        JPanel pokemonCard = DisplayPokemonSearch(result); // ✅ fixed here
                        resultPanel.add(pokemonCard);
                        ButtonBg subButton = new ButtonBg("BACK", new Dimension(100, 50), Color.RED);

                        subButton.addActionListener(e -> {
                            SearchMenu(); // Return to management
                        });
                        resultPanel.add(subButton);
                    } else {
                        JLabel notFoundLabel = new JLabel("No Pokémon found");
                        notFoundLabel.setForeground(Color.RED);
                        notFoundLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        resultPanel.add(notFoundLabel);
                        ButtonBg subButton = new ButtonBg("BACK", new Dimension(100, 50), Color.RED);
                        subButton.addActionListener(e -> {
                            SearchMenu(); // Return to management
                        });
                        resultPanel.add(subButton);
                    }
                    resultPanel.revalidate();
                    resultPanel.repaint();
                }
        );

        // 🧩 Assemble layout
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(promptLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(resultPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(180, 0, 0, 0);

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static Pokemon searchByName(Pokemon[] pokemonList, String nameToSearch) {
        if (nameToSearch == null || nameToSearch.trim().isEmpty()) {
            return null;
        }

        String searchTerm = nameToSearch.trim().toLowerCase();

        for (Pokemon p : pokemonList) {
            if (p != null && p.getName().toLowerCase().contains(searchTerm)) {
                return p; // Found matching name
            }
        }
        return null; // Not found
    }

    public static void SearchPokName(Pokemon[] pokemonList) {
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("searchName.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel promptLabel = new JLabel("Search Pokémon Name:");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel resultPanel = new JPanel();
        resultPanel.setOpaque(false);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200, 30));
        inputPanel.add(nameField);

        inputPanel.add(Box.createHorizontalStrut(10));

        JButton submitButton = new JButton("Search");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(e -> {
            String nameInput = nameField.getText().trim();
            resultPanel.removeAll();

            Pokemon result = searchByName(pokemonList, nameInput);

            if (result != null) {
                JPanel pokemonCard = DisplayPokemonSearch(result);
                resultPanel.add(pokemonCard);
            } else {
                JLabel notFoundLabel = new JLabel("No Pokémon found");
                notFoundLabel.setForeground(Color.RED);
                notFoundLabel.setFont(new Font("Arial", Font.BOLD, 16));
                resultPanel.add(notFoundLabel);
            }

            resultPanel.add(Box.createVerticalStrut(10));
            ButtonBg backButton = new ButtonBg("BACK", new Dimension(100, 50), Color.RED);
            backButton.addActionListener(ev -> {
                SearchMenu();
            });
            resultPanel.add(backButton);

            resultPanel.revalidate();
            resultPanel.repaint();
        });
        inputPanel.add(submitButton);

        // Assemble layout
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(promptLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(resultPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(180, 0, 0, 0);

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static void SearchPokType(Pokemon[] pokemonList) {
        JFrame pokFrame2 = new JFrame();
        pokFrame2.setSize(1300, 700);
        pokFrame2.setUndecorated(true);
        pokFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("searchType.png");

        JPanel backgroundPanel2 = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel2.setOpaque(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel promptLabel = new JLabel("Search Pokémon by Type:");
        promptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        JTextField typeField = new JTextField();
        typeField.setMaximumSize(new Dimension(200, 30));
        inputPanel.add(typeField);

        inputPanel.add(Box.createHorizontalStrut(10));

        JButton submitButton = new JButton("Search");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(e -> {
            String typeInput = typeField.getText().trim();
            if (!typeInput.isEmpty()) {
                displayPokemonsByType(typeInput, pokemonList);
            }
        });
        inputPanel.add(submitButton);

        // Assemble layout
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(promptLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(inputPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(180, 200, 0, 0);

        backgroundPanel2.add(mainPanel, gbc);

        pokFrame2.setContentPane(backgroundPanel2);
        pokFrame2.setLocationRelativeTo(null);
        pokFrame2.setVisible(true);
    }

    public static void displayPokemonsByType(String typeInput, Pokemon[] pokemonList) {
        JFrame frame = new JFrame("Pokémon Type Search Results");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        boolean found = false;

        for (Pokemon p : pokemonList) {
            if (p == null) continue;

            String t1 = p.getType1().toLowerCase();
            String t2 = p.getType2().toLowerCase();
            String input = typeInput.toLowerCase();

            if (t1.contains(input) || t2.contains(input)) {
                JPanel pokemonCard = DisplayPokemonSearch(p);
                mainPanel.add(pokemonCard);
                mainPanel.add(Box.createVerticalStrut(10));
                found = true;
            }
        }

        if (!found) {
            JLabel notFoundLabel = new JLabel("No Pokémon found with type: " + typeInput);
            notFoundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            notFoundLabel.setForeground(Color.RED);
            notFoundLabel.setFont(new Font("Arial", Font.BOLD, 16));
            mainPanel.add(Box.createVerticalStrut(50));
            mainPanel.add(notFoundLabel);
        }

        // Add Back Button
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
            topFrame.dispose();
            SearchMenu();
        });

        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(backButton);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        if (found) {
            frame.setSize(900, 600); // normal size
        } else {
            frame.setSize(400, 200); // smaller frame for "not found" message
        }

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addTrainerMenu(){
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon pokBg = new ImageIcon("addtrainermen.png");

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
        mainPanel.setLayout(new GridLayout(4, 1, 20, 60)); // 4 rows, 1 column, spacing

        // Button labels
        String[] labels = {"ADD NEW TRAINER", "MANAGE TRAINER PROFILE","BACK"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(300, 60), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "ADD NEW TRAINER":
                        AddTrainer();
                        break;
                    case "MANAGE TRAINER PROFILE":
                        ManageTrainerProf();
                        break;
                    case "BACK":
                        TrainerManagement();
                        break;
                }
            });

            mainPanel.add(subButton);
        }

        // Center mainPanel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(150, 0, 0, 0); // top, left, bottom, right — pushes panel downward
        backgroundPanel.add(mainPanel, gbc);

        // Final setup
        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    private static boolean trainerExists(String id, String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length >= 2) {
                    String existingId = parts[0].trim();
                    String existingName = parts[1].trim();
                    if (existingId.equals(id) || existingName.equalsIgnoreCase(name)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void AddTrainer() {
        JFrame addFrame = new JFrame("Add New Trainer");
        addFrame.setSize(800, 600);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Form fields
        JLabel idLabel = new JLabel("Trainer ID (5 digits):");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Trainer Name:");
        JTextField nameField = new JTextField();

        JLabel sexLabel = new JLabel("Sex:");
        JComboBox<String> sexCombo = new JComboBox<>(new String[]{"Male", "Female"});

        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(3, 20);
        JScrollPane descScroll = new JScrollPane(descArea);

        JLabel homeLabel = new JLabel("Hometown:");
        JTextField homeField = new JTextField();

        JLabel birthLabel = new JLabel("Birth Date:");
        JPanel birthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JSpinner monthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        JSpinner daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        JSpinner yearSpinner = new JSpinner(new SpinnerNumberModel(2000, 1900, 2025, 1));
        birthPanel.add(new JLabel("Month:")); birthPanel.add(monthSpinner);
        birthPanel.add(new JLabel("Day:"));   birthPanel.add(daySpinner);
        birthPanel.add(new JLabel("Year:"));  birthPanel.add(yearSpinner);

        // Add form elements to panel
        formPanel.add(idLabel);     formPanel.add(idField);
        formPanel.add(nameLabel);   formPanel.add(nameField);
        formPanel.add(sexLabel);    formPanel.add(sexCombo);
        formPanel.add(descLabel);   formPanel.add(descScroll);
        formPanel.add(homeLabel);   formPanel.add(homeField);
        formPanel.add(birthLabel);  formPanel.add(birthPanel);

        // Result area
        JTextArea resultArea = new JTextArea(8, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane resultScroll = new JScrollPane(resultArea);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add Trainer");
        JButton backButton = new JButton("Back");
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);

        // Add button logic
        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String sex = (String) sexCombo.getSelectedItem();
            String description = descArea.getText().trim();
            String home = homeField.getText().trim();
            int month = (int) monthSpinner.getValue();
            int day = (int) daySpinner.getValue();
            int year = (int) yearSpinner.getValue();

            StringBuilder errors = new StringBuilder();

            // Validation
            if (id.isEmpty()) {
                errors.append("• Trainer ID cannot be blank\n");
            } else if (id.length() != 5) {
                errors.append("• Trainer ID must be exactly 5 digits\n");
            } else {
                for (char c : id.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        errors.append("• Trainer ID must contain only digits\n");
                        break;
                    }
                }
            }
            if (name.isEmpty()) {
                errors.append("• Trainer name cannot be blank\n");
            } else {
                for (char c : name.toCharArray()) {
                    if (Character.isDigit(c)) {
                        errors.append("• Trainer name cannot contain numbers\n");
                        break;
                    }
                }
            }

            // Description and Hometown Validation
            if (description.isEmpty()) errors.append("• Description cannot be blank\n");
            if (home.isEmpty()) errors.append("• Hometown cannot be blank\n");

            if (trainerExists(id, name)) {
                errors.append("• A trainer with this ID or Name already exists\n");
            }

            if (errors.length() > 0) {
                resultArea.setText("Please correct the following errors:\n" + errors);
                return;
            }

            try {
                // Create the trainer with default values
                Trainers trainer = new Trainers(id, name, month, day, year, sex, home, description);
                trainer.setMoney(1000000); // Default starting money

                // Format birth date
                String birthDate = String.format("%02d/%02d/%04d", month, day, year);

                // Build the trainer data string
                StringBuilder trainerData = new StringBuilder();
                trainerData.append("[TRAINER_START]\n")
                        .append("ID=").append(id).append("\n")
                        .append("Name=").append(name).append("\n")
                        .append("Birth=").append(birthDate).append("\n")
                        .append("Sex=").append(sex).append("\n")
                        .append("Home=").append(home).append("\n")
                        .append("Description=").append(description).append("\n")
                        .append(String.format("Money=%.2f\n", trainer.getMoney()))
                        .append("[POKEMON_TEAM]\n")
                        .append("[POKEMON_STORAGE]\n")
                        .append("[ITEMS]\n")
                        .append("[UNIQUE_ITEMS]\n")
                        .append("[TRAINER_END]\n\n");

                // Append to file
                try (FileWriter writer = new FileWriter("trainers.txt", true)) {
                    writer.write(trainerData.toString());
                }

                resultArea.setText("Trainer added successfully!\n\n" +
                        "ID: " + id + "\n" +
                        "Name: " + name + "\n" +
                        "Sex: " + sex + "\n" +
                        "Hometown: " + home + "\n" +
                        "Birth Date: " + birthDate + "\n" +
                        "Description: " + description);

                // Clear fields
                idField.setText("");
                nameField.setText("");
                descArea.setText("");
                homeField.setText("");
                monthSpinner.setValue(1);
                daySpinner.setValue(1);
                yearSpinner.setValue(2000);

            } catch (IOException ex) {
                resultArea.setText("Error saving trainer: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        backButton.addActionListener(e -> addFrame.dispose());

        // Layout
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(resultScroll, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addFrame.add(mainPanel);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }

    private static Trainers getTrainerById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length >= 8 && parts[0].equals(id)) {
                    String name = parts[1];
                    int month = Integer.parseInt(parts[2]);
                    int day = Integer.parseInt(parts[3]);
                    int year = Integer.parseInt(parts[4]);
                    String sex = parts[5];
                    String home = parts[6];
                    String description = parts[7];
                    return new Trainers(id, name, month, day, year, sex, home, description);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void ManageTrainerProf() {

        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon pokBg = new ImageIcon("managetrainerprof.png");

        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        JPanel containerPanel = new JPanel(new CardLayout());
        containerPanel.setOpaque(false);

        // ===================== Updated Input Panel =====================
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel promptLabel = new JLabel("Enter Trainer ID:");
        promptLabel.setForeground(Color.WHITE); // White font
        promptLabel.setBackground(Color.BLACK); // Black background
        promptLabel.setOpaque(true);            // Make background visible
        promptLabel.setFont(new Font("Arial", Font.BOLD, 28));
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField trainerIdField = new JTextField();
        trainerIdField.setMaximumSize(new Dimension(300, 40));
        trainerIdField.setFont(new Font("Arial", Font.PLAIN, 20));
        trainerIdField.setHorizontalAlignment(JTextField.CENTER);
        trainerIdField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setMaximumSize(new Dimension(150, 40));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputPanel.add(promptLabel);
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(trainerIdField);
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(submitButton);

        // Welcome label (shown after login)
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 60));
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBackground(Color.BLACK);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setPreferredSize(new Dimension(1000, 80));

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        final Trainers[] activeTrainer = {null};

        JPanel welcomePanel = new JPanel();
        welcomePanel.setOpaque(false);
        welcomePanel.add(welcomeLabel);
        mainPanel.add(welcomePanel);
        mainPanel.add(Box.createVerticalStrut(40));

        String[] labels = {"MANAGE YOUR POKEMON", "MANAGE YOUR ITEMS", "BACK"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(250, 50), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "MANAGE YOUR POKEMON":
                        ManageTrainerPok(activeTrainer[0]);
                        break;
                    case "MANAGE YOUR ITEMS":
                        ManageTrainerItem(activeTrainer[0]);
                        break;
                    case "BACK":
                        pokFrame.dispose();
                        addTrainerMenu();
                        break;
                }
            });

            JPanel buttonWrapper = new JPanel();
            buttonWrapper.setOpaque(false);
            buttonWrapper.setMaximumSize(new Dimension(250, 50));
            buttonWrapper.add(subButton);
            mainPanel.add(buttonWrapper);
            mainPanel.add(Box.createVerticalStrut(20));
        }

        containerPanel.add(inputPanel, "input");
        containerPanel.add(mainPanel, "menu");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(200, 0, 0, 0); // Adjust top spacing
        backgroundPanel.add(containerPanel, gbc);

        submitButton.addActionListener(e -> {
            String enteredId = trainerIdField.getText().trim();

            if (enteredId.matches("\\d{5}")) {
                Trainers currentTrainer = Trainers.loadFromFile(enteredId);
                if (currentTrainer != null) {
                    activeTrainer[0] = currentTrainer;
                    welcomeLabel.setText("Welcome Trainer " + currentTrainer.getName() + "!");
                    CardLayout cl = (CardLayout) containerPanel.getLayout();
                    cl.show(containerPanel, "menu");
                } else {
                    int choice = JOptionPane.showOptionDialog(
                            pokFrame,
                            "Trainer ID not found!",
                            "Error",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            new Object[]{"Go Back"},
                            "Go Back"
                    );
                    if (choice == 0) {
                        pokFrame.dispose();
                        addTrainerMenu();
                    }
                }
            } else {
                int choice = JOptionPane.showOptionDialog(
                        pokFrame,
                        "Enter a valid 5-digit Trainer ID.",
                        "Invalid Input",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        new Object[]{"Go Back"},
                        "Go Back"
                );
                if (choice == 0) {
                    pokFrame.dispose();
                    addTrainerMenu();
                }
            }
        });

        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    public static void ManageTrainerPok(Trainers trainer) {
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon pokBg = new ImageIcon("managetrainerpok.png");

        // Custom panel to paint background
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pokBg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Main vertical container
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Top button panel
        JPanel topButtons = new JPanel();
        topButtons.setOpaque(false);
        topButtons.setLayout(new GridLayout(3, 2, 20, 30));

        // Button labels
        String[] labels = {
                "ADD POKEMON TO STORAGE",
                "ADD POKEMON TO LINEUP",
                "SWITCH POKEMON FROM STORAGE",
                "RELEASE POKEMON",
                "TEACH MOVES",
                "UNLEARN MOVES"
        };

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(400, 60), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "ADD POKEMON TO STORAGE":
                        showAddPokemonToTrainer(trainer, false); // false = add to storage
                        break;
                    case "ADD POKEMON TO LINEUP":
                        showAddPokemonToTrainer(trainer, true); // true = add to lineup
                        break;
                    case "SWITCH POKEMON FROM STORAGE":
                        showSwitchPokemon(trainer);
                        break;
                    case "RELEASE POKEMON":
                        showReleasePokemon(trainer);
                        break;
                    case "TEACH MOVES":
                        showTeachMove(trainer); // Added teach moves functionality
                        break;
                    case "UNLEARN MOVES":
                        showUnlearnMove(trainer); // Added unlearn moves functionality
                        break;
                }
            });

            topButtons.add(subButton);
        }

        // Back button panel
        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        ButtonBg backButton = new ButtonBg("BACK", new Dimension(300, 80), Color.RED);
        backButton.addActionListener(e -> pokFrame.dispose());
        backPanel.add(backButton);

        // Combine all panels
        contentPanel.add(topButtons);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(backPanel);

        // Center contentPanel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(230, 0, 0, 0);
        backgroundPanel.add(contentPanel, gbc);

        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    private static void ManageTrainerItem(Trainers trainer){
        JFrame pokFrame = new JFrame();
        pokFrame.setSize(1300, 700);
        pokFrame.setUndecorated(true);
        pokFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load background image
        ImageIcon pokBg = new ImageIcon("managetrainitem.png");

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
        mainPanel.setLayout(new GridLayout(4, 1, 20, 30)); // 4 rows, 1 column, spacing

        // Button labels
        String[] labels = {"BUY ITEM", "USE ITEM","SELL ITEM","BACK"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(300, 60), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "BUY ITEM":
                        BuyItem(trainer);
                        break;
                    case "USE ITEM":
                        useItem(trainer);
                        break;
                    case "SELL ITEM":
                        sellItem(trainer);
                        break;
                    case "BACK":
                        pokFrame.dispose();
                        break;
                }
            });

            mainPanel.add(subButton);
        }

        // Center mainPanel using GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(200, 0, 0, 0); // top, left, bottom, right — pushes panel downward
        backgroundPanel.add(mainPanel, gbc);

        // Final setup
        pokFrame.setContentPane(backgroundPanel);
        pokFrame.setLocationRelativeTo(null);
        pokFrame.setVisible(true);
    }

    private static void viewTrainers() {
        JFrame frame = new JFrame("All Trainers");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.dispose());
        mainPanel.add(backButton, BorderLayout.SOUTH);

        try (BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"))) {
            StringBuilder currentTrainer = new StringBuilder();
            boolean inTrainer = false;

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.equals("[TRAINER_START]")) {
                    currentTrainer = new StringBuilder();
                    inTrainer = true;
                }
                else if (line.equals("[TRAINER_END]")) {
                    inTrainer = false;
                    parseAndDisplayTrainer(textArea, currentTrainer.toString());
                }
                else if (inTrainer) {
                    currentTrainer.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            textArea.setText("Error reading trainers.txt: " + e.getMessage());
        }

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void parseAndDisplayTrainer(JTextArea textArea, String trainerData) {
        // Parse trainer info
        String id = getValue(trainerData, "ID=");
        String name = getValue(trainerData, "Name=");
        String birth = getValue(trainerData, "Birth=");
        String sex = getValue(trainerData, "Sex=");
        String home = getValue(trainerData, "Home=");
        String description = getValue(trainerData, "Description=");
        String money = getValue(trainerData, "Money=");

        // Parse Pokémon
        List<String> team = getSection(trainerData, "[POKEMON_TEAM]");
        List<String> storage = getSection(trainerData, "[POKEMON_STORAGE]");

        // Display formatted info
        textArea.append("   Trainer Card\n");
        textArea.append("   ID No.      : " + id + "\n");
        textArea.append("   Name        : " + name + "\n");
        textArea.append("   Birth Date  : " + birth + "\n");
        textArea.append("   Sex         : " + sex + "\n");
        textArea.append("   Hometown    : " + home + "\n");
        textArea.append("   Description : " + description + "\n");
        textArea.append("   Money       : " + money + "\n\n");

        textArea.append("   Pokemon in Lineup:\n");
        if (team.isEmpty()) {
            textArea.append("     None\n");
        } else {
            for (String pokemon : team) {
                textArea.append("     - " + formatPokemonLine(pokemon) + "\n");
            }
        }

        textArea.append("   Pokemon in Storage:\n");
        if (storage.isEmpty()) {
            textArea.append("     None\n");
        } else {
            for (String pokemon : storage) {
                textArea.append("     - " + formatPokemonLine(pokemon) + "\n");
            }
        }

        textArea.append("----------------------------\n\n");
    }

    // Helper methods
    private static String getValue(String data, String key) {
        int start = data.indexOf(key);
        if (start == -1) return "";
        start += key.length();
        int end = data.indexOf("\n", start);
        return end == -1 ? data.substring(start) : data.substring(start, end);
    }

    private static List<String> getSection(String data, String sectionMarker) {
        List<String> lines = new ArrayList<>();
        int start = data.indexOf(sectionMarker);
        if (start == -1) return lines;

        start += sectionMarker.length();
        int end = data.indexOf("\n[", start);
        String section = end == -1 ? data.substring(start) : data.substring(start, end);

        for (String line : section.split("\n")) {
            if (!line.trim().isEmpty()) {
                lines.add(line.trim());
            }
        }
        return lines;
    }

    private static String formatPokemonLine(String csv) {
        String[] parts = csv.split(",");
        if (parts.length >= 3) {
            return parts[1] + " (Lv." + parts[4] + ")";
        }
        return csv; // fallback
    }

    // Helper method to display trainer information
    private static void displayTrainerInfo(JTextArea textArea, Trainers trainer) {
        textArea.append("   Trainer Card\n");
        textArea.append("   ID No.      : " + trainer.getID() + "\n");
        textArea.append("   Name        : " + trainer.getName() + "\n");

        Date birth = trainer.getBirth();
        textArea.append("   Birth Date  : " +
                String.format("%02d/%02d/%04d", birth.getMonth(), birth.getDay(), birth.getYear()) + "\n");

        textArea.append("   Sex         : " + trainer.getSex() + "\n");
        textArea.append("   Hometown    : " + trainer.getHome() + "\n");
        textArea.append("   Description : " + trainer.getDescription() + "\n");
        textArea.append("   Money       : " + String.format("%.2f", trainer.getMoney()) + "\n\n");

        // Display Pokémon team
        textArea.append("   Pokemon in Lineup:");
        boolean hasTeam = false;
        for (int i = 0; i < trainer.getLineupCount(); i++) {
            Pokemon p = trainer.getPokemonFromLineup(i);
            if (p != null) {
                textArea.append("     - " + p.getName() + " (Lv." + p.getBaseLevel() + ")\n");
                hasTeam = true;
            }
        }
        if (!hasTeam) textArea.append(" None\n");

        // Display Pokémon storage
        textArea.append("   Pokemon in Storage:");
        boolean hasStorage = false;
        for (int i = 0; i < trainer.getStorageCount(); i++) {
            Pokemon p = trainer.getPokemonFromStorage(i);
            if (p != null) {
                textArea.append("     - " + p.getName() + " (Lv." + p.getBaseLevel() + ")\n");
                hasStorage = true;
            }
        }
        if (!hasStorage) textArea.append(" None\n");

        textArea.append("----------------------------\n\n");
    }


    public static void SearchTrainer() {
        JFrame searchFrame = new JFrame("Search Trainer");
        searchFrame.setSize(600, 400);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel searchLabel = new JLabel("Enter Trainer Keyword:");
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
            String keyword = searchField.getText().trim().toLowerCase();
            if (keyword.isEmpty()) {
                resultArea.setText("Please enter a trainer keyword to search.");
                return;
            }

            StringBuilder result = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"))) {
                String line;
                boolean inTrainer = false;
                boolean found = false;
                Trainers currentTrainer = null;
                String section = null;
                Map<String, String> trainerData = new HashMap<>();

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    if (line.equals("[TRAINER_START]")) {
                        inTrainer = true;
                        trainerData.clear();
                        currentTrainer = new Trainers();
                        section = null;
                        continue;
                    } else if (line.equals("[TRAINER_END]")) {
                        inTrainer = false;

                        // Check if current trainer matches search criteria
                        if (currentTrainer != null && trainerMatchesKeyword(currentTrainer, keyword)) {
                            appendTrainerInfo(result, currentTrainer);
                            found = true;
                        }
                        continue;
                    } else if (line.startsWith("[")) {
                        section = line.replace("[", "").replace("]", "");
                        continue;
                    }

                    if (inTrainer && currentTrainer != null) {
                        if (section == null) {
                            // Parse trainer attributes
                            if (line.startsWith("ID=")) {
                                currentTrainer.setID(line.substring(3).trim());
                                trainerData.put("ID", line.substring(3).trim());
                            } else if (line.startsWith("Name=")) {
                                currentTrainer.setName(line.substring(5).trim());
                                trainerData.put("Name", line.substring(5).trim());
                            } else if (line.startsWith("Birth=")) {
                                trainerData.put("Birth", line.substring(6).trim());
                            } else if (line.startsWith("Sex=")) {
                                currentTrainer.setSex(line.substring(4).trim());
                                trainerData.put("Sex", line.substring(4).trim());
                            } else if (line.startsWith("Home=")) {
                                currentTrainer.setHome(line.substring(5).trim());
                                trainerData.put("Home", line.substring(5).trim());
                            } else if (line.startsWith("Description=")) {
                                currentTrainer.setDescription(line.substring(12).trim());
                                trainerData.put("Description", line.substring(12).trim());
                            } else if (line.startsWith("Money=")) {
                                currentTrainer.setMoney(Double.parseDouble(line.substring(6).trim()));
                                trainerData.put("Money", line.substring(6).trim());
                            }
                        } else {
                            // Handle sections (team, storage, items)
                            if (section.equals("POKEMON_TEAM")) {
                                Pokemon p = Pokemon.fromCsvString(line);
                                if (p != null) currentTrainer.addPokemonToLineup(p);
                            } else if (section.equals("POKEMON_STORAGE")) {
                                Pokemon p = Pokemon.fromCsvString(line);
                                if (p != null) currentTrainer.addPokemonToStorage(p);
                            }
                        }
                    }
                }

                if (!found) {
                    resultArea.setText("No trainer matches the keyword: " + keyword);
                } else {
                    resultArea.setText(result.toString());
                }

            } catch (IOException ex) {
                resultArea.setText("Error reading trainers.txt: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                resultArea.setText("Error parsing trainer data: " + ex.getMessage());
            }
        });

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        searchFrame.add(mainPanel);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
    }

    private static boolean trainerMatchesKeyword(Trainers trainer, String keyword) {
        return trainer.getID().toLowerCase().contains(keyword) ||
                trainer.getName().toLowerCase().contains(keyword) ||
                trainer.getSex().toLowerCase().contains(keyword) ||
                trainer.getHome().toLowerCase().contains(keyword) ||
                trainer.getDescription().toLowerCase().contains(keyword);
    }

    private static void appendTrainerInfo(StringBuilder result, Trainers trainer) {
        result.append("   Trainer Card\n");
        result.append("   ID No.      : ").append(trainer.getID()).append("\n");
        result.append("   Name        : ").append(trainer.getName()).append("\n");
        result.append("   Birth Date  : ").append(trainer.getBirth()).append("\n");
        result.append("   Sex         : ").append(trainer.getSex()).append("\n");
        result.append("   Hometown    : ").append(trainer.getHome()).append("\n");
        result.append("   Description : ").append(trainer.getDescription()).append("\n");
        result.append("   Money       : ").append(trainer.getMoney()).append("\n");

        result.append("   Pokemon in Lineup:");
        Pokemon[] team = trainer.getPokemonLineup();
        boolean hasTeam = false;
        for (Pokemon p : team) {
            if (p != null) {
                result.append("     - ").append(p.getName()).append("\n");
                hasTeam = true;
            }
        }
        if (!hasTeam) result.append(" None\n");

        result.append("   Pokemon in Storage:");
        Pokemon[] pc = trainer.getPokemonStorage();
        boolean hasStorage = false;
        for (Pokemon p : pc) {
            if (p != null) {
                result.append("     - ").append(p.getName()).append("\n");
                hasStorage = true;
            }
        }
        if (!hasStorage) result.append(" None\n");

        result.append("----------------------------\n\n");
    }
    public static void showAddPokemonToTrainer(Trainers trainer, boolean addToLineup) {
        JFrame frame = new JFrame("Add Pokémon to " + trainer.getName());
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Pokémon Selection
        JLabel pokemonLabel = new JLabel("Select Pokémon:");
        JComboBox<String> pokemonCombo = new JComboBox<>();

        for (int i = 0; i < Pokedex.pokemonCount; i++) {
            Pokemon p = Pokedex.pokemon[i];
            if (p != null) {
                pokemonCombo.addItem(p.getName() + " (Lv. " + p.getBaseLevel() + ")");
            }
        }

        // Team/PC Status
        JLabel statusLabel = new JLabel();
        if (addToLineup) {
            statusLabel.setText("Team: " + trainer.getLineupCount() + "/6");
        } else {
            statusLabel.setText("PC: " + trainer.getStorageCount() + "/10");
        }

        formPanel.add(pokemonLabel);
        formPanel.add(pokemonCombo);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusLabel);

        // Result Area
        JTextArea resultArea = new JTextArea(10, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setPreferredSize(new Dimension(700, 200));

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton(addToLineup ? "Add to Team" : "Add to Storage");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(e -> {
            int selectedIndex = pokemonCombo.getSelectedIndex();

            if (selectedIndex >= 0 && selectedIndex < Pokedex.pokemonCount) {
                Pokemon selectedPokemon = Pokedex.pokemon[selectedIndex];

                // Check if Pokémon already exists
                boolean alreadyExists = false;
                for (Pokemon p : trainer.getPokemonLineup()) {
                    if (p != null && p.getPokedexNo() == selectedPokemon.getPokedexNo()) {
                        alreadyExists = true;
                        break;
                    }
                }
                if (!alreadyExists) {
                    for (Pokemon p : trainer.getPokemonStorage()) {
                        if (p != null && p.getPokedexNo() == selectedPokemon.getPokedexNo()) {
                            alreadyExists = true;
                            break;
                        }
                    }
                }

                if (alreadyExists) {
                    resultArea.setText("❌ This Pokémon already exists in the trainer’s team or PC storage.");
                    return;
                }

                // Create a new instance using base level from selected Pokémon
                Pokemon newPokemon = new Pokemon(
                        selectedPokemon.getPokedexNo(),
                        selectedPokemon.getName(),
                        selectedPokemon.getType1(),
                        selectedPokemon.getType2(),
                        selectedPokemon.getBaseLevel(),
                        selectedPokemon.getFrom(),
                        selectedPokemon.getTo(),
                        selectedPokemon.getEvoLevel(),
                        selectedPokemon.getHP(),
                        selectedPokemon.getAtk(),
                        selectedPokemon.getDef(),
                        selectedPokemon.getSpd()
                );

                // Always teach default moves
                newPokemon.teachMove("Tackle", false);
                newPokemon.teachMove("Defend", false);

                // Copy other moves (skip duplicates)
                for (int i = 0; i < selectedPokemon.getPMoves(); i++) {
                    Moves move = selectedPokemon.getMoves()[i];
                    if (move != null && !move.getName().equalsIgnoreCase("Tackle") && !move.getName().equalsIgnoreCase("Defend")) {
                        newPokemon.teachMove(move.getName(), false);
                    }
                }



                String result;
                if (addToLineup) {
                    result = trainer.addPokemonToLineup(newPokemon);
                } else {
                    result = trainer.addPokemonToStorage(newPokemon);
                }

                resultArea.setText(result);

                // Update status
                if (addToLineup) {
                    statusLabel.setText("Team: " + trainer.getLineupCount() + "/6");
                } else {
                    statusLabel.setText("PC: " + trainer.getStorageCount() + "/10");
                }
            }
            trainer.saveToFile();
        });

        cancelButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(resultScroll, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String getPokemonDetails(Pokemon p) {
        String heldItemName = (p.getHeldItem() != null) ? p.getHeldItem().getitemName() : "None";

        return String.format(
                "Name: %s\nLevel: %d\nType: %s%s\nHP: %d\nAttack: %d\nDefense: %d\nSpeed: %d\n\nMoves:\n%s\nHeld Item: %s",
                p.getName(),
                p.getBaseLevel(),
                p.getType1(),
                p.getType2().equals("0") ? "" : "/" + p.getType2(),
                p.getHP(),
                p.getAtk(),
                p.getDef(),
                p.getSpd(),
                getMovesAsString(p),
                heldItemName
        );
    }

    private static String getPokemonInfo(Pokemon p) {
        String heldItemName = (p.getHeldItem() != null) ? p.getHeldItem().getitemName() : "None";

        return String.format(
                "%s (Lv.%d)\nType: %s%s\nHP: %d  ATK: %d  DEF: %d  SPD: %d\nHeld Item: %s",
                p.getName(), p.getBaseLevel(),
                p.getType1(), p.getType2().equals("0") ? "" : "/" + p.getType2(),
                p.getHP(), p.getAtk(), p.getDef(), p.getSpd(),
                heldItemName
        );
    }

    private static String getMovesAsString(Pokemon p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.getPMoves(); i++) {
            Moves m = p.getMoves()[i];
            if (m != null) {
                sb.append("- ").append(m.getName()).append(" (").append(m.getType1());
                if (!m.getType2().equals("0") && !m.getType2().isEmpty()) {
                    sb.append("/").append(m.getType2());
                }
                sb.append(")");

                // Append machine (TM/HM) info if available
                if (m.getMachine() != null && !m.getMachine().isEmpty()) {
                    sb.append(" [").append(m.getMachine()).append("]");
                }

                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static void showSwitchPokemon(Trainers trainer) {
        JFrame frame = new JFrame("Switch Pokémon - " + trainer.getName());
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        // Create panels for lineup and storage
        JPanel lineupPanel = new JPanel(new BorderLayout());
        JPanel storagePanel = new JPanel(new BorderLayout());

        // Create lists with custom renderers
        DefaultListModel<Pokemon> lineupModel = new DefaultListModel<>();
        for (int i = 0; i < trainer.getLineupCount(); i++) {
            lineupModel.addElement(trainer.getPokemonFromLineup(i));
        }

        DefaultListModel<Pokemon> storageModel = new DefaultListModel<>();
        for (int i = 0; i < trainer.getStorageCount(); i++) {
            storageModel.addElement(trainer.getPokemonFromStorage(i));
        }

        JList<Pokemon> lineupList = new JList<>(lineupModel);
        JList<Pokemon> storageList = new JList<>(storageModel);


        // Set custom renderers
        lineupList.setCellRenderer(new PokemonListRenderer());
        storageList.setCellRenderer(new PokemonListRenderer());

        // Add scroll panes
        lineupPanel.add(new JLabel("Current Team (Max 6)"), BorderLayout.NORTH);
        lineupPanel.add(new JScrollPane(lineupList), BorderLayout.CENTER);

        storagePanel.add(new JLabel("PC Storage"), BorderLayout.NORTH);
        storagePanel.add(new JScrollPane(storageList), BorderLayout.CENTER);
        JPanel infoPanel = new JPanel(new BorderLayout());
        JTextArea pokemonInfo = new JTextArea(10, 30);
        pokemonInfo.setEditable(false);
        infoPanel.add(new JScrollPane(pokemonInfo), BorderLayout.CENTER);

// Add selection listeners
        lineupList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Pokemon p = lineupList.getSelectedValue();
                if (p != null) {
                    pokemonInfo.setText(getPokemonDetails(p));
                }
            }
        });

        storageList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Pokemon p = storageList.getSelectedValue();
                if (p != null) {
                    pokemonInfo.setText(getPokemonDetails(p));
                }
            }
        });

// Add info panel to main layout
        mainPanel.add(infoPanel, BorderLayout.EAST);
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton toStorageButton = new JButton("To Storage");
        JButton toLineupButton = new JButton("To Team");
        JButton swapButton = new JButton("Swap Selected");
        JButton cancelButton = new JButton("Cancel");

        // Result area
        JTextArea resultArea = new JTextArea(3, 50);
        resultArea.setEditable(false);

        // Button actions
        toStorageButton.addActionListener(e -> {
            int selectedIndex = lineupList.getSelectedIndex();
            if (selectedIndex == -1) {
                resultArea.setText("Please select a Pokémon from your team!");
                return;
            }

            String result = trainer.switchPokemon(selectedIndex, -1);
            resultArea.setText(result);

            // Update UI
            if (result.startsWith("Error")) {
                return;
            }

            lineupModel.remove(selectedIndex);
            storageModel.addElement(trainer.getPokemonFromStorage(trainer.getStorageCount() - 1));
            trainer.saveToFile();
        });

        toLineupButton.addActionListener(e -> {
            int selectedIndex = storageList.getSelectedIndex();
            if (selectedIndex == -1) {
                resultArea.setText("Please select a Pokémon from storage!");
                return;
            }

            String result = trainer.switchPokemon(-1, selectedIndex);
            resultArea.setText(result);

            // Update UI
            if (result.startsWith("Error")) {
                return;
            }

            storageModel.remove(selectedIndex);
            lineupModel.addElement(trainer.getPokemonFromLineup(trainer.getLineupCount() - 1));
            trainer.saveToFile();
        });

        swapButton.addActionListener(e -> {
            int lineupIndex = lineupList.getSelectedIndex();
            int storageIndex = storageList.getSelectedIndex();

            if (lineupIndex == -1 || storageIndex == -1) {
                resultArea.setText("Please select one Pokémon from each list!");
                return;
            }

            String result = trainer.switchPokemon(lineupIndex, storageIndex);
            resultArea.setText(result);

            // Update UI
            if (result.startsWith("Error")) {
                return;
            }

            Pokemon lineupPokemon = lineupModel.get(lineupIndex);
            Pokemon storagePokemon = storageModel.get(storageIndex);

            lineupModel.set(lineupIndex, storagePokemon);
            storageModel.set(storageIndex, lineupPokemon);
            trainer.saveToFile();
        });

        cancelButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(toStorageButton);
        buttonPanel.add(toLineupButton);
        buttonPanel.add(swapButton);
        buttonPanel.add(cancelButton);

        // Layout
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        listsPanel.add(lineupPanel);
        listsPanel.add(storagePanel);

        mainPanel.add(listsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(new JScrollPane(resultArea), BorderLayout.NORTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public static void showReleasePokemon(Trainers trainer) {
        JFrame frame = new JFrame("Release Pokémon");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel setup
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tabbed pane for team vs storage
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create lists
        DefaultListModel<Pokemon> teamModel = new DefaultListModel<>();
        DefaultListModel<Pokemon> storageModel = new DefaultListModel<>();

        // Populate models
        for (int i = 0; i < trainer.getLineupCount(); i++) {
            teamModel.addElement(trainer.getPokemonFromLineup(i));
        }
        for (int i = 0; i < trainer.getStorageCount(); i++) {
            storageModel.addElement(trainer.getPokemonFromStorage(i));
        }

        // Create lists with custom renderer
        JList<Pokemon> teamList = new JList<>(teamModel);
        JList<Pokemon> storageList = new JList<>(storageModel);
        teamList.setCellRenderer(new PokemonListRenderer());
        storageList.setCellRenderer(new PokemonListRenderer());

        // Add tabs
        tabbedPane.addTab("Team (" + trainer.getLineupCount() + "/6)", new JScrollPane(teamList));
        tabbedPane.addTab("Storage (" + trainer.getStorageCount() + ")", new JScrollPane(storageList));

        // Info panel
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);

        // Selection listener for both lists
        ListSelectionListener selectionListener = e -> {
            if (!e.getValueIsAdjusting()) {
                JList<Pokemon> source = (JList<Pokemon>) e.getSource();
                Pokemon selected = source.getSelectedValue();
                if (selected != null) {
                    infoArea.setText(getPokemonInfo(selected));
                }
            }
        };
        teamList.addListSelectionListener(selectionListener);
        storageList.addListSelectionListener(selectionListener);

        // Release button
        JButton releaseButton = new JButton("Release Pokémon");
        releaseButton.addActionListener(e -> {
            int tabIndex = tabbedPane.getSelectedIndex();
            JList<Pokemon> currentList = tabIndex == 0 ? teamList : storageList;

            int selectedIndex = currentList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a Pokémon first!");
                return;
            }

            Pokemon selected = currentList.getSelectedValue();
            boolean fromLineup = tabIndex == 0;

            // Confirm release
            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Release " + selected.getName() + " forever?\nThis cannot be undone!",
                    "Confirm Release",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                Pokemon released = trainer.releasePokemon(fromLineup, selectedIndex);
                if (released != null) {
                    // Update the model
                    DefaultListModel<Pokemon> model = (DefaultListModel<Pokemon>) currentList.getModel();
                    model.remove(selectedIndex);

                    // Update tab titles
                    tabbedPane.setTitleAt(0, "Team (" + trainer.getLineupCount() + "/6)");
                    tabbedPane.setTitleAt(1, "Storage (" + trainer.getStorageCount() + ")");

                    infoArea.setText(released.getName() + " was released!");
                } else {
                    JOptionPane.showMessageDialog(frame,
                            fromLineup ? "You must keep at least 1 Pokémon in your team!" : "Failed to release Pokémon!");
                }
            }
            trainer.saveToFile();
        });

        // Layout
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(infoArea), BorderLayout.SOUTH);
        mainPanel.add(releaseButton, BorderLayout.NORTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createPokemonListPanel(Trainers trainer, boolean isLineup) {
        JPanel panel = new JPanel(new BorderLayout());

        // Create list model
        DefaultListModel<Pokemon> model = new DefaultListModel<>();
        Pokemon[] pokemon = isLineup ? trainer.getPokemonLineup() : trainer.getPokemonStorage();

        for (Pokemon p : pokemon) {
            if (p != null) {
                model.addElement(p);
            }
        }

        // Create list with custom renderer
        JList<Pokemon> list = new JList<>(model);
        list.setCellRenderer(new PokemonListRenderer());

        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        // Add info label
        String labelText = isLineup ?
                "Select Pokémon from your team to release" :
                "Select Pokémon from storage to release";
        panel.add(new JLabel(labelText), BorderLayout.NORTH);

        return panel;
    }
    // Custom renderer for Pokémon lists
    static class PokemonListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Pokemon) {
                Pokemon p = (Pokemon) value;
                String type2 = p.getType2().equals("0") ? "" : "/" + p.getType2();
                setText(String.format("%s (Lv.%d) %s%s HP:%d",
                        p.getName(), p.getBaseLevel(), p.getType1(), type2, p.getHP()));

                // Color coding by type could be added here
            }

            return this;
        }
    }
    public static void showTeachMove(Trainers trainer) {
        JFrame frame = new JFrame("Teach Moves");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pokemonPanel = new JPanel(new FlowLayout());
        JLabel pokemonLabel = new JLabel("Select Pokémon:");
        JComboBox<Pokemon> pokemonCombo = new JComboBox<>();

        // Custom renderer for Pokémon names
        pokemonCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Pokemon) {
                    Pokemon p = (Pokemon) value;
                    String types = p.getType1();
                    if (!p.getType2().equals("0")) { // Assuming "0" means no second type
                        types += "/" + p.getType2();
                    }
                    setText(p.getName() + " (Lv. " + p.getBaseLevel() + " | " + types + ")");
                }
                return this;
            }
        });

        // Only add lineup Pokémon
        for (int i = 0; i < trainer.getLineupCount(); i++) {
            Pokemon p = trainer.getPokemonFromLineup(i);
            if (p != null) {
                pokemonCombo.addItem(p);
            }
        }

        JPanel moveSelectionPanel = new JPanel(new FlowLayout()); // Changed name from movePanel to be more descriptive
        JLabel moveLabel = new JLabel("Select Move:");
        JComboBox<String> moveCombo = new JComboBox<>();

        // Add a text area to display selected Pokémon's types and current moves
        JTextArea pokemonInfoArea = new JTextArea(6, 30); // Increased rows to show types + moves
        pokemonInfoArea.setEditable(false);
        pokemonInfoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pokemonInfoArea.setBorder(BorderFactory.createTitledBorder("Pokémon Info & Current Moves"));


        // Update current moves and Pokémon types when Pokémon changes
        pokemonCombo.addActionListener(e -> {
            Pokemon selected = (Pokemon) pokemonCombo.getSelectedItem();
            if (selected != null) {
                StringBuilder info = new StringBuilder();
                info.append("Name: ").append(selected.getName()).append("\n");
                info.append("Level: ").append(selected.getBaseLevel()).append("\n");
                String types = selected.getType1();
                if (!selected.getType2().equals("0")) {
                    types += "/" + selected.getType2();
                }
                info.append("Type(s): ").append(types).append("\n\n");
                info.append("Known Moves:\n");
                info.append(String.join("\n", selected.getKnownMoves()));
                pokemonInfoArea.setText(info.toString());
            } else {
                pokemonInfoArea.setText(""); // Clear if no Pokémon is selected
            }
            trainer.saveToFile();
        });

        // Populate move list with compatible moves and their types/classification
        // This listener must be AFTER the initial setup of pokemonCombo, and
        // potentially AFTER the listener that updates pokemonInfoArea,
        // so that pokemonCombo.getSelectedItem() is correctly initialized.
        pokemonCombo.addActionListener(e -> {
            moveCombo.removeAllItems();
            Pokemon selected = (Pokemon) pokemonCombo.getSelectedItem();
            if (selected != null) {
                for (Moves move : Moves.moveList) {
                    if (move != null) {
                        boolean isNormal = move.getType1().equalsIgnoreCase("Normal");
                        boolean matchType1 = move.getType1().equalsIgnoreCase(selected.getType1()) ||
                                (!selected.getType2().equals("0") && move.getType1().equalsIgnoreCase(selected.getType2()));
                        boolean matchType2 = (!move.getType2().equals("0") &&
                                (move.getType2().equalsIgnoreCase(selected.getType1()) ||
                                        (!selected.getType2().equals("0") && move.getType2().equalsIgnoreCase(selected.getType2()))));

                        if (isNormal || matchType1 || matchType2) {
                            // Display move name, type, and classification
                            String moveDisplayName = String.format("%s (%s | %s)",
                                    move.getName(),
                                    move.getType1() + (move.getType2().equals("0") ? "" : "/" + move.getType2()),
                                    move.getMachine()); // Assuming getClassification() exists
                            moveCombo.addItem(moveDisplayName);
                        }
                    }
                }
            }
            trainer.saveToFile();
        });

        JButton teachButton = new JButton("Teach Move");
        teachButton.addActionListener(e -> {
            Pokemon pokemon = (Pokemon) pokemonCombo.getSelectedItem();
            String selectedMoveDisplay = (String) moveCombo.getSelectedItem(); // Get the display string

            if (pokemon == null || selectedMoveDisplay == null) {
                JOptionPane.showMessageDialog(frame, "Please select both a Pokémon and a move!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Extract the actual move name from the display string (e.g., "Tackle (Normal | Physical)" -> "Tackle")
            String moveName = selectedMoveDisplay.split(" \\(")[0];

            Moves moveToTeach = Moves.getMoveByName(moveName);
            if (moveToTeach == null) {
                JOptionPane.showMessageDialog(frame, "Invalid move selected!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Compatibility check (this logic remains the same as it's correct)
            boolean isNormal = moveToTeach.getType1().equalsIgnoreCase("Normal");
            boolean matchType1 = moveToTeach.getType1().equalsIgnoreCase(pokemon.getType1()) ||
                    (!pokemon.getType2().equals("0") && moveToTeach.getType1().equalsIgnoreCase(pokemon.getType2()));
            boolean matchType2 = (!moveToTeach.getType2().equals("0") &&
                    (moveToTeach.getType2().equalsIgnoreCase(pokemon.getType1()) ||
                            (!pokemon.getType2().equals("0") && moveToTeach.getType2().equalsIgnoreCase(pokemon.getType2()))));

            if (!(isNormal || matchType1 || matchType2)) {
                JOptionPane.showMessageDialog(frame,
                        "Cannot teach this move!\n" +
                                pokemon.getName() + " is " + pokemon.getType1() +
                                (pokemon.getType2().equals("0") ? "" : "/" + pokemon.getType2()) +
                                " type\n" +
                                moveToTeach.getName() + " is " + moveToTeach.getType1() +
                                (moveToTeach.getType2().equals("0") ? "" : "/" + moveToTeach.getType2()) +
                                " type\n" +
                                "Pokémon can only learn moves that match their type(s) or are Normal type.",
                        "Type Mismatch", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // --- REMOVED OVERWRITE CHECK AND LOGIC ---
            // If the Pokémon already knows 4 moves, the teachMove method should handle it
            // by returning false (if no space) or indicating that an HM cannot be overwritten.

            if (pokemon.teachMove(moveName, false)) { // Always pass false for overwrite
                // Update the info area after teaching
                StringBuilder info = new StringBuilder();
                info.append("Name: ").append(pokemon.getName()).append("\n");
                info.append("Level: ").append(pokemon.getBaseLevel()).append("\n");
                String types = pokemon.getType1();
                if (!pokemon.getType2().equals("0")) {
                    types += "/" + pokemon.getType2();
                }
                info.append("Type(s): ").append(types).append("\n\n");
                info.append("Known Moves:\n");
                info.append(String.join("\n", pokemon.getKnownMoves()));
                pokemonInfoArea.setText(info.toString());

                JOptionPane.showMessageDialog(frame,
                        moveName + " was successfully taught to " + pokemon.getName() + "!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Save changes to trainer data
                trainer.saveToFile();
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Failed to teach move!\n" +
                                "Possible reasons:\n" +
                                "- Already knows this move\n" +
                                "- No space (max 4 moves) - use 'Unlearn Move' first!\n" +
                                "- Cannot overwrite HM move", // Removed overwrite not enabled
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            trainer.saveToFile();
        });

        // Layout setup
        pokemonPanel.add(pokemonLabel);
        pokemonPanel.add(pokemonCombo);

        moveSelectionPanel.add(moveLabel);
        moveSelectionPanel.add(moveCombo);
        // Removed overwriteCheck and overwriteButton

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(pokemonPanel);
        topPanel.add(moveSelectionPanel); // Use the renamed panel

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(pokemonInfoArea), BorderLayout.CENTER); // Use the new info area
        mainPanel.add(teachButton, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Manually trigger the action listener for initial display
        // after all components are added and visible
        if (pokemonCombo.getSelectedItem() != null) {
            pokemonCombo.setSelectedItem(pokemonCombo.getSelectedItem()); // This triggers the listener
        } else if (pokemonCombo.getItemCount() > 0) {
            pokemonCombo.setSelectedIndex(0); // Select first if available
        }
    }

    public static void showUnlearnMove(Trainers trainer) {
        JFrame frame = new JFrame("Unlearn Moves");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Pokémon selection - now with custom renderer for types
        JPanel pokemonPanel = new JPanel(new FlowLayout());
        JLabel pokemonLabel = new JLabel("Select Pokémon:");
        JComboBox<Pokemon> pokemonCombo = new JComboBox<>();

        // Set custom renderer to show Pokémon name, level, AND types
        pokemonCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Pokemon) {
                    Pokemon p = (Pokemon) value;
                    String types = p.getType1();
                    if (!p.getType2().equals("0")) { // Assuming "0" means no second type
                        types += "/" + p.getType2();
                    }
                    setText(p.getName() + " (Lv. " + p.getBaseLevel() + " | " + types + ")");
                }
                return this;
            }
        });

        for (int i = 0; i < trainer.getLineupCount(); i++) {
            Pokemon p = trainer.getPokemonFromLineup(i);
            if (p != null) {
                pokemonCombo.addItem(p);
            }
        }

        // Move selection
        JPanel movePanel = new JPanel(new FlowLayout());
        JLabel moveLabel = new JLabel("Select Move to Unlearn:");
        JComboBox<String> moveCombo = new JComboBox<>();

        // Current moves display - now also includes Pokémon's type info
        JTextArea pokemonInfoArea = new JTextArea(6, 30); // Increased rows for more info
        pokemonInfoArea.setEditable(false);
        pokemonInfoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        pokemonInfoArea.setBorder(BorderFactory.createTitledBorder("Pokémon Info & Known Moves"));

        // Update Pokémon info and populate unlearnable moves when Pokémon changes
        pokemonCombo.addActionListener(e -> {
            moveCombo.removeAllItems(); // Clear previous moves
            Pokemon selected = (Pokemon) pokemonCombo.getSelectedItem();
            if (selected != null) {
                // Update Pokémon info area
                StringBuilder info = new StringBuilder();
                info.append("Name: ").append(selected.getName()).append("\n");
                info.append("Level: ").append(selected.getBaseLevel()).append("\n");
                String types = selected.getType1();
                if (!selected.getType2().equals("0")) {
                    types += "/" + selected.getType2();
                }
                info.append("Type(s): ").append(types).append("\n\n");
                info.append("Known Moves:\n");
                pokemonInfoArea.setText(info.toString() + String.join("\n", selected.getKnownMoves()));


                // Populate move combo (excluding HM moves)
                for (String moveName : selected.getKnownMoves()) { // Iterate directly from known moves
                    Moves move = Moves.getMoveByName(moveName);
                    if (move != null && !move.getMachine().equalsIgnoreCase("HM")) { // Check for HM property
                        // Format: MoveName (Type | Classification)
                        String moveDisplayName = String.format("%s (%s | %s)",
                                move.getName(),
                                move.getType1() + (move.getType2().equals("0") ? "" : "/" + move.getType2()),
                                move.getMachine()); // Assuming getClassification() exists
                        moveCombo.addItem(moveDisplayName);
                    }
                }
                if (moveCombo.getItemCount() == 0 && selected.getKnownMoves().length > 0) {
                    // If a Pokemon has moves but none are unlearnable (e.g., all are HMs)
                    moveCombo.addItem("No unlearnable moves");
                    moveCombo.setEnabled(false); // Disable selection
                    // Potentially disable the unlearn button too
                } else {
                    moveCombo.setEnabled(true);
                }
            } else {
                pokemonInfoArea.setText(""); // Clear if no Pokémon is selected
                moveCombo.removeAllItems();
            }
            trainer.saveToFile();
        });

        // Unlearn button
        JButton unlearnButton = new JButton("Unlearn Move");
        unlearnButton.addActionListener(e -> {
            Pokemon pokemon = (Pokemon) pokemonCombo.getSelectedItem();
            String selectedMoveDisplay = (String) moveCombo.getSelectedItem();

            if (pokemon == null || selectedMoveDisplay == null || selectedMoveDisplay.equals("No unlearnable moves")) {
                JOptionPane.showMessageDialog(frame, "Please select a Pokémon and a valid move to unlearn!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Extract the actual move name from the display string
            String moveName = selectedMoveDisplay.split(" \\(")[0];

            // Re-check if move is an HM (redundant if combo is filtered, but good for robustness)
            Moves move = Moves.getMoveByName(moveName);
            if (move != null && move.getMachine().equalsIgnoreCase("HM")) {
                JOptionPane.showMessageDialog(frame,
                        "Cannot unlearn HM moves!\n" +
                                moveName + " is an HM move and cannot be forgotten.",
                        "HM Move Protection", JOptionPane.ERROR_MESSAGE);
                return; // Should not be reached if combo is filtered correctly
            }

            if (pokemon.unlearnMove(moveName)) {
                // Refresh both the info area and the move combo
                pokemonInfoArea.setText(""); // Clear before repopulating
                moveCombo.removeAllItems(); // Clear before repopulating

                StringBuilder info = new StringBuilder();
                info.append("Name: ").append(pokemon.getName()).append("\n");
                info.append("Level: ").append(pokemon.getBaseLevel()).append("\n");
                String types = pokemon.getType1();
                if (!pokemon.getType2().equals("0")) {
                    types += "/" + pokemon.getType2();
                }
                info.append("Type(s): ").append(types).append("\n\n");
                info.append("Known Moves:\n");
                pokemonInfoArea.setText(info.toString() + String.join("\n", pokemon.getKnownMoves()));


                // Re-populate move combo with updated list (excluding HMs)
                for (String updatedMoveName : pokemon.getKnownMoves()) {
                    Moves m = Moves.getMoveByName(updatedMoveName);
                    if (m != null && !m.getMachine().equalsIgnoreCase("HM")) {
                        String updatedMoveDisplay = String.format("%s (%s | %s)",
                                m.getName(),
                                m.getType1() + (m.getType2().equals("0") ? "" : "/" + m.getType2()),
                                m.getMachine());
                        moveCombo.addItem(updatedMoveDisplay);
                    }
                }
                if (moveCombo.getItemCount() == 0 && pokemon.getKnownMoves().length > 0) {
                    moveCombo.addItem("No unlearnable moves");
                    moveCombo.setEnabled(false);
                }


                JOptionPane.showMessageDialog(frame,
                        moveName + " was successfully unlearned!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Update trainer data in file
                trainer.saveToFile();
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Failed to unlearn move!\n" +
                                "Possible reasons:\n" +
                                "- Pokémon must keep at least 1 move (cannot have 0 moves)\n" +
                                "- Move is an HM and cannot be forgotten",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            trainer.saveToFile();
        });

        // Layout
        pokemonPanel.add(pokemonLabel);
        pokemonPanel.add(pokemonCombo);

        movePanel.add(moveLabel);
        movePanel.add(moveCombo);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(pokemonPanel);
        topPanel.add(movePanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(pokemonInfoArea), BorderLayout.CENTER);
        mainPanel.add(unlearnButton, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Manually trigger the action listener for initial display
        if (pokemonCombo.getSelectedItem() != null) {
            pokemonCombo.setSelectedItem(pokemonCombo.getSelectedItem()); // This triggers the listener
        } else if (pokemonCombo.getItemCount() > 0) {
            pokemonCombo.setSelectedIndex(0); // Select first if available
        }
    }

    private static void BuyItem(Trainers trainer) {
        JFrame buyFrame = new JFrame();
        buyFrame.setSize(1300, 700);
        buyFrame.setUndecorated(true);
        buyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon bg = new ImageIcon("buyItem.png");

        // Custom panel to paint background
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(680, 400)); // slightly smaller width
        contentPanel.setMaximumSize(new Dimension(680, 400));

        JTextArea itemsTextArea = new JTextArea(15, 60);
        itemsTextArea.setEditable(false);
        itemsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        itemsTextArea.setOpaque(false);
        itemsTextArea.setForeground(Color.BLACK);
        itemsTextArea.setLineWrap(true);
        itemsTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(itemsTextArea);
        scrollPane.setPreferredSize(new Dimension(640, 320));
        scrollPane.setMaximumSize(new Dimension(640, 320));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> buyFrame.dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(680, 40));
        buttonPanel.add(backButton);
        StringBuilder itemsText = new StringBuilder();
        // Regular items (non-evolution)
        itemsText.append("============================== NON EVOLUTION STONE ==========================\n\n");
        for (Items item : Items.itemList) {
            if (item != null && !item.getitemCategory().equals("Evolution Stone")) {
                itemsText.append("    Item ID: ").append(item.getitemID()).append("\n");
                itemsText.append("    Name: ").append(item.getitemName()).append("\n");
                itemsText.append("    Category: ").append(item.getitemCategory()).append("\n");
                itemsText.append("    Description: ").append(item.getitemDesc()).append("\n");
                itemsText.append("    Effects: ").append(item.getitemEffects()).append("\n");
                itemsText.append(String.format("    Buying Price: P %.2f\n", item.getstartBuyingPrice()));
                itemsText.append(String.format("    Selling Price: P %.2f\n", item.getsellingPrice()));
                itemsText.append("\n-----------------------------------------------------------------------------\n\n");
            }
        }

        // Evolution Stones
        itemsText.append("=============================== EVOLUTION STONES ============================\n\n");
        for (Items item : Items.itemList) {
            if (item != null && item.getitemCategory().equals("Evolution Stone")) {
                itemsText.append("    Item ID: ").append(item.getitemID()).append("\n");
                itemsText.append("    Name: ").append(item.getitemName()).append("\n");
                itemsText.append("    Category: ").append(item.getitemCategory()).append("\n");
                itemsText.append("    Description: ").append(item.getitemDesc()).append("\n");
                itemsText.append("    Effects: ").append(item.getitemEffects()).append("\n");
                itemsText.append(String.format("    Buying Price: P %.2f\n", item.getstartBuyingPrice()));
                itemsText.append(String.format("    Selling Price: P %.2f\n", item.getsellingPrice()));
                itemsText.append("\n-----------------------------------------------------------------------------\n\n");
            }
        }

        itemsTextArea.setText(itemsText.toString());

        contentPanel.add(scrollPane);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(buttonPanel);

        // --- New Quick Buy Panel with JComboBox and single Buy button ---
        // Create a Vector to hold Items objects for the JComboBox
        Vector<Items> purchasableItems = new Vector<>();
        for (Items item : Items.itemList) {
            if (item != null) {
                purchasableItems.add(item);
            }
        }
        // Declare quickBuyComboBox here so it's in scope for the ActionListener
        JComboBox<Items> quickBuyComboBox = new JComboBox<>(purchasableItems);

        // Custom renderer to display item name and price in the JComboBox
        quickBuyComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Items) {
                    Items item = (Items) value;
                    setText(item.getitemName() + " (P" + String.format("%.2f", item.getstartBuyingPrice()) + ")");
                } else if (value == null && index == -1) {
                    setText("Select an item...");
                }
                return this;
            }
        });
        quickBuyComboBox.setSelectedIndex(-1); // No item selected initially
        quickBuyComboBox.setPreferredSize(new Dimension(200, 25)); // Set a reasonable size for the combo box

        JButton buySelectedButton = new JButton("Buy Selected");
        // Assign the consolidated action listener
        // The quickBuyComboBox is now in scope here
        buySelectedButton.addActionListener(e -> {
            Items itemToBuy = (Items) quickBuyComboBox.getSelectedItem();

            if (itemToBuy == null) {
                JOptionPane.showMessageDialog(buyFrame, "Please select an item to buy.", "No Item Selected", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (trainer.getItemCount() >= 50 ||
                    (trainer.isItemUnique(itemToBuy) && !trainer.hasItem(itemToBuy) && trainer.getUniqueCount() >= 10)) { // Changed == to >= for safety
                JOptionPane.showMessageDialog(buyFrame,
                        "You cannot buy more items. You've reached one (or both) of the following limits:\n"
                                + "Items in bag (max 50): " + trainer.getItemCount() + "\n"
                                + "Unique items (max 10): " + trainer.getUniqueCount() + "\n"
                                + "Try modifying your bag to make space.",
                        "Limit Reached", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double price = itemToBuy.getstartBuyingPrice();
            if (trainer.getMoney() < price) {
                JOptionPane.showMessageDialog(buyFrame,
                        "Insufficient funds.\n"
                                + "Your Money: P" + trainer.getMoney() + "\n"
                                + "Item Price: P" + price,
                        "Not Enough Money", JOptionPane.WARNING_MESSAGE);
            } else {
                String result = trainer.addItemToBag(itemToBuy);
                trainer.setMoney(trainer.getMoney() - price);
                // Call the method to update the trainer's data in the file
                updateTrainerInFile(trainer); // <--- ADD THIS LINE HERE
                JOptionPane.showMessageDialog(buyFrame,
                        result + "\nRemaining Money: P" + String.format("%.2f", trainer.getMoney()),
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Update trainer status display
                moneyLabel.setText("Available PokeDollars: P" + trainer.getMoney());
                itemCountLabel.setText("Items in bag (max 50): " + trainer.getItemCount());
                uniqueCountLabel.setText("Unique items in bag (max 10): " + trainer.getUniqueCount());
            }
            trainer.saveToFile();
        });


        JPanel quickBuyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // FlowLayout for combo and button side-by-side
        quickBuyPanel.setOpaque(false);
        quickBuyPanel.add(quickBuyComboBox);
        quickBuyPanel.add(buySelectedButton);


        // --- Add contentPanel directly to backgroundPanel ---
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0; // Place it in the first column
        gbcContent.gridy = 0; // Place it in the first row
        gbcContent.insets = new Insets(230, 0, 0, 450); // Original position for contentPanel
        gbcContent.anchor = GridBagConstraints.NORTHWEST; // Anchor to top-left of its grid cell
        backgroundPanel.add(contentPanel, gbcContent);

        // Trainer Status Panel (on the right) - NOW INDEPENDENTLY POSITIONED
        moneyLabel = new JLabel("Available PokeDollars: P" + String.format("%.2f", trainer.getMoney()));
        itemCountLabel = new JLabel("Items in bag (max 50): " + trainer.getItemCount());
        uniqueCountLabel = new JLabel("Unique items in bag (max 10): " + trainer.getUniqueCount());

        moneyLabel.setForeground(Color.RED);
        itemCountLabel.setForeground(Color.RED);
        uniqueCountLabel.setForeground(Color.RED);

        moneyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        itemCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        uniqueCountLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setOpaque(false);
        statusPanel.setBorder(null);
        statusPanel.add(moneyLabel);
        statusPanel.add(Box.createVerticalStrut(10));
        statusPanel.add(itemCountLabel);
        statusPanel.add(Box.createVerticalStrut(10));
        statusPanel.add(uniqueCountLabel);

        // --- Position statusPanel independently using its own GridBagConstraints ---
        GridBagConstraints gbcStatus = new GridBagConstraints();
        gbcStatus.gridx = 0; // Keep it in the same "column" (gridx) as contentPanel
        gbcStatus.gridy = 0; // Keep it in the same "row" (gridy) as contentPanel
        // Adjust these insets to move it higher and to the right.
        gbcStatus.insets = new Insets(240, 700, 0, 0);
        gbcStatus.anchor = GridBagConstraints.NORTHWEST;
        backgroundPanel.add(statusPanel, gbcStatus);

        // --- Position Quick Buy Panel below statusPanel ---
        GridBagConstraints gbcQuickBuy = new GridBagConstraints();
        gbcQuickBuy.gridx = 0;
        gbcQuickBuy.gridy = 0;
        gbcQuickBuy.insets = new Insets(350, 690, 0, 0);
        gbcQuickBuy.anchor = GridBagConstraints.NORTHWEST;
        backgroundPanel.add(quickBuyPanel, gbcQuickBuy); // Add the panel, not just the combo box

        buyFrame.setContentPane(backgroundPanel);
        buyFrame.setLocationRelativeTo(null);
        buyFrame.setVisible(true);
    }

    private static void updateTrainerInFile(Trainers updatedTrainer) {
        List<String> fileLines = new ArrayList<>();
        boolean trainerFoundInFile = false; // Flag to track if the trainer was found and updated

        try (BufferedReader reader = new BufferedReader(new FileReader("trainers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Attempt to split the line by the first hyphen to quickly get the ID.
                // This is safer than splitting by all hyphens if item names could contain them.
                String[] parts = line.split("-", 2);

                // Ensure the line has at least an ID part and trim it for comparison
                String lineId = (parts.length > 0) ? parts[0].trim() : "";

                // Check if the ID from the file line matches the ID of the trainer we want to update
                if (!lineId.isEmpty() && lineId.equals(updatedTrainer.getID())) {
                    // If IDs match, parse the full line into a temporary Trainer object
                    Trainers tempTrainer = Trainers.fromFileString(line);

                    // Now, check if the parsed trainer is valid AND its name matches.
                    // This combined check ensures we're updating the correct unique trainer.
                    if (tempTrainer != null && tempTrainer.getName().equals(updatedTrainer.getName())) {
                        // This is our trainer! Add the UPDATED string to the list.
                        fileLines.add(updatedTrainer.toFileString());
                        trainerFoundInFile = true; // Mark that we found and updated this trainer
                    } else {
                        // ID matched but name didn't, or parsing failed for some reason.
                        // Keep the original line, as it's not the exact trainer we're looking for.
                        fileLines.add(line);
                    }
                } else {
                    // The ID didn't match, so this line belongs to a different trainer. Keep it as is.
                    fileLines.add(line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading trainer file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return; // Exit if there's a read error
        }

        // After attempting to read the entire file and find/replace the trainer:
        // If 'trainerFoundInFile' is still false, it means the trainer was not in the file at all.
        // This happens for newly created trainers. In this case, add their data as a new line.
        if (!trainerFoundInFile) {
            fileLines.add(updatedTrainer.toFileString());
        }

        // Now, write all the lines (including the one updated trainer, and all other original trainers)
        // back to the file, completely overwriting its previous content.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("trainers.txt"))) {
            for (String line : fileLines) {
                writer.write(line);
                writer.newLine(); // Add a new line character after each entry
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to trainer file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static void sellItem(Trainers trainer){
        JFrame buyFrame = new JFrame();
        buyFrame.setSize(1300, 700);
        buyFrame.setUndecorated(true);
        buyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon bg = new ImageIcon("sellItem.png");

        // Custom panel to paint background
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setOpaque(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setPreferredSize(new Dimension(680, 400)); // slightly smaller width
        contentPanel.setMaximumSize(new Dimension(680, 400));

        JTextArea itemsTextArea = new JTextArea(15, 60);
        itemsTextArea.setEditable(false);
        itemsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        itemsTextArea.setOpaque(false);
        itemsTextArea.setForeground(Color.BLACK);
        itemsTextArea.setLineWrap(true);
        itemsTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(itemsTextArea);
        scrollPane.setPreferredSize(new Dimension(640, 320));
        scrollPane.setMaximumSize(new Dimension(640, 320));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> buyFrame.dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(680, 40));
        buttonPanel.add(backButton);
        StringBuilder itemsText = new StringBuilder();
        itemsText.append("================================== ITEMS IN BAG ============================\n\n\n");
        itemsText.append("============================== NON-EVOLUTION ITEMS ==========================\n\n");

        Items[] bag = trainer.getBag();
        int count = trainer.getItemCount();
        boolean hasNonEvo = false;
        boolean hasEvo = false;

// First, display NON-EVOLUTION ITEMS
        for (int i = 0; i < count; i++) {
            Items item = bag[i];
            if (item != null && !item.getitemCategory().equalsIgnoreCase("Evolution Stone")) {
                hasNonEvo = true;
                itemsText.append("    Item ID: ").append(item.getitemID()).append("\n");
                itemsText.append("    Name: ").append(item.getitemName()).append("\n");
                itemsText.append("    Category: ").append(item.getitemCategory()).append("\n");
                itemsText.append("    Description: ").append(item.getitemDesc()).append("\n");
                itemsText.append("    Effects: ").append(item.getitemEffects()).append("\n");
                itemsText.append(String.format("    Selling Price: P %.2f\n", item.getsellingPrice()));
                itemsText.append("\n-----------------------------------------------------------------------------\n\n");
            }
        }
        if (!hasNonEvo) {
            itemsText.append("You have no non-evolution items in your bag.\n\n");
        }

        itemsText.append("============================== EVOLUTION STONES ============================\n\n");
        // Then, display EVOLUTION STONES
        for (int i = 0; i < count; i++) {
            Items item = bag[i];
            if (item != null && item.getitemCategory().equalsIgnoreCase("Evolution Stone")) {
                hasEvo = true;
                itemsText.append("    Item ID: ").append(item.getitemID()).append("\n");
                itemsText.append("    Name: ").append(item.getitemName()).append("\n");
                itemsText.append("    Category: ").append(item.getitemCategory()).append("\n");
                itemsText.append("    Description: ").append(item.getitemDesc()).append("\n");
                itemsText.append("    Effects: ").append(item.getitemEffects()).append("\n");
                itemsText.append(String.format("    Selling Price: P %.2f\n", item.getsellingPrice()));
                itemsText.append("\n-----------------------------------------------------------------------------\n\n");
            }
        }

        if (!hasEvo) {
            itemsText.append("You have no evolution stones in your bag.\n\n");
        }

        itemsTextArea.setText(itemsText.toString());

        contentPanel.add(scrollPane);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(buttonPanel);
        // --- Add contentPanel directly to backgroundPanel ---
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0; // Place it in the first column
        gbcContent.gridy = 0; // Place it in the first row
        gbcContent.insets = new Insets(230, 0, 0, 450); // Original position for contentPanel
        gbcContent.anchor = GridBagConstraints.NORTHWEST; // Anchor to top-left of its grid cell
        backgroundPanel.add(contentPanel, gbcContent);

        // ========== SELL ITEM COMBO + BUTTON ==========

// Build a Vector of items in trainer's bag for the JComboBox
        Vector<Items> itemsInBag = new Vector<>();
        for (int i = 0; i < trainer.getItemCount(); i++) {
            Items item = trainer.getBag()[i];
            if (item != null) itemsInBag.add(item);
        }

        JComboBox<Items> sellComboBox = new JComboBox<>(itemsInBag);
        sellComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Items) {
                    Items item = (Items) value;
                    setText(item.getitemName() + " (P" + String.format("%.2f", item.getsellingPrice()) + ")");
                } else if (value == null && index == -1) {
                    setText("Select item...");
                }
                return this;
            }
        });
        sellComboBox.setSelectedIndex(-1);
        sellComboBox.setPreferredSize(new Dimension(200, 25));

        JButton sellButton = new JButton("Sell Selected");
        sellButton.addActionListener(e -> {
            Items selected = (Items) sellComboBox.getSelectedItem();
            if (selected == null) {
                JOptionPane.showMessageDialog(buyFrame, "Please select an item to sell.", "No Item Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double sellPrice = selected.getsellingPrice();
            trainer.setMoney(trainer.getMoney() + sellPrice);
            trainer.removeItemFromBag(selected); // You must implement this method in your Trainers class
            updateTrainerInFile(trainer); // Save updated trainer info

            JOptionPane.showMessageDialog(buyFrame,
                    selected.getitemName() + " sold for P" + String.format("%.2f", sellPrice) + ".\nNew Balance: P" + String.format("%.2f", trainer.getMoney()),
                    "Item Sold", JOptionPane.INFORMATION_MESSAGE);

            buyFrame.dispose();
            sellItem(trainer); // Refresh the GUI to reflect the updated bag
            trainer.saveToFile();
        });

// ========== TRAINER STATUS PANEL ==========
        JLabel moneyLabel = new JLabel("Available PokeDollars: P" + String.format("%.2f", trainer.getMoney()));
        JLabel itemCountLabel = new JLabel("Items in bag (max 50): " + trainer.getItemCount());
        JLabel uniqueCountLabel = new JLabel("Unique items in bag (max 10): " + trainer.getUniqueCount());

        moneyLabel.setForeground(Color.RED);
        itemCountLabel.setForeground(Color.RED);
        uniqueCountLabel.setForeground(Color.RED);

        moneyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        itemCountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        uniqueCountLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setOpaque(false);
        statusPanel.setBorder(null);
        statusPanel.add(moneyLabel);
        statusPanel.add(Box.createVerticalStrut(10));
        statusPanel.add(itemCountLabel);
        statusPanel.add(Box.createVerticalStrut(10));
        statusPanel.add(uniqueCountLabel);

// Position status panel on the right
        GridBagConstraints gbcStatus = new GridBagConstraints();
        gbcStatus.gridx = 0;
        gbcStatus.gridy = 0;
        gbcStatus.insets = new Insets(240, 700, 0, 0); // Adjust as needed
        gbcStatus.anchor = GridBagConstraints.NORTHWEST;
        backgroundPanel.add(statusPanel, gbcStatus);
// Put combo + button in a horizontal panel
        JPanel sellPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        sellPanel.setOpaque(false);
        sellPanel.add(sellComboBox);
        sellPanel.add(sellButton);

// Position panel on right side
        GridBagConstraints gbcSellPanel = new GridBagConstraints();
        gbcSellPanel.gridx = 0;
        gbcSellPanel.gridy = 0;
        gbcSellPanel.insets = new Insets(350, 690, 0, 0);  // Adjust position
        gbcSellPanel.anchor = GridBagConstraints.NORTHWEST;
        backgroundPanel.add(sellPanel, gbcSellPanel);

        buyFrame.setContentPane(backgroundPanel);
        buyFrame.setVisible(true);
    }

    public static void useItem(Trainers trainer) {
        JFrame useFrame = new JFrame("Use Item");
        useFrame.setSize(800, 600);
        useFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create tabbed pane for Pokémon selection (team vs storage)
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel for team Pokémon
        JPanel teamPanel = new JPanel(new BorderLayout());
        DefaultListModel<Pokemon> teamModel = new DefaultListModel<>();
        for (int i = 0; i < trainer.getLineupCount(); i++) {
            teamModel.addElement(trainer.getPokemonFromLineup(i));
        }
        JList<Pokemon> teamList = new JList<>(teamModel);
        teamList.setCellRenderer(new PokemonListRenderer());
        teamPanel.add(new JScrollPane(teamList), BorderLayout.CENTER);
        teamPanel.add(new JLabel("Team Pokémon"), BorderLayout.NORTH);

        // Panel for storage Pokémon
        JPanel storagePanel = new JPanel(new BorderLayout());
        DefaultListModel<Pokemon> storageModel = new DefaultListModel<>();
        for (int i = 0; i < trainer.getStorageCount(); i++) {
            storageModel.addElement(trainer.getPokemonFromStorage(i));
        }
        JList<Pokemon> storageList = new JList<>(storageModel);
        storageList.setCellRenderer(new PokemonListRenderer());
        storagePanel.add(new JScrollPane(storageList), BorderLayout.CENTER);
        storagePanel.add(new JLabel("Storage Pokémon"), BorderLayout.NORTH);

        tabbedPane.addTab("Team", teamPanel);
        tabbedPane.addTab("Storage", storagePanel);

        // Item selection panel
        JPanel itemPanel = new JPanel(new BorderLayout());
        DefaultListModel<Items> itemModel = new DefaultListModel<>();
        for (Items item : trainer.getBag()) {
            if (item != null) {
                itemModel.addElement(item);
            }
        }
        JList<Items> itemList = new JList<>(itemModel);
        itemList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Items) {
                    Items item = (Items) value;
                    setText(item.getitemName() + " (x" + item.getQuantity() + ")");
                }
                return this;
            }
        });
        itemPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);
        itemPanel.add(new JLabel("Your Items"), BorderLayout.NORTH);

        // Info display area
        JTextArea infoArea = new JTextArea(8, 30);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);

        // Selection listeners
        ListSelectionListener pokemonListener = e -> {
            if (!e.getValueIsAdjusting()) {
                JList<Pokemon> source = (JList<Pokemon>) e.getSource();
                Pokemon selected = source.getSelectedValue();
                if (selected != null) {
                    infoArea.setText(getPokemonInfo(selected));
                }
            }
        };

        ListSelectionListener itemListener = e -> {
            if (!e.getValueIsAdjusting()) {
                Items selected = itemList.getSelectedValue();
                if (selected != null) {
                    infoArea.setText("Item: " + selected.getitemName() + "\n" +
                            "Category: " + selected.getitemCategory() + "\n" +
                            "Effect: " + selected.getitemEffects() + "\n\n" +
                            "Select a Pokémon to use this item on.");
                }
            }
        };

        teamList.addListSelectionListener(pokemonListener);
        storageList.addListSelectionListener(pokemonListener);
        itemList.addListSelectionListener(itemListener);

        // Use button
        JButton useButton = new JButton("Use Item");
        useButton.addActionListener(e -> {
            Items selectedItem = itemList.getSelectedValue();
            Pokemon selectedPokemon = null;

            // Get selected Pokémon based on active tab
            if (tabbedPane.getSelectedIndex() == 0) { // Team tab
                selectedPokemon = teamList.getSelectedValue();
            } else { // Storage tab
                selectedPokemon = storageList.getSelectedValue();
            }

            if (selectedItem == null || selectedPokemon == null) {
                JOptionPane.showMessageDialog(useFrame,
                        "Please select both an item and a Pokémon!",
                        "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Check if item can be used on this Pokémon
            if (selectedItem.getitemCategory().equals("Evolution Stone")) {
                // Check if Pokémon can evolve with this stone
                if (!selectedPokemon.canEvolveWith(selectedItem.getitemName())) {
                    JOptionPane.showMessageDialog(useFrame,
                            selectedPokemon.getName() + " cannot evolve with " + selectedItem.getitemName(),
                            "Invalid Use", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Use evolution stone
                int response = JOptionPane.showConfirmDialog(useFrame,
                        "Use " + selectedItem.getitemName() + " on " + selectedPokemon.getName() +
                                " to evolve it into " + selectedPokemon.getEvolutionName() + "?",
                        "Confirm Evolution", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    String result = trainer.useItem(selectedItem, selectedPokemon);
                    JOptionPane.showMessageDialog(useFrame, result);

                    // Update UI if evolution was successful
                    if (result.contains("evolved")) {
                        // Show evolution animation
                        showEvolutionAnimation(useFrame, selectedPokemon.getName(), selectedPokemon.getEvolutionName());

                        // Update Pokémon in the list
                        if (tabbedPane.getSelectedIndex() == 0) { // Team
                            int index = teamList.getSelectedIndex();
                            teamModel.set(index, selectedPokemon);
                        } else { // Storage
                            int index = storageList.getSelectedIndex();
                            storageModel.set(index, selectedPokemon);
                        }

                        // Update item list (quantity decreased)
                        itemModel.removeElement(selectedItem);
                        if (selectedItem.getQuantity() > 1) {
                            selectedItem.setQuantity(selectedItem.getQuantity() - 1);
                            itemModel.addElement(selectedItem);
                        }

                        // Save trainer data
                        trainer.saveToFile();
                    }
                }
            } else if (selectedItem.getitemName().equals("Rare Candy")) {
                // Use Rare Candy
                int response = JOptionPane.showConfirmDialog(useFrame,
                        "Use Rare Candy on " + selectedPokemon.getName() + " to level it up?",
                        "Confirm Level Up", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    String result = trainer.useItem(selectedItem, selectedPokemon);

                    // Check if the Pokémon evolved from level up
                    if (result.contains("evolved")) {
                        // Show evolution animation
                        showEvolutionAnimation(useFrame, selectedPokemon.getName(), selectedPokemon.getEvolutionName());
                    }

                    JOptionPane.showMessageDialog(useFrame, result);

                    // Update item list (quantity decreased)
                    itemModel.removeElement(selectedItem);
                    if (selectedItem.getQuantity() > 1) {
                        selectedItem.setQuantity(selectedItem.getQuantity() - 1);
                        itemModel.addElement(selectedItem);
                    } else {
                        // Remove from bag if last one was used
                        trainer.removeItemFromBag(selectedItem);
                    }

                    // Update Pokémon in the list
                    if (tabbedPane.getSelectedIndex() == 0) { // Team
                        int index = teamList.getSelectedIndex();
                        teamModel.set(index, selectedPokemon);
                    } else { // Storage
                        int index = storageList.getSelectedIndex();
                        storageModel.set(index, selectedPokemon);
                    }

                    // Save trainer data
                    trainer.saveToFile();
                }
            } else {
                // Use regular item (assume it's consumed immediately)
                String result = trainer.useItem(selectedItem, selectedPokemon);

                // Always replace held item with the used one
                String heldResult = selectedPokemon.giveHeldItem(selectedItem.getitemName());

                JOptionPane.showMessageDialog(useFrame, result + "\n" + heldResult);

                JOptionPane.showMessageDialog(useFrame, result);

                // Update item list (quantity decreased)
                itemModel.removeElement(selectedItem);
                if (selectedItem.getQuantity() > 1) {
                    selectedItem.setQuantity(selectedItem.getQuantity() - 1);
                    itemModel.addElement(selectedItem);
                } else {
                    // Remove from bag if last one was used
                    trainer.removeItemFromBag(selectedItem);
                }

                // Save trainer data
                trainer.saveToFile();
            }
        });

        // Layout
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(tabbedPane, BorderLayout.CENTER);
        leftPanel.add(useButton, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(itemPanel, BorderLayout.CENTER);
        rightPanel.add(new JScrollPane(infoArea), BorderLayout.SOUTH);

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        useFrame.add(mainPanel);
        useFrame.setLocationRelativeTo(null);
        useFrame.setVisible(true);
    }

    // Helper method to show evolution animation
    private static void showEvolutionAnimation(JFrame parent, String oldName, String newName) {
        JDialog evolutionDialog = new JDialog(parent, "Evolution!", true);
        evolutionDialog.setSize(400, 300);
        evolutionDialog.setLayout(new BorderLayout());
        evolutionDialog.setLocationRelativeTo(parent);

        JLabel animationLabel = new JLabel("", SwingConstants.CENTER);
        animationLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(animationLabel, BorderLayout.CENTER);

        evolutionDialog.add(contentPanel, BorderLayout.CENTER);

        // Animation thread
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    SwingUtilities.invokeLater(() -> {
                        animationLabel.setText(oldName + " is evolving!");
                        animationLabel.setForeground(Color.BLUE);
                    });
                    Thread.sleep(500);

                    SwingUtilities.invokeLater(() -> {
                        animationLabel.setText("...");
                        animationLabel.setForeground(Color.RED);
                    });
                    Thread.sleep(500);
                }

                SwingUtilities.invokeLater(() -> {
                    animationLabel.setText("Congratulations!");
                    animationLabel.setForeground(Color.GREEN);

                    JLabel resultLabel = new JLabel(oldName + " evolved into " + newName + "!", SwingConstants.CENTER);
                    resultLabel.setFont(new Font("Arial", Font.BOLD, 18));

                    JButton closeButton = new JButton("OK");
                    closeButton.addActionListener(e -> evolutionDialog.dispose());

                    JPanel bottomPanel = new JPanel(new BorderLayout());
                    bottomPanel.add(resultLabel, BorderLayout.CENTER);
                    bottomPanel.add(closeButton, BorderLayout.SOUTH);
                    bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

                    contentPanel.add(bottomPanel, BorderLayout.SOUTH);
                    contentPanel.revalidate();
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        evolutionDialog.setVisible(true);
    }
}