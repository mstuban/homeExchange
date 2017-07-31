package hr.mstuban.homeexchange.domain;

import javax.persistence.*;

/**
 * Created by mstuban on 31/07/17.
 */

@Entity
@Table(name = "IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "IMAGE_SEQUENCE", sequenceName = "IMAGE_SEQUENCE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATA")
    private byte[] data;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @OneToOne
    @JoinColumn(name = "HOME_ID")
    private Home home;

    public Image() {
    }

    public Image(String originalFilename, byte[] bytes, Home home, String contentType) {
        this.name = originalFilename;
        this.data = bytes;
        this.home = home;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
