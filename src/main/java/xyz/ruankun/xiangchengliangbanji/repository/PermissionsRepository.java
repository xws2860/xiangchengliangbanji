package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Integer> {
}
