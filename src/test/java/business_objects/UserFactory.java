package business_objects;

public class UserFactory {
    public static User createSimpleUser(){
        User user = new User();
        user.setLOGIN("67366T");
        user.setPASSWORD("password1");

        return user;
    }


    public static User createUserFromLogin(String Login) {
        User user = createSimpleUser();
        user.setLOGIN(Login);
        return user;
    }
}
