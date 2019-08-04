package com.cch.hiot2.entity;

import java.util.List;

public class MyGson {


    /**
     * deviceId : c9b27b7ac80246dcb6817046a7e79cbe
     * title : 智能灯_dev_7
     * dev_type : 1.0
     * mac : null
     * status : 1
     * created : 2018-12-17
     * updated : 2019-03-14
     * deviceimg : /uploadfiles/images/template/1544973823872.jpg
     * description :
     * updatastreamDataDtoList : [{"upDataStreamId":"03fd8393224146fea625bff183f7155d","title":"数值型单向通道","data_type":1,"measureunit":null,"datastreamlink":null,"dataList":[{"upDataStreamId":"03fd8393224146fea625bff183f7155d","timing":"2019-07-29 09:19:25","value":"28.9"}],"direction":1},{"upDataStreamId":"6fb3dc3dc54b420591bd637aa7bcf0ee","title":"数值型双向通道","data_type":1,"measureunit":null,"datastreamlink":{"id":"f3f89c541bf94f0bab8b18bfe6197a13","title":"数值型双向通道","updatastream":null,"downdatastream":{"id":"00617acc392040bfa373072a0c29c4f9","title":"数值型双向通道","data_type":1,"device":null,"configunits":null,"configurations":null,"messages":null,"locations":null,"switches":null,"datastreamlink":null,"direction":2}},"dataList":[{"upDataStreamId":"6fb3dc3dc54b420591bd637aa7bcf0ee","timing":"2019-07-29 09:19:35","value":"30.1"}],"direction":1},{"upDataStreamId":"d50fb88859b54120897f82d3efee21f6","title":"开关","data_type":2,"measureunit":null,"datastreamlink":{"id":"85a10e19afb44086b907a7d4f0a0c8be","title":"开关","updatastream":null,"downdatastream":{"id":"48e0a6f3bb654015a8c1be0ef02c6be3","title":"开关","data_type":2,"device":null,"configunits":null,"configurations":null,"messages":null,"locations":null,"switches":null,"datastreamlink":null,"direction":2}},"dataList":[{"upDataStreamId":"d50fb88859b54120897f82d3efee21f6","timing":"2019-07-03 20:10:32","status":0}],"direction":1},{"upDataStreamId":"67eea7438d514583a70c5503abe5633a","title":"GPS型单向通道","data_type":3,"measureunit":null,"datastreamlink":null,"dataList":[{"upDataStreamId":"67eea7438d514583a70c5503abe5633a","timing":"2019-07-29 09:16:28","longitude":"102.435916","latitude":"24.898665","elevation":null}],"direction":1},{"upDataStreamId":"0afbf359d84b401ca651ade7b9d771ab","title":"GPS型双向通道","data_type":3,"measureunit":null,"datastreamlink":{"id":"06bb3035800c45798aa6d44fc356c9d8","title":"GPS型双向通道","updatastream":null,"downdatastream":{"id":"4bf1332db1934a51a858e24b14c6fb7b","title":"GPS型双向通道","data_type":3,"device":null,"configunits":null,"configurations":null,"messages":null,"locations":null,"switches":null,"datastreamlink":null,"direction":2}},"dataList":[{"upDataStreamId":"0afbf359d84b401ca651ade7b9d771ab","timing":"2019-07-29 09:17:15","longitude":"102.435654","latitude":"24.898525","elevation":null}],"direction":1},{"upDataStreamId":"27535cb441f2442490efc2036051965b","title":"GPS型双向通道wushuju","data_type":3,"measureunit":null,"datastreamlink":{"id":"ff86e1ef0e3f4e4a87d040626b84a6dc","title":"GPS型双向通道wushuju","updatastream":null,"downdatastream":{"id":"1a46544ea68a46adbaf7b231195f010c","title":"GPS型双向通道wushuju","data_type":3,"device":null,"configunits":null,"configurations":null,"messages":null,"locations":null,"switches":null,"datastreamlink":null,"direction":2}},"dataList":null,"direction":1},{"upDataStreamId":"0dc37bf7f2664d7c9445f68036da85a7","title":"文本型单向通道","data_type":4,"measureunit":null,"datastreamlink":null,"dataList":[{"upDataStreamId":"0dc37bf7f2664d7c9445f68036da85a7","timing":"2019-07-29 09:18:28","news":"文本数据11111","isRead":0}],"direction":1},{"upDataStreamId":"f3249823a52f4b67a6c1c36e2aac8563","title":"文本型双向通道","data_type":4,"measureunit":null,"datastreamlink":{"id":"1bdd33b6af57437f87a96f41d2766f31","title":"文本型双向通道","updatastream":null,"downdatastream":{"id":"6c2211540ac24c04ad5e63d41d229cd2","title":"文本型双向通道","data_type":4,"device":null,"configunits":null,"configurations":null,"messages":null,"locations":null,"switches":null,"datastreamlink":null,"direction":2}},"dataList":[{"upDataStreamId":"f3249823a52f4b67a6c1c36e2aac8563","timing":"2019-07-29 09:18:40","news":"文本数据22222","isRead":0}],"direction":1}]
     * devicemetadataList : []
     */

    private String deviceId;
    private String title;
    private String dev_type;
    private Object mac;
    private int status;
    private String created;
    private String updated;
    private String deviceimg;
    private String description;
    private List<UpdatastreamDataDtoListBean> updatastreamDataDtoList;
    private List<?> devicemetadataList;

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

    public Object getMac() {
        return mac;
    }

    public void setMac(Object mac) {
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

    public List<UpdatastreamDataDtoListBean> getUpdatastreamDataDtoList() {
        return updatastreamDataDtoList;
    }

    public void setUpdatastreamDataDtoList(List<UpdatastreamDataDtoListBean> updatastreamDataDtoList) {
        this.updatastreamDataDtoList = updatastreamDataDtoList;
    }

    public List<?> getDevicemetadataList() {
        return devicemetadataList;
    }

    public void setDevicemetadataList(List<?> devicemetadataList) {
        this.devicemetadataList = devicemetadataList;
    }

    public static class UpdatastreamDataDtoListBean {
        /**
         * upDataStreamId : 03fd8393224146fea625bff183f7155d
         * title : 数值型单向通道
         * data_type : 1
         * measureunit : null
         * datastreamlink : null
         * dataList : [{"upDataStreamId":"03fd8393224146fea625bff183f7155d","timing":"2019-07-29 09:19:25","value":"28.9"}]
         * direction : 1
         */

        private String upDataStreamId;
        private String title;
        private int data_type;
        private Object measureunit;
        private Object datastreamlink;
        private int direction;
        private List<DataListBean> dataList;

        public String getUpDataStreamId() {
            return upDataStreamId;
        }

        public void setUpDataStreamId(String upDataStreamId) {
            this.upDataStreamId = upDataStreamId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getData_type() {
            return data_type;
        }

        public void setData_type(int data_type) {
            this.data_type = data_type;
        }

        public Object getMeasureunit() {
            return measureunit;
        }

        public void setMeasureunit(Object measureunit) {
            this.measureunit = measureunit;
        }

        public Object getDatastreamlink() {
            return datastreamlink;
        }

        public void setDatastreamlink(Object datastreamlink) {
            this.datastreamlink = datastreamlink;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * upDataStreamId : 03fd8393224146fea625bff183f7155d
             * timing : 2019-07-29 09:19:25
             * value : 28.9
             */

            private String upDataStreamId;
            private String timing;
            private String value;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
