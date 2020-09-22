package com.yaorange.service.impl;

import com.yaorange.dao.ModuleDao;
import com.yaorange.entity.Module;
import com.yaorange.service.ModuleService;
import com.yaorange.utils.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassNameModuleServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/12 23:48
 * @Version 1.0
 **/
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
    @Resource(name = "moduleDao")
    private ModuleDao moduleDao;

    @Override
    public Pagination getPage(Integer pageSize, Integer pageNo) {
        Pagination pagination = moduleDao.getPage("from Module", pageNo, pageSize);
        return pagination;
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            moduleDao.delete(id);
        }
    }

    @Override
    public void add(Module module) {
        //当新增父模块时将parentId设置为null
        if (module.getParentId() == 0) {
            module.setParentId(null);
        }
        moduleDao.insert(module);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public List<Module> getAll() {
        List<Module> modules = moduleDao.getList("from Module");
        return modules;
    }

    @Override
    public List<Module> getModulesOneList() {
        //模块为可用状态
        final Integer state = 0;
        List<Module> modules = moduleDao.getList("from Module where parentId is null and state=?1", 0);
        return modules;
    }
}
