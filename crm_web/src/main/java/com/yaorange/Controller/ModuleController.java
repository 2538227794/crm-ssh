package com.yaorange.Controller;

import com.yaorange.Controller.vo.ZtreeVo;
import com.yaorange.entity.Module;
import com.yaorange.service.ModuleService;
import com.yaorange.utils.Pagination;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNameModuleController
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/12 23:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/module")
public class ModuleController {
    @Resource(name = "moduleService")
    private ModuleService moduleService;

    @GetMapping("/ztreeNodes")
    public List<ZtreeVo> getZtreeNode() {
        //调用业务方法，获取所有模块对象
        List<Module> modules = moduleService.getAll();
        // 遍历模块集合，组装vo对象集合
        List<ZtreeVo> ztreeList = new ArrayList<ZtreeVo>();
        for (Module module : modules) {
            ZtreeVo ztreeVo = new ZtreeVo();
            ztreeVo.setId(module.getId());
            ztreeVo.setpId(module.getParentId());
            ztreeVo.setName(module.getName());
            //默认节点都不选中
            ztreeVo.setChecked(false);
            //默认节点展开
            ztreeVo.setOpen(true);
            ztreeList.add(ztreeVo);
        }
        return ztreeList;
    }

    @GetMapping("/showPage")
    public Pagination showPage(Integer pageSize, Integer pageNo) {
        Pagination page = moduleService.getPage(pageSize, pageNo);
        return page;
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Integer[] ids) {
        moduleService.delete(ids);
        return "ok";
    }

    @PostMapping("/add")
    public String add(@RequestBody Module module) {
        moduleService.add(module);
        return "ok";
    }

    @PostMapping("/update")
    public String update(@RequestBody Module module) {
        moduleService.update(module);
        return "ok";
    }

    @GetMapping("/getModulesOneList")
    public List<Module> getModulesOneList() {
        List<Module> modules = moduleService.getModulesOneList();
        return modules;
    }

}
