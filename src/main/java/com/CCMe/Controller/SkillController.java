package com.CCMe.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        List<Skill> res = skillService.getAllDistinctSkills();
        return ResponseEntity.ok(res);
    }

    @GetMapping("")
    public ResponseEntity<List<Skill>> getSkillLimitBy(@RequestParam("limit") Integer limit) {
        List<Skill> res = skillService.getPageableSkill(limit);
        return ResponseEntity.ok(res);
    }

}
