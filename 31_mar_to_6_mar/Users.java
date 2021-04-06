
package entity;

public class Users {
    private final int user_id;
    private final Sozlesmeli sozlesmeli;
    private final String username;
    private final String password;

    public Users(UsersBuilder builder) {
        this.user_id = builder.user_id;
        this.username = builder.username;
        this.password = builder.password;
        this.sozlesmeli = builder.sozlesmeli;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Sozlesmeli getSozlesmeli() {
        return sozlesmeli;
    }

    @Override
    public String toString() {
        return "Users{" + "user_id=" + user_id + ", sozlesmeli=" + sozlesmeli.getEleman().getEleman_id() + ", username=" + username + ", password=" + password + '}';
    }


    
    
}
