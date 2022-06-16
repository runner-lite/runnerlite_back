package ru.runnerlite.mail.model;

public class Letter {
    private String to;
    private String topic;
    private String message;
    public Letter(String to, String topic, String message) {
        this.to = to;
        this.message = message;
        this.topic = topic;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
