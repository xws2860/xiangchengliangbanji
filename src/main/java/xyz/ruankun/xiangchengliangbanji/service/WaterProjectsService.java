package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.entity.WaterProjects;
import xyz.ruankun.xiangchengliangbanji.repository.WaterProjectsRepository;

import java.util.List;

@Service
public class WaterProjectsService {
    private final WaterProjectsRepository waterProjectsRepository;

    @Autowired
    public WaterProjectsService(WaterProjectsRepository waterProjectsRepository) {
        this.waterProjectsRepository = waterProjectsRepository;
    }

    public List<WaterProjects> getAllWaterProjects() {
        return waterProjectsRepository.findAll();
    }
}
