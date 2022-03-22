package ro.robert.bugreport.controller.programmer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/homepage/programmer")
public class ProgrammerController {

    @GetMapping("")
    public ResponseEntity<String> programmer() {
        return new ResponseEntity<>("PROGRAMMER HERE BITCH!", HttpStatus.OK);
    }
}
