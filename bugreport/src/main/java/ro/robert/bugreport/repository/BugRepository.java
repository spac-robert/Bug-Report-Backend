package ro.robert.bugreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.robert.bugreport.model.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {

}