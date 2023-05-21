package xyz.ruankun.xiangchengliangbanji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.ruankun.xiangchengliangbanji.entity.HealthEvaluation;

@Repository
public interface HealthEvaluationRepository extends JpaRepository<HealthEvaluation, Integer> {
    /**
     * 通过水系ID查询健康评价
     * @param waterSystemsId
     * @return
     */
    HealthEvaluation findByWaterSystemsId(Integer waterSystemsId);
}
