package com.yaorange.service.impl;

import com.yaorange.dao.EmpDao;
import com.yaorange.entity.Emp;
import com.yaorange.entity.Role;
import com.yaorange.service.EmpService;
import com.yaorange.utils.MailUtil;
import com.yaorange.utils.Pagination;
import com.yaorange.utils.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassNameEmpServiceImpl
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/11 17:44
 * @Version 1.0
 **/
@Service("empService")
public class EmpServiceImpl implements EmpService {
    @Resource(name = "empDao")
    private EmpDao empDao;
    @Resource(name = "threadPool")
    private ThreadPoolTaskExecutor threadPool;
    @Resource(name = "mailUtil")
    private MailUtil mailUtil;

    @Override
    public Pagination getPages(Integer pageSize, Integer current) {
        Pagination pagination = empDao.getPage("from Emp", current, pageSize);
        return pagination;
    }

    @Override
    public void add(Emp emp) throws MessagingException {
        String email = emp.getEmail();
        //初始化员工密码
        String password = "123456";
        emp.setPassword(password);
        //初始化邮件发送状态
        short a = 1;
        emp.setMailState(a);
        if (!StringUtils.isEmpty(email)) {
            //实现配置的线程池
            //当前创建的线程对象，会被加入到线程池进行控制管理
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //给员工发送欢迎邮件
                    int count = 0;
                    boolean isOk = false;
                    while (count < 3 && !isOk) {//在发送失败的请求下，最多循环3次
                        try {
                            mailUtil.sendMail(email, "欢迎加入中润和泰大家庭",
                                    "你好:" + emp.getName() + "," +
                                            "登录账户：" + emp.getAccount() + ",登录初始密码：" + password);
                            //发送成功
                            isOk = true;//标识成功，结束循环
                        } catch (Exception e) {
                            //发送失败
                            count++;
                            //优化，设置停顿时间
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
        empDao.insert(emp);
        //初始化邮件发送状态
        emp.setMailState((short) 0);
    }

    @Override
    public void updateUsableState(Integer[] ids, Short state) {
        for (int i = 0; i < ids.length; i++) {
            empDao.update("update Emp set usableState=?1 where id=?2", state, ids[i]);
        }
    }

    @Override
    public void update(Emp emp) {
        empDao.update(emp);
    }

    @Override
    public void assginRole(Integer[] ids, Integer id) {
        Emp emp = empDao.get(id);
        List<Role> roles = new ArrayList<Role>();
        for (Integer roleId : ids) {
            Role role = new Role();
            role.setId(roleId);
            roles.add(role);
        }
        emp.setRoleList(roles);
    }

    @Override
    public Emp getEmpByUsername(String username) {
        Emp emp = empDao.get("from Emp where account=?1", username);
        return emp;
    }

    @Override
    public List<Emp> getByDept(Integer id) {
        List<Emp> emps = empDao.getList("from Emp where dept_id=?1", id);
        return emps;
    }

}
