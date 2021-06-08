package helper;

import java.util.*;

public class StatisticManager {
    public static double getAverageGrades(List<Double> datos) {
        return datos.stream().mapToDouble(d -> d)
                .average()
                .orElse(0.0);
    }

    public static double getMinGrade(List<Double> grades) {
        return Collections.min(grades);
    }

    public static Double getMaxGrade(List<Double> notas) {
        return Collections.max(notas);
    }

    public static Double getMostRepeatedGrade(List<Double> grades) {
        Set<Double> unique = new HashSet<>(grades);
        Double mostRepeated;
        HashMap<Integer, Double> allFrecuencies = new HashMap<>();
        for (Double key : unique) {
            int frecuency =Collections.frequency(grades, key);
            allFrecuencies.put(frecuency, key);
        }
        List<Integer> keys = allFrecuencies.keySet().stream().toList();
        // Obtener el key del diccionario, que mas se repite.
        int mr = Collections.max(keys);
        mostRepeated = allFrecuencies.get(mr);
        return mostRepeated;
    }

    public static Double getLessRepeatedGrade(List<Double> notas) {
        Set<Double> unique = new HashSet<>(notas);
        Double lessRepeated;
        HashMap<Integer, Double> allFrecuencies = new HashMap<>();
        for (Double key : unique) {
            int frecuency = Collections.frequency(notas, key);
            allFrecuencies.put(frecuency, key);
        }
        List<Integer> keys = allFrecuencies.keySet().stream().toList();
        // Obtener el key del diccionario, que menos se repite.
        int lr = Collections.min(keys);
        lessRepeated = allFrecuencies.get(lr);
        return lessRepeated;
    }
}
