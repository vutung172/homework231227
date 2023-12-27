package com.ra.run;

import com.ra.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookRun {
    public static List<Book> books = new ArrayList<>();
    private static String select;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("*********************************MENU********************************");
            System.out.println("1. Nhập thông tin sách");
            System.out.println("2. Hiển thị thông tin sách");
            System.out.println("3. Cập nhật thông tin sách theo mã sách");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("6. Thống kê sách theo khoảng giá (a-b nhập từ bàn phím)");
            System.out.println("7. Tìm kiếm sách theo tên tác giả");
            System.out.println("8. Thoát");
            System.out.println("Mời lựa chọn (1-8): ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    displayBook();
                    break;
                case 3:
                    updateBookById(sc);
                    break;
                case 4:
                    deleteBook(sc);
                    break;
                case 5:
                    sortBookByExportPrice();
                    break;
                case 6:
                    searchBookByExportPrice(sc);
                    break;
                case 7:
                    searchBookByAuthor(sc);
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Lựa chọn không phù hợp");
            }
        } while (true);

    }

    public static void addBook(Scanner sc){
        do {
            Book book =  new Book();
            book.inputData(sc);
            books.add(book);
            System.out.println("Thêm sách thành công");
            System.out.println("Bạn có muốn tiếp tục (Y/N): ");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }
    public static void displayBook(){
        books.forEach(Book::displayData);
    }

    public static void updateBookById(Scanner sc){
        do {
            try {
                System.out.println("Nhập ID sách bạn muốn tìm");
                int id = Integer.parseInt(sc.nextLine());
                Book searchBook = BookRun.searchBookById(id,books);
                if (searchBook != null){
                    searchBook.inputBookName(sc);
                    searchBook.inputImportPrice(sc);
                    searchBook.inputExportPrice(sc);
                    searchBook.inputAuthor(sc);
                    searchBook.inputCreated();
                    searchBook.inputDescription(sc);
                } else {
                    System.err.println("ID sách không tồn tại");
                }
            }catch (NumberFormatException nfe){
                System.err.println("Mã sách là số nguyên lớn hơn 0");
            }
            catch (Exception e){
                System.err.println("Lỗi nhập dữ liệu");
            }

            System.out.println("Bạn có muốn tiếp tục (Y/N): ");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }

    public static void deleteBook(Scanner sc){
        do {
            try {
                System.out.println("Nhập ID sách bạn muốn xóa:");
                int id = Integer.parseInt(sc.nextLine());
                Book searchBook = BookRun.searchBookById(id,books);
                if (searchBook != null){
                    books.remove(searchBook);
                    System.out.println("Xóa thành công");
                } else {
                    System.err.println("ID sách không tồn tại");
                }
            }catch (NumberFormatException nfe){
                System.err.println("Mã sách là số nguyên lớn hơn 0");
            }
            catch (Exception e){
                System.err.println("Lỗi nhập dữ liệu");
            }
            System.out.println("Bạn có muốn tiếp tục (Y/N): ");
            select = sc.nextLine();
        }while (select.equalsIgnoreCase("Y"));
    }
    public static void sortBookByExportPrice(){
        books.sort((b1,b2) -> {
            if (b1.getExportPrice() < b2.getExportPrice()){
                return -1;
            } else if (b1.getExportPrice() == b2.getExportPrice()) {
                return 0;
            } else {
                return 1;
            }
        });
    }
    public static void searchBookByExportPrice(Scanner sc){
        do {
            System.out.println("Nhập vào giá sách bạn muốn tìm: ");
            System.out.println("Giá thấp nhất: ");
            float lowerPrice = Float.parseFloat(sc.nextLine());
            System.out.println("Giá cao nhất: ");
            float upperPrice = Float.parseFloat(sc.nextLine());
            int searchCount = searchBookByExportPrice(lowerPrice,upperPrice);
            System.out.printf("Đã tìm được %d  sách có giá từ %.3f đến %.3f.\n", searchCount, lowerPrice, upperPrice);
            System.out.println("Bạn có muốn tiếp tục tìm tên sinh viên khác (Y/N):");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }

    public static void searchBookByAuthor(Scanner sc) {
        do {
            System.out.println("Nhập vào tên tác giả muốn tìm: ");
            String name = sc.nextLine();
            int searchCount = searchBookByAuthor(name);
            System.out.printf("Đã tìm được %d  sách có tên tác giả chứa: %s.\n", searchCount, name);
            System.out.println("Bạn có muốn tiếp tục tìm tên sinh viên khác (Y/N):");
            select = sc.nextLine();
        } while (select.equalsIgnoreCase("Y"));
    }

    // Class method
    public static Book searchBookById(int id,List<Book> books){
        for (Book b: books) {
            if (b.getBookId() == id){
                return b;
            }
        }
        return null;
    }

    public static int searchBookByAuthor(String name) {
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(name.toLowerCase())) {
                book.displayData();
                count++;
            }
        }
        return count;
    }

    public static int searchBookByExportPrice(float lowerPrice,float upperPrice){
        int count =0;
        for (Book book : books) {
            if (lowerPrice < book.getExportPrice() && book.getExportPrice() < upperPrice) {
                book.displayData();
                count++;
            }
        }
        return count;
    }
}
