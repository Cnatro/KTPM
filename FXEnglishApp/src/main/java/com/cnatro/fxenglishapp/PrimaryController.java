package com.cnatro.fxenglishapp;

import com.cnatro.pojo.Question;
import com.cnatro.pojo.Utils;
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

    public void checkHandler(ActionEvent e) {
        Question q = this.questions.get(this.currentIndex);
        boolean v1 = ridoA.isSelected() == true && q.getChoices().get(0).isCorrect() == true;
        boolean v2 = ridoB.isSelected() == true && q.getChoices().get(1).isCorrect() == true;
        boolean v3 = ridoC.isSelected() == true && q.getChoices().get(2).isCorrect() == true;
        boolean v4 = ridoD.isSelected() == true && q.getChoices().get(3).isCorrect() == true;

        if (v1 || v2 || v3 || v4) {
            Utils.getAlert("CORRECT!!!").show();
        } else {
            Utils.getAlert("INCORRECT!!!").show();
        }
    }

    public void nextHandler(ActionEvent e) {
        if (this.currentIndex < this.questions.size() - 1) {
            this.currentIndex++;
        } else {
            this.currentIndex = 0;
        }

        try {
            loadQuestionToUI();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prevHandler(ActionEvent e) {
        if (this.currentIndex > 0) {
            this.currentIndex--;
        } else {
            this.currentIndex = this.questions.size() - 1;
        }

        try {
            loadQuestionToUI();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            QuestionServices q = new QuestionServices();
            this.questions = q.getQuestion(3,"");

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
