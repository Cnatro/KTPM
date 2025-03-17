/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cnatro.fxenglishapp;

import com.cnatro.pojo.Category;
import com.cnatro.pojo.Choice;
import com.cnatro.pojo.Question;
import com.cnatro.pojo.Utils;
import com.cnatro.services.CategoryServices;
import com.cnatro.services.QuestionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FXQuestionsController implements Initializable {

    @FXML
    private ComboBox<Category> cbCategories;
    @FXML
    private TableView<Question> tbQuestions;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextArea txtContent;
    @FXML
    private TextField txtA;
    @FXML
    private TextField txtB;
    @FXML
    private TextField txtC;
    @FXML
    private TextField txtD;
    @FXML
    private RadioButton rdoA;
    @FXML
    private RadioButton rdoB;
    @FXML
    private RadioButton rdoC;
    @FXML
    private RadioButton rdoD;

    private final CategoryServices categoriesServices = new CategoryServices();
    private final QuestionServices questionServices = new QuestionServices();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.loadCategories();
        } catch (SQLException ex) {
            Logger.getLogger(FXQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadColumn();
        this.loadDataTable("");
        this.txtSearch.textProperty().addListener(e -> {
            this.loadDataTable(this.txtSearch.getText());
        });
    }

    public void loadCategories() throws SQLException {
        this.cbCategories.setItems(FXCollections.observableList(this.categoriesServices.getCategories()));
    }

    public void loadColumn() {
        TableColumn colContent = new TableColumn("Noi dung cau hoi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(400);

        TableColumn colCates = new TableColumn("Danh muc");
        colCates.setCellValueFactory(new PropertyValueFactory("categoryId"));
        colCates.setPrefWidth(200);

        this.tbQuestions.getColumns().addAll(colContent, colCates);

    }

    public void loadDataTable(String kw) {
        try {
            this.tbQuestions.setItems(FXCollections.observableList(this.questionServices.getQuestion(0, kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createQuestionHandler() {
        Question q = new Question(UUID.randomUUID().toString(), this.txtContent.getText(),
                this.cbCategories.getSelectionModel().getSelectedItem().getId());

        Choice c1 = new Choice(UUID.randomUUID().toString(), this.txtA.getText(), this.rdoA.isSelected(), q.getId());
        Choice c2 = new Choice(UUID.randomUUID().toString(), this.txtB.getText(), this.rdoB.isSelected(), q.getId());
        Choice c3 = new Choice(UUID.randomUUID().toString(), this.txtC.getText(), this.rdoC.isSelected(), q.getId());
        Choice c4 = new Choice(UUID.randomUUID().toString(), this.txtD.getText(), this.rdoD.isSelected(), q.getId());

        List<Choice> choices = new ArrayList<>();
        choices.add(c1);
        choices.add(c2);
        choices.add(c3);
        choices.add(c4);

        try {
            this.questionServices.addQuestion(q, choices);
            this.loadDataTable("");
            Utils.getAlert("Add Successfull !!").show();
            this.clearFieldsCreate();
        } catch (SQLException ex) {
            Logger.getLogger(FXQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearFieldsCreate() {
        this.cbCategories.setValue(null);
        this.txtContent.clear();
        this.txtA.clear();
        this.txtB.clear();
        this.txtC.clear();
        this.txtD.clear();
        this.rdoA.setSelected(false);
        this.rdoB.setSelected(false);
        this.rdoC.setSelected(false);
        this.rdoD.setSelected(false);
    }
}
