package hr.mstuban.homeexchange.services.impl;

import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.domain.UserRole;
import hr.mstuban.homeexchange.domain.form.EditUserForm;
import hr.mstuban.homeexchange.domain.form.NewUserForm;
import hr.mstuban.homeexchange.domain.mapper.UserMapper;
import hr.mstuban.homeexchange.repositories.UserRepository;
import hr.mstuban.homeexchange.repositories.UserRoleRepository;
import hr.mstuban.homeexchange.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        /*if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }*/
        return userRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Object find(Pageable pageable) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(NewUserForm form) {
        User user = new User();
        user.setUserName(form.getUsername());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setUser(user);
        userRoles.add(userRole);
        user.setRoles(userRoles);
        user.setPhoneNumber(form.getPhoneNumber());
        user.setEnabled(true);

        userRepository.save(user);

        userRoleRepository.save(userRole);

        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public boolean existsByPhoneNumberIgnoreCase(String phoneNumber) {
        return userRepository.existsByPhoneNumberIgnoreCase(phoneNumber);
    }

    @Override
    public boolean existsByUserNameIgnoreCase(String username) {
        return userRepository.existsByUserNameIgnoreCase(username);
    }

    @Transactional
    @Override
    public void editUser(EditUserForm form, Long id) {

        String encryptedPassword = BCrypt.hashpw(form.getPassword(), BCrypt.gensalt());

        userRepository.editUser(form.getUsername(), form.getFirstName(), form.getLastName(), encryptedPassword, form.getEmail(), form.getPhoneNumber(), id);
    }

    @Override
    public List<User> findAllExceptOneWithUsername(String username) {
        return userRepository.findAllExceptOneWithUsername(username);
    }

}