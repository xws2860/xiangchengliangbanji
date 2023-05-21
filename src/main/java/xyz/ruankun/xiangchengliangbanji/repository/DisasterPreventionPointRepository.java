package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.DisasterPreventionPoint;

@Repository
public interface DisasterPreventionPointRepository extends JpaRepository<DisasterPreventionPoint, Integer> {
}
