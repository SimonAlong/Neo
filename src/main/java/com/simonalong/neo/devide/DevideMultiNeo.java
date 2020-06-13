package com.simonalong.neo.devide;

import com.simonalong.neo.Columns;
import com.simonalong.neo.Neo;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.db.NeoPage;
import com.simonalong.neo.exception.NeoNotSupport;
import com.simonalong.neo.exception.NotFindDevideTableException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * 分库分表中的多库多表查询处理
 * <p>
 * 谨记：该类处理的是扫描多库和多表，所以性能会不是很高。对于单个查询，查找到返回，对于集合查询，则会查找所有的库的所有表进行返回
 *
 * @author shizi
 * @since 2020/6/11 2:41 PM
 */
public class DevideMultiNeo extends AbstractBaseQuery {

    /**
     * 待分库的库集合
     */
    private List<Neo> dbList = new ArrayList<>();
    /**
     * 表的哈希处理映射, key：表名，value表的哈希信息
     */
    private Map<String, TableDevideConfig> devideTableInfoMap = new ConcurrentHashMap<>();

    public DevideMultiNeo(List<Neo> dbList, Map<String, TableDevideConfig> devideTableInfoMap) {
        this.dbList = dbList;
        this.devideTableInfoMap = devideTableInfoMap;
    }

    private List<String> getActTableList(String tableName) {
        if (devideTableInfoMap.containsKey(tableName)) {
            return devideTableInfoMap.get(tableName).getActTableNameList();
        }
        return Collections.singletonList(tableName);
    }

    @Override
    public NeoMap one(String tableName, Columns columns, NeoMap searchMap) {
        return executeOne(tableName, (db, actTableName) -> db.one(actTableName, columns, searchMap));
    }

    @Override
    public <T> T one(String tableName, Columns columns, T entity) {
        return executeOne(tableName, (db, actTableName) -> db.one(actTableName, columns, entity));
    }

    @Override
    public NeoMap one(String tableName, Columns columns, Number key) {
        return executeOne(tableName, (db, actTableName) -> db.one(actTableName, columns, key));
    }

    @Override
    public NeoMap one(String tableName, NeoMap searchMap) {
        return executeOne(tableName, (db, actTableName) -> db.one(actTableName, searchMap));
    }

    @Override
    public <T> T one(String tableName, T entity) {
        return executeOne(tableName, (db, actTableName) -> db.one(actTableName, entity));
    }

    @Override
    public NeoMap one(String tableName, Number key) {
        return executeOne(tableName, (db, actTableName) -> db.one(actTableName, key));
    }

    @Override
    public List<NeoMap> list(String tableName, Columns columns, NeoMap searchMap) {
        return executeList(tableName, (db, actTableName) -> db.list(actTableName, columns, searchMap));
    }

    @Override
    public <T> List<T> list(String tableName, Columns columns, T entity) {
        return executeList(tableName, (db, actTableName) -> db.list(actTableName, columns, entity));
    }

    @Override
    public List<NeoMap> list(String tableName, NeoMap searchMap) {
        return executeList(tableName, (db, actTableName) -> db.list(actTableName, searchMap));
    }

    @Override
    public <T> List<T> list(String tableName, T entity) {
        return executeList(tableName, (db, actTableName) -> db.list(actTableName, entity));
    }

    @Override
    public List<NeoMap> list(String tableName, Columns columns) {
        throw new NeoNotSupport("数据量太大，该api分库分表场景不支持");
    }

    @Override
    @Deprecated
    public <T> T value(String tableName, Class<T> tClass, String field, NeoMap searchMap) {
        return executeOne(tableName, (db, actTableName) -> db.value(actTableName, tClass, field, searchMap));
    }

    @Override
    @Deprecated
    public <T> T value(String tableName, Class<T> tClass, String field, Object entity) {
        return executeOne(tableName, (db, actTableName) -> db.value(actTableName, tClass, field, entity));
    }

    @Override
    public <T> T value(Class<T> tClass, String tableName, String field, NeoMap searchMap) {
        return executeOne(tableName, (db, actTableName) -> db.value(tClass, actTableName, field, searchMap));
    }

    @SuppressWarnings("all")
    @Override
    public <T> T value(Class<T> tClass, String tableName, String field, Object entity) {
        return executeOne(tableName, (db, actTableName) -> db.value(tClass, actTableName, field, entity));
    }

    @SuppressWarnings("all")
    @Override
    public String value(String tableName, String field, NeoMap searchMap) {
        return executeOne(tableName, (db, actTableName) -> db.value(actTableName, field, searchMap));
    }

    @SuppressWarnings("all")
    @Override
    public String value(String tableName, String field, Object entity) {
        return executeOne(tableName, (db, actTableName) -> db.value(actTableName, field, entity));
    }

    @Override
    public String value(String tableName, String field, Number id) {
        return executeOne(tableName, (db, actTableName) -> db.value(actTableName, field, id));
    }

    @Override
    @Deprecated
    public <T> List<T> values(String tableName, Class<T> tClass, String field, NeoMap searchMap) {
        return executeList(tableName, (db, actTableName) -> db.values(actTableName, tClass, field, searchMap));
    }

    @Override
    @Deprecated
    public <T> List<T> values(String tableName, Class<T> tClass, String field, Object entity) {
        return executeList(tableName, (db, actTableName) -> db.values(actTableName, tClass, field, entity));
    }

