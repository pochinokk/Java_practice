package practice7;

class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("Операция конкретного компонента");
    }
}