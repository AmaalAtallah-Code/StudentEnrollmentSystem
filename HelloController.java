package com.amaal.studentenrollmentsystem.controllers;
import javafx.scene.control.cell.PropertyValueFactory;
import com.amaal.studentenrollmentsystem.dao.EnrollmentDAO;
import com.amaal.studentenrollmentsystem.models.Enrollment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    // هذه المعرفات يجب أن تطابق الـ fx:id في Scene Builder تماماً
    @FXML private TextField studentIdField;
    @FXML private TextField courseIdField;
    @FXML private DatePicker datePicker;
    @FXML private TableView<Enrollment> enrollmentTable;
    @FXML private TableColumn<Enrollment, String> studentCol;
    @FXML private TableColumn<Enrollment, String> courseCol;
    @FXML private TableColumn<Enrollment, String> dateCol;

    @FXML
    private void handleAdd(ActionEvent event) {
        String sId = studentIdField.getText();
        String cId = courseIdField.getText();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";

        if (sId.isEmpty() || cId.isEmpty() || date.isEmpty()) {
            showAlert("Error", "All fields are required!", Alert.AlertType.ERROR);
            return;
        }

        Enrollment newEnrollment = new Enrollment(sId, cId, date);
        boolean success = EnrollmentDAO.addEnrollment(newEnrollment);

        if (success) {
            enrollmentTable.setItems(EnrollmentDAO.getAllEnrollments());
            clearFields();
            showAlert("Success", "Enrollment added successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Duplicate Enrollment: Student already registered in this course!", Alert.AlertType.ERROR);
        }
    }

    private void clearFields() {
        studentIdField.clear();
        courseIdField.clear();
        datePicker.setValue(null);
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // ميثود الحذف
    @FXML
    private void handleDelete(ActionEvent event) {
        Enrollment selected = enrollmentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            EnrollmentDAO.deleteEnrollment(selected);
            showAlert("Success", "Enrollment deleted!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Warning", "Please select a row to delete!", Alert.AlertType.WARNING);
        }
    }

    // ميثود التحديث
    @FXML
    private void handleUpdate(ActionEvent event) {
        int selectedIndex = enrollmentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String sId = studentIdField.getText();
            String cId = courseIdField.getText();
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";

            if (sId.isEmpty() || cId.isEmpty() || date.isEmpty()) {
                showAlert("Error", "Fields cannot be empty for update!", Alert.AlertType.ERROR);
                return;
            }

            Enrollment updated = new Enrollment(sId, cId, date);
            EnrollmentDAO.updateEnrollment(selectedIndex, updated);
            enrollmentTable.refresh(); // لتحديث البيانات في الجدول فوراً
            showAlert("Success", "Enrollment updated!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Warning", "Please select a row to update!", Alert.AlertType.WARNING);
        }
    }

    // ميثود لجعل البيانات تظهر في الحقول عند الضغط على سطر في الجدول (اختياري لكنه احترافي)
    @FXML
    private void handleTableClick() {
        Enrollment selected = enrollmentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            studentIdField.setText(selected.getStudentId());
            courseIdField.setText(selected.getCourseId());
            // يمكنك إضافة كود لتحويل النص إلى تاريخ في DatePicker هنا
        }
    }
    @FXML
    public void initialize() {
        // ربط الأعمدة بخصائص كلاس Enrollment
        studentCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        courseCol.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("enrollmentDate"));

        // ربط القائمة بالجدول
        enrollmentTable.setItems(EnrollmentDAO.getAllEnrollments());
    }

}