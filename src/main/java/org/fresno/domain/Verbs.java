package org.fresno.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * todo Document type User
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "verbs")
public class Verbs {
    @Id
    private String verb;
    private String forms;


    public Verbs(String verb, String forms) {
        this.verb = verb;
        this.forms = forms;
    }
}
