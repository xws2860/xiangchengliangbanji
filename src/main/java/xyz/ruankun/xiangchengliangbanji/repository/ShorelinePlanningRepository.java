package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.ShorelinePlanning;

import java.util.List;

@Repository
public interface ShorelinePlanningRepository extends JpaRepository<ShorelinePlanning, Integer> {
    List<ShorelinePlanning> findByWaterSystemsId(Integer waterSystemsId);
}