package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSystems;
import xyz.ruankun.xiangchengliangbanji.entity.WaterType;

import java.util.List;

@Repository
public interface WaterSystemsRepository extends JpaRepository<WaterSystems, Integer> {
    List<WaterSystems> findByRiverCodeOrRiverName(String riverCode, String riverName);
    List<WaterSystems> findByWaterType(WaterType waterType);
}
