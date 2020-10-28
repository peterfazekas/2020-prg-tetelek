package hu.prgitems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    private static List<Integer> numbers;

    public static void main(String[] args) {
        numbers = parse(readFromFile("szamok.txt"));
        print(numbers);
        System.out.println("1. Sorozatszámatás: A számok összege: "
                + summation());
        System.out.print("Mi legyen az osztó értéke: ");
        int divisor = readFromConsole();
        boolean hasItem = decision(divisor);
        System.out.println("2. Eldöntés: " + (hasItem ? "Van" : "Nincs") +
                " a tömbben " + divisor + "-al osztható szám!");
        if (hasItem) {
            System.out.println("3. Kiválasztás: A sorozat "
                    + selection(divisor) + " értékű eleme osztható "
                    + divisor + "-al.");
        }
        System.out.print("Mi legyen az osztó értéke: ");
        int anotherDivisor = readFromConsole();
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
        printToFile("rendezett.txt", convertToString(sort()));
    }

    private static int readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static List<String> readFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static List<Integer> parse(List<String> lines) {
        return lines.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<String> convertToString(List<Integer> list) {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    private static void printToFile(String filename, List<String> lines) {
        try {
            Files.write(Path.of(filename), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
