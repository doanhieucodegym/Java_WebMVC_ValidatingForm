package com.DoanHieu.model;

import javafx.scene.paint.PhongMaterial;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Annotation;
@Component
public class PhoneNumber implements Validator {

    private String number;
    public String getNumber(){
        return number;
    }
    public void setNumber(String number){
        this.number= number;
    }

    public PhoneNumber() {
    }


    @Override
  public boolean supports(Class<?>clazz){
        return PhoneNumber.class.isAssignableFrom(clazz);

  }

    @Override
    public void validate(Object target, Errors errors) {
        PhoneNumber phoneNumber =(PhoneNumber)target;
        String number =phoneNumber.getNumber();
        ValidationUtils.rejectIfEmpty(errors,"number","number.empty");
        if(number.length()>11||number.length()<10){
            errors.rejectValue("number","number.length");
        }
        if(!number.startsWith("0")){
            errors.rejectValue("number","number.length");
        }
        if(!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("number", "number.matches");
        }
    }
}
