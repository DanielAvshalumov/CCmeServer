package com.CCMe.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private Long id;
    private String field;
    private String company;
    private String location;
    private String decision;
    private String status;
    private String owner;
}
