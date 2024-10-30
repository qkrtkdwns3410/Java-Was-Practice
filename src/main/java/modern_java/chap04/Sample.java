package modern_java.chap04;

import java.util.List;

/**
 * packageName    : modern_java.chap04
 * fileName       : Sample
 * author         : ipeac
 * date           : 24. 10. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 10. 30.        ipeac       최초 생성
 */
public class Sample {
    public static void main(String[] args) {
        List<Dish> menu = List.of(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }
    
    public static class Dish {
        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;
        
        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }
        
        public String getName() {
            return name;
        }
        
        public boolean isVegetarian() {
            return vegetarian;
        }
        
        public int getCalories() {
            return calories;
        }
        
        public Type getType() {
            return type;
        }
        
        @Override
        public String toString() {
            return name;
        }
        
        public enum Type {
            MEAT, FISH, OTHER
        }
    }
}
