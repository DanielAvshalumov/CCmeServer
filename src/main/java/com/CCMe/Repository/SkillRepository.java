package com.CCMe.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CCMe.Model.Skill;
import com.CCMe.Model.User;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>{
    List<Skill> findAllByUser(User user);

    List<Skill> findDistinctBy();

    List<Skill> findByNameIn(List<String> names);
}
