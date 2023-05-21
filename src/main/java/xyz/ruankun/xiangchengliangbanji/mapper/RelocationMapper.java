package xyz.ruankun.xiangchengliangbanji.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.ruankun.xiangchengliangbanji.entity.Relocation;
import xyz.ruankun.xiangchengliangbanji.entity.RolePermissions;

import java.util.List;


@Mapper
public interface RelocationMapper extends BaseMapper<Relocation> {

    @Select("select * from relocation where project_name=#{project_name}")
    public List<Relocation> selectByProjectName(String project_name);

    @Select("select * from relocation")
    public List<Relocation> selectAll();
}
