package modern_java.chap03;

import java.util.function.Function;

/**
 * packageName    : modern_java.chap03
 * fileName       : Letter
 * author         : sjunpark
 * date           : 24. 10. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 10. 28.        sjunpark       최초 생성
 */
public class Letter {
    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
    
    public static String addFooter(String text) {
        return text + " Kind regards";
    }
    
    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
    
    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;
        //이런식으로 변환 파이프라이닝이 가능함.
        Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        
        print(transformationPipeline.apply("labda"));
    }
    
    public static <T> void print(T t) {
        System.out.println(t);
    }
}
