package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportModel {
    private static String topic;
    private static String detail;
    private static Integer voteScore;
    private String category;
    private String dateTime;
    private String authorName;

    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm:ss");
    String timeReport = localDateTime.format(formatter);

    public ReportModel(String topic, String detail, Integer voteScore, String category, String dateTime, String authorName) {
        this.topic = topic;
        this.detail = detail;
        this.voteScore = 0;
        this.category = category;
        this.dateTime = dateTime;
        this.authorName =  authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public static String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public static String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static Integer getVoteScore() {
        return voteScore;
    }

    public int addScore(int score){
        voteScore += score;
        return voteScore;
    }

    public void setVoteScore(Integer voteScore) {
        this.voteScore = voteScore;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
