package practice6;

class ConcretePrototype implements Prototype {
    @Override
    public Prototype clone() {
        return new ConcretePrototype();
    }

    @Override
    public String toString() {
        return "Конкретный прототип";
    }
}