package org.fresno.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "newsduplicatestats")
public class NewsDuplicateStats {
    @Id
    private Long id;
    private String text;
    private String text2;
    private int score;

}
