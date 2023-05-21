package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.RolePermissions;

@Repository
public interface RolePermissionsRepository extends JpaRepository<RolePermissions, Integer> {
}
