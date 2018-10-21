package com.montego.flow.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Note too long(more than 2kb)")
    private String text;

    @Length(max = 255, message = "Tag too long")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany
    @JoinTable(
            name="note_likes",
            joinColumns = {@JoinColumn(name="note_id")},
            inverseJoinColumns = {@JoinColumn(name="user_id")}
    )
    private Set<User> likes = new HashSet<>();

    public Note(String text, String tag, User user){
        this.text = text;
        this.tag = tag;
        this.author = user;
    }
    public Note(){

    }
}