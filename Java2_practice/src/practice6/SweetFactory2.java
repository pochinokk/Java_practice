package practice6;

public class SweetFactory2 implements AbstractSweetFactory {
    public Cake createCake()
    {
        return new ChocolateCake();
    }
    public Sweets createSweets()
    {
        return new ChocolateSweets();
    }
}
