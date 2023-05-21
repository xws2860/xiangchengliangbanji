package xyz.ruankun.xiangchengliangbanji.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSystems;

@Repository
public interface WaterSafetyRepository extends JpaRepository<WaterSystems, Integer> {
}