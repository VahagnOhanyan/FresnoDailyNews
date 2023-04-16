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
@Table(name = "users")
public class User {
    @Id
    private String telegramId;
    private String firstName;
    private String lastName;
    private String username;

    public User(String telegramId, String firstName, String lastName, String username) {
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;

    }

}
