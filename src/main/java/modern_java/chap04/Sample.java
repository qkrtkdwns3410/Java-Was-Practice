package modern_java.chap04;

import java.util.List;
import java.util.stream.Stream;

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
        
        //4.3.1  스트림의 일회성
        //Stream<Dish> stream = menu.stream();
        //stream.forEach(System.out::println);
        //stream.forEach(System.out::println);
        //
        
        // 4.4.1 중간 연산
 /*       List<String> names = menu.stream()
                .filter(dish -> {
                    System.out.println("filtering: " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping: " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .toList();*/
        
        // 5.1.1 프레디케이트로 필터링
        menu.stream()
                .filter(Dish::isVegetarian)
                .forEach(System.out::println);
        
        // 5.1.2 고유 요소 필터링
        Stream.of(1, 2, 1, 3, 3, 2, 4)
                .distinct()
                .forEach(System.out::println);
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
