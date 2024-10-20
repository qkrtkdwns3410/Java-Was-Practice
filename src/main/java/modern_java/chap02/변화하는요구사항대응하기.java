package modern_java.chap02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * packageName    : modern_java.chap02
 * fileName       : 변화하는요구사항대응하기
 * author         : ipeac
 * date           : 24. 10. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 10. 18.        ipeac       최초 생성
 */
public class 변화하는요구사항대응하기 {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            numbers.add(i);
        }
        
        
        // IntPredicate 사용
        IntPredicate evenNumberIntPredicate = i -> i % 2 == 0;
        long startTime = System.nanoTime();
        long countIntPredicate = numbers.stream().filter(evenNumberIntPredicate::test).count();
        long endTime = System.nanoTime();
        long durationIntPredicate = endTime - startTime;
        System.out.println("IntPredicate duration: " + durationIntPredicate + " nanoseconds");
        
        // Predicate<Integer> 사용
        Predicate<Integer> evenNumberPredicate = i -> i % 2 == 0;
        startTime = System.nanoTime();
        long countPredicate = numbers.stream().filter(evenNumberPredicate).count();
        endTime = System.nanoTime();
        long durationPredicate = endTime - startTime;
        System.out.println("Predicate<Integer> duration: " + durationPredicate + " nanoseconds");
    }
    
    public String processFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return reader.readLine();
        }
    }
    
    public String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(br);
        }
    }
    
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }
    

    
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) { // 형식 파라미터
        List<T> result = new ArrayList<>();
        
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        
        return result;
    }
    
    
    public static void prettyPrintApple(List<Apple> inventories, AppleFormatter predicate) {
        for (Apple apple : inventories) {
            String message = predicate.accept(apple);
            
            System.out.println(message);
        }
    }
    
    public interface AppleFormatter {
        String accept(Apple apple);
    }
    
    public static class ApplePrettyPrintFormatter implements AppleFormatter {
        @Override
        public String accept(Apple apple) {
            String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
            
            return MessageFormat.format("{0} apple with {1} weight", apple.getColor(), characteristic);
        }
    }
    

    
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.GREEN) {
                result.add(apple);
            }
        }
        
        return result;
    }
    
    public static List<Apple> filterRedApplesByColor(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.RED) {
                result.add(apple);
            }
        }
        return result;
    }
    
    //무게 기준 추가
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        
        return result;
    }
    
    //동작 파라미터화
/*    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        
        return result;
    }*/
    
    static class Apple {
        private Color color;
        
        private int weight;
        
        public Apple(Color color, int weight) {
            this.color = color;
            this.weight = weight;
        }
        
        public int getWeight() {
            return weight;
        }
        
        public Color getColor() {
            return color;
        }
        
        @Override
        public String toString() {
            return "Apple{" + "color=" + color +
                    ", weight=" + weight +
                    '}';
        }
    }
    
    enum Color {
        RED, GREEN
    }
    
    public interface ApplePredicate {
        boolean test(Apple apple);
    }
    
    public static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150 && apple.getColor() == Color.RED;
        }
    }
    
    public static class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }
    }
}
