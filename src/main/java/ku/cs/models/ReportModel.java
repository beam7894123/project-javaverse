package ku.cs.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ReportModel{
    private String topic;
    private String detail;
    private Integer voteScore;
    private String category;
    private String dateTime;
    private String authorName;
    private String solveProblem;
    private String status;
    private ArrayList<ReportModel> anything;

    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    String timeReport = localDateTime.format(formatter);

    public ReportModel(String topic, String detail, Integer voteScore, String category, String dateTime, String authorName) {
        this.topic = topic;
        this.detail = detail;
        this.voteScore = voteScore;
        this.category = category;
        this.dateTime = dateTime;
        this.authorName =  authorName;
    }

    public ReportModel(String topic, String detail, Integer voteScore, String category, String dateTime, String authorName, String solveProblem, String status) {
        this.topic = topic;
        this.detail = detail;
        this.voteScore = voteScore;
        this.category = category;
        this.dateTime = dateTime;
        this.authorName = authorName;
        this.solveProblem = solveProblem;
        this.status = status;
    }

    public ReportModel() {
        this.authorName = authorName;
    }

    public String getSolveProblem() {
        return solveProblem;
    }

    public void setSolveProblem(String solveProblem) {
        this.solveProblem = solveProblem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getVoteScore() {
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

    // TIME CONVERTER !!! DO NOT TOUCH !!! ห้ามลบ ถ้าเกิด Error บอก beam7894123 ก่อน ไม่งั้นต่อยนะ >:< //
    private Date realDateTime;
    public void setRealDateTime(Date realDateTime) {
        this.realDateTime = realDateTime;
    }
    public Date getRealDateTime() {
        return realDateTime;
    }
    // TIME CONVERTER // END // END // END // END // END // END // END // END // END // END // END // END // END

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


}