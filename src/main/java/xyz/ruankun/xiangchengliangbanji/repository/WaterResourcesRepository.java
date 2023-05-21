package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.WaterResources;

import java.util.List;

@Repository
public interface WaterResourcesRepository extends JpaRepository<WaterResources, Integer> {
    List<WaterResources> findAllByWaterSystemsId(Integer waterSystemsId);
}
