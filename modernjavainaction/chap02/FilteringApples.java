package modernjavainaction.chap02;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {
    
    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN), new Apple(155, Color.GREEN), new Apple(120, Color.RED));
        filterApples(inventory, FilteringApples::isHeavyApple);
        
        filterApples(inventory, Apple::isGreenApple);
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
    
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                result.add(apple);
            }
        }
        
        return result;
    }
    
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        
        return result;
    }
    
    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        
        return result;
    }
    
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 10;
    }
    
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) { // 사과는 PrediCate 자 제시하는 조건에 맞는가?
                result.add(apple);
            }
        }
        
        return result;
    }
    
    enum Color {
        RED, GREEN
    }
    
    public static class Apple {
        
        private int weight = 0;
        private Color color;
        
        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }
        
        public int getWeight() {
            return weight;
        }
        
        public void setWeight(int weight) {
            this.weight = weight;
        }
        
        public Color getColor() {
            return color;
        }
        
        public void setColor(Color color) {
            this.color = color;
        }
        
        public boolean isGreenApple() {
            return color == Color.GREEN;
        }
        
        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
        
    }
    
    interface ApplePredicate {
        
        boolean test(Apple a);
        
    }
    
    static class AppleWeightPredicate implements ApplePredicate {
        
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
        
    }
    
    static class AppleColorPredicate implements ApplePredicate {
        
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }
        
    }
    
    static class AppleRedAndHeavyPredicate implements ApplePredicate {
        
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.RED && apple.getWeight() > 150;
        }
        
    }
    
}
