package com.simonalong.neo.core;

import com.simonalong.neo.Columns;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.db.NeoPage;
import com.simonalong.neo.db.PageReq;
import com.simonalong.neo.db.PageRsp;
import com.simonalong.neo.express.SearchQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 填充默认线程池
 *
 * @author zhouzhenyong
 * @since 2019/8/18 下午5:59
 */
public abstract class AbstractDbAsync implements CqrsAsync{

    @Override
    public CompletableFuture<NeoMap> insertAsync(String tableName, NeoMap dataMap) {
        return insertAsync(tableName, dataMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> insertAsync(String tableName, T object) {
        return insertAsync(tableName, object, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> insertOfUnExistAsync(String tableName, NeoMap dataMap, String... searchColumnKey) {
        return insertOfUnExistAsync(tableName, dataMap, getExecutor(), searchColumnKey);
    }

    @Override
    public <T> CompletableFuture<T> insertOfUnExistAsync(String tableName, T object, String... searchColumnKey) {
        return insertOfUnExistAsync(tableName, object, getExecutor(), searchColumnKey);
    }

    @Override
    public CompletableFuture<NeoMap> saveAsync(String tableName, NeoMap dataMap, String... searchColumnKey) {
        return saveAsync(tableName, dataMap, getExecutor(), searchColumnKey);
    }

    @Override
    public <T> CompletableFuture<T> saveAsync(String tableName, T object, String... searchColumnKey) {
        return saveAsync(tableName, object, getExecutor(), searchColumnKey);
    }

    @Override
    public CompletableFuture<Integer> deleteAsync(String tableName, NeoMap dataMap) {
        return deleteAsync(tableName, dataMap, getExecutor());
    }

    @Override
    public CompletableFuture<Integer> deleteAsync(String tableName, SearchQuery searchQuery) {
        return deleteAsync(tableName, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<Integer> deleteAsync(String tableName, T object) {
        return deleteAsync(tableName, object, getExecutor());
    }

    @Override
    public CompletableFuture<Integer> deleteAsync(String tableName, Number id) {
        return deleteAsync(tableName, id, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> updateAsync(String tableName, NeoMap dataMap, NeoMap searchMap) {
        return updateAsync(tableName, dataMap, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> updateAsync(String tableName, T setEntity, NeoMap searchMap) {
        return updateAsync(tableName, setEntity, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> updateAsync(String tableName, T setEntity, T searchEntity) {
        return updateAsync(tableName, setEntity, searchEntity, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> updateAsync(String tableName, NeoMap dataMap, Number id) {
        return updateAsync(tableName, dataMap, id, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> updateAsync(String tableName, T setEntity, Number id) {
        return updateAsync(tableName, setEntity, id, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> updateAsync(String tableName, NeoMap dataMap, Columns columns) {
        return updateAsync(tableName, dataMap, columns, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> updateAsync(String tableName, T entity, Columns columns) {
        return updateAsync(tableName, entity, columns, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> updateAsync(String tableName, NeoMap dataMap) {
        return updateAsync(tableName, dataMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> updateAsync(String tableName, T entity) {
        return updateAsync(tableName, entity, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> updateAsync(String tableName, NeoMap dataMap, SearchQuery searchQuery) {
        return updateAsync(tableName, dataMap, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> updateAsync(String tableName, T setEntity, SearchQuery searchQuery) {
        return updateAsync(tableName, setEntity, searchQuery, getExecutor());
    }


    @Override
    public CompletableFuture<NeoMap> oneAsync(String tableName, Columns columns, NeoMap searchMap) {
        return oneAsync(tableName, columns, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> oneAsync(String tableName, Columns columns, SearchQuery searchQuery) {
        return oneAsync(tableName, columns, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(String tableName, Columns columns, T entity) {
        return oneAsync(tableName, columns, entity, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> oneAsync(String tableName, NeoMap searchMap) {
        return oneAsync(tableName, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> oneAsync(String tableName, SearchQuery searchQuery) {
        return oneAsync(tableName, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(String tableName, T entity) {
        return oneAsync(tableName, entity, getExecutor());
    }

    @Override
    public CompletableFuture<NeoMap> oneAsync(String tableName, Number id) {
        return oneAsync(tableName, id, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap) {
        return oneAsync(tClass, tableName, columns, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(Class<T> tClass, String tableName, Columns columns, SearchQuery searchQuery) {
        return oneAsync(tClass, tableName, columns, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(Class<T> tClass, String tableName, NeoMap searchMap) {
        return oneAsync(tClass, tableName, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(Class<T> tClass, String tableName, SearchQuery searchQuery) {
        return oneAsync(tClass, tableName, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(Class<T> tClass, String tableName, Number id) {
        return oneAsync(tClass, tableName, id, getExecutor());
    }


    @Override
    public CompletableFuture<List<NeoMap>> listAsync(String tableName, Columns columns, NeoMap searchMap) {
        return listAsync(tableName, columns, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<List<NeoMap>> listAsync(String tableName, Columns columns, SearchQuery searchQuery) {
        return listAsync(tableName, columns, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(String tableName, Columns columns, T entity) {
        return listAsync(tableName, columns, entity, getExecutor());
    }

    @Override
    public CompletableFuture<List<NeoMap>> listAsync(String tableName, NeoMap searchMap) {
        return listAsync(tableName, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<List<NeoMap>> listAsync(String tableName, SearchQuery searchQuery) {
        return listAsync(tableName, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(String tableName, T entity) {
        return listAsync(tableName, entity, getExecutor());
    }


    @Override
    public <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap) {
        return listAsync(tClass, tableName, columns, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, String tableName, Columns columns, SearchQuery searchQuery) {
        return listAsync(tClass, tableName, columns, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, String tableName, NeoMap searchMap) {
        return listAsync(tClass, tableName, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, String tableName, SearchQuery searchQuery) {
        return listAsync(tClass, tableName, searchQuery, getExecutor());
    }


    @Override
    public <T> CompletableFuture<List<T>> valuesAsync(String tableName, Class<T> tClass, String field, NeoMap searchMap) {
        return valuesAsync(tableName, tClass, field, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesAsync(String tableName, Class<T> tClass, String field, SearchQuery searchQuery) {
        return valuesAsync(tableName, tClass, field, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesAsync(String tableName, Class<T> tClass, String field, Object entity) {
        return valuesAsync(tableName, tClass, field, entity, getExecutor());
    }


    @Override
    public <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, String tableName, String field, NeoMap searchMap) {
        return valuesAsync(tClass, tableName, field, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, String tableName, String field, SearchQuery searchQuery) {
        return valuesAsync(tClass, tableName, field, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, String tableName, String field, Object entity) {
        return valuesAsync(tClass, tableName, field, entity, getExecutor());
    }


    @Override
    public CompletableFuture<List<String>> valuesAsync(String tableName, String field, NeoMap searchMap) {
        return valuesAsync(tableName, field, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<List<String>> valuesAsync(String tableName, String field, SearchQuery searchQuery) {
        return valuesAsync(tableName, field, searchQuery, getExecutor());
    }

    @Override
    public CompletableFuture<List<String>> valuesAsync(String tableName, String field, Object entity) {
        return valuesAsync(tableName, field, entity, getExecutor());
    }

    @Override
    public CompletableFuture<List<String>> valuesAsync(String tableName, String field) {
        return valuesAsync(tableName, field, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesOfDistinctAsync(String tableName, Class<T> tClass, String field, NeoMap searchMap) {
        return valuesOfDistinctAsync(tableName, tClass, field, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesOfDistinctAsync(String tableName, Class<T> tClass, String field, SearchQuery searchQuery) {
        return valuesOfDistinctAsync(tableName, tClass, field, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesOfDistinctAsync(String tableName, Class<T> tClass, String field, Object entity) {
        return valuesOfDistinctAsync(tableName, tClass, field, entity, getExecutor());
    }


    @Override
    public <T> CompletableFuture<List<T>> valuesOfDistinctAsync(Class<T> tClass, String tableName, String field, NeoMap searchMap) {
        return valuesOfDistinctAsync(tClass, tableName, field, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesOfDistinctAsync(Class<T> tClass, String tableName, String field, SearchQuery searchQuery) {
        return valuesOfDistinctAsync(tClass, tableName, field, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesOfDistinctAsync(Class<T> tClass, String tableName, String field, Object entity) {
        return valuesOfDistinctAsync(tClass, tableName, field, entity, getExecutor());
    }


    @Override
    public CompletableFuture<List<String>> valuesOfDistinctAsync(String tableName, String field, NeoMap searchMap) {
        return valuesOfDistinctAsync(tableName, field, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<List<String>> valuesOfDistinctAsync(String tableName, String field, SearchQuery searchQuery) {
        return valuesOfDistinctAsync(tableName, field, searchQuery, getExecutor());
    }

    @Override
    public CompletableFuture<List<String>> valuesOfDistinctAsync(String tableName, String field, Object entity) {
        return valuesOfDistinctAsync(tableName, field, entity, getExecutor());
    }

    @Override
    public CompletableFuture<List<String>> valuesOfDistinctAsync(String tableName, String field) {
        return valuesOfDistinctAsync(tableName, field, getExecutor());
    }


    @Override
    public <T> CompletableFuture<T> valueAsync(String tableName, Class<T> tClass, String field, NeoMap searchMap) {
        return valueAsync(tableName, tClass, field, searchMap, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> valueAsync(String tableName, Class<T> tClass, String field, SearchQuery searchQuery) {
        return valueAsync(tableName, tClass, field, searchQuery, getExecutor());
    }

    @Override
    public <T> CompletableFuture<T> valueAsync(String tableName, Class<T> tClass, String field, Object entity) {
        return valueAsync(tableName, tClass, field, entity, getExecutor());
    }

    @Override
    public CompletableFuture<String> valueAsync(String tableName, String field, NeoMap searchMap) {
        return valueAsync(tableName, field, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<String> valueAsync(String tableName, String field, SearchQuery searchQuery) {
        return valueAsync(tableName, field, searchQuery, getExecutor());
    }

    @Override
    public CompletableFuture<String> valueAsync(String tableName, String field, Object entity) {
        return valueAsync(tableName, field, entity, getExecutor());
    }

    @Override
    @Deprecated
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, Columns columns, NeoMap searchMap, NeoPage page) {
        return pageAsync(tableName, columns, searchMap, page, getExecutor());
    }

    @Override
    @Deprecated
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, Columns columns, SearchQuery searchQuery, NeoPage page) {
        return pageAsync(tableName, columns, searchQuery, page, getExecutor());
    }

    @Override
    @Deprecated
    public <T> CompletableFuture<List<T>> pageAsync(String tableName, Columns columns, T entity, NeoPage page) {
        return pageAsync(tableName, columns, entity, page, getExecutor());
    }

    @Override
    @Deprecated
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, NeoMap searchMap, NeoPage page) {
        return pageAsync(tableName, searchMap, page, getExecutor());
    }

    @Override
    @Deprecated
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, SearchQuery searchQuery, NeoPage page) {
        return pageAsync(tableName, searchQuery, page, getExecutor());
    }

    @Override
    @Deprecated
    public <T> CompletableFuture<List<T>> pageAsync(String tableName, T entity, NeoPage page) {
        return pageAsync(tableName, entity, page, getExecutor());
    }

    @Override
    @Deprecated
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, Columns columns, NeoPage page) {
        return pageAsync(tableName, columns, page, getExecutor());
    }


    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, Columns columns, NeoMap searchMap, PageReq<?> pageReq) {
        return pageAsync(tableName, columns, searchMap, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, Columns columns, SearchQuery searchQuery, PageReq<?> pageReq) {
        return pageAsync(tableName, columns, searchQuery, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(String tableName, Columns columns, T entity, PageReq<?> pageReq) {
        return pageAsync(tableName, columns, entity, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, NeoMap searchMap, PageReq<?> pageReq) {
        return pageAsync(tableName, searchMap, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, SearchQuery searchQuery, PageReq<?> pageReq) {
        return pageAsync(tableName, searchQuery, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(String tableName, T entity, PageReq<?> pageReq) {
        return pageAsync(tableName, entity, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, Columns columns, PageReq<?> pageReq) {
        return pageAsync(tableName, columns, pageReq, getExecutor());
    }


    @Override
    @Deprecated
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, NeoPage page) {
        return pageAsync(tableName, page, getExecutor());
    }

    @Override
    @Deprecated
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap, NeoPage page) {
        return pageAsync(tClass, tableName, columns, searchMap, page, getExecutor());
    }

    @Override
    @Deprecated
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, Columns columns, SearchQuery searchQuery, NeoPage page) {
        return pageAsync(tClass, tableName, columns, searchQuery, page, getExecutor());
    }

    @Override
    @Deprecated
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, NeoMap searchMap, NeoPage page) {
        return pageAsync(tClass, tableName, searchMap, page, getExecutor());
    }

    @Override
    @Deprecated
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, SearchQuery searchQuery, NeoPage page) {
        return pageAsync(tClass, tableName, searchQuery, page, getExecutor());
    }

    @Override
    @Deprecated
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, Columns columns, NeoPage page) {
        return pageAsync(tClass, tableName, columns, page, getExecutor());
    }

    @Override
    @Deprecated
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, NeoPage page) {
        return pageAsync(tClass, tableName, page, getExecutor());
    }


    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, PageReq<?> pageReq) {
        return pageAsync(tableName, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap, PageReq<?> pageReq) {
        return pageAsync(tClass, tableName, columns, searchMap, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, Columns columns, SearchQuery searchQuery, PageReq<?> pageReq) {
        return pageAsync(tClass, tableName, columns, searchQuery, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, NeoMap searchMap, PageReq<?> pageReq) {
        return pageAsync(tClass, tableName, searchMap, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, SearchQuery searchQuery, PageReq<?> pageReq) {
        return pageAsync(tClass, tableName, searchQuery, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, Columns columns, PageReq<?> pageReq) {
        return pageAsync(tClass, tableName, columns, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, PageReq<?> pageReq) {
        return pageAsync(tClass, tableName, pageReq, getExecutor());
    }


    @Override
    public CompletableFuture<PageRsp<NeoMap>> getPageAsync(String tableName, Columns columns, NeoMap searchMap, PageReq<?> pageReq) {
        return getPageAsync(tableName, columns, searchMap, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<PageRsp<NeoMap>> getPageAsync(String tableName, Columns columns, SearchQuery searchQuery, PageReq<?> pageReq) {
        return getPageAsync(tableName, columns, searchQuery, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<PageRsp<T>> getPageAsync(String tableName, Columns columns, T entity, PageReq<?> pageReq) {
        return getPageAsync(tableName, columns, entity, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<PageRsp<NeoMap>> getPageAsync(String tableName, NeoMap searchMap, PageReq<?> pageReq) {
        return getPageAsync(tableName, searchMap, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<PageRsp<NeoMap>> getPageAsync(String tableName, SearchQuery searchQuery, PageReq<?> pageReq) {
        return getPageAsync(tableName, searchQuery, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<PageRsp<T>> getPageAsync(String tableName, T entity, PageReq<?> pageReq) {
        return getPageAsync(tableName, entity, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<PageRsp<NeoMap>> getPageAsync(String tableName, Columns columns, PageReq<?> pageReq) {
        return getPageAsync(tableName, columns, pageReq, getExecutor());
    }

    @Override
    public CompletableFuture<PageRsp<NeoMap>> getPageAsync(String tableName, PageReq<?> pageReq) {
        return getPageAsync(tableName, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<PageRsp<T>> getPageAsync(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap, PageReq<?> pageReq) {
        return getPageAsync(tClass, tableName, columns, searchMap, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<PageRsp<T>> getPageAsync(Class<T> tClass, String tableName, Columns columns, SearchQuery searchQuery, PageReq<?> pageReq) {
        return getPageAsync(tClass, tableName, columns, searchQuery, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<PageRsp<T>> getPageAsync(Class<T> tClass, String tableName, NeoMap searchMap, PageReq<?> pageReq) {
        return getPageAsync(tClass, tableName, searchMap, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<PageRsp<T>> getPageAsync(Class<T> tClass, String tableName, SearchQuery searchQuery, PageReq<?> pageReq) {
        return getPageAsync(tClass, tableName, searchQuery, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<PageRsp<T>> getPageAsync(Class<T> tClass, String tableName, Columns columns, PageReq<?> pageReq) {
        return getPageAsync(tClass, tableName, columns, pageReq, getExecutor());
    }

    @Override
    public <T> CompletableFuture<PageRsp<T>> getPageAsync(Class<T> tClass, String tableName, PageReq<?> pageReq) {
        return getPageAsync(tClass, tableName, pageReq, getExecutor());
    }


    @Override
    public CompletableFuture<Integer> countAsync(String tableName, NeoMap searchMap) {
        return countAsync(tableName, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<Integer> countAsync(String tableName, SearchQuery searchQuery) {
        return countAsync(tableName, searchQuery, getExecutor());
    }

    @Override
    public CompletableFuture<Integer> countAsync(String tableName, Object entity) {
        return countAsync(tableName, entity, getExecutor());
    }

    @Override
    public CompletableFuture<Integer> countAsync(String tableName) {
        return countAsync(tableName, getExecutor());
    }

    @Override
    public CompletableFuture<Boolean> existAsync(String tableName, NeoMap searchMap) {
        return existAsync(tableName, searchMap, getExecutor());
    }

    @Override
    public CompletableFuture<Boolean> existAsync(String tableName, SearchQuery searchQuery) {
        return existAsync(tableName, searchQuery, getExecutor());
    }

    @Override
    public CompletableFuture<Boolean> existAsync(String tableName, Object entity) {
        return existAsync(tableName, entity, getExecutor());
    }

    @Override
    public CompletableFuture<Boolean> existAsync(String tableName, Number id) {
        return existAsync(tableName, id, getExecutor());
    }


    @Override
    public CompletableFuture<Integer> batchInsertAsync(String tableName, List<NeoMap> dataMapList) {
        return batchInsertAsync(tableName, dataMapList, getExecutor());
    }

    @Override
    public <T> CompletableFuture<Integer> batchInsertEntityAsync(String tableName, List<T> dataList) {
        return batchInsertEntityAsync(tableName, dataList, getExecutor());
    }


    @Override
    public CompletableFuture<Integer> batchUpdateAsync(String tableName, List<NeoMap> dataList) {
        return batchUpdateAsync(tableName, dataList, getExecutor());
    }

    @Override
    public CompletableFuture<Integer> batchUpdateAsync(String tableName, List<NeoMap> dataList, Columns columns) {
        return batchUpdateAsync(tableName, dataList, getExecutor());
    }

    @Override
    public <T> CompletableFuture<Integer> batchUpdateEntityAsync(String tableName, List<T> dataList) {
        return batchUpdateEntityAsync(tableName, dataList, getExecutor());
    }

    @Override
    public <T> CompletableFuture<Integer> batchUpdateEntityAsync(String tableName, List<T> dataList, Columns columns) {
        return batchUpdateEntityAsync(tableName, dataList, columns, getExecutor());
    }
}
