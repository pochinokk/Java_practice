package practice8;

class ConcreteAlgorithm extends Algorithm {
    @Override
    protected void step1() {
        System.out.println("Первый шаг");
    }

    @Override
    protected void step2() {
        System.out.println("Второй шаг");
    }

    @Override
    protected void step3() {
        System.out.println("Третий шаг");
    }
}
