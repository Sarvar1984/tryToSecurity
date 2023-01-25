package uz.forLearn.demo.rest.response;

import lombok.Data;
import org.springframework.http.HttpStatus;


import javax.persistence.SecondaryTable;
import java.util.*;

@Data
public class SingleResponse {

    private boolean success;
    private String message;
    private Object data;
    private List<Object> dataList;
    private Map<String,Object>meta=new HashMap<>();
    private HttpStatus httpStatus;


    public SingleResponse(boolean success, String message) {
        this.success = success;
        this.message = message;

        if(success)
            this.httpStatus=HttpStatus.OK;

    }


    public SingleResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;

        if(data instanceof List){
            this.dataList=(List)data;
        }else if (data instanceof Set){
            this.dataList=(List<Object>) data;
        }
        else this.data=data;

        if(success)
            this.httpStatus=HttpStatus.OK;

    }
}
