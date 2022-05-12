package ictgradschool.project.articles;

public class Article implements Comparable<Article>{

    private Integer artId;
    private Integer userId;
    private String userName;
    private String title;
    private String date;
    private String body;


    public Article() {
    }

    public Article(Integer artId, Integer userId, String userName, String title, String date, String body) {
        this.artId = artId;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public Article( Integer userId, String userName, String title, String date, String body) {
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.date = date;
        this.body = body;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artId=" + artId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public int compareTo(Article otherArticle) {
        return (this.title).compareTo(otherArticle.title);
    }
}

