package org.example.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.PaymentBO;
import org.example.dto.CourseDTO;
import org.example.dto.PaymentDTO;
import org.example.dto.StudentDTO;
import org.example.dto.StudentCourseDTO;
import java.sql.SQLException;
import java.util.List;


public class PaymentController {

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colPayID;

    @FXML
    private TableColumn<PaymentDTO, String> colStudent_courseID;

    @FXML
    private TableView<PaymentDTO> tblPaymentHistory;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Payment);

    public void initialize() throws SQLException, ClassNotFoundException {
        LoadAllData();
        SetCellValue();
    }

    private void LoadAllData() {
        ObservableList<PaymentDTO> obList = FXCollections.observableArrayList();
        try {
            List<PaymentDTO> joinList = paymentBO.getAll();

            if (joinList != null) {
                for (PaymentDTO LIST : joinList) {
                    PaymentDTO tm = new PaymentDTO(
                            LIST.getPay_id(),
                            LIST.getPay_date(),
                            LIST.getPay_amount(),
                            new StudentCourseDTO(
                                    LIST.getStudentCourse().getStudent_course_id(),
                                    new StudentDTO(),
                                    new CourseDTO(),
                                    LIST.getStudentCourse().getRegistration_date()
                            )

                    );
                    obList.add(tm);
                }
            } else {
                System.out.println("No data returned from getAll() method.");
            }

            tblPaymentHistory.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void SetCellValue() {
        colPayID.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("pay_date"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("pay_amount"));
        colStudent_courseID.setCellValueFactory(cellData -> {
            PaymentDTO PS = cellData.getValue();
            return new SimpleStringProperty(
                    PS.getStudentCourse() != null ? PS.getStudentCourse().getStudent_course_id() : "N/A"
            );
        });

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

}
