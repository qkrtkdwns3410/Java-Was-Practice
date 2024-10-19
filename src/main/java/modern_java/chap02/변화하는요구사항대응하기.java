package modern_java.chap02;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

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
    public static void main(String[] args) {
        List<Apple> inventories = List.of(
                new Apple(Color.RED, 100),
                new Apple(Color.GREEN, 200),
                new Apple(Color.RED, 300),
                new Apple(Color.GREEN, 400)
        );
        
        Thread t = new Thread(() -> System.out.println("Hello world"));
        
        t.start();  // 스레드를 시작
    }
    
    public interface Predicate<T> {
        boolean test(T t);
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
