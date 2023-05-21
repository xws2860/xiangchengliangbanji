package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.xiangchengliangbanji.entity.WaterResources;
import xyz.ruankun.xiangchengliangbanji.service.WaterResourcesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/water-resources")
@CrossOrigin
public class WaterResourcesController {
    private final WaterResourcesService waterResourcesService;

    @Autowired
    public WaterResourcesController(WaterResourcesService waterResourcesService) {
        this.waterResourcesService = waterResourcesService;
    }

    @GetMapping("")
    public List<WaterResources> getAllWaterResources() {
        return waterResourcesService.getAllWaterResources();
    }

    @GetMapping("/water-systems/{id}")
    public List<WaterResources> getWaterResourcesByWaterSystemsId(@PathVariable("id") Integer waterSystemsId) {
        return waterResourcesService.getWaterResourcesByWaterSystemsId(waterSystemsId);
    }

    @GetMapping("/{id}")
    public Optional<WaterResources> getWaterResourcesById(@PathVariable("id") Integer id) {
        return waterResourcesService.getWaterResourcesById(id);
    }
}
