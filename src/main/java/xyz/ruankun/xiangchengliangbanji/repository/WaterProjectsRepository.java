package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.WaterProjects;

@Repository
public interface WaterProjectsRepository extends JpaRepository<WaterProjects, Integer> {
}
