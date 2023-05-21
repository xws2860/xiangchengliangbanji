package xyz.ruankun.xiangchengliangbanji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.ruankun.xiangchengliangbanji.entity.Relocation;
import xyz.ruankun.xiangchengliangbanji.entity.RiverLakeBoundary;
import xyz.ruankun.xiangchengliangbanji.entity.RolePermissions;

import java.util.List;

@Mapper
public interface RiverLakeBoundaryMapper extends BaseMapper<RiverLakeBoundary> {

    @Select("select * from river_lake_boundary")
    public List<RiverLakeBoundary> selectAll();

    @Select("select * from river_lake_boundary where water_systems_id=#{water_systems_id}")
    public RiverLakeBoundary selectByWaterSystemsId(int water_systems_id);
}
