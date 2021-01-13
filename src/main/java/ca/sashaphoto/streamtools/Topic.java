package ca.sashaphoto.streamtools;

public class Topic {
    public String getContent() {
        return content;
    }

    private final String content;

    public Topic(String content) {
        this.content = content;
    }
}
