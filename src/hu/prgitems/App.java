package hu.prgitems;

import java.util.Random;

public class App {

    private static final int SIZE = 100;
    public static final int BOUND = 99;

    private static int[] numbers;

    public static void main(String[] args) {
        numbers = init();
        print();
        System.out.println("1. Sorozatszámatás: A számok összege: "
                + summation());
        int divisor = 100;
        boolean hasItem = decision(divisor);
        System.out.println("2. Eldöntés: " + (hasItem ? "Van" : "Nincs") +
                " a tömbben " + divisor + "-al osztható szám!");
        if (hasItem) {
            System.out.println("3. Kiválasztás: A sorozat "
                    + (selection(divisor) + 1) + ". eleme osztható "
                    + divisor + "-al.");
        }
        divisor = 3;
        System.out.println("4. Keresés:  A sorozat "
                + (search(divisor) + 1) + ". eleme osztható "
                + divisor + "-al.");
        System.out.println("5. Megszámolás: A sorozatban " + count(divisor) + "" +
                " db " + divisor + "-al osztható szám van.");
        int max = maxSelection();
        System.out.println("6. Maximum kiválasztás: A sorozat " + (max + 1) +
                ". eleme a legnagyobb, értéke: " + numbers[max]);
        System.out.println("A rendezetlen tömb elemei:");
        print();
//        simpleSort();
        bubbleSort();
        System.out.println("A rendezett tömb elemei:");
        print();
    }

    private static int[] init() {
        int[] numbers = new int[SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = random.nextInt(BOUND) + 1;
        }
        return numbers;
    }

    private static void print() {
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%4d", numbers[i]);
        }
        System.out.println();
    }

    private static int summation() {
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum += numbers[i]; // sum = sum + numbers[i]
        }
        return sum;
    }

    private static boolean decision(int divisor) {
        int i = 0;
        while (i < numbers.length && !(numbers[i] % divisor == 0)) {
            i++;
        }
        return i < numbers.length;
    }

    private static int selection(int divisor) {
        int i = 0;
        while (!(numbers[i] % divisor == 0)) {
            i++;
        }
        return i;
    }

    private static int search(int divisor) {
        int i = 0;
        while (i < numbers.length && !(numbers[i] % divisor == 0)) {
            i++;
        }
        return i < numbers.length ? i : -1;
    }

    private static int count(int divisor) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % divisor == 0) {
                count++;
            }
        }
        return count;
    }

    private static int maxSelection() {
        int max = 0;
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[max]) {
                max = i;
            }
        }
        return max;
    }

    private static void simpleSort() {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    int p = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = p;
                }
            }
        }
    }

    private static void bubbleSort() {
        for (int i = numbers.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int p = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = p;
                }
            }
        }
    }
}
