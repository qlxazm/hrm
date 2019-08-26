package org.java.hrm.domain;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Long id;
    private String content;
    private String source;
    private String target;
    private Date releaseTime;

    public Message(String content, String source, String target) {
        this.content = content;
        this.source = source;
        this.target = target;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", releaseTime=" + releaseTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
}
