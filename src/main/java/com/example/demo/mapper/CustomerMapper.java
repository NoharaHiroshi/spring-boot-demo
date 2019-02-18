package com.example.demo.mapper;

import com.example.demo.model.Customer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CustomerMapper {
    @Delete({
        "delete from customer",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into customer (id, name, ",
        "user_id)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=INTEGER})"
    })
    int insert(Customer record);

    @InsertProvider(type=CustomerSqlProvider.class, method="insertSelective")
    int insertSelective(Customer record);

    @Select({
        "select",
        "id, name, user_id",
        "from customer",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    Customer selectByPrimaryKey(Integer id);

    // 联表查询
    // 上面使用@Select注解配置查询所需要的SQL，使用@Results配置结果集，使用@Result配置某一个数据库字段与实体类属性之间的关系。
    // property是模型的属性，column是数据库中的字段，使用one表示关联关系中的一方
    // 在@One中，指定 查询该对象的Mapper的方法（以column作为参数的），这样就能将该对象查出来并赋值给相应属性。
    @Select("select * from customer where id = #{id, jdbcType=INTEGER}")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(property = "user",
                    column = "user_id",
                    one = @One(select = "com.example.demo.mapper.UserMapper.selectByPrimaryKey"))
    })
    Customer getCustomerAndUser(Integer id);


    @UpdateProvider(type=CustomerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Customer record);

    @Update({
        "update customer",
        "set name = #{name,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Customer record);
}