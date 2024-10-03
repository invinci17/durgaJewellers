package durgaJewellers.durgaJewellers.configuration.environment;

public class DevDB implements DB{
    @Override
    public String getData() {
        return "Dev db";
    }
}
