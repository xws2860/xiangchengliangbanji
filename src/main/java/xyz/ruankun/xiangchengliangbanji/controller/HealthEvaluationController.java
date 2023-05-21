package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ruankun.xiangchengliangbanji.entity.HealthEvaluation;
import xyz.ruankun.xiangchengliangbanji.service.HealthEvaluationService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/health-evaluation")
public class HealthEvaluationController {
    private final HealthEvaluationService healthEvaluationService;

    public HealthEvaluationController(HealthEvaluationService healthEvaluationService) {
        this.healthEvaluationService = healthEvaluationService;
    }

    @GetMapping("/{waterSystemsId}")
    public ResponseEntity<HealthEvaluation> getHealthEvaluationByWaterSystemsId(@PathVariable Integer waterSystemsId) {
        HealthEvaluation healthEvaluation = healthEvaluationService.getHealthEvaluationByWaterSystemsId(waterSystemsId);
        if (healthEvaluation == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(healthEvaluation);
        }
    }
}