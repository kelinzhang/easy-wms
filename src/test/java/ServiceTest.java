import com.luis.wms.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ServiceTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void testBatchDelete() throws Exception {
        employeeService.batchDelete(Arrays.asList(23L,22L,24L));
    }
}
