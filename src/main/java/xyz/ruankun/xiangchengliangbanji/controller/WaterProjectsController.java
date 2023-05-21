package xyz.ruankun.xiangchengliangbanji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ruankun.xiangchengliangbanji.entity.WaterProjects;
import xyz.ruankun.xiangchengliangbanji.service.WaterProjectsService;

import java.util.List;

@RestController
@RequestMapping("/api/water-projects")
public class WaterProjectsController {
    private final WaterProjectsService waterProjectsService;

    @Autowired
    public WaterProjectsController(WaterProjectsService waterProjectsService) {
        this.waterProjectsService = waterProjectsService;
    }

    @GetMapping("")
    public List<WaterProjects> getAllWaterProjects() {
        return waterProjectsService.getAllWaterProjects();
    }
}