package presentaion;

import business.config.InputMethods;
import business.design.IProduct;
import business.implement.ProductImplement;

import java.util.Scanner;

public class ProductManagement {
    public static IProduct iProduct = new ProductImplement();
    public static void ProductManager(){
        boolean isExit = false;
        while (!isExit){
            System.out.println("*******************PRODUCT MANAGEMENT*****************\n" +
                    "1.Nhập thông tin các sản phẩm\n" +
                    "2.Hiển thị thông tin các sản phẩm\n" +
                    "3.Sắp xếp các sản phẩm theo giá\n" +
                    "4.Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5.Xóa sản phẩm theo mã sản phẩm\n" +
                    "6.Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7.Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8.Quay lại\n" +
                    "Mời nhập lựa chọn");
            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    iProduct.createData();
                    break;
                case 2:
                    iProduct.displayAll();
                    break;
                case 3:
                    iProduct.sortProduct();
                    break;
                case 4:
                    try {
                        iProduct.updateData();
                    } catch (RuntimeException e){
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        iProduct.deleteData();
                    } catch (RuntimeException e){
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        iProduct.searchByName();
                    } catch (RuntimeException e){
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        iProduct.searchWithin();
                    } catch (RuntimeException e){
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    isExit=true;
                    break;
                default:
                    System.out.println("Mời nhập lại");
                    break;
            }
        }
    }
}
