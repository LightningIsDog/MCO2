import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

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
        String[] labels = {"ADD MOVE", "VIEW MOVE", "SEARCH MOVE", "MAIN-MENU"};

        for (String label : labels) {
            ButtonBg subButton = new ButtonBg(label, new Dimension(220, 50), Color.RED);
            final String currentLabel = label;

            subButton.addActionListener(e -> {
                switch (currentLabel) {
                    case "ADD MOVE":
                        break;
                    case "VIEW MOVE":
                        break;
                    case "SEARCH MOVE":
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
