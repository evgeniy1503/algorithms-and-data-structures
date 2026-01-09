package ru.prokhorov.task7;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @author Evgeniy_Prohorov
 */
public class HashCalculator {
    /**
     * Метод для вычисления MD5 хеша строки
     * @param input Входная строка
     * @return MD5 хеш в шестнадцатеричном формате
     */
    public static String calculateMD5(String input) {
        try {
            // Получаем экземпляр MessageDigest для алгоритма MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Вычисляем хеш
            byte[] hashBytes = md.digest(input.getBytes());

            // Преобразуем байты в шестнадцатеричную строку
            return bytesToHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 алгоритм не найден", e);
        }
    }

    /**
     * Метод для вычисления SHA-256 хеша строки
     * @param input Входная строка
     * @return SHA-256 хеш в шестнадцатеричном формате
     */
    public static String calculateSHA256(String input) {
        try {
            // Получаем экземпляр MessageDigest для алгоритма SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Вычисляем хеш
            byte[] hashBytes = md.digest(input.getBytes());

            // Преобразуем байты в шестнадцатеричную строку
            return bytesToHex(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 алгоритм не найден", e);
        }
    }

    /**
     * Преобразует массив байтов в шестнадцатеричную строку
     * @param bytes Массив байтов
     * @return Шестнадцатеричная строка
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            // Преобразуем байт в шестнадцатеричное значение
            String hex = Integer.toHexString(0xff & b);

            // Добавляем ведущий ноль, если необходимо
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    /**
     * Метод для отображения хеша в удобном формате
     * @param algorithm Название алгоритма
     * @param hash Хеш-строка
     */
    private static void displayHash(String algorithm, String hash) {
        System.out.println(algorithm + ":");
        System.out.println("  Длина: " + hash.length() + " символов");
        System.out.println("  Хеш:   " + hash);

        // Для длинных хешей выводим построчно
        if (hash.length() > 64) {
            System.out.println("\n  Построчно:");
            for (int i = 0; i < hash.length(); i += 64) {
                int end = Math.min(i + 64, hash.length());
                System.out.println("  " + hash.substring(i, end));
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Калькулятор хешей MD5 и SHA-256 ===");
        System.out.println("Введите текст для вычисления хешей:");
        System.out.println("(Для выхода введите 'exit')");

        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.print("Введите текст: ");
            String input = scanner.nextLine();

            // Проверка на выход
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы...");
                break;
            }

            // Проверка на пустой ввод
            if (input.trim().isEmpty()) {
                System.out.println("Ошибка: Введена пустая строка!");
                continue;
            }

            System.out.println("\nРезультаты для строки: \"" + input + "\"");
            System.out.println("Длина строки: " + input.length() + " символов");

            try {
                // Вычисляем и отображаем MD5
                String md5Hash = calculateMD5(input);
                displayHash("MD5", md5Hash);

                // Вычисляем и отображаем SHA-256
                String sha256Hash = calculateSHA256(input);
                displayHash("SHA-256", sha256Hash);

                // Сравниваем длины
                System.out.println("Сравнение:");
                System.out.println("  MD5:    " + md5Hash.length() + " hex символов, " +
                        (md5Hash.length() * 4) + " бит");
                System.out.println("  SHA-256: " + sha256Hash.length() + " hex символов, " +
                        (sha256Hash.length() * 4) + " бит");

                // Дополнительная информация
                System.out.println(""" 
                Примечание:
                - MD5 создает 128-битный (16-байтный) хеш
                - SHA-256 создает 256-битный (32-байтный) хеш
                - SHA-256 считается более безопасным, чем MD5"""
                );

            } catch (Exception e) {
                System.out.println("Ошибка при вычислении хеша: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
