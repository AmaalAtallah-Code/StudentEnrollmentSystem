package com.amaal.studentenrollmentsystem.dao;

import com.amaal.studentenrollmentsystem.models.Enrollment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EnrollmentDAO {
    // هذه القائمة هي التي ستظهر في الجدول (TableView)
    private static ObservableList<Enrollment> enrollmentList = FXCollections.observableArrayList();

    // ميثود لإضافة تسجيل جديد مع التحقق من التكرار
    public static boolean addEnrollment(Enrollment newEnroll) {
        for (Enrollment e : enrollmentList) {
            // التحقق: إذا كان نفس الطالب مسجل في نفس المادة
            if (e.getStudentId().equals(newEnroll.getStudentId()) &&
                    e.getCourseId().equals(newEnroll.getCourseId())) {
                return false; // منع الإضافة لأنها مكررة
            }
        }
        enrollmentList.add(newEnroll);
        return true; // تمت الإضافة بنجاح
    }

    public static ObservableList<Enrollment> getAllEnrollments() {
        return enrollmentList;
    }
    // ميثود لحذف تسجيل
    public static void deleteEnrollment(Enrollment enrollment) {
        enrollmentList.remove(enrollment);
    }

    // ميثود لتحديث بيانات تسجيل موجود
    public static void updateEnrollment(int index, Enrollment updatedEnroll) {
        enrollmentList.set(index, updatedEnroll);
    }
}