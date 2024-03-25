package practice7;

public class TestAdapterAndDecorator {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.operation();
        Component decoratedComponent = new ConcreteDecorator(new ConcreteComponent());
        decoratedComponent.operation();
        Adaptee adaptee = new Adaptee();
        Target adapter = new Adapter(adaptee);
        adapter.request();
    }
}
