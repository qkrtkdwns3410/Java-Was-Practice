package modern_java.chap04;

import java.util.Arrays;
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
        
        //takeWhile 사용하기
        /*menu.stream()
                .takeWhile(dish -> {
                    System.out.println("takeWhile : " + dish.getName());
                    return dish.getCalories() < 320;
                })
                .forEach(System.out::println);
        
        System.out.println("==================================");
        
        //dropWhile 사용
        menu.stream()
                .dropWhile(dish -> {
                    System.out.println("dropWhile : " + dish.getName());
                    return dish.getCalories() < 320;
                })
                .forEach(System.out::println);
        
        System.out.println("==================================");
        
        // 처음 등장하는 두 고기 요리 필터링
        menu.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .forEach(System.out::println);*/
        
        /*menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .forEach(System.out::println);*/
        
        //스트림 평면화
/*        menu.stream()
                .map(Dish::getName)
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .forEach(System.out::println);
        
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);*/
        
        //5-2 매핑
        // 숫자 리스트가 주어진 경우 각 숫자의 제곱근으로 이루어진 리스트 반환
        // [1,2,3,4,5] -> [1,4,9,16,25] 반환
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
                .map(n -> n * n)
                .forEach(System.out::println);
        
        // 2개의 숫자 리스트가 있는 경우 모든 숫자 쌍의 리스트 반환
        // 2개의 리스트 [1,2,3],  [3,4] -> [(1,3), (1,4), (2,3), (2,4), (3,3), (3,4)] 반환
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        
        numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .forEach(ints -> System.out.println("(" + ints[0] + ", " + ints[1] + ")"));
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
