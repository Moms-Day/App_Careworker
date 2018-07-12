package momsday.careworker.Model;

public class RequestListModel {
    private String name;

    public RequestListModel(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
