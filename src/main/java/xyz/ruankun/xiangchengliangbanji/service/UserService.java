package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.entity.User;
import xyz.ruankun.xiangchengliangbanji.entity.YesNoEnum;
import xyz.ruankun.xiangchengliangbanji.repository.UserRepository;
import xyz.ruankun.xiangchengliangbanji.util.JwtUtil;
import xyz.ruankun.xiangchengliangbanji.util.MD5PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MD5PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, MD5PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

    }

    /**
     * 用户注册
     * @param userDto
     * @throws DuplicateKeyException
     */
    public void registerUser(User userDto) throws DuplicateKeyException {
        User user = new User()
                .setUsername(userDto.getUsername())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setEmail(userDto.getEmail())
                .setIfDelete(YesNoEnum.否)
                .setCreateTime(new Date())
                .setModifyTime(new Date());
        userRepository.save(user);
    }

    /**
     * 查询用户,用spring的userDetails
     * @param username
     * @return
     */
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findByRoleIdAndOrganizationId(int roleId, int organizationId) {
        return userRepository.findByRoleIdAndOrganizationId(roleId, organizationId);
    }
    public void save(User users) {
        System.out.println("service输出:" + users);
        userRepository.save(users);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public boolean passwordMatches(String plainPassword, String encodedPassword) {
        MD5PasswordEncoder passwordEncoder = new MD5PasswordEncoder();
        return passwordEncoder.matches(plainPassword, encodedPassword);
    }

    public User getUserByToken(String token) {
        System.out.println(token);
        User user = jwtUtil.getUserFromToken(token);
        return user;
    }
}
