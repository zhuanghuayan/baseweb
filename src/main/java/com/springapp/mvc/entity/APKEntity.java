package com.springapp.mvc.entity;



/**
 * Created by wuqinghai on 16/10/20.
 */
public class APKEntity {
    @Override
    public String toString() {
        return "APKEntity{" +
                "softId='" + softId + '\'' +
                ", typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", name='" + name + '\'' +
                ", packName='" + packName + '\'' +
                ", verNo='" + verNo + '\'' +
                ", packUrl='" + packUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", isuTime='" + isuTime + '\'' +
                ", isForce='" + isForce + '\'' +
                ", updateLog='" + updateLog + '\'' +
                ", about='" + about + '\'' +
                '}';
    }

    private String  softId	;//软件ID	String	是
    private String  typeId	;//类型ID	String	是
    private String  typeName;	//类型名称	String	是
    private String  name	;//名称	String	是
    private String  packName;//	包名	String	是
    private String  verNo	;//版本号	String	是
    private String  packUrl;//安装包下载地址	String	是
    private String  imgUrl;	//图片下载地址	String	是
    private String  isuTime	;//发布时间	String	是
    private String  isForce	;//是否强制更新	String	是
    private String  updateLog;//	更新日志	String
    private String  about	;//介绍	String

    public String getSoftId() {
        return softId;
    }

    public void setSoftId(String softId) {
        this.softId = softId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getVerNo() {
        return verNo;
    }

    public void setVerNo(String verNo) {
        this.verNo = verNo;
    }

    public String getPackUrl() {
        return packUrl;
    }

    public void setPackUrl(String packUrl) {
        this.packUrl = packUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsuTime() {
        return isuTime;
    }

    public void setIsuTime(String isuTime) {
        this.isuTime = isuTime;
    }

    public String getIsForce() {
        return isForce;
    }

    public void setIsForce(String isForce) {
        this.isForce = isForce;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


}
