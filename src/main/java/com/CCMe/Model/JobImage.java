package com.CCMe.Model;

import com.CCMe.Entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobImage extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Job job;

    private String iamge;

}
