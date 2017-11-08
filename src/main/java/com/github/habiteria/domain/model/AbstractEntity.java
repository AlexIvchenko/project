package com.github.habiteria.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString(of = "id")
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements Identifiable<String> {
    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Override
    public String getId() {
        return id;
    }

    /* audit fields */
    @CreatedBy
    @Column(name = "created_by")
    @JsonIgnore
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    @JsonIgnore
    private String lastModifiedBy;

    /* equals and hash code */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public final int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
