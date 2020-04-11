package com.zjc.demo.dao;

import com.zjc.demo.bean.dbparam.UserParam;
import com.zjc.demo.common.TableName;
import com.zjc.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao {


    /**
     *
     * @param user 用户对象
     * @return 成功则返回"success"，失败则返回错误信息
     */
    @Insert("insert into "+ TableName.TABLE_USER+"(account,password,email,delete_flag,check_flag) values(#{account},#{password},#{email},0,0)")
    Integer addUser(User user) throws DataAccessException;



    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    @Select("SELECT * FROM "+ TableName.TABLE_USER+" WHERE id=#{userId}")
    User getUserById(@Param("userId") Integer userId) throws DataAccessException;


    /** 分页语句查询SQL */
    String LIMIT_SQL = "  <if test='startIndex != null and lineSize!=null '> "
            + "   limit #{startIndex},#{lineSize} "
            + "  </if>  ";

    /** 查询用户条件SQL */
    String SELECT_USER_SQL =  "<where> "
            + "  <if test='id != null and id != &quot;&quot;'> "
            + "    and id = #{id}  "
            + "  </if>  "
            + "  <if test='account != null and account != &quot;&quot;'> "
            + "    and account like CONCAT('%', #{account}, '%') "
            + "  </if> "
            + "  <if test='password != null and password != &quot;&quot;'> "
            + "    and password = #{password} "
            + "  </if>  "
            + "  <if test='email != null and email != &quot;&quot;'> "
            + "    and email like CONCAT('%', #{email}, '%') "
            + "  </if>  "
            + "</where> ";
    /**
     * if 对内容进行判断
     * 在注解方法中，若要使用MyBatis的动态SQL，需要编写在<script></script>标签内
     * 在 <script></script>内使用特殊符号，则使用java的转义字符，如  双引号 "" 使用&quot;&quot; 代替
     * concat函数：mysql拼接字符串的函数
     */
    /** 根据条件查询用户总数 */
    @Select("<script>"
            + "select id, account, password ,email  "
            + "from "+ TableName.TABLE_USER+" "
            + SELECT_USER_SQL
            + LIMIT_SQL
            + "</script> ")
    List<User> getAllUser(UserParam userParam);


    /** 根据条件查询用户总数 */
    @Select("<script>"
            + "select count(*)  "
            + "from "+ TableName.TABLE_USER+" "
            + SELECT_USER_SQL
            + "</script> ")
    Integer getAllUserCount(UserParam userParam);


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from "+ TableName.TABLE_USER+" where account=#{username} and delete_flag=0 ")
    User getUserByUsername(@Param("username") String username);

}
