package com.event.producer.controllers;


import com.event.producer.services.ProducerService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jsondoc.core.annotation.ApiParams;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.InputMismatchException;
import java.util.Objects;

@RestController
public class ProducerServiceController {

    @Autowired
    private ProducerService producerService;

    @ApiParams(queryparams = {
            @ApiQueryParam(name = "fuellid", description = "Fuellid status Open/Closed", required = true),
            @ApiQueryParam(name = "city", description = "Any Indian City", required = true)
    })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Response OK"),
            @ApiResponse(code = 400, message = "Input Exception"),
            @ApiResponse(code = 404, message = "Resource Not Found Exception"),
            @ApiResponse(code = 500, message = "Internal Service Exception")
    })
    @RequestMapping(value = "/trigger", method = RequestMethod.POST)
    public void triggerEvent(@RequestParam(required = true) Boolean fuellid, @RequestParam(required = true) String city) {
        if (Objects.isNull(fuellid) || !StringUtils.hasText(city)) {
            throw new InputMismatchException("Bad input data");
        }
        producerService.calclulateFuelCost(fuellid, city);
    }
}
