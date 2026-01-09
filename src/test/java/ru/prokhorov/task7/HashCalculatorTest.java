package ru.prokhorov.task7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Evgeniy_Prohorov
 */
class HashCalculatorTest {

    private static HashCalculator hashCalculator;

    @BeforeAll
    static void setUp() {
        hashCalculator = new HashCalculator();
    }

    // Вспомогательные методы для вычисления хешей
    private String calculateMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes());
        return String.format("%032x", new BigInteger(1, hashBytes));
    }

    private String calculateSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(input.getBytes());
        return String.format("%064x", new BigInteger(1, hashBytes));
    }

    @Test
    @DisplayName("Тест MD5 для строки 'Hello'")
    void testMD5Hello() throws NoSuchAlgorithmException {
        String input = "Hello";
        String expected = "8b1a9953c4611296a827abf8c47804d7";
        String actual = calculateMD5(input);

        assertEquals(expected, actual,
                "MD5 хеш для 'Hello' должен быть " + expected);
        assertEquals(32, actual.length(),
                "Длина MD5 хеша должна быть 32 символа");
    }

    @Test
    @DisplayName("Тест MD5 для строки 'hello'")
    void testMD5helloLowercase() throws NoSuchAlgorithmException {
        String input = "hello";
        String expected = "5d41402abc4b2a76b9719d911017c592";
        String actual = calculateMD5(input);

        assertEquals(expected, actual,
                "MD5 хеш для 'hello' должен быть " + expected);
    }

    @Test
    @DisplayName("Тест MD5 для строки 'Hello!'")
    void testMD5HelloExclamation() throws NoSuchAlgorithmException {
        String input = "Hello!";
        String expected = "952d2c56d0485958336747bcdd98590d";
        String actual = calculateMD5(input);

        assertEquals(expected, actual,
                "MD5 хеш для 'Hello!' должен быть " + expected);
    }

    @Test
    @DisplayName("Тест SHA-256 для строки 'Hello'")
    void testSHA256Hello() throws NoSuchAlgorithmException {
        String input = "Hello";
        String expected = "185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969";
        String actual = calculateSHA256(input);

        assertEquals(expected, actual,
                "SHA-256 хеш для 'Hello' должен быть " + expected);
        assertEquals(64, actual.length(),
                "Длина SHA-256 хеша должна быть 64 символа");
    }

    @Test
    @DisplayName("Тест SHA-256 для строки 'hello'")
    void testSHA256helloLowercase() throws NoSuchAlgorithmException {
        String input = "hello";
        String expected = "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824";
        String actual = calculateSHA256(input);

        assertEquals(expected, actual,
                "SHA-256 хеш для 'hello' должен быть " + expected);
    }

    @Test
    @DisplayName("Тест SHA-256 для строки 'Hello!'")
    void testSHA256HelloExclamation() throws NoSuchAlgorithmException {
        String input = "Hello!";
        String expected = "334d016f755cd6dc58c53a86e183882f8ec14f52fb05345887c8a5edd42c87b7";
        String actual = calculateSHA256(input);

        assertEquals(expected, actual,
                "SHA-256 хеш для 'Hello!' должен быть " + expected);
    }

    @Test
    @DisplayName("Тест на чувствительность к регистру для MD5")
    void testCaseSensitivityMD5() throws NoSuchAlgorithmException {
        String helloUpper = "Hello";
        String helloLower = "hello";

        String md5Upper = calculateMD5(helloUpper);
        String md5Lower = calculateMD5(helloLower);

        assertNotEquals(md5Upper, md5Lower,
                "MD5 хеши для 'Hello' и 'hello' должны быть разными");
        assertNotEquals(helloUpper, helloLower,
                "Исходные строки должны быть разными");
    }

    @Test
    @DisplayName("Тест на чувствительность к регистру для SHA-256")
    void testCaseSensitivitySHA256() throws NoSuchAlgorithmException {
        String helloUpper = "Hello";
        String helloLower = "hello";

        String sha256Upper = calculateSHA256(helloUpper);
        String sha256Lower = calculateSHA256(helloLower);

        assertNotEquals(sha256Upper, sha256Lower,
                "SHA-256 хеши для 'Hello' и 'hello' должны быть разными");
    }

    @Test
    @DisplayName("Тест на чувствительность к пунктуации для MD5")
    void testPunctuationSensitivityMD5() throws NoSuchAlgorithmException {
        String hello = "Hello";
        String helloExclamation = "Hello!";

        String md5Hello = calculateMD5(hello);
        String md5HelloExclamation = calculateMD5(helloExclamation);

        assertNotEquals(md5Hello, md5HelloExclamation,
                "MD5 хеши для 'Hello' и 'Hello!' должны быть разными");
    }

    @Test
    @DisplayName("Тест на чувствительность к пунктуации для SHA-256")
    void testPunctuationSensitivitySHA256() throws NoSuchAlgorithmException {
        String hello = "Hello";
        String helloExclamation = "Hello!";

        String sha256Hello = calculateSHA256(hello);
        String sha256HelloExclamation = calculateSHA256(helloExclamation);

        assertNotEquals(sha256Hello, sha256HelloExclamation,
                "SHA-256 хеши для 'Hello' и 'Hello!' должны быть разными");
    }

    @Test
    @DisplayName("Тест на детерминированность MD5")
    void testMD5Deterministic() throws NoSuchAlgorithmException {
        String input = "Hello";

        // Вычисляем хеш дважды
        String hash1 = calculateMD5(input);
        String hash2 = calculateMD5(input);

        assertEquals(hash1, hash2,
                "MD5 должен быть детерминированным - одинаковый ввод дает одинаковый хеш");
    }

    @Test
    @DisplayName("Тест на детерминированность SHA-256")
    void testSHA256Deterministic() throws NoSuchAlgorithmException {
        String input = "Hello";

        // Вычисляем хеш дважды
        String hash1 = calculateSHA256(input);
        String hash2 = calculateSHA256(input);

        assertEquals(hash1, hash2,
                "SHA-256 должен быть детерминированным - одинаковый ввод дает одинаковый хеш");
    }

    @Test
    @DisplayName("Сравнение длин хешей разных алгоритмов")
    void testHashLengths() throws NoSuchAlgorithmException {
        String input = "Hello";

        String md5Hash = calculateMD5(input);
        String sha256Hash = calculateSHA256(input);

        assertEquals(32, md5Hash.length(),
                "MD5 хеш должен быть длиной 32 hex символа");
        assertEquals(64, sha256Hash.length(),
                "SHA-256 хеш должен быть длиной 64 hex символа");

        assertTrue(sha256Hash.length() > md5Hash.length(),
                "SHA-256 хеш должен быть длиннее MD5 хеша");
    }

    @Test
    @DisplayName("Групповой тест всех трех строк")
    void testAllThreeStrings() throws NoSuchAlgorithmException {
        // Массив тестовых данных: [строка, ожидаемый MD5, ожидаемый SHA-256]
        String[][] testData = {
                {"Hello", "8b1a9953c4611296a827abf8c47804d7",
                        "185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969"},
                {"hello", "5d41402abc4b2a76b9719d911017c592",
                        "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824"},
                {"Hello!", "952d2c56d0485958336747bcdd98590d",
                        "334d016f755cd6dc58c53a86e183882f8ec14f52fb05345887c8a5edd42c87b7"}
        };

        for (String[] data : testData) {
            String input = data[0];
            String expectedMD5 = data[1];
            String expectedSHA256 = data[2];

            String actualMD5 = calculateMD5(input);
            String actualSHA256 = calculateSHA256(input);

            assertEquals(expectedMD5, actualMD5,
                    "Неверный MD5 для строки: " + input);
            assertEquals(expectedSHA256, actualSHA256,
                    "Неверный SHA-256 для строки: " + input);

            // Дополнительные проверки
            assertNotNull(actualMD5, "MD5 не должен быть null");
            assertNotNull(actualSHA256, "SHA-256 не должен быть null");
            assertFalse(actualMD5.isEmpty(), "MD5 не должен быть пустым");
            assertFalse(actualSHA256.isEmpty(), "SHA-256 не должен быть пустым");
        }
    }

    @Test
    @DisplayName("Тест на уникальность всех хешей")
    void testAllHashesAreUnique() throws NoSuchAlgorithmException {
        String[] inputs = {"Hello", "hello", "Hello!"};

        // Собираем все хеши
        String[] allHashes = new String[6]; // 3 строки × 2 алгоритма

        for (int i = 0; i < inputs.length; i++) {
            allHashes[i * 2] = calculateMD5(inputs[i]);
            allHashes[i * 2 + 1] = calculateSHA256(inputs[i]);
        }

        // Проверяем, что все хеши уникальны
        for (int i = 0; i < allHashes.length; i++) {
            for (int j = i + 1; j < allHashes.length; j++) {
                assertNotEquals(allHashes[i], allHashes[j],
                        String.format("Хеши %d и %d должны быть разными", i, j));
            }
        }
    }

    @Test
    @DisplayName("Тест формата хешей (только hex символы)")
    void testHashFormat() throws NoSuchAlgorithmException {
        String input = "Hello";
        String md5Hash = calculateMD5(input);
        String sha256Hash = calculateSHA256(input);

        // Проверяем, что хеши содержат только hex символы (0-9, a-f)
        assertTrue(md5Hash.matches("[0-9a-f]{32}"),
                "MD5 хеш должен содержать только hex символы (0-9, a-f)");
        assertTrue(sha256Hash.matches("[0-9a-f]{64}"),
                "SHA-256 хеш должен содержать только hex символы (0-9, a-f)");
    }

}