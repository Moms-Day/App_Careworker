package momsday.careworker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PatientListModel implements Parcelable{

    public static final int VIEWTYPE_HEADER = 0;
    public static final int VIEWTYPE_PATIENT = 1;
    public static final int VIEWTYPE_REQUEST = 2;
    private String name;
    private String info;
    private String protectorName;
    private String age;
    private int viewType;
    private String id;
    private String requestTime;
    private String requestId;

    public PatientListModel(String name, String age, String protectorName, String id, int viewType) {
        this.name = name;
        this.age = age;
        this.protectorName = protectorName;
        this.id = id;
        this.viewType = viewType;
    }

    public PatientListModel(String name, String age, String protectorName, String id, String requestId, int viewType) {
        this.name = name;
        this.age = age;
        this.protectorName = protectorName;
        this.viewType = viewType;
        this.id = id;
        this.requestTime = requestTime;
        this.requestId = requestId;
    }

    public PatientListModel(String name, int viewType) {
        this.name = name;
        this.viewType = viewType;
    }

    private PatientListModel(Parcel in) {
        name = in.readString();
        info = in.readString();
        protectorName = in.readString();
        age = in.readString();
        viewType = in.readInt();
        id = in.readString();
        requestTime = in.readString();
        requestId = in.readString();
    }

    public static final Creator<PatientListModel> CREATOR = new Creator<PatientListModel>() {
        @Override
        public PatientListModel createFromParcel(Parcel in) {
            return new PatientListModel(in);
        }

        @Override
        public PatientListModel[] newArray(int size) {
            return new PatientListModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getProtectorName() {
        return protectorName;
    }

    public void setProtectorName(String protectorName) {
        this.protectorName = protectorName;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(info);
        parcel.writeString(protectorName);
        parcel.writeString(age);
        parcel.writeInt(viewType);;
        parcel.writeString(id);
        parcel.writeString(requestTime);
    }
}
