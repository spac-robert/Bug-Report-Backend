package ro.robert.bugreport.controller.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.robert.bugreport.model.Bug;
import ro.robert.bugreport.service.TesterService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/homepage/tester")
public class TesterController {
    @Autowired
    private TesterService testerService;

    @GetMapping("")
    public List<Bug> index() {
        return testerService.getBugs();
    }

}
