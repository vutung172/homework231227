/*
import java.util.Scanner;

public class Exception_UD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            float mark;
            try {
                System.out.println("Nhập vào điểm sinh viên: ");
                mark =  Float.parseFloat(sc.nextLine());
                if(mark >= 0 && mark <=10 ){
                    break;
                } else {
                    System.err.println("Điểm trong khoảng từ 0 đến 10");
                }

            } catch (NumberFormatException nfe){
                System.err.println("Lỗi kiểu dữ liệu Float");
            }
            System.out.println("Điểm sinh viên vừa nhập là: "+mark);
        } while (true);
        do {
            int age;
            try {
                System.out.println("Nhập vào tuổi sinh viên: ");
                age = Integer.parseInt(sc.nextLine());
                if (age > 18){
                    break;;
                } else {
                    System.err.println("Tuổi không được dưới 18");
                }
            } catch (NumberFormatException nfe){
                System.err.println("Lỗi kiểu dữ liệu Integer");
            }
            System.out.println("Tuổi sinh viên vừa nhập là: "+age);
        } while (true);
    }
}
*/
