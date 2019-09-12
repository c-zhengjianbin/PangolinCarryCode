package  com.zhengjianbin.generatecode.testfile.service;

import com.zhengjianbin.generatecode.testfile.model.AdminUser;
import java.util.List;

public interface AdminUserService {

    void save(AdminUser adminUser);

    void delete(Long id);

    void modify(AdminUser adminUser);

    AdminUser getAdminUserById(Long id);

    List<AdminUser> listObjects(AdminUser adminUser);

}