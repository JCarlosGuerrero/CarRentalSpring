package mx.edu.j2se.Guerrero.CarRental.Service;

import lombok.extern.slf4j.Slf4j;
import mx.edu.j2se.Guerrero.CarRental.DAO.UsersDAO;
import mx.edu.j2se.Guerrero.CarRental.Domain.Rol;
import mx.edu.j2se.Guerrero.CarRental.Domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
@Slf4j
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersDAO usersDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersDAO.findByUsername(username);

        if(username == null){
            throw new UsernameNotFoundException(username);
        }
        ArrayList roles = new ArrayList<GrantedAuthority>();

        for (Rol rol: user.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getName()));
        }
        return new User(user.getUsername(), user.getPassword(),roles);
    }

    @Transactional
    public void guardarU(Users users) {
        usersDAO.save(users);
    }
}
