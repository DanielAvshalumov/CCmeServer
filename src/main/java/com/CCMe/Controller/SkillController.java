package com.CCMe.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CCMe.Model.Skill;
import com.CCMe.Service.SkillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skill")
public class SkillController {
    
    private final SkillService skillService;

    @GetMapping("/")
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> res = skillService.getAllSkills();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Skill>> getUserSkills(@PathVariable("userId") Long userId) {
        List<Skill> res = skillService.getUserSkills();
        return ResponseEntity.ok(res);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Skill> create(@RequestBody Skill skill) throws Exception {
        Skill res = skillService.create(skill);
        return ResponseEntity.ok(res);
    }
}
