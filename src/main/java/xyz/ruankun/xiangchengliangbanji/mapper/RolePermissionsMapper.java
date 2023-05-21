package xyz.ruankun.xiangchengliangbanji.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.ruankun.xiangchengliangbanji.entity.RiverLakeBoundary;
import xyz.ruankun.xiangchengliangbanji.entity.RolePermissions;

import java.util.List;

@Mapper
public interface RolePermissionsMapper extends BaseMapper<RolePermissions> {

    @Select("select * from role_permissions where role_id=#{role_id} and permission_id=#{permission_id}")
    public RolePermissions selectByRole_idAndPermission_id(int role_id ,int permission_id);

    @Select("select * from role_permissions")
    public List<RolePermissions> selectAll();
}