    @Override
    public <T> List<T> values(Class<T> tClass, String tableName, String field, NeoMap searchMap) {
        return executeList(tableName, (db, actTableName) -> db.values(tClass, actTableName, field, searchMap));
    }

    @Override
    public <T> List<T> values(Class<T> tClass, String tableName, String field, Object entity) {
        return executeList(tableName, (db, actTableName) -> db.values(tClass, actTableName, field, entity));
    }

    @Override
    public List<String> values(String tableName, String field, NeoMap searchMap) {
        return executeList(tableName, (db, actTableName) -> db.values(actTableName, field, searchMap));
    }

    @Override
    public List<String> values(String tableName, String field, Object entity) {
        return executeList(tableName, (db, actTableName) -> db.values(actTableName, field, entity));
    }

    @Override
    public List<String> values(String tableName, String field) {
        throw new NeoNotSupport("数据量太大，该api分库分表场景不支持");
    }

    @Override
    public List<NeoMap> page(String tableName, Columns columns, NeoMap searchMap, NeoPage page) {
        NeoPage extendPage = NeoPage.of(0, page.getStartIndex() + page.getPageSize());
        Integer startIndex = page.getStartIndex();
        Integer pageSize = page.getPageSize();
        List<NeoMap> resultList = executeList(tableName, (db, actTableName) -> db.page(actTableName, columns, searchMap, extendPage));
        if (!resultList.isEmpty()) {
            return resultList.subList(startIndex, startIndex + pageSize);
        }
        return resultList;
    }

    @Override
    public <T> List<T> page(String tableName, Columns columns, T entity, NeoPage page) {
        NeoPage extendPage = NeoPage.of(0, page.getStartIndex() + page.getPageSize());
        Integer startIndex = page.getStartIndex();
        Integer pageSize = page.getPageSize();
        List<T> resultList = executeList(tableName, (db, actTableName) -> db.page(actTableName, columns, entity, extendPage));
        if (!resultList.isEmpty()) {
            return resultList.subList(startIndex, startIndex + pageSize);
        }
        return resultList;
    }

    @SuppressWarnings("all")
    @Override
    public List<NeoMap> page(String tableName, NeoMap searchMap, NeoPage page) {
        NeoPage extendPage = NeoPage.of(0, page.getStartIndex() + page.getPageSize());
        Integer startIndex = page.getStartIndex();
        Integer pageSize = page.getPageSize();
        List<NeoMap> resultList = executeList(tableName, (db, actTableName) -> db.page(actTableName, searchMap, extendPage));
        if (!resultList.isEmpty()) {
            return resultList.subList(startIndex, startIndex + pageSize);
        }
        return resultList;
    }

    @Override
    public <T> List<T> page(String tableName, T entity, NeoPage page) {
        NeoPage extendPage = NeoPage.of(0, page.getStartIndex() + page.getPageSize());
        Integer startIndex = page.getStartIndex();
        Integer pageSize = page.getPageSize();
        List<T> resultList = executeList(tableName, (db, actTableName) -> db.page(actTableName, entity, extendPage));
        if (!resultList.isEmpty()) {
            return resultList.subList(startIndex, startIndex + pageSize);
        }
        return resultList;
    }

    @Override
    public List<NeoMap> page(String tableName, Columns columns, NeoPage page) {
        throw new NeoNotSupport("数据量太大，该api分库分表场景不支持");
    }

    @Override
    public List<NeoMap> page(String tableName, NeoPage page) {
        throw new NeoNotSupport("数据量太大，该api分库分表场景不支持");
    }

    @Override
    public Integer count(String tableName, NeoMap searchMap) {
        return executeOne(tableName, (db, actTableName) -> db.count(actTableName, searchMap));
    }

    @Override
    public Integer count(String tableName, Object entity) {
        return executeOne(tableName, (db, actTableName) -> db.count(actTableName, entity));
    }

    @Override
    public Integer count(String tableName) {
        return executeOne(tableName, Neo::count);
    }

    /**
     * 回调执行器，找到一个就返回
     *
     * @param tableName 逻辑表名
     * @param function  回调处理
     * @param <T>       返回类型
     * @return 返回类型的值
     */
    @SuppressWarnings("unchecked")
    private <T> T executeOne(String tableName, BiFunction<Neo, String, T> function) {
        for (Neo db : dbList) {
            List<String> tableList = getActTableList(tableName);
            for (String actTableName : tableList) {
                T result = function.apply(db, actTableName);
                if (result instanceof NeoMap) {
                    if (NeoMap.isUnEmpty(NeoMap.class.cast(result))) {
                        return result;
                    } else {
                        return (T) NeoMap.of();
                    }
                }
                if (null != result) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * 回调执行器，找到所有的数据合并返回
     *
     * @param tableName 逻辑表名
     * @param function  回调处理
     * @param <T>       返回类型
     * @return 返回类型的值
     */
    @SuppressWarnings("unchecked")
    private <T> T executeList(String tableName, BiFunction<Neo, String, T> function) {
        List resultList = new ArrayList();
        for (Neo db : dbList) {
            List<String> tableList = getActTableList(tableName);
            for (String actTableName : tableList) {
                T result = function.apply(db, actTableName);
                if (null != result) {
                    if (result instanceof List) {
                        resultList.addAll(List.class.cast(result));
                    }
                }
            }
        }
        return (T) resultList;
    }
}