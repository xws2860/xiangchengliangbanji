package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.entity.HealthEvaluation;
import xyz.ruankun.xiangchengliangbanji.repository.HealthEvaluationRepository;

@Service
public class HealthEvaluationService {
    private final HealthEvaluationRepository healthEvaluationRepository;

    public HealthEvaluationService(HealthEvaluationRepository healthEvaluationRepository) {
        this.healthEvaluationRepository = healthEvaluationRepository;
    }

    public HealthEvaluation getHealthEvaluationByWaterSystemsId(Integer waterSystemsId) {
        return healthEvaluationRepository.findByWaterSystemsId(waterSystemsId);
    }

}
