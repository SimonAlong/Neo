package com.simonalong.neo.operate;

import com.simonalong.neo.NeoBaseTest;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.express.Express;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.simonalong.neo.express.Operate.And;

/**
 * @author shizi
 * @since 2020/8/29 11:13 上午
 */
public class NeoExpress extends NeoBaseTest {

    /**
     * 测试and所有形式
     * where `name`=1 and `group`='test' and `age` = 3
     */
    @Test
    public void antTest() {
        Express neoExpress;
        String sql;

        //--------------------- 采用 and 函数的（函数and不带括号） ---------------------
        sql = "where `name`=1 and `group`='test' and `age` = 3";
        neoExpress = new Express().and("name", 1, "group", "test", "age", 3);
        Assert.assertEquals(sql, neoExpress.toSql());

        // where `name`=1 and (`group`='test') and (`age` = 3)
//        neoExpress = new Express().and("name", 1).and("group", "test").and("age", 3);
//
//
//        //--------------------- 采用 op 函数的 ---------------------
//        // where `name`=1 and (`group`='test') and (`age` = 3)
//        neoExpress = new Express().op("name", 1).and("group", "test").and("age", 3);
//
//        // where `name`=1 and (`group`='test' and `age` = 3)
//        neoExpress = new Express().op("name", 1).and("group", "test", "age", 3);
//
//
//        //--------------------- 采用 And 类的（类And生成的带括号） ---------------------
//        sql = "where `name`=1 and `group`='test' and `age` = 3";
//        neoExpress = new Express(And("name", 1, "group", "test", "age", 3));
//        Assert.assertEquals(sql, neoExpress.toSql());
//        // where `name`=1 and (`group`='test') and (`age` = 3)
//        neoExpress = new Express().op("name", 1, And("group", "test"), And("age", 3));
//
//        // where `name`=1 and (`group`='test' and `age` = 3)
//        neoExpress = new Express("name", 1, And("group", "test", "age", 3));
//
//
//        //--------------------- 采用 NeoMap参数 ---------------------
//        NeoMap searchMap = NeoMap.of();
//        searchMap.put("name", 1);
//        searchMap.put("group", "test");
//        searchMap.put("age", 3);
//        // where `name`=1 and `group`='test' and `age` = 3
//        neoExpress = new Express(searchMap);
//
//        // where `name`=1 and `group`='test' and `age` = 3
//        neoExpress = new Express().and(searchMap);
//
//        // 注意：这里如果采用op，支持默认为And的形式
//        // where `name`=1 and `group`='test' and `age` = 3
//        neoExpress = new Express().op(searchMap);
//
//
//        //--------------------- 采用 NeoMap多参数 ---------------------
//        // where `name`=1 and `group`='test' and `age` = 3
//        neoExpress = new Express(NeoMap.of("name", 1), NeoMap.of("group", "test"), NeoMap.of("age", 3));
//
//        // where `name`=1 and `group`='test' and `age` = 3
//        neoExpress = new Express(NeoMap.of("name", 1), NeoMap.of("group", "test", "age", 3));
//
//        // where `name`=1 and `group`='test' and `age` = 3
//        neoExpress = new Express().and(searchMap);
//
//        // 注意：这里如果采用op，支持默认为 and 的形式
//        // where `name`=1 and `group`='test' and `age` = 3
//        neoExpress = new Express().op(searchMap);
    }
//
//    /**
//     * 测试 or
//     * where `name`=1 or `group`='test' or `age` = 3
//     */
//    @Test
//    public void orTest() {
//        Express neoExpress;
//
//        //--------------------- 采用 or 函数的（函数or生成的不带括号） ---------------------
//        // where `name`=1 or `group`='test' or `age` = 3
//        neoExpress = new Express().or("name", 1, "group", "test", "age", 3);
//
//        // where `name`=1 or `group`='test' or `age` = 3
//        neoExpress = new Express().or("name", 1).or("group", "test").or("age", 3);
//
//
//        //--------------------- 采用 op 函数的 ---------------------
//        // where `name`=1 or `group`='test' or `age` = 3
//        neoExpress = new Express().op("name", 1).or("group", "test").or("age", 3);
//
//        // where `name`=1 or `group`='test' or `age` = 3
//        neoExpress = new Express().op("name", 1).or("group", "test", "age", 3);
//
//
//        //--------------------- 采用 Or 类的（类Or生成的sql是带括号的） ---------------------
//        // where `name`=1 or (`group`='test' or `age` = 3)
//        neoExpress = new Express("name", 1, Or("group", "test", "age", 3));
//
//        // where `name`=1 or (`group`='test') or (`age` = 3)
//        neoExpress = new Express().op("name", 1, Or("group", "test"), Or("age", 3));
//
//
//        //--------------------- 采用 NeoMap参数 ---------------------
//        NeoMap searchMap = NeoMap.of();
//        searchMap.put("name", 1);
//        searchMap.put("group", "test");
//        searchMap.put("age", 3);
//
//        // where `name`=1 or `group`='test' or `age` = 3
//        neoExpress = new Express().or(searchMap);
//
//        // where `name`=1 or `group`='test' or `age` = 3
//        neoExpress = new Express().op(Or(searchMap));
//
//
//        //--------------------- 采用 NeoMap多参数 ---------------------
//        // where `name`=1 or `group`='test' or `age` = 3
//        neoExpress = new Express(Or(NeoMap.of("name", 1)), Or(NeoMap.of("group", "test")), Or(NeoMap.of("age", 3)));
//
//        // where `name`=1 or `group`='test' or `age` = 3
//        neoExpress = new Express(Or(NeoMap.of("name", 1)), Or(NeoMap.of("group", "test", "age", 3)));
//    }
//
//    /**
//     * 测试 or 和 and 结合的部分
//     * where `name`=1 and (`group`='test' or `age` = 3)
//     */
//    @Test
//    public void andOrTest() {
//        Express neoExpress;
//
//        // where `name`=1 and (`group`='test' or `age` = 3)
//        neoExpress = new Express().and("name", 1, Or("group", "test", "age", 3));
//
//        // 对于内部已经有的Or会在遇到外部的and时候会处理掉
//        // where `name`=1 and (`group`='test' or `age` = 3)
//        neoExpress = new Express().op("name", 1).and(Or("group", "test", "age", 3));
//
//        // where `name`=1 and ((`group`='test') or (`age` = 3))
//        neoExpress = new Express().and("name", 1, And(Or("group", "test"), Or("age", 3)));
//
//        // where `name`=1 and ((`group`='test') or (`age` = 3))
//        neoExpress = new Express().op("name", 1).and(And(Or("group", "test"), Or("age", 3)));
//
//        // 其中Op是空串（但是带括号）
//        // where `name`=1 and ((`group`='test') or (`age` = 3))
//        neoExpress = new Express().op("name", 1).and(Op("group", "test", Or("age", 3)));
//
//        // 其中Op是空串（但是带括号）
//        // where `name`=1 and ((`group`='test') or (`age` = 3))
//        neoExpress = new Express().op("name", 1).and(Op(Or("group", "test"), Or("age", 3)));
//    }
//
//
//    /**
//     * 其他比较符号测试：等于
//     * 也是默认的操作符
//     * where `name` != 12 and `age` = 3
//     */
//    @Test
//    public void equalTest() {
//        // where `name` = 'tt' and `age` = 3
//        Express express1 = new Express().and(Equal("name", "tt")).and(Equal("age", 3));
//
//        Express express2 = new Express().and("name", 12, "age", 3);
//    }
//
//
//    /**
//     * 其他比较符号测试：不等于
//     * where `name` != 12 and `age` = 3
//     */
//    @Test
//    public void notEqualTest() {
//        Express express1 = new Express().and(NotEqual("name", 12)).and("age", 3);
//
//        Express express2 = new Express().and(NotEqual("name", 12), "age", 3);
//    }
//
//    /**
//     * 其他比较符号测试：大于、小于
//     * where `name` = 'chou' and `age` > 3 and `age` < 50
//     */
//    @Test
//    public void compareTest() {
//        Express express2 = new Express().and("name", "chou", GreaterThan("age", 3), LessThan("age", 50));
//    }
//
//    /**
//     * 其他比较符号测试：大于等于、小于等于
//     * where `name` = 'chou' and `age` > 3 and `age` < 50
//     */
//    @Test
//    public void compareEqualTest() {
//        Express express2 = new Express().and("name", "chou", GreaterEqual("age", 3), LessEqual("age", 50));
//    }
//
//    /**
//     * 其他符号测试：like
//     * where `name` like '%chou' and `age` = 3
//     */
//    @Test
//    public void likeTest() {
//        Express express;
//        express = new Express().and(Like("name", "chou", "%"), "age", 3);
//
//        express = new Express().and(Like("name", "%", "chou"), "age", 3);
//
//        express = new Express().and(Like("name", "%", "chou", "%"), "age", 3);
//    }
//
//    /**
//     * 其他符号测试：not like
//     * where `name` like '%chou' and `age` = 3
//     */
//    @Test
//    public void notLikeTest() {
//        Express express;
//        express= new Express().and(NotLike("name", "chou", "%"), "age", 3);
//
//        express = new Express().and(NotLike("name", "%", "chou"), "age", 3);
//
//        express = new Express().and(NotLike("name", "%", "chou", "%"), "age", 3);
//    }
//
//    /**
//     * 其他符号测试：in
//     * where id in (12,32,43,43)
//     */
//    @Test
//    public void inTest() {
//        Express express;
//
//        List<Long> dataList = new ArrayList<>();
//        dataList.add(12L);
//        dataList.add(13L);
//        dataList.add(14L);
//        express = new Express().and(In("id", dataList));
//    }
//
//
//    /**
//     * 其他符号测试：not in
//     * where not id in (12,32,43,43)
//     */
//    @Test
//    public void notInTest() {
//        Express express;
//
//        List<Long> dataList = new ArrayList<>();
//        dataList.add(12L);
//        dataList.add(13L);
//        dataList.add(14L);
//        express = new Express().and(NotIn("id", dataList));
//    }
//
//    /**
//     * 其他符号测试：is null
//     * where `name` is null
//     */
//    @Test
//    public void isNullTest() {
//        Express express;
//
//        List<Long> dataList = new ArrayList<>();
//        dataList.add(12L);
//        dataList.add(13L);
//        dataList.add(14L);
//        express = new Express().and(IsNull("id"));
//    }
//
//    /**
//     * 其他符号测试：is null
//     * where `name` is null
//     */
//    @Test
//    public void isNotNullTest() {
//        Express express;
//
//        List<Long> dataList = new ArrayList<>();
//        dataList.add(12L);
//        dataList.add(13L);
//        dataList.add(14L);
//        express = new Express().op(IsNotNull("id"));
//    }
//
//    /**
//     * 其他符号测试：group by
//     * where `name` = 'test' group by 'group'
//     */
//    @Test
//    public void groupByTest() {
//        Express express;
//
//        express = new Express().and("name", "test").op(GroupBy("group"));
//    }
//
//    /**
//     * 其他符号测试：order by
//     * where `name` = 'test' order by 'create_time'
//     */
//    @Test
//    public void orderByTest() {
//        Express express;
//
//        // 默认为升序
//        // where `name` = 'test' order by 'create_time'
//        express = new Express().and("name", "test").op(OrderBy("create_time"));
//        express = new Express().and("name", "test").op(OrderBy("create_time", "asc"));
//        express = new Express().and("name", "test").op(OrderBy("create_time", "desc"));
//    }
//
//    /**
//     * where字符测试
//     * <p>
//     *    在没有一些条件的时候，where字段不再存在
//     *
//     *  where `name` = 'test' order by 'create_time'
//     */
//    @Test
//    public void whereTest() {
//        Express express;
//
//        // 默认为升序
//        // where `name` = 'test' order by `create_time`
//        express = new Express().and("name", "test").op(OrderBy("create_time"));
//        // order by 'create_time'
//        express = new Express().op(OrderBy("create_time"));
//
//        // where `name` = 'test' order by `create_time` asc
//        express = new Express().and("name", "test").op(OrderBy("create_time", "asc"));
//
//        // where `name` = 'test' order by `create_time` desc
//        express = new Express().and("name", "test").op(OrderBy("create_time", "desc"));
//    }
//
//    /**
//     * 符号测试：exist
//     * where exist(xxxxx)
//     */
//    @Test
//    public void orderByTest() {
//        Express express;
//
//        // 默认为升序
//        // where exists(select id from xxx)
//        express = new Express().and(Exists("select id from xxx"));
//    }
//
//    /**
//     * 符号测试：not exist
//     * where exist(xxxxx)
//     */
//    @Test
//    public void orderByTest() {
//        Express express;
//
//        // 默认为升序
//        // where not exists(select id from xxx)
//        express = new Express().and(NotExists("select id from xxx"));
//    }
//
//    /**
//     * 符号测试：not exist
//     * where `age` between 12 and 60
//     */
//    @Test
//    public void betweenTest() {
//        Express express;
//
//        // 默认为升序
//        // where not exists(select id from xxx)
//        express = new Express().and(BetweenAnd("age", 12, 60));
//    }
//
//    /**
//     * 符号测试：not exist
//     * where `age` not between 12 and 60
//     */
//    @Test
//    public void notBetweenTest() {
//        Express express;
//
//        // 默认为升序
//        // where `age` not between 12 and 60
//        express = new Express().and(NotBetweenAnd("age", 12, 60));
//    }
}