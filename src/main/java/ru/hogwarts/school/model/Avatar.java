package ru.hogwarts.school.model;

import javax.persistence.*;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filePath;
    private String mediaType;
    private Long fileSize;

    @Lob
    private byte[] data;

    @OneToOne
    private Student student;

    public Avatar () {
    }

    public Avatar(Long id, String filePath, String mediaType, Long fileSize, byte[] data, Student student) {
        this.id = id;
        this.filePath = filePath;
        this.mediaType = mediaType;
        this.fileSize = fileSize;
        this.data = data;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public Student getStudent() {
        return student;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
