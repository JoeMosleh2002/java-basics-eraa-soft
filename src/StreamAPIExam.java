
import java.util.*;
import java.util.stream.Collectors;

public class StreamAPIExam {

    static class Employee {
        String name;
        String department;
        double salary;

        Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    }

    public static void main(String[] args) {

        // Q1: Find the First Non-Repeated Character
        String input = "swiss";

        Character q1 = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
                .findFirst()
                .orElse(null);

        System.out.println("Q1: " + q1);


        // Q2: Find Duplicate Elements in a List
        List<Integer> numbers2 = Arrays.asList(
                10, 20, 30, 20, 40, 10, 50, 30
        );

        Set<Integer> seen = new HashSet<>();

        List<Integer> q2 = numbers2.stream()
                .filter(n -> !seen.add(n))
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Q2: " + q2);


        // Q3: Find the Second Highest Number
        List<Integer> numbers3 = Arrays.asList(
                10, 5, 20, 20, 8, 15, 10
        );

        Integer q3 = numbers3.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);

        System.out.println("Q3: " + q3);


        // Q4: Find the Frequency of Each Word
        String sentence =
                "Java Spring Java Hibernate Spring Java";

        Map<String, Long> q4 = Arrays.stream(
                        sentence.toLowerCase(Locale.ROOT).split("\\s+"))
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));

        System.out.println("Q4: " + q4);


        // Q5: Find the Longest String
        List<String> words = Arrays.asList(
                "Java", "SpringBoot", "Microservices",
                "Hibernate", "Docker"
        );

        String q5 = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

        System.out.println("Q5: " + q5);


        // Q6: Group Employees by Department
        // and Calculate Average Salary
        List<Employee> employees6 = Arrays.asList(
                new Employee("Ahmed", "IT", 10000),
                new Employee("Mohamed", "IT", 15000),
                new Employee("Sara", "HR", 8000),
                new Employee("Omar", "HR", 12000),
                new Employee("Ali", "Finance", 20000)
        );

        Map<String, Double> q6 = employees6.stream()
                .collect(Collectors.groupingBy(
                        e -> e.department,
                        Collectors.averagingDouble(e -> e.salary)
                ));

        System.out.println("Q6: " + q6);


        // Q7: Find the Highest-Paid Employee
        // in Each Department
        List<Employee> employees7 = Arrays.asList(
                new Employee("Ahmed", "IT", 10000),
                new Employee("Mohamed", "IT", 18000),
                new Employee("Sara", "HR", 9000),
                new Employee("Omar", "HR", 14000),
                new Employee("Ali", "Finance", 20000)
        );

        Map<String, Employee> q7 = employees7.stream()
                .collect(Collectors.toMap(
                        e -> e.department,
                        e -> e,
                        (e1, e2) -> e1.salary >= e2.salary ? e1 : e2
                ));

        System.out.println("Q7:");
        q7.forEach((department, employee) ->
                System.out.println(
                        department + " = " + employee.name
                ));


        // Q8: Find Common Elements Between Two Lists
        List<Integer> list1 = Arrays.asList(
                1, 2, 3, 4, 5, 6, 3
        );

        List<Integer> list2 = Arrays.asList(
                4, 5, 6, 7, 8, 4
        );

        List<Integer> q8 = list1.stream()
                .filter(list2::contains)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Q8: " + q8);


        // Q9: Find the Top 3 Most Frequent Numbers
        List<Integer> numbers9 = Arrays.asList(
                1, 2, 2, 3, 3, 3, 4, 4, 4, 4,
                5, 5, 5, 6
        );

        Map<Integer, Long> frequency = numbers9.stream()
                .collect(Collectors.groupingBy(
                        n -> n,
                        Collectors.counting()
                ));

        System.out.println("Q9:");

        frequency.entrySet().stream()
                .sorted(
                        Map.Entry.<Integer, Long>comparingByValue()
                                .reversed()
                                .thenComparing(Map.Entry.comparingByKey())
                )
                .limit(3)
                .forEach(e ->
                        System.out.println(
                                e.getKey() + " = " +
                                        e.getValue() + " occurrences"
                        ));


        // Q10: Find the Top 3 Highest-Paid Employees
        // in Each Department
        List<Employee> employees10 = Arrays.asList(
                new Employee("Ahmed", "IT", 10000),
                new Employee("Mohamed", "IT", 18000),
                new Employee("Ali", "IT", 15000),
                new Employee("Omar", "IT", 22000),
                new Employee("Sara", "HR", 12000),
                new Employee("Mona", "HR", 16000),
                new Employee("Nour", "HR", 14000),
                new Employee("Hana", "HR", 9000),
                new Employee("Khaled", "Finance", 20000),
                new Employee("Youssef", "Finance", 25000),
                new Employee("Mostafa", "Finance", 18000)
        );

        Map<String, List<Employee>> q10 = employees10.stream()
                .collect(Collectors.groupingBy(
                        e -> e.department
                ));

        System.out.println("Q10:");

        q10.forEach((department, employees) -> {
            System.out.println(department + ":");

            employees.stream()
                    .sorted(
                            Comparator.comparingDouble(
                                            (Employee e) -> e.salary
                                    )
                                    .reversed()
                                    .thenComparing(e -> e.name)
                    )
                    .limit(3)
                    .forEach(e ->
                            System.out.println(
                                    e.name + " " + e.salary
                            ));
        });
    }
}
