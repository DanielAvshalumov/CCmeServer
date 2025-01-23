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
    public ResponseEntity<List<String>> getAllSkills() {
        List<String> res = skillService.getAllDistinctSkills();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Skill>> getUserSkills(@PathVariable("userId") Long userId) {
        List<Skill> res = skillService.getUserSkills();
        return ResponseEntity.ok(res);
    }
    
    // @PostMapping("/create")
    // public ResponseEntity<Skill> create(@RequestBody Skill skill) throws Exception {
    //     Skill res = skillService.create(skill);
    //     return ResponseEntity.ok(res);
    // }

    @PatchMapping("/license-picture/{skillId}")
    public ResponseEntity<Skill> updateLicensePicture(@PathVariable("skillId") Long id, @RequestParam("file") MultipartFile file) {
        Skill res = skillService.addLicensePicture(id,file);
        return ResponseEntity.ok(res);
    }
}
