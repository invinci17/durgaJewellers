package durgaJewellers.durgaJewellers.configuration.environment;

public class ProdDB implements DB{

    public String getData(){
        return "Prod db";
    }
}