package lk.ijse.dep12.client_management_UI;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client {

    private String id;
    private String nic;
    private String name;
    private String address;
    private  String gender;
    private LocalDate date;
    private ArrayList<String> properties;

    public Client(String id, String nic, String name, String address, String gender, LocalDate date, ArrayList<String> properties) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.date = date;
        this.properties = properties;
    }

    public Client(String id, String nic, String name, String gender, LocalDate date, ArrayList<String> properties) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
