package ro.robert.bugreport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.robert.bugreport.model.Bug;
import ro.robert.bugreport.repository.BugRepository;

import java.util.List;

@Service
public class TesterService {
    @Autowired
    private BugRepository bugRepository;

    public List<Bug> getBugs() {
        return bugRepository.findAll();
    }

    public Bug addBug(Bug bug) {
        return bugRepository.save(bug);
    }
}
