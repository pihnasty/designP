package pattern.mvvm.passiv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class View {
    private Presenter presenter;   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  ******* Presenter  in  View ********
    private JLabel statusLabel;
    private JTextField inputField;

    public View() {
        createUI();
    }

    private void createUI() {
        statusLabel = new JLabel("This updates in reponse to input: ");
        inputField = new JTextField(20);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener((ActionEvent e) -> {
            presenter.login(inputField.getText());
        });

        Box topBox = Box.createHorizontalBox();
        topBox.add(statusLabel);
        topBox.add(Box.createHorizontalStrut(5));
        topBox.add(inputField);
        topBox.add(Box.createHorizontalStrut(5));
        topBox.add(loginButton);

        JFrame frame = new JFrame("Passive MVP Swing");
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(topBox, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void setPresenter(Presenter pres) {
        presenter = pres;
    }

    //called by the presenter to update the status label.
    public void updateStatusLabel(String text) {
        statusLabel.setText(text);
    }
}

