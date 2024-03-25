package practice7;

class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }
    @Override
    public void operation() {
        super.operation();
        System.out.println("Операция в конкретном декораторе");
    }
}