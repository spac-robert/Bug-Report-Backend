package ro.robert.bugreport.controller.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.robert.bugreport.dbo.BugReportRequest;
import ro.robert.bugreport.dbo.BugReportResponse;
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

    @PostMapping("/post")
    public BugReportResponse addBug(@RequestBody BugReportRequest bugReportRequest) {
        Bug bug = new Bug(bugReportRequest.getTesterName(),
                bugReportRequest.getBugName(),
                bugReportRequest.getDescription(),
                bugReportRequest.getSolved());
        Bug bug1 = testerService.addBug(bug);

        if (bug1 != null) {
            return new BugReportResponse(bug1.getId(), bug1.getBugName(), "Bug added successfully", true);
        } else
            return new BugReportResponse(0, bug.getBugName(), "Couldn't added", false);
    }
}
