package practice8;

public class TestStrategyAndAlgorithm {
    public static void main(String[] args) {
        Strategy s1 = new ConcreteStrategyA();
        Strategy s2 = new ConcreteStrategyB();
        s1.execute();
        s2.execute();

        Algorithm algorithm = new ConcreteAlgorithm();
        algorithm.execute();
    }
}
