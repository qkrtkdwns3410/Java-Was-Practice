package modern_java.chap02;

import modernjavainaction.chap06.GroupingTransactions;
import modernjavainaction.chap06.GroupingTransactions.Transaction;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;
import static modernjavainaction.chap06.GroupingTransactions.transactions;

public class FilteringApples {
    
    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN), new Apple(155, Color.GREEN), new Apple(120, Color.RED));
        filterApples(inventory, FilteringApples::isHeavyApple).forEach(System.out::println);
        
        System.out.println("---");
        
        filterApples(inventory, Apple::isGreenApple).forEach(System.out::println);
        
        System.out.println("---");
        
        // 스트림 관련 - 만약 스트림이 없는 경우
        // 그룹화된 트랜잭션을 더할 Map 을 생성한다.
        Map<GroupingTransactions.Currency, List<Transaction>> transactionsByCurrencies =
                new HashMap<>();
        
        for (Transaction transaction : transactions) { // 트랜잭션의 리스트를 반복한다.
            if (transaction.getValue() > 1000) { // 고가의 트랜잭션을 필터링 한다.
                GroupingTransactions.Currency currency = transaction.getCurrency(); // 트랜잭션의 통화를 추출한다.
                List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency); // 통화에 대한 트랜잭션 리스트를 가져온다.
                
                if (transactionsForCurrency == null) { // 통화에 대한 트랜잭션 리스트가 없으면 새로 생성한다.
                    transactionsForCurrency = new ArrayList<>();
                    transactionsByCurrencies.put(currency, transactionsForCurrency);
                }
                
                //현재 탐색된 트랜잭션을 같은 통화의 트랜잭션 리스트에 추가
                transactionsForCurrency.add(transaction);
            }
        }
        
        System.out.println(transactionsByCurrencies);
        
        System.out.println("--");
        
        // 스트림이 있는 경우
        Map<GroupingTransactions.Currency, List<Transaction>> transactionsByCurrenciesStream =
                transactions.stream()
                        .filter(transaction -> transaction.getValue() > 1000)
                        .collect(groupingBy(Transaction::getCurrency));
        
        System.out.println(transactionsByCurrenciesStream);
        
        System.out.println("--");
        
        // 병렬스트림  - 순차 처리 방식
        List<Apple> heavyApples2 = inventory.stream()
                .filter(apple -> apple.getWeight() > 150)
                .toList();
        
        // 병렬스트림 - 병렬 처리
        List<Apple> heavyApples3 = inventory.parallelStream()
                .filter(apple -> apple.getWeight() > 150)
                .toList();
        
        System.out.println(heavyApples2);
        System.out.println(heavyApples3);
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
