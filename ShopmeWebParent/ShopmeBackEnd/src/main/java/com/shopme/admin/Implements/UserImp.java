package com.shopme.admin.Implements;


import com.shopme.admin.Repository.RoleRepo;
import com.shopme.admin.Repository.UserRepo;
import com.shopme.admin.services.UserService;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.Users;
import com.shopme.common.exception.UserNotFoundException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author gnaht
 */
@Service
@Transactional
public class UserImp implements UserService {
    public final static int USER_PER_PAGE = 5;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Users> findAll() {
        return (List<Users>) userRepo.findAll();
    }

    @Override
    public Users Login(String username, String password) {
        Users user = userRepo.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public Users add(Users user) {
        boolean isUpdatingUser = (user.getId() != null);
        if (isUpdatingUser) {
            Users existingUser = userRepo.findById(user.getId());
            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }
        } else {
            encodePassword(user);
        }
        return userRepo.save(user);
    }

    @Override
    public void remove(String username) {
        userRepo.deleteByUsername(username);
    }

    @Override
    public Users GetByUserName(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Users Update(Users user) {
        return userRepo.save(user);
    }

    @Override
    public boolean IsExists(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void saveRoletoUser(String username, String name) {
        Users user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(name);
        user.getRoles().add(role);
    }

    private void encodePassword(Users user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }

    @Override
    public boolean isEmailUnique(String email, Integer id) {
        Users userByEmail = userRepo.findByEmail(email);

        if (userByEmail == null) {
            return true;
        }
        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            if (userByEmail != null) {
                return false;
            }
        } else {
            if (userByEmail.getId() != id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isUserUnique(String username, Integer id) {
        Users userByUsername = userRepo.findByUsername(username);

        if (userByUsername == null) {
            return true;
        }
        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            if (userByUsername != null) {
                return false;
            }
        } else {
            if (userByUsername.getId() != id) {
                return false;
            }
        }
        return true;

    }

    @Override
    public Users GetById(Integer id) throws UserNotFoundException {

        return Optional.ofNullable(id)
                .map(value -> userRepo.findById(id))
                .orElseThrow(() -> new UserNotFoundException("Could not find with id: " + id));

    }

    @Override
    public void deleteUser(Integer id) throws UserNotFoundException {
        Long countById = userRepo.countById(id);

        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find user with ID " + id);
        }
        userRepo.delete(userRepo.findById(id));
    }

    @Override
    public void updateUserEnabledStatus(Integer id, boolean active) {
        userRepo.updateUserActiveStatus(id, active);
    }

    @Override
    public Page<Users> listByPage(int pageNum) {
        Pageable page = PageRequest.of(pageNum - 1, USER_PER_PAGE);
        return userRepo.findAll(page);
    }
}
