package com.ra.entity;

import com.ra.run.BookRun;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements IBook{
    private int bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private Date created;



    private String description;

    public Book() {

    }

    public Book(int bookId, String bookName, float importPrice, float exportPrice, String author, Date created, String description) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.author = author;
        this.created = created;
        this.description = description;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public void inputData(Scanner sc) {
        //1
        inputBookId(BookRun.books);
        inputBookName(sc);
        inputImportPrice(sc);
        inputExportPrice(sc);
        inputAuthor(sc);
        inputCreated();
        inputDescription(sc);
    }

    // Method
    public void inputBookId(List<Book> books){
        int max;
        if (books.isEmpty()){
            this.bookId = 1;
        } else {
            max = books.getFirst().bookId;
            for (Book b:books) {
                if (max < b.getBookId()){
                    max = b.getBookId();
                }
            }
            this.bookId = max +1;
        }
    }
    public void inputBookName(Scanner sc){
        do {
            try {
                System.out.println("Nhâp vào tên sách: ");
                String bookName = sc.nextLine();
                if (isValid("B.{3}",bookName)){
                    for (Book b:BookRun.books) {
                        if (b.getBookName().equals(bookName)){
                            System.err.println("Tên sách đã tồn tại");
                        }
                    }
                    this.bookName = bookName;
                    break;
                } else {
                    System.err.println("Tên sách không hợp lệ");
                }
            } catch (Exception e){
                System.err.println("Lỗi nhập dữ liệu");
            }

        } while (true);
    }

    public void inputImportPrice(Scanner sc){
        do {
            try {
                System.out.println("Nhập vào giá nhập sách: ");
                float importPrice = Float.parseFloat(sc.nextLine());
                if (importPrice > 0){
                    this.importPrice =importPrice;
                    break;
                } else {
                    System.err.println("Giá nhập phải lớn hơn 0");
                }
            } catch (NumberFormatException nfe){
                System.err.println("Giá nhập phải là số thực");
            }
        } while (true);
    }

    public void inputExportPrice(Scanner sc){
        do {
            try {
                System.out.println("Mời nhập vào giá bán: ");
                this.exportPrice = Float.parseFloat(sc.nextLine());
                if (this.exportPrice > this.importPrice){
                    break;
                } else {
                    System.err.println("Giá bán phải lớn hơn: "+this.importPrice);
                }
            } catch (NumberFormatException nfe){
                System.err.println("Giá bán phải là số thực");
            }
        } while (true);
    }

    public void inputAuthor(Scanner sc){
        do {
            try {
                System.out.println("Nhập vào tên tác giả sách: ");
                this.author = sc.nextLine();
                if (isValid(".{6,50}",this.author)){
                    break;
                } else {
                    System.err.println("Tên tác giải phải có từ 6 đên 50 kí tự");
                }
            } catch (Exception e){
                System.err.println("Lỗi khi nhập tên tác giả");
            }
        } while (true);
    }
    public void inputCreated(){
        Long curentTime = System.currentTimeMillis();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        this.created = new Date(curentTime);
        String created = simpleDate.format(this.created);
        System.out.println("Ngày khởi tạo là: "+created);
    }

    public void inputDescription(Scanner sc){
        do {
            try {
                System.out.println("Nhập mô tả sách: ");
                this.description = sc.nextLine();
                if (isValid(".{1,500}",this.description)){
                    break;
                } else {
                    System.err.println("Tối đa chỉ 500 kí tự");
                }
            } catch (Exception e){
                System.err.println("Lỗi nhập dữ liệu");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", importPrice=" + importPrice +
                ", exportPrice=" + exportPrice +
                ", author='" + author + '\'' +
                ", created=" + created +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public void displayData() {

    }

    public boolean isValid(String formValid,String object){
        Pattern p = Pattern.compile(formValid);
        Matcher m = p.matcher(object);
        return m.matches();
    }
}
