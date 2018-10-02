package pl.marta.kopp.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.marta.kopp.flight.domain.service.FlightService;
import pl.marta.kopp.flight.dto.FlightDto;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService service;

    @Autowired
    public FlightController(FlightService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public long create(@ModelAttribute FlightDto dto){
        return service.add(dto);
    }

    @RequestMapping(path = "/{identifier}",method = RequestMethod.DELETE)
    public void remove(@PathVariable long identifier){
        service.delete(identifier);
    }

    @RequestMapping(path = "/{identifier}",method = RequestMethod.GET)
    public FlightDto get(@PathVariable long identifier){
        return service.get(identifier);
    }

    @RequestMapping(path="/{identifier}", method = RequestMethod.PUT)
    public void update(@PathVariable long identifier,@ModelAttribute FlightDto dto){
        service.update(identifier,dto);
    }
}
