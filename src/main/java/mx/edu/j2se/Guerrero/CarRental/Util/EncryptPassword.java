package mx.edu.j2se.Guerrero.CarRental.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPassword {
    public static void main(String[] args) {
        String password = "321";
        System.out.println("password: " + password);
        System.out.println("password encriptado: " + encryptPassword(password));
    }

    public static String encryptPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
