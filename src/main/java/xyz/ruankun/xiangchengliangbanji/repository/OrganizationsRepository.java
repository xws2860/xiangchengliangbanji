package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.Organizations;

@Repository
public interface OrganizationsRepository extends JpaRepository<Organizations, Integer> {
}