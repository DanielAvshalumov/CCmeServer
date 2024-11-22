package com.CCMe.Model.Request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String company;
    private String email;
}
