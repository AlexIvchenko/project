package com.github.netcracker2017team.project.domain.model.template;

import com.github.netcracker2017team.project.domain.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Alex Ivchenko
 */
@Setter
@Getter
@ToString(callSuper = true, exclude = "owner")
@Entity
@DiscriminatorValue("doer")
public class UserContinuationTemplate extends ContinuationTemplate {

    @ManyToOne
    @JoinColumn(name = "user_owner_id")
    private User owner;
}
