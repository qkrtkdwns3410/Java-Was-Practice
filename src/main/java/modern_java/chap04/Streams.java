package modern_java.chap04;

import java.util.Comparator;
import java.util.List;

/**
 * packageName    : modern_java.chap04
 * fileName       : Streams
 * author         : sjunpark
 * date           : 24. 10. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 10. 28.        sjunpark       최초 생성
 */
public class Streams {
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
        
        //싱글 스레드처리
        long start = System.currentTimeMillis();
        menu.stream()
                .filter(d -> d.getCalories() > 400) // 400 칼로리 이하의 요리 선택
                .sorted(Comparator.comparing(Dish::getCalories)) // 칼로리로 요리 정렬
                .map(Dish::getName) // 요리명 추출
                .forEach(System.out::println); // 요리명 출력
        
        long end = System.currentTimeMillis();
        
        System.out.println("싱글 스레드 처리 시간 : " + (end - start));
        
        //병렬 처리
        start = System.currentTimeMillis();
        
        menu.stream()
                .parallel()
                .filter(d -> d.getCalories() > 400) // 400 칼로리 이하의 요리 선택
                .sorted(Comparator.comparing(Dish::getCalories)) // 칼로리로 요리 정렬
                .map(Dish::getName) // 요리명 추출
                .forEach(System.out::println); // 요리명 출력
        
        end = System.currentTimeMillis();
        
        System.out.println("병렬 처리 시간 : " + (end - start));
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
