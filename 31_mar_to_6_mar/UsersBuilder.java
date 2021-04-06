
package entity;

public class UsersBuilder {
    int user_id;
    Sozlesmeli sozlesmeli;
    String username;
    String password;

    
    public UsersBuilder SetUser_id(int user_id){
        this.user_id = user_id;
        return this;
    }
    
    public UsersBuilder SetUsername(String username){
        this.username = username;
        return this;
    }
    
    public UsersBuilder SetPassword(String password){
        this.password = password;
        return this;
    }
    
    public UsersBuilder SetSozlesmeli(Sozlesmeli sozlesmeli){
        this.sozlesmeli = sozlesmeli;
        return this;
    }
    
    
    //user bilgileri doldurulduktan sonra yapilandirilmasi
    public Users build(){
        Users user = new Users(this);
        //userNesneKontrol(user); baska bir asama
        return user;
    }
    
    
    public void userNesneKontrol(Users user){
        //nesneyi dogru bir sekilde yuklenip yuklenmedigini kontrol ediler 
        //kodlarimizda gereken her hangi bir kontrol
    }
    
}
