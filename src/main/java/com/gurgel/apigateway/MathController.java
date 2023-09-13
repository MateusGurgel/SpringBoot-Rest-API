package com.gurgel.apigateway;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @RequestMapping(value="/math/{num}/{num2}", method=RequestMethod.GET)
    public float calculator(@PathVariable("num") Float num,
                            @PathVariable("num2") Float num2){

        return num + num2;
    }
}
