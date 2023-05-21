package xyz.ruankun.xiangchengliangbanji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.xiangchengliangbanji.entity.WaterResources;
import xyz.ruankun.xiangchengliangbanji.repository.WaterResourcesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WaterResourcesService {
    private final WaterResourcesRepository waterResourcesRepository;

    @Autowired
    public WaterResourcesService(WaterResourcesRepository waterResourcesRepository) {
        this.waterResourcesRepository = waterResourcesRepository;
    }

    public List<WaterResources> getAllWaterResources() {
        return waterResourcesRepository.findAll();
    }

    public List<WaterResources> getWaterResourcesByWaterSystemsId(Integer waterSystemsId) {
        return waterResourcesRepository.findAllByWaterSystemsId(waterSystemsId);
    }

    public Optional<WaterResources> getWaterResourcesById(Integer id) {
        return waterResourcesRepository.findById(id);
    }
}
