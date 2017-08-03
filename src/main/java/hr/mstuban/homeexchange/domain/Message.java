package hr.mstuban.homeexchange.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * Created by mstuban on 02/08/17.
 */
@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "MESSAGE_SEQUENCE", sequenceName = "MESSAGE_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "AUTHOR")
    private String author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User receiver;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED_ON")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createdOn;

    @Column(name = "HAS_BEEN_READ")
    private boolean hasBeenRead;

    public Message() {

    }

    public Message(String author, User receiver, String content, LocalDateTime createdOn, boolean hasBeenRead) {
        this.author = author;
        this.receiver = receiver;
        this.content = content;
        this.createdOn = createdOn;
        this.hasBeenRead = hasBeenRead;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isHasBeenRead() {
        return hasBeenRead;
    }

    public void setHasBeenRead(boolean hasBeenRead) {
        this.hasBeenRead = hasBeenRead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

