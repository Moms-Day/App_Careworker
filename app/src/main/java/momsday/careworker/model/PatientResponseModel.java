package momsday.careworker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PatientResponseModel {

    @SerializedName("connectionRequests")
    private ArrayList<ConnectionRequest> connectionRequests = null;
    @SerializedName("inChargeList")
    private ArrayList<InChargeList> inChargeList = null;

    public ArrayList<ConnectionRequest> getConnectionRequests() {
        return connectionRequests;
    }

    public void setConnectionRequests(ArrayList<ConnectionRequest> connectionRequests) {
        this.connectionRequests = connectionRequests;
    }

    public ArrayList<InChargeList> getInChargeList() {
        return inChargeList;
    }

    public void setInChargeList(ArrayList<InChargeList> inChargeList) {
        this.inChargeList = inChargeList;
    }


public class ConnectionRequest {

        @SerializedName("p_age")
        private int age;
        @SerializedName("p_gender")
        private boolean gender;
        @SerializedName("p_name")
        private String parentName;
        @SerializedName("r_name")
        private String userName;
        @SerializedName("req_id")
        private String reqId;
        @SerializedName("request_time")
        private String requestTime;
        @SerializedName("requester_id")
        private String requesterId;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }
}

    public class InChargeList {

        @SerializedName("age")
        private Integer age;
        @SerializedName("daughter")
        private String daughter;
        @SerializedName("gender")
        private Boolean gender;
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getDaughter() {
            return daughter;
        }

        public void setDaughter(String daughter) {
            this.daughter = daughter;
        }

        public Boolean getGender() {
            return gender;
        }

        public void setGender(Boolean gender) {
            this.gender = gender;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
