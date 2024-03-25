package practice6;

public class AutomobileFactory {
    public Automobile createAutomobile(int purpose) {
        if (purpose == 0) {
            return new Passenger_car();
        } else {
            return new Lorry();
        }
    }
}
