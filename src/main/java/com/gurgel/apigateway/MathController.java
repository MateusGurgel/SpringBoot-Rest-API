package com.gurgel.apigateway;

import com.gurgel.apigateway.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @RequestMapping(value="/math/{num}/{num2}", method=RequestMethod.GET)
    public double sum(@PathVariable("num") String num,
                            @PathVariable("num2") String num2
    ) throws Exception {

        if(!isNumber(num) || !isNumber(num2)){
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }

        return convertToDouble(num) + convertToDouble(num2);
    }

    private double convertToDouble(String strNumber){
        if (strNumber == null) return 0D;

        String number = strNumber.replaceAll(",", ".");
        if (isNumber(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumber(String strNumber){
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
