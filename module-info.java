module com.amaal.studentenrollmentsystem {
    requires javafx.controls;
    requires javafx.fxml;

    // هذا السطر يسمح لـ JavaFX بالوصول إلى ملفات الواجهة
    opens com.amaal.studentenrollmentsystem to javafx.fxml;

    // مهم جداً: السماح لـ JavaFX بالوصول إلى الـ Controller
    opens com.amaal.studentenrollmentsystem.controllers to javafx.fxml;

    // السماح للجدول (TableView) بالوصول إلى بيانات الـ Model
    opens com.amaal.studentenrollmentsystem.models to javafx.base;

    exports com.amaal.studentenrollmentsystem;
    exports com.amaal.studentenrollmentsystem.controllers;
    exports com.amaal.studentenrollmentsystem.models;
}