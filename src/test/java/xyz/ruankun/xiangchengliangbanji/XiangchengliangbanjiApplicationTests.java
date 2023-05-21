package xyz.ruankun.xiangchengliangbanji;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ruankun.xiangchengliangbanji.entity.Relocation;
import xyz.ruankun.xiangchengliangbanji.entity.RiverLakeBoundary;
import xyz.ruankun.xiangchengliangbanji.entity.RolePermissions;
import xyz.ruankun.xiangchengliangbanji.mapper.RelocationMapper;
import xyz.ruankun.xiangchengliangbanji.mapper.RiverLakeBoundaryMapper;
import xyz.ruankun.xiangchengliangbanji.mapper.RolePermissionsMapper;

import java.util.List;


@SpringBootTest
class XiangchengliangbanjiApplicationTests {

    @Autowired
    RelocationMapper relocationMapper;

    @Autowired
    RolePermissionsMapper rolePermissionsMapper;

    @Autowired
    RiverLakeBoundaryMapper riverLakeBoundaryMapper;

//    @Test
//    void contextLoads() {
//    }

//    @Test
//    void testRelocationMapper(){
//        List<Relocation> relocationsList = relocationMapper.selectAll();
//      List<Relocation> relocations = relocationMapper.selectByProjectName("abc");
//        System.out.println("查询结果："+relocationsList);
//    }


//    @Test
//    void testRolePermissionsMapper(){
//        List<RolePermissions> rolePermissionsList = rolePermissionsMapper.selectAll();
//        System.out.println("查询结果："+rolePermissionsList);
//    }

    @Test
    void testRiverLakeBoundaryMapper(){
        List<RiverLakeBoundary> riverLakeBoundaryList = riverLakeBoundaryMapper.selectAll();
        RiverLakeBoundary riverLakeBoundary = riverLakeBoundaryMapper.selectByWaterSystemsId(1);
        RiverLakeBoundary riverLakeBoundary1 = riverLakeBoundaryMapper.selectById(1);

        System.out.println("查询结果："+riverLakeBoundary1+"\n"+riverLakeBoundary+"\n"+riverLakeBoundaryList);
    }

}
