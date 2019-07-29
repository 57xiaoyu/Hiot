package com.cch.hiot2.entity;

public class UserEntity {
    private String id;// "c352b3b56c1d490ea980e4e249589d29",
    private String password;// "c1aafc7e23f24ba11aae492f5caa2d97",
    private String lastlogin;// "2019-07-24 15:57:15",
    private String username;// "huawei",
    private String email;// "huawei@163.com",
    private int is_active;// 1,
    private String date_joined;// "2019-03-08",
    private int is_superuser;// 0,
    private int is_staff;// 1,
    private int is_developer;// 0,
    private String img;// "/uploadfiles/images/default/default_user.jpg",
    private String phone;// null,
    private String actions;// null,
    private String holders;// null,
    private String jobs;// null,
    private String scenarios;// null,
    private String schedules;// null,
    private String templates;// null,
    private String tokens;// null,
    private String triggers;// null,
    private String devices;// null

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", lastlogin='" + lastlogin + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", is_active=" + is_active +
                ", date_joined='" + date_joined + '\'' +
                ", is_superuser=" + is_superuser +
                ", is_staff=" + is_staff +
                ", is_developer=" + is_developer +
                ", img='" + img + '\'' +
                ", phone='" + phone + '\'' +
                ", actions='" + actions + '\'' +
                ", holders='" + holders + '\'' +
                ", jobs='" + jobs + '\'' +
                ", scenarios='" + scenarios + '\'' +
                ", schedules='" + schedules + '\'' +
                ", templates='" + templates + '\'' +
                ", tokens='" + tokens + '\'' +
                ", triggers='" + triggers + '\'' +
                ", devices='" + devices + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public int getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(int is_superuser) {
        this.is_superuser = is_superuser;
    }

    public int getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(int is_staff) {
        this.is_staff = is_staff;
    }

    public int getIs_developer() {
        return is_developer;
    }

    public void setIs_developer(int is_developer) {
        this.is_developer = is_developer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getHolders() {
        return holders;
    }

    public void setHolders(String holders) {
        this.holders = holders;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getScenarios() {
        return scenarios;
    }

    public void setScenarios(String scenarios) {
        this.scenarios = scenarios;
    }

    public String getSchedules() {
        return schedules;
    }

    public void setSchedules(String schedules) {
        this.schedules = schedules;
    }

    public String getTemplates() {
        return templates;
    }

    public void setTemplates(String templates) {
        this.templates = templates;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

    public String getTriggers() {
        return triggers;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }
}
