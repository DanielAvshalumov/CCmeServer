package com.CCMe.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.*;

import com.CCMe.Entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@NoArgsConstructor
public class VerificationCode extends AbstractEntity {

    private String code;
    
    @Setter
    private boolean emailSent = false;

    @OneToOne
    @JsonIgnore
    private User user;

    public VerificationCode(User user) {
        this.user = user;
        this.code = RandomStringUtils.random(6,false, true);
    }
}
