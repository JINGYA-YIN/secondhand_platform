import com.example.secondhand_platform.model.User;
import com.example.secondhand_platform.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestRegister {
    public static void main(String[] args) {
        String username = "testuser";
        String password = "123456"; // 这里可以先用明文测试
        String salt = "testsalt";
        String nickname = "测试昵称";

        String sql = "INSERT INTO user(username, password, salt, nickname) VALUES (?,?,?,?)";

        try (Connection conn = DBUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, salt);
            ps.setString(4, nickname);

            int rows = ps.executeUpdate();
            System.out.println("插入成功，受影响行数：" + rows);

        } catch (Exception e) {
            e.printStackTrace(); // 这里会打印完整 SQL 异常
        }
    }
}
