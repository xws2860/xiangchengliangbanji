package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 通过账号查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 通过email查询用户
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    /**
     * 通过角色ID和组织ID寻找用户
     * @param roleId
     * @param organizationId
     * @return
     */
    List<User> findByRoleIdAndOrganizationId(int roleId, int organizationId);
}

