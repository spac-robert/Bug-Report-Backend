package ro.robert.bugreport.controller.tester;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/homepage/tester")
public class TesterController {

    @GetMapping("")
    public ResponseEntity<String> tester() {
        return new ResponseEntity<>("TESTER HERE BITCH!", HttpStatus.OK);
    }

}
