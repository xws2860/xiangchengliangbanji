package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.xiangchengliangbanji.entity.ShorelinePlanning;
import xyz.ruankun.xiangchengliangbanji.service.ShorelinePlanningService;

import java.util.List;

@RestController
@RequestMapping("/api/shoreline-planning")
@CrossOrigin
public class ShorelinePlanningController {

    @Autowired
    private ShorelinePlanningService shorelinePlanningService;

    @GetMapping("/{id}")
    public ResponseEntity<ShorelinePlanning> findById(@PathVariable Integer id) {
        ShorelinePlanning shorelinePlanning = shorelinePlanningService.findById(id);
        if (shorelinePlanning == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shorelinePlanning);
    }

    @GetMapping("/by-water-systems-id")
    public ResponseEntity<List<ShorelinePlanning>> findByWaterSystemsId(@RequestParam Integer waterSystemsId) {
        List<ShorelinePlanning> shorelinePlannings = shorelinePlanningService.findByWaterSystemsId(waterSystemsId);
        if (shorelinePlannings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shorelinePlannings);
    }
}
