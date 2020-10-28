package hu.prgitems;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    private static final int SIZE = 100;
    private static final int BOUND = 99;

    private static List<Integer> numbers;

    public static void main(String[] args) {
        numbers = init();
        print(numbers);
        System.out.println("1. Sorozatszámatás: A számok összege: "
                + summation());
        int divisor = 36;
        boolean hasItem = decision(divisor);
        System.out.println("2. Eldöntés: " + (hasItem ? "Van" : "Nincs") +
                " a tömbben " + divisor + "-al osztható szám!");
        if (hasItem) {
            System.out.println("3. Kiválasztás: A sorozat "
                    + (selection(divisor) + 1) + ". eleme osztható "
                    + divisor + "-al.");
        }
        final int anotherDivisor = 100;
        String value = search(anotherDivisor)
                .map(i -> " A sorozat " + i + " értékű eleme osztható " +
                        anotherDivisor + "-al.")
                .orElse("Nincs ilyen érték");
        System.out.println("4. Keresés: " + value);
        System.out.println("5. Megszámolás: A sorozatban " + count(divisor) + "" +
                " db " + divisor + "-al osztható szám van.");
        System.out.println("6. Maximum kiválasztás: A sorozat legnagyobb, értékű eleme: " +
                maxSelection());
        System.out.println("A rendezetlen lista elemei:");
        print(numbers);
        System.out.println("A rendezett lista elemei:");
        print(sort());

    }

    private static List<Integer> init() {
        Random random = new Random();
        return IntStream.range(0, SIZE)
                .mapToObj(i -> random.nextInt(BOUND) + 1)
                .collect(Collectors.toList());
    }

    private static void print(List<Integer> list) {
        list.forEach(i -> System.out.printf("%4d", i));
        System.out.println();
    }

    private static int summation() {
        return numbers.stream()
                .mapToInt(i -> i)
                .sum();
    }

    private static boolean decision(int divisor) {
        return numbers.stream()
                .anyMatch(i -> i % divisor == 0);
    }

    private static int selection(int divisor) {
        return numbers.stream()
                .filter(i -> i % divisor == 0)
                .findFirst()
                .get();
    }

    private static Optional<Integer> search(int divisor) {
        return numbers.stream()
                .filter(i -> i % divisor == 0)
                .findFirst();

    }

    private static long count(int divisor) {
        return numbers.stream()
                .filter(i -> i % divisor == 0)
                .count();
    }

    private static int maxSelection() {
        return numbers.stream()
                .mapToInt(i -> i)
                .max()
                .getAsInt();
    }

    private static List<Integer> sort() {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
