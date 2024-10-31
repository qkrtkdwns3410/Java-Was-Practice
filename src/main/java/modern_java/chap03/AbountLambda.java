package modern_java.chap03;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

/**
 * packageName    : modern_java.chap03
 * fileName       : AbountLambda
 * author         : sjunpark
 * date           : 24. 10. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 10. 28.        sjunpark       최초 생성
 */
public class AbountLambda {
    public static void main(String[] args) {
        //List<Apple> apples = new ArrayList<>(List.of(new Apple("green", 80), new Apple("red", 155), new Apple("green", 120)));
        //1단계 : 코드 전달
        // sort 메서드에 정렬 전략을 전달
        //apples.sort(new AppleComparator());
        
        //오름차순으로 정렬됨.
        //System.out.println(apples);
        
        //2단계 : 익명 클래스 사용
        /*apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        
        System.out.println(apples);*/
        
        //역정렬
    /*    apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed() // 무게 내림차순 정렬
                .thenComparing(Apple::getCountry) // 같은 무게일 경우 국가 오름차순 정렬
        );
        
        Predicate<Apple> redApples = apple -> Objects.equals("red", apple.getColor());
        
        //Predicate 반전
        Predicate<Apple> notRedApple = redApples.negate();
        
        //Predicate 조합
        Predicate<Apple> redAndHeavyApple = redApples.and(apple -> apple.getWeight() > 150);
        */
        //Function 조합
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        
        System.out.println("result = " + result);
    }
    
    public static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }
    
    public static class Apple {
        private String color;
        private Integer weight;
        
        private String country;
        
        public Apple(String color, int weight, String country) {
            this.color = color;
            this.weight = weight;
            this.country = country;
        }
        
        public String getColor() {
            return color;
        }
        
        public Integer getWeight() {
            return weight;
        }
        
        public String getCountry() {
            return country;
        }
        
        public boolean isRed() {
            return Objects.equals("red", color);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Apple{");
            sb.append("color='").append(color).append('\'');
            sb.append(", weight=").append(weight);
            sb.append('}');
            return sb.toString();
        }
    }
}
