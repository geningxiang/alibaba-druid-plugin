package com.genx.druid.service;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: genx
 * @date: 2019/3/8 00:26
 */
public class ParserMain {

    public static void main(String[] args) {
        String sql = "select u.id, u.name from user u, msg m where u.id in (select id from user_a)";

        // 新建 MySQL Parser
        SQLStatementParser parser = new MySqlStatementParser(sql);

        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLStatement statement = parser.parseStatement();

        // 使用visitor来访问AST
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statement.accept(visitor);
                
        // 从visitor中拿出你所关注的信息        
        System.out.println(visitor.getColumns());

        System.out.println(visitor.getDbType());


        System.out.println(visitor.getTables().entrySet());
    }
}