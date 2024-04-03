package presentaion;

import business.config.InputMethods;
import business.design.ICategories;
import business.implement.CategoriesImplement;

import java.util.Scanner;

public class CategoriesManagement {
    public static ICategories iCategories = new CategoriesImplement();

    public static void CategoriesManager() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("************CATEGORIES MENU************\n" +
                    "1.Nhập thông tin các danh mục\n" +
                    "2.Hiển thị thông tin các danh mục\n" +
                    "3.Cập nhật thông tin danh mục\n" +
                    "4.Xóa danh mục\n" +
                    "5.Cập nhật trạng thái danh mục\n" +
                    "6.Quay lại\n" +
                    "Mời nhập lựa chọn");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    iCategories.createData();
                    break;
                case 2:
                    iCategories.displayAll();
                    break;
                case 3:
                    try {
                        iCategories.updateData();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        iCategories.deleteData();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        iCategories.changeStatus();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    isExit = true;
                    break;
                default:
                    System.out.println("Mời nhập lại");
                    break;
            }
        }
    }
}
