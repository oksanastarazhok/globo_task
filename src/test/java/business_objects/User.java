package business_objects;

public class User {
    private static String LOGIN = null;
    private static String PASSWORD = null;

    public String getPASSWORD(){
        return PASSWORD;
    }

    public void setPASSWORD (String PASSWORD){
        this.PASSWORD = PASSWORD;
    }

    public String getLOGIN(){
        return LOGIN;
    }

    public void setLOGIN(String LOGIN){
        this.LOGIN = LOGIN;
    }
}
