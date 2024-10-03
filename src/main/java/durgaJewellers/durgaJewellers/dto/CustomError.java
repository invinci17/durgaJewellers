package durgaJewellers.durgaJewellers.dto;

import lombok.Data;

@Data
public class CustomError {


    private String message;

    public CustomError(String message){
        this.message=message;
    }

}
