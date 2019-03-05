package com.genx.druid.plugin;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.filter.stat.StatFilterMBean;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: genx
 * @date: 2019/3/5 23:41
 */
public class MyStatFilter extends FilterEventAdapter implements StatFilterMBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
        this.internalAfterStatementExecute(statement, false, updateCount);
    }

    @Override
    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
        this.internalAfterStatementExecute(statement, true);
    }

    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean firstResult) {
        this.internalAfterStatementExecute(statement, firstResult);
    }

    @Override
    protected void statementExecuteBatchAfter(StatementProxy statement, int[] result) {
        this.internalAfterStatementExecute(statement, false, result);
    }

    @Override
    protected void statement_executeErrorAfter(StatementProxy statement, String sql, Throwable error) {


        long nanos = System.nanoTime() - statement.getLastExecuteStartNano();

        logger.info("sql:{}, 耗时:{}, 发生异常:{}", sql, nanos, error);
    }

    private final void internalAfterStatementExecute(StatementProxy statement, boolean firstResult,
                                                     int... updateCountArray) {
        final long nowNano = System.nanoTime();
        final long nanos = nowNano - statement.getLastExecuteStartNano();
        String sql = statement.getLastExecuteSql();
        logger.info("sql:{}, 耗时:{}", sql, nanos);
    }

    @Override
    public boolean isMergeSql() {
        return false;
    }

    @Override
    public void setMergeSql(boolean b) {

    }

    @Override
    public boolean isLogSlowSql() {
        return false;
    }

    @Override
    public void setLogSlowSql(boolean b) {

    }

    @Override
    public String mergeSql(String s, String s1) {
        return null;
    }

    @Override
    public long getSlowSqlMillis() {
        return 0;
    }

    @Override
    public void setSlowSqlMillis(long l) {

    }
}
