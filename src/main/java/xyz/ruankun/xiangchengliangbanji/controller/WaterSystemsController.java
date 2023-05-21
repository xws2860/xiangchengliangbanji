package xyz.ruankun.xiangchengliangbanji.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.xiangchengliangbanji.annotation.RequirePermission;
import xyz.ruankun.xiangchengliangbanji.entity.WaterSystems;
import xyz.ruankun.xiangchengliangbanji.service.WaterSystemsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/water-systems")
@Api(tags = "水系相关API")
@CrossOrigin
public class WaterSystemsController {
    @Autowired
    private WaterSystemsService waterSystemsService;

    @GetMapping
    @ApiOperation("获取所有水系")
    public ResponseEntity<List<WaterSystems>> getAllWaterSystems() {
        List<WaterSystems> waterSystemsList = waterSystemsService.getAllWaterSystems();
        return new ResponseEntity<>(waterSystemsList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WaterSystems> getWaterSystemsById(@PathVariable Integer id) {
        Optional<WaterSystems> waterSystems = waterSystemsService.getWaterSystemsById(id);
        if (waterSystems.isPresent()) {
            return new ResponseEntity<>(waterSystems.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by-river-code-or-river-name")
    public ResponseEntity<List<WaterSystems>> getWaterSystemsByRiverCodeOrRiverName(
            @RequestParam(value = "riverCode", required = false) String riverCode,
            @RequestParam(value = "riverName", required = false) String riverName) {
        List<WaterSystems> waterSystemsList = waterSystemsService.getWaterSystemsByRiverCodeOrRiverName(riverCode, riverName);
        return new ResponseEntity<>(waterSystemsList, HttpStatus.OK);
    }

    @RequirePermission("user")
    @GetMapping("/by-water-type")
    public ResponseEntity<List<WaterSystems>> getWaterSystemsByWaterType(@RequestParam(value = "waterType") String waterType) {
        List<WaterSystems> waterSystemsList = waterSystemsService.getWaterSystemsByWaterType(waterType);
        return new ResponseEntity<>(waterSystemsList, HttpStatus.OK);
    }
}

