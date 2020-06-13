package com.simonalong.neo.devide;

import com.simonalong.neo.Columns;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.core.DefaultExecutor;
import com.simonalong.neo.core.QuerySync;
import com.simonalong.neo.db.NeoPage;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author shizi
 * @since 2020/6/11 3:11 PM
 */
public abstract class AbstractBaseQuery extends AbstractQueryAsync implements QuerySync {

    @Override
    public Executor getExecutor() {
        return DefaultExecutor.getInstance().getExecutor();
    }

    @Override
    public CompletableFuture<NeoMap> oneAsync(String tableName, Columns columns, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> one(tableName, columns, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(String tableName, Columns columns, T entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> one(tableName, columns, entity), executor);
    }

    @Override
    public CompletableFuture<NeoMap> oneAsync(String tableName, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> one(tableName, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(String tableName, T entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> one(tableName, entity), executor);
    }

    @Override
    public CompletableFuture<NeoMap> oneAsync(String tableName, Number id, Executor executor){
        return CompletableFuture.supplyAsync(() -> one(tableName, id), executor);
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap, Executor executor) {
        return CompletableFuture.supplyAsync(() -> one(tClass, tableName, columns, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(Class<T> tClass, String tableName, NeoMap searchMap, Executor executor) {
        return CompletableFuture.supplyAsync(() -> one(tClass, tableName, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<T> oneAsync(Class<T> tClass, String tableName, Number id, Executor executor) {
        return CompletableFuture.supplyAsync(() -> one(tClass, tableName, id), executor);
    }


    @Override
    public CompletableFuture<List<NeoMap>> listAsync(String tableName, Columns columns, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> list(tableName, columns, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(String tableName, Columns columns, T entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> list(tableName, columns, entity), executor);
    }

    @Override
    public CompletableFuture<List<NeoMap>> listAsync(String tableName, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> list(tableName, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(String tableName, T entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> list(tableName, entity), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap, Executor executor) {
        return CompletableFuture.supplyAsync(() -> list(tClass, tableName, columns, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, String tableName, NeoMap searchMap, Executor executor) {
        return CompletableFuture.supplyAsync(() -> list(tClass, tableName, searchMap), executor);
    }


    @Override
    public <T> CompletableFuture<List<T>> valuesAsync(String tableName, Class<T> tClass, String field, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> values(tableName, tClass, field, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> valuesAsync(String tableName, Class<T> tClass, String field, Object entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> values(tableName, tClass, field, entity), executor);
    }

    @Override
    public CompletableFuture<List<String>> valuesAsync(String tableName, String field, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> values(tableName, field, searchMap), executor);
    }

    @Override
    public CompletableFuture<List<String>> valuesAsync(String tableName, String field, Object entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> values(tableName, field, entity), executor);
    }

    @Override
    public CompletableFuture<List<String>> valuesAsync(String tableName, String field, Executor executor){
        return CompletableFuture.supplyAsync(() -> values(tableName, field), executor);
    }

    @Override
    public <T> CompletableFuture<T> valueAsync(String tableName, Class<T> tClass, String field, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> value(tableName, tClass, field, searchMap), executor);
    }

    @Override
    public <T> CompletableFuture<T> valueAsync(String tableName, Class<T> tClass, String field, Object entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> value(tableName, tClass, field, entity), executor);
    }

    @Override
    public CompletableFuture<String> valueAsync(String tableName, String field, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> value(tableName, field, searchMap), executor);
    }

    @Override
    public CompletableFuture<String> valueAsync(String tableName, String field, Object entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> value(tableName, field, entity), executor);
    }

    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, Columns columns, NeoMap searchMap, NeoPage page, Executor executor){
        return CompletableFuture.supplyAsync(() -> page(tableName, columns, searchMap, page), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(String tableName, Columns columns, T entity, NeoPage page, Executor executor){
        return CompletableFuture.supplyAsync(() -> page(tableName, columns, entity, page), executor);
    }

    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, NeoMap searchMap, NeoPage page, Executor executor){
        return CompletableFuture.supplyAsync(() -> page(tableName, searchMap, page), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(String tableName, T entity, NeoPage page, Executor executor){
        return CompletableFuture.supplyAsync(() -> page(tableName, entity, page), executor);
    }

    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, Columns columns, NeoPage page, Executor executor){
        return CompletableFuture.supplyAsync(() -> page(tableName, columns, page), executor);
    }

    @Override
    public CompletableFuture<List<NeoMap>> pageAsync(String tableName, NeoPage page, Executor executor){
        return CompletableFuture.supplyAsync(() -> page(tableName, page), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap, NeoPage page, Executor executor) {
        return CompletableFuture.supplyAsync(() -> page(tClass, tableName, columns, searchMap, page), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, NeoMap searchMap, NeoPage page, Executor executor) {
        return CompletableFuture.supplyAsync(() -> page(tClass, tableName, searchMap, page), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, Columns columns, NeoPage page, Executor executor) {
        return CompletableFuture.supplyAsync(() -> page(tClass, tableName, columns, page), executor);
    }

    @Override
    public <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, String tableName, NeoPage page, Executor executor) {
        return CompletableFuture.supplyAsync(() -> page(tClass, tableName, page), executor);
    }

    @Override
    public CompletableFuture<Integer> countAsync(String tableName, NeoMap searchMap, Executor executor){
        return CompletableFuture.supplyAsync(() -> count(tableName, searchMap), executor);
    }

    @Override
    public CompletableFuture<Integer> countAsync(String tableName, Object entity, Executor executor){
        return CompletableFuture.supplyAsync(() -> count(tableName, entity), executor);
    }

    @Override
    public CompletableFuture<Boolean> existAsync(String tableName, NeoMap searchMap, Executor executor) {
        return CompletableFuture.supplyAsync(() -> exist(tableName, searchMap), executor);
    }

    @Override
    public CompletableFuture<Boolean> existAsync(String tableName, Object entity, Executor executor) {
        return CompletableFuture.supplyAsync(() -> exist(tableName, entity), executor);
    }

    @Override
    public CompletableFuture<Integer> countAsync(String tableName, Executor executor){
        return CompletableFuture.supplyAsync(() -> count(tableName), executor);
    }

    @Override
    public <T> T one(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap) {
        NeoMap data = one(tableName, columns, searchMap);
        if(null == data){
            return null;
        }
        return data.as(tClass);
    }

    @Override
    public <T> T one(Class<T> tClass, String tableName, Columns columns, Number key) {
        NeoMap data = one(tableName, columns, key);
        if(null == data){
            return null;
        }
        return data.as(tClass);
    }

    @Override
    public <T> T one(Class<T> tClass, String tableName, NeoMap searchMap) {
        NeoMap data = one(tableName, searchMap);
        if(null == data){
            return null;
        }
        return data.as(tClass);
    }

    @Override
    public <T> T one(Class<T> tClass, String tableName, Number id) {
        NeoMap data = one(tableName, id);
        if(null == data){
            return null;
        }
        return data.as(tClass);
    }


    @Override
    public <T> List<T> list(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap) {
        return NeoMap.asArray(list(tableName, columns, searchMap), tClass);
    }

    @Override
    public <T> List<T> list(Class<T> tClass, String tableName, NeoMap searchMap) {
        return NeoMap.asArray(list(tableName, searchMap), tClass);
    }

    @Override
    public <T> List<T> list(Class<T> tClass, String tableName, Columns columns) {
        return NeoMap.asArray(list(tableName, columns), tClass);
    }

    @Override
    public <T> List<T> page(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap, NeoPage page) {
        return NeoMap.asArray(page(tableName, columns, searchMap, page), tClass);
    }

    @Override
    public <T> List<T> page(Class<T> tClass, String tableName, NeoMap searchMap, NeoPage page) {
        return NeoMap.asArray(page(tableName, searchMap, page), tClass);
    }

    @Override
    public <T> List<T> page(Class<T> tClass, String tableName, Columns columns, NeoPage page) {
        return NeoMap.asArray(page(tableName, columns, page), tClass);
    }

    @Override
    public <T> List<T> page(Class<T> tClass, String tableName, NeoPage page) {
        return NeoMap.asArray(page(tableName, page), tClass);
    }

    @Override
    public Boolean exist(String tableName, NeoMap searchMap) {
        return NeoMap.isUnEmpty(one(tableName, searchMap));
    }

    @Override
    public Boolean exist(String tableName, Object entity) {
        return null != one(tableName, entity);
    }
}