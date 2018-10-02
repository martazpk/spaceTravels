package pl.marta.kopp.tourist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.marta.kopp.tourist.domain.service.TouristService;
import pl.marta.kopp.tourist.dto.TouristDto;

@RestController
@RequestMapping("/tourist")
public class TouristController {
    private final TouristService service;

    @Autowired
    public TouristController(TouristService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public long create(@ModelAttribute TouristDto dto){
        return service.add(dto);
    }

    @RequestMapping(path = "/{identifier}",method = RequestMethod.DELETE)
    public void remove(@PathVariable long identifier){
        service.delete(identifier);
    }

    @RequestMapping(path = "/{identifier}",method = RequestMethod.GET)
    public TouristDto get(@PathVariable long identifier){
        return service.get(identifier);
    }

    @RequestMapping(path="/{identifier}", method = RequestMethod.PUT)
    public void update(@PathVariable long identifier,@ModelAttribute TouristDto dto){
        service.update(identifier,dto);
    }
}
