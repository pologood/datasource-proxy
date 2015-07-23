package net.ttddyy.dsproxy.test.hamcrest;

import net.ttddyy.dsproxy.test.ProxyTestDataSource;
import net.ttddyy.dsproxy.test.StatementExecution;
import org.junit.Test;

import static net.ttddyy.dsproxy.test.hamcrest.ExecutionTypeMatcher.statement;
import static net.ttddyy.dsproxy.test.hamcrest.ProxyTestDataSourceMatcher.executions;
import static net.ttddyy.dsproxy.test.hamcrest.ProxyTestDataSourceMatcher.firstStatement;
import static net.ttddyy.dsproxy.test.hamcrest.QueryExecutionMatcher.fail;
import static net.ttddyy.dsproxy.test.hamcrest.QueryExecutionMatcher.success;
import static net.ttddyy.dsproxy.test.hamcrest.StatementExecutionMatcher.query;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tadaya Tsuyukubo
 * @since 1.4
 */
public class ProxyTestDataSourceMatcherTest {

    @Test
    public void testExecutions() {
        StatementExecution se = new StatementExecution();

        ProxyTestDataSource ds = new ProxyTestDataSource();
        ds.getQueryExecutions().add(se);

        assertThat(ds, executions(0, ExecutionType.IS_STATEMENT));
        assertThat(ds, executions(0, statement()));
        assertThat(ds, executions(0, is(statement())));
    }

    @Test
    public void testExecutionSuccess() {
        StatementExecution se = new StatementExecution();
        se.setSuccess(true);

        ProxyTestDataSource ds = new ProxyTestDataSource();
        ds.getQueryExecutions().add(se);

        assertThat(ds, executions(0, success()));
        assertThat(ds, executions(0, is(success())));
    }

    @Test
    public void testExecutionFail() {
        StatementExecution se = new StatementExecution();
        se.setSuccess(false);

        ProxyTestDataSource ds = new ProxyTestDataSource();
        ds.getQueryExecutions().add(se);

        assertThat(ds, executions(0, fail()));
        assertThat(ds, executions(0, is(fail())));
    }

    @Test
    public void testFirstStatement() {
        StatementExecution se1 = new StatementExecution();
        se1.setQuery("query-1");
        StatementExecution se2 = new StatementExecution();
        se2.setQuery("query-2");

        ProxyTestDataSource ds = new ProxyTestDataSource();
        ds.getQueryExecutions().add(se1);
        ds.getQueryExecutions().add(se2);

        assertThat(ds, firstStatement(query(is("query-1"))));
        assertThat(ds, firstStatement(is(query(is("query-1")))));
    }

    // TODO: test for fistStatement when there is no StatementExecution
}
