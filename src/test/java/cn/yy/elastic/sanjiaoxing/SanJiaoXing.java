package cn.yy.elastic.sanjiaoxing;


import java.util.Scanner;

public class SanJiaoXing {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入行数:");
        int row = input.nextInt();
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        System.out.println("==================================");

        for (int i = 0; i < row; i++) {
            for (int j = row - i - 1; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(" ");
            }
            for (int j = 2 * (row - i - 1 - 1) + 1; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("==================================");

        for (int i = 0; i < row; i++) {
            for (int j = row - i - 1; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                if (j == 0 || j == 2 * i)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(" ");
            }
            for (int j = 2 * (row - i - 1 - 1) + 1; j > 0; j--) {
                if (j == 1 || j == 2 * (row - i - 1 - 1) + 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}