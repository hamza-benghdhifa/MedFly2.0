/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Medfly.gui;



import tn.esprit.Medfly.services.ServicePatient;
import tn.esprit.Medfly.utilities.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Random;
import javafx.fxml.FXMLLoader;

public class LoginPatController implements Initializable {

    @FXML
    private TextField mailpat;
    @FXML
    private TextField motpat;
    @FXML
    private Label LabMail;
    @FXML
    private Label Passelab;
    @FXML
    private Label captchaLabel;
    @FXML
    private TextField captchaInput;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String currentCaptcha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshCaptcha();
    }

    @FXML
    private void toIns(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUpPatient.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

     public class CaptchaGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CAPTCHA_LENGTH = 6;

    public String generateCaptcha() {
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            captcha.append(CHARACTERS.charAt(index));
        }

        return captcha.toString();
    }
}
    @FXML
    private void refreshCaptcha() {
    CaptchaGenerator captchaGenerator = new CaptchaGenerator(); // Create an instance
    currentCaptcha = captchaGenerator.generateCaptcha(); // Call the method
    captchaLabel.setText(currentCaptcha);
    }

    @FXML
    private void loginPat(ActionEvent event) throws IOException {
        PreparedStatement preparedStatement = null;
        ServicePatient service = new ServicePatient();

        String email = mailpat.getText();
        String password = motpat.getText();

        try {
            Connection cnx = MyConnection.getInstance().getcnx();
            preparedStatement = cnx.prepareStatement("SELECT Password FROM Patient WHERE EmailP = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("Password");

                if (password.equals(storedPassword)) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientMainMenu.fxml"));
                    root = loader.load();

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    LabMail.setText("");
                    Passelab.setText("");
                    System.out.println("Welcome.");
                } else {
                    LabMail.setText("");
                    Passelab.setText("Incorrect password. Please try again.");
                }
            } else {
                LabMail.setText("User not found. Please check your email.");
                Passelab.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String enteredCaptcha = captchaInput.getText();

        if (!enteredCaptcha.equals(currentCaptcha)) {
            LabMail.setText("Incorrect CAPTCHA. Please try again.");
            Passelab.setText("");
            captchaInput.clear();
            refreshCaptcha();
        }
    }
}
  

    

