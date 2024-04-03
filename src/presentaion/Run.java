package presentaion;

import business.config.InputMethods;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit){
            System.out.println(" ***************SHOP MENU****************\n" +
                    "1.Quản lý danh mục sản phẩm\n" +
                    "2.Quản lý sản phẩm\n" +
                    "3.Thoát\n" +
                    "Nhập lựa chọn");
            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    CategoriesManagement.CategoriesManager();
                    break;
                case 2:
                    ProductManagement.ProductManager();
                    break;
                case 3:
                    isExit=true;
                    break;
                default:
                    System.out.println("Mời nhập lại");
                    break;
            }
        }
    }
}
