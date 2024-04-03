package business.entity;

import business.config.InputMethods;

import java.io.Serializable;
import java.util.Date;

import static business.implement.CategoriesImplement.categoriesList;
import static business.implement.ProductImplement.productList;

public class Product implements Serializable {
    private String productId;
    private String productName;
    private float price;
    private Date created;
    private String description;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, Date created, String description, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.created = created;
        this.description = description;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }
//nhập dữ liệu
    public void inputData() {
        this.productId = getId();
        this.productName = getName();
        this.price = getPrdPrice();
        this.description = getPrdDescription();
        this.created = getDate();
        this.catalogId = getPrdCatalogId();
        this.productStatus = getStatus();
    }
// lấy id
    public String getId() {
        while (true) {
            System.out.println("Nhập mã sản phẩm:");
            String inputId = InputMethods.getString();
            String regex = "^[CSA][a-zA-Z0-9]{3}$";
            if (inputId.matches(regex)) {
                if (productList.stream().noneMatch(product -> product.getProductId().equals(inputId))) {
                    return inputId;
                } else {
                    System.err.println("Id đã tồn tại, mời nhập lại");
                }
            } else {
                System.err.println("Id không đúng định dạng(C/S/A___), mời nhập lại");
            }
        }
    }
// lấy tên
    public String getName() {
        while (true) {
            System.out.println("Nhập tên sản phẩm");
            String inputName = InputMethods.getString();
            if (inputName.length() >= 10 && inputName.length() <= 50) {
                if (productList.stream().noneMatch(product -> product.getProductName().equals(inputName))) {
                    return inputName;
                } else {
                    System.err.println("Tên đã tồn tại, mời nhập lại");
                }
            } else {
                System.err.println("Tên phải có độ dài trong khoảng 10-50 ký ");
            }
        }
    }
// lấy giá
    public float getPrdPrice() {
        while (true) {
            System.out.println("Nhập giá của sản phẩm: ");
            float inputPrice = InputMethods.getFloat();
            if (inputPrice > 0) {
                return inputPrice;
            } else {
                System.err.println("Giá phải lớn hơn ");
            }
        }
    }
// lấy mô tả sp
    public String getPrdDescription() {
        System.out.println("Nhập mô tả:");
        String inputDescription = InputMethods.getString();
        return inputDescription;
    }
    // lấy ngày nhập hàng
    public Date getDate() {
        System.out.println("Nhập ngày nhập sản phẩm");
        Date inputDate = InputMethods.getDate();
        return inputDate;
    }
// lấy id danh mục
    public int getPrdCatalogId() {
        while (true) {
            System.out.println("Mời nhập Id danh mục cho sản phẩm");
            categoriesList.stream().forEach(categories -> {
                System.out.printf("Mã ID: %-5d || Tên danh mục: %-10s\n", categories.getCatalogId(), categories.getCatalogName());
            });
            int inputCatalogId = InputMethods.getInteger();
            if (categoriesList.stream().anyMatch(categories -> categories.getCatalogId() == inputCatalogId)) {
                return inputCatalogId;
            } else {
                System.err.println("Tên danh mục không tồn tại, mời nhập lại");
            }
        }
    }
//lấy trạng thái
    public int getStatus() {
        while (true) {
            System.out.println("Nhập trạng thái: ");
            String inputStatus = InputMethods.getString();
            if (inputStatus.matches("^[012]$")) {
                return Integer.parseInt(inputStatus);
            } else {
                System.err.println("Trạng thái chỉ có giá trị 0-Đang bán/1-Hết hàng/2-Không bán");
            }
        }
    }
// hiển thị thông tin
    public void displayData() {
        System.out.printf("Mã sản phẩm: %-5s || Tên sản phẩm: %-10s || Giá: %-10f\nMô tả: %-15s\nNgày nhập: %-10s || Thuộc mã danh mục: %-5s\nTrạng thái: %-8s\n",
                this.productId, this.productName, this.price, this.description, this.created.toString(),
                this.catalogId, this.productStatus == 0 ? "Đang bán" : (this.productStatus == 1 ? "Hết hàng" : "Không bán"));
        System.out.println("=====================================");
    }
}
