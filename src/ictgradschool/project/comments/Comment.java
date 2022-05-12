package ictgradschool.project.comments;

public class Comment implements Comparable<Comment>{
    private int comId;
    private int userId;
    private int artId;
    private String comDate;
    private String comContent;
    private String userName;
    private int parentId = 0;
    private boolean hidden = false;

    public Comment() {
    }

    public Comment(int comId, int userId, String userName , int artId, String comDate, String comContent, int parentId, boolean hidden) {
        this.comId = comId;
        this.userId = userId;
        this.artId = artId;
        this.comDate = comDate;
        this.comContent = comContent;
        this.userName = userName;
        this.parentId = parentId;
        this.hidden = hidden;
    }

    public Comment(int userId, int artId, String comDate, String comContent) {
        this.userId = userId;
        this.artId = artId;
        this.comDate = comDate;
        this.comContent = comContent;
    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public String getComDate() {
        return comDate;
    }

    public void setComDate(String comDate) {
        this.comDate = comDate;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public int getParentId() {return parentId;}

    public void setParentId(int parentId) {this.parentId = parentId;}

    public boolean getHidden() {return hidden;}

    public void setHidden(boolean hidden) {this.hidden = hidden;}

    @Override
    public String toString() {
        return "Comment{" +
                "comId=" + comId +
                ", userId=" + userId +
                ", artId=" + artId +
                ", comDate='" + comDate + '\'' +
                ", comContent='" + comContent + '\'' +
                '}';
    }

    @Override
    public int compareTo(Comment otherComment) {
        return (this.comDate).compareTo(otherComment.comDate);
    }
}