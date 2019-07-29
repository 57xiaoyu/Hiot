package com.cch.hiot2.entity;

//用户持有设备
public class HolderDeviceEntity {
    private String id;//": "c2b1859957164348b97385aeed4634f6",
    private String title;//": "人体感应_dev_7",
    private String dev_type;//": "1.0",
    private String mac;//": null,
    private int status;//": 1,
    private String created;//": "2018-12-18",
    private String updated;//": "2019-03-14",
    private String deviceimg;//": "/uploadfiles/images/template/1544974126039.jpg",
    private String description;//": "",
    private String template;//": null,
    private String user;//": null,
    private String devicelinks;//": null,
    private String holders;//": null,
    private String updatastreams;//": null,
    private String downdatastreams;//": null,
    private String is_bind;//": null

    @Override
    public String toString() {
        return "HolderDeviceEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", dev_type='" + dev_type + '\'' +
                ", mac='" + mac + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", deviceimg='" + deviceimg + '\'' +
                ", description='" + description + '\'' +
                ", template='" + template + '\'' +
                ", user='" + user + '\'' +
                ", devicelinks='" + devicelinks + '\'' +
                ", holders='" + holders + '\'' +
                ", updatastreams='" + updatastreams + '\'' +
                ", downdatastreams='" + downdatastreams + '\'' +
                ", is_bind='" + is_bind + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDev_type() {
        return dev_type;
    }

    public void setDev_type(String dev_type) {
        this.dev_type = dev_type;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDeviceimg() {
        return deviceimg;
    }

    public void setDeviceimg(String deviceimg) {
        this.deviceimg = deviceimg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevicelinks() {
        return devicelinks;
    }

    public void setDevicelinks(String devicelinks) {
        this.devicelinks = devicelinks;
    }

    public String getHolders() {
        return holders;
    }

    public void setHolders(String holders) {
        this.holders = holders;
    }

    public String getUpdatastreams() {
        return updatastreams;
    }

    public void setUpdatastreams(String updatastreams) {
        this.updatastreams = updatastreams;
    }

    public String getDowndatastreams() {
        return downdatastreams;
    }

    public void setDowndatastreams(String downdatastreams) {
        this.downdatastreams = downdatastreams;
    }

    public String getIs_bind() {
        return is_bind;
    }

    public void setIs_bind(String is_bind) {
        this.is_bind = is_bind;
    }
}
