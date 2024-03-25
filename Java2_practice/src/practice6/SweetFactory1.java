package practice6;

public class SweetFactory1 implements AbstractSweetFactory {
    public Cake createCake()
    {
        return new Cheesecake();
    }
    public Sweets createSweets()
    {
        return new StrawberrySweets();
    }

}
