package business.entity;

import business.config.InputMethods;

import java.io.Serializable;
import java.util.Scanner;

import static business.implement.CategoriesImplement.categoriesList;

public class Categories implements Serializable {
    private int catalogId;
    private String catalogName;
    private String catalogDescription;
    private boolean catalogStatus;

    public Categories() {
        this.catalogId = autoId();
    }

    public Categories(String catalogName, String catalogDescription, boolean catalogStatus) {
        this.catalogId = autoId();
        this.catalogName = catalogName;
        this.catalogDescription = catalogDescription;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
//nhập dữ liệu
    public void inputData() {
        this.catalogName = getName();
        this.catalogDescription = getDescription();
        this.catalogStatus = getStatus();
    }
//id tự tăng
    public int autoId() {
        int maxId = 0;
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCatalogId() >= maxId) {
                maxId = categoriesList.get(i).getCatalogId();
            }
        }
        return maxId+1;
    }
//lấy tên
    public String getName() {
        while (true) {
            System.out.println("Nhập tên danh mục:");
            String inputName = InputMethods.getString();
            if (inputName.length() > 50) {
                System.err.println("Độ dài tên tối đa 50 ký tự, mời nhập lại.");
            } else {
                if (categoriesList.stream().noneMatch(categories -> categories.getCatalogName().equals(inputName))) {
                    return inputName;
                } else {
                    System.err.println("Tên đã tồn, mời nhập lại.");
                }
            }
        }
    }
// lấy mô tả
    public String getDescription() {
        System.out.println("Nhập mô tả:");
        String inputDescription = InputMethods.getString();
        return inputDescription;
    }
// lấy trạng thái
    public boolean getStatus() {
        System.out.println("Nhập trạng thái: ");
        boolean inputStatus = InputMethods.getBoolean();
        return inputStatus;
    }
// hiển thị thông tin
    public void displayData() {
        System.out.printf("ID: %-5d || Tên sản phẩm: %-10s \nMô tả: %-20s\nTrạng thái: %-5s\n",
                this.catalogId, this.catalogName, this.catalogDescription, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
        System.out.println("==================================================");
    }
}
