package com.CCMe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.Skill;
import com.CCMe.Model.User;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
    // List<Skill> findAllByUser(User user);

    List<Skill> findDistinctBy();

    @Query("SELECT DISTINCT s.name FROM Skill s")
    List<String> findDistinctSkillNames();

    List<Skill> findByNameIn(List<String> names);
    
}
