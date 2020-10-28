package hu.prgitems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    private static final int SIZE = 100;
    private static final int BOUND = 99;

    private static List<Integer> numbers;

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
                ". eleme a legnagyobb, értéke: " + numbers.get(max));
    }

    private static List<Integer> init() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            numbers.add(random.nextInt(BOUND) + 1);
        }
        return numbers;
    }

    private static void print() {
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%4d", numbers.get(i));
        }
        System.out.println();
    }

    private static int summation() {
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum += numbers.get(i); // sum = sum + numbers[i]
        }
        return sum;
    }

    private static boolean decision(int divisor) {
        int i = 0;
        while (i < numbers.size() && !(numbers.get(i) % divisor == 0)) {
            i++;
        }
        return i < numbers.size();
    }

    private static int selection(int divisor) {
        int i = 0;
        while (!(numbers.get(i) % divisor == 0)) {
            i++;
        }
        return i;
    }

    private static int search(int divisor) {
        int i = 0;
        while (i < numbers.size() && !(numbers.get(i) % divisor == 0)) {
            i++;
        }
        return i < numbers.size() ? i : -1;
    }

    private static int count(int divisor) {
        int count = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % divisor == 0) {
                count++;
            }
        }
        return count;
    }

    private static int maxSelection() {
        int max = 0;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) > numbers.get(max)) {
                max = i;
            }
        }
        return max;
    }
}
