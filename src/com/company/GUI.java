package com.company;
import org.jdom2.Document;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This class is the Graphical user interface of the program
 */
public class GUI extends JFrame {
    private JFrame f;
    private JPanel panel = new JPanel();
    private JTextField nameInput;
    private JTextField emailInput;
    private JTextField passInput;
    private JTextField homePageInput;
    private JTextField messageInput;
    private JTextField subjectInput;
    private JButton sendButton;
    JTextPane messageText;
    JScrollPane jScrollPane;

    private JTextArea message;
    private XMLHandler xmlHandler;
    private ChatHandler chatHandler;

    /**
     * Constructor that builds the Graphical user interface and sets default values to the components in the GUI
     * @throws SQLException
     */
    GUI() throws IOException {
        //Input
        JLabel nameText = new JLabel("Name:");
        panel.add(nameText);
        nameInput = new JTextField("smtp.gmail.com");
        panel.add(nameInput);
        JLabel emailText = new JLabel("E-mail:");
        panel.add(emailText);
        emailInput = new JTextField("");
        panel.add(emailInput);
        JLabel homePageText = new JLabel("HomePage:");
        panel.add(homePageText);
        homePageInput = new JTextField("");
        panel.add(homePageInput);

        JLabel messageText = new JLabel("Message:");
        panel.add(messageText);
        messageInput = new JTextField("");

        panel.add(messageInput);

        JLabel subjectText = new JLabel("Subject:");
        panel.add(subjectText);
        subjectInput = new JTextField("Hello subject");
        panel.add("North",subjectInput);
        this.xmlHandler = new XMLHandler();
        this.chatHandler = new ChatHandler();





        sendButton = new JButton("Send");
        this.getContentPane().add("North", sendButton);
        /**
         * Add actionListener to Button to trigger method "sendButtonClicked()"
         */
        sendButton.addActionListener(e -> {
            Document document = this.xmlHandler.createXMLDoc(homePageInput.getText(), nameInput.getText(), emailInput.getText(), messageInput.getText());
            String message = this.xmlHandler.covertXMLToString(document);
            try {
                this.chatHandler.sendMessage(message);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        });

        //Output

        message = new JTextArea();
        panel.add(message);
        jScrollPane = new JScrollPane(message);
        message.setSize(600, 300);
        jScrollPane.setSize(600,300);
        this.getContentPane().add("Center", jScrollPane);
        panel.setLayout(new GridLayout(6, 2));
        this.getContentPane().add("South", panel);
        setSize(500, 2000);
        setVisible(true);
        message.setText("Hello Friend");

    }







}