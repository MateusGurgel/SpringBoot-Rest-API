package com.gurgel.apigateway.controllers;

import com.gurgel.apigateway.services.BasicMath;
import com.gurgel.apigateway.services.NumberConverter;
import com.gurgel.apigateway.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @RequestMapping(value="/sum/{num}/{num2}", method=RequestMethod.GET)
    public double sum(@PathVariable("num") String num,
                            @PathVariable("num2") String num2
    ) throws Exception {

        if(!NumberConverter.isNumber(num) || !NumberConverter.isNumber(num2)){
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }

        return BasicMath.sum(NumberConverter.convertToDouble(num), NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value="/sub/{num}/{num2}", method=RequestMethod.GET)
    public double sub(@PathVariable("num") String num,
                      @PathVariable("num2") String num2
    ) throws Exception {

        if(!NumberConverter.isNumber(num) || !NumberConverter.isNumber(num2)){
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }

        return BasicMath.sub(NumberConverter.convertToDouble(num), NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value="/sqrt/{num}", method=RequestMethod.GET)
    public double sqrt(@PathVariable("num") String num) throws Exception {

        if(!NumberConverter.isNumber(num)){
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }

        return BasicMath.sqrt(NumberConverter.convertToDouble(num));
    }

    @RequestMapping(value="/mean/{num}/{num2}", method=RequestMethod.GET)
    public double mean(@PathVariable("num") String num,
                      @PathVariable("num2") String num2
    ) throws Exception {

        if(!NumberConverter.isNumber(num) || !NumberConverter.isNumber(num2)){
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }

        return BasicMath.mean(NumberConverter.convertToDouble(num), NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value="/div/{num}/{num2}", method=RequestMethod.GET)
    public double div(@PathVariable("num") String num,
                      @PathVariable("num2") String num2
    ) throws Exception {

        if(!NumberConverter.isNumber(num) || !NumberConverter.isNumber(num2)){
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }

        return BasicMath.div(NumberConverter.convertToDouble(num), NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value="/mul/{num}/{num2}", method=RequestMethod.GET)
    public double mul(@PathVariable("num") String num,
                      @PathVariable("num2") String num2
    ) throws Exception {

        if(!NumberConverter.isNumber(num) || !NumberConverter.isNumber(num2)){
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }

        return BasicMath.mul(NumberConverter.convertToDouble(num), NumberConverter.convertToDouble(num2));
    }
}
