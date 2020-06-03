package com.example.webservice.model;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class SimpleEmail {

    private Set<String> toSet;

    private String subject;

    private String content;

    private boolean isHtml;

    private Map<String,File> attachments;

    public Set<String> getToSet() {
        return toSet;
    }

    public void setToSet(Set<String> toSet) {
        this.toSet = toSet;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean html) {
        isHtml = html;
    }

    public Map<String, File> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, File> attachments) {
        this.attachments = attachments;
    }
}
