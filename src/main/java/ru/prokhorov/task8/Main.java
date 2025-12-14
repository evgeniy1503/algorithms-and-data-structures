package ru.prokhorov.task8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Evgeniy_Prohorov
 */

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "people.txt";

        Map<String, List<String>> result = Files.lines(Paths.get(filename))
                .parallel()
                .filter(line -> line.contains(":"))
                .map(line -> line.split(":"))
                .filter(parts -> parts.length == 2 &&
                        !parts[0].trim().isEmpty() &&
                        !parts[1].trim().isEmpty())
                .map(parts -> new String[]{
                        capitalizeName(parts[0].trim()),
                        parts[1].trim()
                })
                .collect(Collectors.groupingByConcurrent(
                        parts -> parts[1],
                        Collectors.mapping(parts -> parts[0], Collectors.toList())
                ));

        System.out.println(result);
    }

    private static String capitalizeName(String name) {
        if (name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() +
                name.substring(1).toLowerCase();
    }
}
