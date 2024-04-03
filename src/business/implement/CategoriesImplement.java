package business.implement;

import business.config.IOFile;
import business.config.InputMethods;
import business.design.ICategories;
import business.entity.Categories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static business.implement.ProductImplement.productList;

public class CategoriesImplement implements ICategories {
    public static File categoriesFile = new File("./src/business/storage/categoriesstorage.txt");
    public static List<Categories> categoriesList = new ArrayList<>();

    @Override
    public int findIndexById() {
        System.out.println("Nhập Id danh mục: ");
        int inputId = InputMethods.getInteger();
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).getCatalogId() == inputId) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void createData() {
        System.out.println("Nhập số danh mục muốn thêm");
        int count = InputMethods.getInteger();
        for (int i = 0; i < count; i++) {
            Categories newCategories = new Categories();
            System.out.println("Nhập thông tin cho danh mục thứ: " + (i + 1));
            newCategories.inputData();
            categoriesList.add(newCategories);
        }
        IOFile.updateFile(categoriesFile, categoriesList);
    }

    @Override
    public void displayAll() {
        if (categoriesFile.length() != 0) {
            categoriesList = IOFile.getFile(categoriesFile);
            categoriesList.forEach(Categories::displayData);
        } else {
            System.err.println("Không có danh mục nào trong danh sách");
        }
    }

    @Override
    public void updateData() {
        categoriesList.forEach(categories -> System.out.printf("Mã Id: %-5s || Tên danh mục: %-10s\n",categories.getCatalogId(),categories.getCatalogName()));
        System.out.println("Nhập Id danh mục muốn cập nhật");
        int index = findIndexById();
        if (index != -1) {
            boolean isExit = false;
            while (!isExit) {
                System.out.println("1. Cập nhật tên");
                System.out.println("2. Cập nhật mô tả");
                System.out.println("3. Cập nhật trạng thái");
                System.out.println("4. Thoát");
                int choice = InputMethods.getInteger();
                switch (choice) {
                    case 1:
                        System.out.println("Tên cũ: " + categoriesList.get(index).getCatalogName());
                        categoriesList.get(index).setCatalogName("");
                        categoriesList.get(index).setCatalogName(categoriesList.get(index).getName());
                        break;
                    case 2:
                        System.out.println("Mô tả cũ: " + categoriesList.get(index).getCatalogDescription());
                        categoriesList.get(index).setDescription(categoriesList.get(index).getDescription());
                        break;
                    case 3:
                        System.out.println("Trạng thái cũ: " + (categoriesList.get(index).isCatalogStatus() ? "Hoạt động" : "Không hoạt động"));
                        categoriesList.get(index).setCatalogStatus(categoriesList.get(index).getStatus());
                        break;
                    case 4:
                        isExit = true;
                        break;
                    default:
                        System.out.println("Mời nhập lại");
                        break;
                }
            }
            IOFile.updateFile(categoriesFile, categoriesList);
        } else {
            throw new RuntimeException("Danh mục không tồn tại");
        }
    }

    @Override
    public void deleteData() {
        categoriesList.forEach(categories -> System.out.printf("Mã Id: %-5s || Tên danh mục: %-10s\n",categories.getCatalogId(),categories.getCatalogName()));
        int index = findIndexById();
        if (index != -1) {
            if (productList.stream().noneMatch(product -> product.getCatalogId() == categoriesList.get(index).getCatalogId())) {
                categoriesList.remove(index);
                IOFile.updateFile(categoriesFile, categoriesList);
            } else {
                throw new RuntimeException("Danh mục đã có sản phẩm");
            }
        } else {
            throw new RuntimeException("Danh mục không tồn tại");
        }
    }

    @Override
    public void changeStatus() {
        int index = findIndexById();
        if (index != -1) {
            categoriesList.get(index).setCatalogStatus(!categoriesList.get(index).isCatalogStatus());
            IOFile.updateFile(categoriesFile, categoriesList);
        } else {
            throw new RuntimeException("Danh mục không tồn tại");
        }
    }
}
