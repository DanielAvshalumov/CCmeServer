package com.CCMe.Model.Request;

import com.CCMe.Model.Role;
import com.CCMe.Model.User;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private Role role;
    private String email;
    private String firstName;
    private String lastName;
    private String company;

    public UserResponse(User user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.company = user.getCompany();
    }
}
