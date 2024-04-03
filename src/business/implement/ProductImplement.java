package business.implement;

import business.config.IOFile;
import business.config.InputMethods;
import business.design.IProduct;
import business.entity.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static business.implement.CategoriesImplement.categoriesList;

public class ProductImplement implements IProduct {
    public static File productFile = new File("./src/business/storage/productstorage.txt");
    public static List<Product> productList = new ArrayList<>();

    @Override
    public int findIndexById() {
        System.out.println("Nhập Id sản phẩm: ");
        String inputId = InputMethods.getString();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId().equals(inputId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void createData() {
        System.out.println("Nhập số sản phẩm muốn thêm");
        int count = InputMethods.getInteger();
        for (int i = 0; i < count; i++) {
            Product newProduct = new Product();
            System.out.println("NHập thông tin cho sản phẩm thứ: " + (i + 1));
            newProduct.inputData();
            productList.add(newProduct);
        }
        IOFile.updateFile(productFile, productList);
    }

    @Override
    public void displayAll() {
        if (productFile.length() != 0) {
            productList = IOFile.getFile(productFile);
            productList.forEach(Product::displayData);
        } else {
            System.err.println("Không có sản phẩm nào trong danh sách");
        }
    }

    @Override
    public void updateData() {
        productList.forEach(product -> System.out.printf("Mã Id: %-5s || Tên sản phẩm: %-10s\n",product.getProductId(),product.getProductName()));
        System.out.println("Nhập Id sản phẩm muốn cập nhật");
        int index = findIndexById();
        if (index != -1) {
            boolean isExit = false;
            while (!isExit) {
                System.out.println("1. Cập nhật Id");
                System.out.println("2. Cập nhật tên");
                System.out.println("3. Cập nhật giá");
                System.out.println("4. Cập nhật mô tả");
                System.out.println("5. Cập nhật ngày nhập");
                System.out.println("6. Cập nhật mã danh mục");
                System.out.println("7. Cập nhật trạng thái");
                System.out.println("8. Thoát");
                int choice = InputMethods.getInteger();
                switch (choice) {
                    case 1:
                        System.out.println("ID cũ: " + productList.get(index).getProductId());
                        productList.get(index).setProductId("");
                        productList.get(index).setProductId(productList.get(index).getId());
                        break;
                    case 2:
                        System.out.println("Tên cũ: " + productList.get(index).getProductName());
                        productList.get(index).setProductName("");
                        productList.get(index).setProductName(productList.get(index).getName());
                        break;
                    case 3:
                        System.out.println("Giá cũ: " + productList.get(index).getPrice());
                        productList.get(index).setPrice(productList.get(index).getPrdPrice());
                        break;
                    case 4:
                        System.out.println("Mô tả cũ: " + productList.get(index).getDescription());
                        productList.get(index).setDescription(productList.get(index).getPrdDescription());
                        break;
                    case 5:
                        System.out.println("Ngày nhập cũ: " + productList.get(index).getCreated());
                        productList.get(index).setCreated(productList.get(index).getDate());
                        break;
                    case 6:
                        System.out.println("ID danh mục cũ: " + productList.get(index).getCatalogId());
                        productList.get(index).setCatalogId(productList.get(index).getPrdCatalogId());
                        break;
                    case 7:
                        System.out.println("Trạng thái cũ: " + (productList.get(index).getProductStatus() == 0 ? "Đang bán" : (productList.get(index).getProductStatus() == 1 ? "Hết hàng" : "Không bán")));
                        productList.get(index).setProductStatus(productList.get(index).getStatus());
                        break;
                    case 8:
                        isExit = true;
                        break;
                    default:
                        System.out.println("Mời nhập lại");
                        break;
                }
            }
            IOFile.updateFile(productFile, productList);
        } else {
            throw new RuntimeException("Sản phẩm không tồn");
        }
    }

    @Override
    public void deleteData() {
        productList.forEach(product -> System.out.printf("Mã Id: %-5s || Tên sản phẩm: %-10s\n",product.getProductId(),product.getProductName()));
        int index = findIndexById();
        if (index != -1) {
            productList.remove(index);
            IOFile.updateFile(productFile, productList);
        } else {
            throw new RuntimeException("Sản phẩm không tồn");
        }
    }

    @Override
    public void sortProduct() {
        productList = IOFile.getFile(productFile);
        productList.sort((o1, o2) -> {
            return (int) (o1.getPrice() - o2.getPrice());
        });
        IOFile.updateFile(productFile, productList);
    }

    @Override
    public void searchByName() {
        productList = IOFile.getFile(productFile);
        System.out.println("Nhập tên sản phẩm cần tìm:");
        String inputName = InputMethods.getString();
        if (productList.stream().anyMatch(product -> product.getProductName().equals(inputName))) {
            productList.stream().filter(product -> product.getProductName().equals(inputName)).forEach(Product::displayData);
        } else {
            throw new RuntimeException("Sản phẩm không tồn");
        }
    }

    @Override
    public void searchWithin() {
        productList = IOFile.getFile(productFile);
        System.out.println("Nhập giá tối thiêu");
        int fromPrice = InputMethods.getInteger();
        System.out.println("Nhập giá tối đa");
        int toPrice = InputMethods.getInteger();
        if (productList.stream().anyMatch(product -> product.getPrice() >= fromPrice && product.getPrice() <= toPrice)) {
            productList.stream().filter(product -> product.getPrice() >= fromPrice && product.getPrice() <= toPrice).forEach(Product::displayData);
        } else {
            throw new RuntimeException("Không có sản phẩm nào");
        }
    }
}
