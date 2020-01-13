package it.teozDa;


public class User {

    private int ID;
    private String firstName;
    private String lastName;

    public User(int ID) {
        this.ID = ID;
    }

    public User(String name, String sName) {
        firstName = name;
        lastName = sName;
    }

    public User(String name, String sName, Connector conn) {
        UserRepository usrRep = new UserRepository(conn);
        firstName = name;
        lastName = sName;
        ID = usrRep.insertUser(this);

    }


    public void setID(int id) {
        ID = id;
    }

    public int getID() {
        return ID;
    }

    public void setFirstName(String name) {
        firstName = name;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String sName) {
        lastName = sName;

    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
