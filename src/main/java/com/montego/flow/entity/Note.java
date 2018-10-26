package com.montego.flow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//
@Entity
@Table
@ToString(of = {"id", "tag", "text"})
@EqualsAndHashCode(of = {"id"})
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Note too long(more than 2kb)")
    @JsonView(Views.IdTextTag.class)
    private String text;

    @Length(max = 255, message = "Tag too long")
    @JsonView(Views.IdTextTag.class)
    private String tag;
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime creationDate;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User author;
//
//    @ManyToMany
//    @JoinTable(
//            name = "note_likes",
//            joinColumns = {@JoinColumn(name = "note_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")}
//    )
//    private Set<User> likes = new HashSet<>();

    public Note(String text, String tag) {
        this.text = text;
        this.tag = tag;
        //this.author = user;
    }

    public Note() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}