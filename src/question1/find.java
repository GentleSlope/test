package question1;

import java.io.IOException;
import java.util.Scanner;

public class find {
    public static void main(String[] args) throws IOException {
        int flag = 0;
        FileUtil fileUtil = new FileUtil();
        Student[] users = fileUtil.parseStudents("D:\\Game\\kaoheproject\\src\\students.txt");
        System.out.println("Please input the lovely boy's ID: ");
        Scanner scanner = new Scanner(System.in);
        String lovelyBoy = scanner.nextLine();
        for (Student u:users)
            if (lovelyBoy.equals(u.getStuid())) {
                System.out.println("I find him!,Lovely:"+u.getName());
                flag = 1;
            }
        if (flag==0){
            System.out.println("I cant fing："+lovelyBoy);
        }
    }

}