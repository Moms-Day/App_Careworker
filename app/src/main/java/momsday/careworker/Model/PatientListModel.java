package momsday.careworker.Model;

public class PatientListModel {
    private String name;
    private String info;
    private String protectorName;

    public PatientListModel(String name, String info, String protectorName) {
        this.name = name;
        this.info = info;
        this.protectorName = protectorName;
    }

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
}
