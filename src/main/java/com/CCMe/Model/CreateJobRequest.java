package com.CCMe.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobRequest {
    private String title;
    private String company;
    private String location;
    private String description;
    private List<String> skills;
}
