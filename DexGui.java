import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class DexGui {

    public static Pokemon tempPokemon = new Pokemon();
    public static Pokemon[] pokemon = new Pokemon[300];
    public static int pokemonCount = 0;

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
                        break;
                    case "SEARCH POKEMON":
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
        JComboBox<String> machineCombo = new JComboBox<>(new String[]{
                "HM","TM"
        });

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
            machineCombo.setSelectedIndex(0);
            type1Combo.setSelectedIndex(0);
            type2Combo.setSelectedIndex(0);
            String data = ("\n" + name + "-" + desc + "-" + machine + "-"+ type1 +"-" + type2 + "-");

            try
            {
                FileWriter writer = new FileWriter("moves.txt",true);
                writer.append(data);
                writer.close();
            }
            catch (IOException f)
            {
                f.printStackTrace();
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
        String[] labels = {"ADD ITEM", "VIEW ITEMS", "SEARCH ITEM", "MAIN-MENU"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(220, 50), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "ADD ITEM":
                        AddItem();
                        break;
                    case "VIEW ITEMS":
                        ViewItems();
                        break;
                    case "SEARCH ITEM":
                        SearchItem();
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
        gbc.insets = new Insets(100, 0, 0, 0);
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

        // Create a text area to display items
        JTextArea itemsTextArea = new JTextArea();
        itemsTextArea.setEditable(false);
        itemsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(itemsTextArea);

        // Button to go back
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> viewFrame.dispose());

        // Display all items
        StringBuilder itemsText = new StringBuilder();
        for (Items item : Items.itemList) {
            if (item != null) {
                itemsText.append("Item ID: ").append(item.getitemID()).append("\n");
                itemsText.append("Name: ").append(item.getitemName()).append("\n");
                itemsText.append("Category: ").append(item.getitemCategory()).append("\n");
                itemsText.append("Description: ").append(item.getitemDesc()).append("\n");
                itemsText.append("Effects: ").append(item.getitemEffects()).append("\n");

                if (item.getToSold()) {
                    if (!item.getForEvo()) {
                        itemsText.append(String.format("Buying Price: P %.2f\n", item.getstartBuyingPrice()));
                    } else {
                        itemsText.append(String.format("Buying Price: P %.2f - P %.2f\n",
                                item.getstartBuyingPrice(), item.getendBuyingPrice()));
                    }
                } else {
                    itemsText.append("Buying Price: Not Sold\n");
                }

                itemsText.append(String.format("Selling Price: P %.2f\n", item.getsellingPrice()));
                itemsText.append("\n----------------------------------------\n\n");
            }
        }

        if (Items.getitemCount == 0) {
            itemsText.append("No items found in the database.");
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
        JLabel searchLabel = new JLabel("Enter Item Name:");
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
            String itemName = searchField.getText().trim();
            if (itemName.isEmpty()) {
                resultArea.setText("Please enter an item name to search.");
                return;
            }

            Items foundItem = Items.getItemByName(itemName);
            if (foundItem != null) {
                StringBuilder itemInfo = new StringBuilder();
                itemInfo.append("Item ID: ").append(foundItem.getitemID()).append("\n");
                itemInfo.append("Name: ").append(foundItem.getitemName()).append("\n");
                itemInfo.append("Category: ").append(foundItem.getitemCategory()).append("\n");
                itemInfo.append("Description: ").append(foundItem.getitemDesc()).append("\n");
                itemInfo.append("Effects: ").append(foundItem.getitemEffects()).append("\n");

                if (foundItem.getToSold()) {
                    if (!foundItem.getForEvo()) {
                        itemInfo.append(String.format("Buying Price: P %.2f\n", foundItem.getstartBuyingPrice()));
                    } else {
                        itemInfo.append(String.format("Buying Price: P %.2f - P %.2f\n",
                                foundItem.getstartBuyingPrice(), foundItem.getendBuyingPrice()));
                    }
                } else {
                    itemInfo.append("Buying Price: Not Sold\n");
                }

                itemInfo.append(String.format("Selling Price: P %.2f\n", foundItem.getsellingPrice()));

                resultArea.setText(itemInfo.toString());
            } else {
                resultArea.setText("Item not found: " + itemName);
            }
        });

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        searchFrame.add(mainPanel);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
    }

    public static void AddItem() {
        JFrame addFrame = new JFrame("Add New Item");
        addFrame.setSize(900, 700);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2, 10, 10));

        // Form fields
        JLabel idLabel = new JLabel("Item ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();

        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(3, 20);
        JScrollPane descScroll = new JScrollPane(descArea);

        JLabel effectsLabel = new JLabel("Effects:");
        JTextArea effectsArea = new JTextArea(3, 20);
        JScrollPane effectsScroll = new JScrollPane(effectsArea);

        JLabel canSellLabel = new JLabel("Can be sold?");
        JCheckBox canSellCheckbox = new JCheckBox();

        JLabel forEvoLabel = new JLabel("For Evolution?");
        JCheckBox forEvoCheckbox = new JCheckBox();

        JLabel startPriceLabel = new JLabel("Start Buying Price:");
        JTextField startPriceField = new JTextField();

        JLabel endPriceLabel = new JLabel("End Buying Price:");
        JTextField endPriceField = new JTextField();

        JLabel sellPriceLabel = new JLabel("Selling Price:");
        JTextField sellPriceField = new JTextField();

        // Add components to form panel
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(categoryLabel);
        formPanel.add(categoryField);
        formPanel.add(descLabel);
        formPanel.add(descScroll);
        formPanel.add(effectsLabel);
        formPanel.add(effectsScroll);
        formPanel.add(canSellLabel);
        formPanel.add(canSellCheckbox);
        formPanel.add(forEvoLabel);
        formPanel.add(forEvoCheckbox);
        formPanel.add(startPriceLabel);
        formPanel.add(startPriceField);
        formPanel.add(endPriceLabel);
        formPanel.add(endPriceField);
        formPanel.add(sellPriceLabel);
        formPanel.add(sellPriceField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add Item");
        JButton backButton = new JButton("Back");

        // Result area
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScroll = new JScrollPane(resultArea);

        // Add action listeners
        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            String desc = descArea.getText().trim();
            String effects = effectsArea.getText().trim();
            boolean canSell = canSellCheckbox.isSelected();
            boolean forEvo = forEvoCheckbox.isSelected();

            double startPrice = 0;
            double endPrice = 0;
            double sellPrice = 0;

            try {
                if (canSell) {
                    startPrice = Double.parseDouble(startPriceField.getText().trim());
                    if (forEvo) {
                        endPrice = Double.parseDouble(endPriceField.getText().trim());
                    }
                }
                sellPrice = Double.parseDouble(sellPriceField.getText().trim());
            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter valid prices (numbers only).");
                return;
            }

            if (id.isEmpty() || name.isEmpty() || category.isEmpty() || desc.isEmpty() || effects.isEmpty()) {
                resultArea.setText("Please fill in all required fields.");
                return;
            }

            if (canSell && startPrice <= 0) {
                resultArea.setText("Start buying price must be greater than 0.");
                return;
            }

            if (forEvo && endPrice <= startPrice) {
                resultArea.setText("End buying price must be greater than start price.");
                return;
            }

            if (sellPrice <= 0) {
                resultArea.setText("Selling price must be greater than 0.");
                return;
            }

            // Check if item already exists
            if (Items.getItemByName(name) != null) {
                resultArea.setText("Error: An item with this name already exists.");
                return;
            }

            // Add the item
            new Items(id, name, category, desc, effects, canSell, forEvo, startPrice, endPrice, sellPrice);

            resultArea.setText("Item added successfully!\n\n" +
                    "ID: " + id + "\n" +
                    "Name: " + name + "\n" +
                    "Category: " + category + "\n" +
                    "Description: " + desc + "\n" +
                    "Effects: " + effects + "\n" +
                    "Can be sold: " + (canSell ? "Yes" : "No") + "\n" +
                    "For Evolution: " + (forEvo ? "Yes" : "No") + "\n" +
                    (canSell ? String.format("Buying Price: P %.2f", startPrice) +
                            (forEvo ? String.format(" - P %.2f", endPrice) : "") + "\n" : "") +
                    String.format("Selling Price: P %.2f", sellPrice));

            // Save to file
            try {
                FileWriter writer = new FileWriter("items.txt", true);
                writer.append("\n" + id + "-" + name + "-" + category + "-" + desc + "-" +
                        effects + "-" + canSell + "-" + forEvo + "-" + startPrice + "-" +
                        endPrice + "-" + sellPrice);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Clear fields for next entry
            idField.setText("");
            nameField.setText("");
            categoryField.setText("");
            descArea.setText("");
            effectsArea.setText("");
            canSellCheckbox.setSelected(false);
            forEvoCheckbox.setSelected(false);
            startPriceField.setText("");
            endPriceField.setText("");
            sellPriceField.setText("");
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
        pokemon[pokemonCount] = new Pokemon(tempPokemon.getPokedexNo(),tempPokemon.getName(),tempPokemon.getType1(),
                tempPokemon.getType2(),1,tempPokemon.getFrom(),
                tempPokemon.getTo(),tempPokemon.getEvoLevel(),tempPokemon.getHP(),tempPokemon.getAtk(),
                tempPokemon.getDef(),tempPokemon.getSpd()); // Create a new Pokemon instance
        pokemon[pokemonCount].teachMove("Tackle",false);
        pokemon[pokemonCount].teachMove("Defend",false);
        saveToFile(pokemon[pokemonCount]);
        pokemonCount++; // Increment the count after adding a new Pokemon

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
                    promptLabel2.setVisible(true);
                    backButton.setVisible(true); // Show back button
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

}