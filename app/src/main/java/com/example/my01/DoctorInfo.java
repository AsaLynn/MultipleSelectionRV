package com.example.my01;

import java.util.List;

/**
 * Created by think on 2018/1/11.
 */

public class DoctorInfo {

    @Override
    public String toString() {
        return "DoctorInfo{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    /**
     * data : [{"Childs":[{"DepName":"儿童保健科","ID":"6","IsFocus":1},{"DepName":"新生儿科","ID":"43","IsFocus":1},{"DepName":"儿内科","ID":"90","IsFocus":1},{"DepName":"儿外科","ID":"91","IsFocus":1}],"DataID":"5","DataName":"儿科"},{"Childs":[{"DepName":"内分泌科","ID":"8","IsFocus":0},{"DepName":"变态反应科","ID":"11","IsFocus":0},{"DepName":"呼吸内科","ID":"13","IsFocus":0},{"DepName":"心内科","ID":"36","IsFocus":0},{"DepName":"消化内科","ID":"48","IsFocus":0},{"DepName":"神经内科","ID":"56","IsFocus":0},{"DepName":"肾内科","ID":"63","IsFocus":0},{"DepName":"血液内科","ID":"67","IsFocus":0},{"DepName":"风湿免疫科","ID":"74","IsFocus":0},{"DepName":"普通内科","ID":"88","IsFocus":0}],"DataID":"9","DataName":"内科"},{"Childs":[{"DepName":"心外科","ID":"37","IsFocus":1},{"DepName":"手外科","ID":"40","IsFocus":1},{"DepName":"烧伤整形科","ID":"42","IsFocus":1},{"DepName":"泌尿外科","ID":"47","IsFocus":1},{"DepName":"神经外科","ID":"57","IsFocus":1},{"DepName":"肝胆外科","ID":"61","IsFocus":1},{"DepName":"胸外科","ID":"65","IsFocus":1},{"DepName":"血管外科","ID":"68","IsFocus":1},{"DepName":"乳腺外科","ID":"80","IsFocus":1},{"DepName":"肛肠外科","ID":"84","IsFocus":1},{"DepName":"普外科","ID":"89","IsFocus":1},{"DepName":"胃肠外科","ID":"92","IsFocus":1},{"DepName":"器官移植科","ID":"93","IsFocus":1},{"DepName":"创伤外科","ID":"94","IsFocus":1},{"DepName":"颌面外科","ID":"95","IsFocus":1}],"DataID":"14","DataName":"外科"},{"Childs":[{"DepName":"产科","ID":"3","IsFocus":1},{"DepName":"生殖医学科","ID":"51","IsFocus":1},{"DepName":"计划生育科","ID":"69","IsFocus":1}],"DataID":"17","DataName":"妇产科"},{"Childs":[{"DepName":"介入科","ID":"4","IsFocus":1},{"DepName":"医学影像科","ID":"10","IsFocus":1},{"DepName":"放疗科","ID":"41","IsFocus":1},{"DepName":"核医学科","ID":"45","IsFocus":1},{"DepName":"检验科","ID":"46","IsFocus":1},{"DepName":"物理治疗与康复科","ID":"50","IsFocus":1},{"DepName":"病理科","ID":"53","IsFocus":1},{"DepName":"营养科","ID":"62","IsFocus":1},{"DepName":"药剂科","ID":"66","IsFocus":1},{"DepName":"超声科","ID":"70","IsFocus":1},{"DepName":"输血科","ID":"81","IsFocus":1},{"DepName":"放射科","ID":"86","IsFocus":1},{"DepName":"其他辅助科室","ID":"87","IsFocus":1},{"DepName":"功能科","ID":"103","IsFocus":1}],"DataID":"85","DataName":"医技辅助类科室"},{"Childs":[{"DepName":"全科医学","ID":"7","IsFocus":0},{"DepName":"口腔科","ID":"12","IsFocus":0},{"DepName":"急诊科","ID":"38","IsFocus":0},{"DepName":"感染科","ID":"39","IsFocus":0},{"DepName":"皮肤性病科","ID":"54","IsFocus":0},{"DepName":"眼科","ID":"55","IsFocus":0},{"DepName":"精神心理科","ID":"58","IsFocus":0},{"DepName":"老年医学科","ID":"59","IsFocus":0},{"DepName":"耳鼻咽喉头颈科","ID":"60","IsFocus":0},{"DepName":"肿瘤科","ID":"64","IsFocus":0},{"DepName":"重症医学科","ID":"72","IsFocus":0},{"DepName":"骨科","ID":"75","IsFocus":0},{"DepName":"麻醉疼痛科","ID":"76","IsFocus":0},{"DepName":"其他临床科室","ID":"77","IsFocus":0},{"DepName":"中医科","ID":"78","IsFocus":0},{"DepName":"预防保健科","ID":"79","IsFocus":0},{"DepName":"中西医结合科","ID":"82","IsFocus":0},{"DepName":"传统医学","ID":"83","IsFocus":0},{"DepName":"职业病科","ID":"101","IsFocus":0},{"DepName":"高压氧科","ID":"102","IsFocus":0},{"DepName":"综合科","ID":"104","IsFocus":0}],"DataID":0,"DataName":"其它"}]
     * msg : success
     * status : 0
     */


    private String msg;
    private int status;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "DataID='" + DataID + '\'' +
                    ", DataName='" + DataName + '\'' +
                    ", Childs=" + Childs +
                    '}';
        }

        /**
         * Childs : [{"DepName":"儿童保健科","ID":"6","IsFocus":1},{"DepName":"新生儿科","ID":"43","IsFocus":1},{"DepName":"儿内科","ID":"90","IsFocus":1},{"DepName":"儿外科","ID":"91","IsFocus":1}]
         * DataID : 5
         * DataName : 儿科
         */



        private String DataID;
        private String DataName;
        private List<ChildsBean> Childs;

        public String getDataID() {
            return DataID;
        }

        public void setDataID(String DataID) {
            this.DataID = DataID;
        }

        public String getDataName() {
            return DataName;
        }

        public void setDataName(String DataName) {
            this.DataName = DataName;
        }

        public List<ChildsBean> getChilds() {
            return Childs;
        }

        public void setChilds(List<ChildsBean> Childs) {
            this.Childs = Childs;
        }

        public static class ChildsBean {
            @Override
            public String toString() {
                return "ChildsBean{" +
                        "DepName='" + DepName + '\'' +
                        ", ID='" + ID + '\'' +
                        ", IsFocus=" + IsFocus +
                        '}';
            }

            /**
             * DepName : 儿童保健科
             * ID : 6
             * IsFocus : 1
             */


            private String DepName;
            private String ID;
            private int IsFocus;

            public String getDepName() {
                return DepName;
            }

            public void setDepName(String DepName) {
                this.DepName = DepName;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public int getIsFocus() {
                return IsFocus;
            }

            public void setIsFocus(int IsFocus) {
                this.IsFocus = IsFocus;
            }
        }
    }
}
