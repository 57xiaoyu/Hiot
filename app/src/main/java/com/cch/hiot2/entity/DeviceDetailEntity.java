package com.cch.hiot2.entity;

import java.util.ArrayList;
import java.util.List;

public class DeviceDetailEntity {
    private String deviceId;//": "c2b1859957164348b97385aeed4634f6",
    private String title;//": "人体感应_dev_7",
    private String dev_type;//": "1.0",
    private String mac;//": null,
    private int status;//": 1,
    private String created;//": "2018-12-18",
    private String updated;//": "2019-03-14",
    private String deviceimg;//": "/uploadfiles/images/template/1544974126039.jpg",
    private String description;//": "",
    private List<UpdataStreamData> updatastreamDataDtoList;//": [
    private List<Metadata> devicemetadataList;//": [

    public class UpdataStreamData {
        private int data_type;//	2
        private DatastreamLink datastreamlink;//	Object
        private int direction;//	1
        private String title;//	开关
        private String upDataStreamId;//	34490d14254d447b99df180fb6c86def
        private ArrayList<DataList> dataList;//开关类型实体

        @Override
        public String toString() {
            return "UpdataStreamData{" +
                    "data_type=" + data_type +
                    ", datastreamlink=" + datastreamlink +
                    ", direction=" + direction +
                    ", title='" + title + '\'' +
                    ", upDataStreamId='" + upDataStreamId + '\'' +
                    ", dataList=" + dataList +
                    '}';
        }

        public ArrayList<DataList> getDataList() {
            return dataList;
        }

        public void setDataList(ArrayList<DataList> dataList) {
            this.dataList = dataList;
        }



        public int getData_type() {
            return data_type;
        }

        public void setData_type(int data_type) {
            this.data_type = data_type;
        }

        public DatastreamLink getDatastreamlink() {
            return datastreamlink;
        }

        public void setDatastreamlink(DatastreamLink datastreamlink) {
            this.datastreamlink = datastreamlink;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpDataStreamId() {
            return upDataStreamId;
        }

        public void setUpDataStreamId(String upDataStreamId) {
            this.upDataStreamId = upDataStreamId;
        }
    }
    @Override
    public String toString() {
        return "DeviceDetailEntity{" +
                "deviceId='" + deviceId + '\'' +
                ", title='" + title + '\'' +
                ", dev_type='" + dev_type + '\'' +
                ", mac='" + mac + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", deviceimg='" + deviceimg + '\'' +
                ", description='" + description + '\'' +
                ", updatastreamDataDtoList=" + updatastreamDataDtoList +
                ", devicemetadataList=" + devicemetadataList +
                '}';
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public List<UpdataStreamData> getUpdatastreamDataDtoList() {
        return updatastreamDataDtoList;
    }

    public void setUpdatastreamDataDtoList(List<UpdataStreamData> updatastreamDataDtoList) {
        this.updatastreamDataDtoList = updatastreamDataDtoList;
    }

    public List<Metadata> getDevicemetadataList() {
        return devicemetadataList;
    }

    public void setDevicemetadataList(List<Metadata> devicemetadataList) {
        this.devicemetadataList = devicemetadataList;
    }



    public class Metadata {
    }

    public class DatastreamLink {
        private Downdatastream downdatastream;//	Object
        private String id;//30a1641a5bb84d08a671103e4c10b53c
        private String title;//	开关

        @Override
        public String toString() {
            return "DatastreamLink{" +
                    "downdatastream=" + downdatastream +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        public Downdatastream getDowndatastream() {
            return downdatastream;
        }

        public void setDowndatastream(Downdatastream downdatastream) {
            this.downdatastream = downdatastream;
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
    }

    public class Downdatastream {
        private int data_type;//2
        private int direction;//	2
        private String id;//	38759353979a4979ad6f1467e081f688
        private String title;//	开关

        @Override
        public String toString() {
            return "Downdatastream{" +
                    "data_type=" + data_type +
                    ", direction=" + direction +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        public int getData_type() {
            return data_type;
        }

        public void setData_type(int data_type) {
            this.data_type = data_type;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
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
    }


    public class DataList  {
        private String upDataStreamId;
        private String timing;
        private String value;//数值型数据值
        private int status;//开关型数据状态
        private double latitude;//	24.898525 GPS数据 纬度
        private double longitude;// 	102.435654  GPS数据 经度
        private int  isRead;//	0  文本数据 是否已读
        private String news;//	 文本数据 :文本数据22222


        @Override
        public String toString() {
            return "DataList{" +
                    "upDataStreamId='" + upDataStreamId + '\'' +
                    ", timing='" + timing + '\'' +
                    ", value='" + value + '\'' +
                    ", status=" + status +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", isRead=" + isRead +
                    ", news='" + news + '\'' +
                    '}';
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getNews() {
            return news;
        }

        public void setNews(String news) {
            this.news = news;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUpDataStreamId() {
            return upDataStreamId;
        }

        public void setUpDataStreamId(String upDataStreamId) {
            this.upDataStreamId = upDataStreamId;
        }

        public String getTiming() {
            return timing;
        }

        public void setTiming(String timing) {
            this.timing = timing;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
