package practice8;

abstract class Algorithm {
    public void execute() {
        step1();
        step2();
        step3();
    }
    protected abstract void step1();
    protected abstract void step2();
    protected abstract void step3();
}
