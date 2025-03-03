package com.cnatro.fxenglishapp;

import com.cnatro.pojo.Question;
import com.cnatro.services.QuestionServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable {

    @FXML
    private Text txtContent;
    @FXML
    private RadioButton ridoA;
    @FXML
    private RadioButton ridoB;
    @FXML
    private RadioButton ridoC;
    @FXML
    private RadioButton ridoD;
    @FXML
    private Text txtA;
    @FXML
    private Text txtB;
    @FXML
    private Text txtC;
    @FXML
    private Text txtD;
    private List<Question> questions;
    private int currentIndex = 0;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void checkHandler(ActionEvent e) throws SQLException {

    }

    public void nextHandler(ActionEvent e) {
        if (this.currentIndex < this.questions.size()) {
            this.currentIndex++;
        }else
            this.currentIndex = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            QuestionServices q = new QuestionServices();
            this.questions = q.getQuestion(3);

            loadQuestionToUI();

        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadQuestionToUI() throws SQLException {
        QuestionServices s = new QuestionServices();
        Question q = this.questions.get(this.currentIndex);
        this.txtContent.setText(q.getContent());

        if (q.getChoices() == null);
        try {
            q.setChoices(s.getChoice(q.getId()));

            this.txtA.setText(q.getChoices().get(0).toString());
            this.txtB.setText(q.getChoices().get(1).toString());
            this.txtC.setText(q.getChoices().get(2).toString());
            this.txtD.setText(q.getChoices().get(3).toString());

        } catch (SQLException e) {

        }

    }

}
