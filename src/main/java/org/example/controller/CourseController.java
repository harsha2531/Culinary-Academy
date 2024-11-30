package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.CourseBO;
import org.example.bo.impl.UserBOImpl;
import org.example.bo.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.impl.LoginDAO;
import org.example.dto.CourseDTO;
import org.example.entity.Login;
import org.example.entity.User;
import org.example.util.regex.Regex;
import java.sql.SQLException;
import java.util.List;

public class CourseController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colProgramme;

    @FXML
    private Label lblCourseID;

    @FXML
    private TableView<CourseDTO> tblCourse;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtProgramName;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Course);
    UserBO userBO = (UserBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoType.User);
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Login);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAll();
        generateNextId();
        lastLoginID();


        tblCourse.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                lblCourseID.setText(newSelection.getCourse_id());
                txtProgramName.setText(newSelection.getCourse_name());
                txtDuration.setText(newSelection.getDuration());
                txtCourseFee.setText(String.valueOf(newSelection.getCourse_fee()));

            }
        });
    }

    /*login table eke log una last kenage user id ek aragannw*/
    private void lastLoginID() throws SQLException, ClassNotFoundException {
        Login login = loginDAO.getLastLogin();
        UserID(login.getUserID());

    }
    /*Access denn security ekak danamw*/
    public void UserID(String ID) throws SQLException, ClassNotFoundException {
        String UserID = ID;
        User user = userBO.searchByIdUser(UserID);
        String position = user.getPosition();

        if (position.equals("Admin")) {
            btnBack.setDisable(false);
            btnClear.setDisable(false);
            btnAdd.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);

        } else if (position.equals("Admissions Coordinator")) {
            btnAdd.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnBack.setDisable(false);
            btnClear.setDisable(false);
        }
        else {
            btnAdd.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
            btnBack.setDisable(true);
            btnClear.setDisable(false);
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colProgramme.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("course_fee"));

    }

    private void loadAll() {
        ObservableList<CourseDTO> obList = FXCollections.observableArrayList();
        try {
            List<CourseDTO>courseList = courseBO.getAll();
            for (CourseDTO courseDTO : courseList) {
                CourseDTO tm = new CourseDTO(
                        courseDTO.getCourse_id(),
                        courseDTO.getCourse_name(),
                        courseDTO.getDuration(),
                        courseDTO.getCourse_fee()
                );

                obList.add(tm);
            }

            tblCourse.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws Exception {
        try {
            String C_ID = lblCourseID.getText();
            String C_NAME = txtProgramName.getText();
            String C_Duration = txtDuration.getText();
            double C_FEE = Double.parseDouble(txtCourseFee.getText());

            CourseDTO courseDTO = new CourseDTO(C_ID,C_NAME,C_Duration,C_FEE);

            if (isValied()){
                boolean isSave = courseBO.save(courseDTO);

                if (isSave) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Course saved successfully!").show();
                    clear();
                    loadAll();
                }
            }
            else{
                new Alert(Alert.AlertType.ERROR, "Course not saved successfully!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void clear() throws SQLException, ClassNotFoundException {
        generateNextId();
        txtCourseFee.setText("");
        txtDuration.setText("");
        txtProgramName.setText("");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblCourseID.getText();

        try {
            boolean isDeleted = courseBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Course deleted successfully!").show();
                clear();
                loadAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Course!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            String C_ID = lblCourseID.getText();
            String C_NAME = txtProgramName.getText();
            String C_Duration = txtDuration.getText();
            double C_FEE = Double.parseDouble(txtCourseFee.getText());

            CourseDTO courseDTO = new CourseDTO(C_ID,C_NAME,C_Duration,C_FEE);

            if (isValied()){boolean isSave = courseBO.update(courseDTO);

                if (isSave) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Course Update successfully!").show();
                    clear();
                    loadAll();
                }
            }
            else{
                new Alert(Alert.AlertType.ERROR, "Course not Update successfully!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void generateNextId() throws SQLException, ClassNotFoundException {
        String code = courseBO.generateNextId();
        lblCourseID.setText(code);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(org.example.util.regex.TextField.NAME,txtProgramName)) return false;
        if (!Regex.setTextColor(org.example.util.regex.TextField.NUMBER,txtCourseFee)) return false;

        return true;
    }

    @FXML
    void Course_Fee(KeyEvent event) {
        Regex.setTextColor(org.example.util.regex.TextField.NUMBER,txtCourseFee);
    }

    @FXML
    void Duration(KeyEvent event) {

    }

    @FXML
    void Programme_Name(KeyEvent event) {
        Regex.setTextColor(org.example.util.regex.TextField.NAME,txtProgramName);
    }

}
