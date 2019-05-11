package com.simon.neo;

import com.simon.neo.exception.NeoMapChgException;
import com.simon.neo.exception.NumberOfValueException;
import com.simon.neo.exception.ParameterNullException;
import com.simon.neo.util.ObjectUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouzhenyong
 * @since 2019/3/12 下午12:46
 */
@Slf4j
public class NeoMap implements Map<String, Object> {

    private ConcurrentSkipListMap<String, Object> dataMap;
    /**
     * 全局的命名转换，默认不转换
     */
    private static NamingChg globalNaming = NamingChg.DEFAULT;
    /**
     * 用于自定义的转换器，key为变量名，value为dataMap的key映射，结构为：{@code Map<String, String>}
     */
    @Setter
    private NeoMap userDefineNaming;
    /**
     * 单体数据的命名转换
     */
    @Setter
    private NamingChg localNaming;
    /**
     * 针对存储表和表对应的字段的时候，table名字和columnsName可能一起设定，这里就是tableName
     */
    @Setter
    @Accessors(chain = true)
    private String keyPreTableName;

    private NeoMap() {
        dataMap = new ConcurrentSkipListMap<>();
    }

    /**
     * 通过key-value-key-value生成
     *
     * @param kvs 参数是通过key-value-key-value等等这种
     * @return 生成的map数据
     */
    public static NeoMap of(Object... kvs) {
        if (kvs.length % 2 != 0) {
            throw new NumberOfValueException("参数请使用：key,value,key,value...这种参数格式");
        }

        NeoMap neoMap = new NeoMap();
        for (int i = 0; i < kvs.length; i += 2) {
            if (null == kvs[i]) {
                throw new ParameterNullException("NeoMap.of()中的参数不可为null");
            }
            neoMap.put((String) kvs[i], kvs[i + 1]);
        }
        return neoMap;
    }

    /**
     * 将多个NeoMap中的value集合起来
     *
     * @param maps 多个NeoMap
     * @return 多个NeoMap的Values集合
     */
    public static List<Object> values(NeoMap... maps) {
        List<Object> valueList = new ArrayList<>();
        if (null != maps && maps.length > 0) {
            Stream.of(maps).forEach(m -> valueList.addAll(m.values()));
        }
        return valueList;
    }

    /**
     * 设置全局名称转换字符，请注意，该转换会对所有NeoMap生效
     *
     * @param namingChg 转换类型
     */
    public static void setDefaultNamingChg(NamingChg namingChg) {
        globalNaming = namingChg;
    }

    /**
     * 对象转换为NeoMap
     *
     * @param object 待转换对象
     * @return 转换之后的NeoMap
     */
    public static NeoMap from(Object object) {
        return from(object, NamingChg.DEFAULT, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * 根据指定的一些列转换为NeoMap对象，该函数同函数 fromInclude，只是为了方便命名的统一
     *
     * @param object 待转换对象
     * @param columns 对象的属性名列表
     * @return 转换之后的NeoMap
     */
    public static NeoMap from(Object object, Columns columns) {
        return from(object, NamingChg.DEFAULT, new ArrayList<>(columns.getFieldSets()), new ArrayList<>());
    }

    /**
     * 对象转换为NeoMap
     *
     * @param object 待转换对象
     * @param columns 对象的属性名列表
     * @param namingChg 对象属性和NeoMap的映射关系
     * @return 转换之后的NeoMap
     */
    public static NeoMap from(Object object, Columns columns, NamingChg namingChg) {
        return from(object, namingChg,
            new ArrayList<>(null == columns ? Collections.emptyList() : columns.getFieldSets()), new ArrayList<>());
    }

    /**
     * 对象转换为NeoMap
     *
     * @param object 待转换对象
     * @param namingChg 对象属性和NeoMap的映射关系
     * @return 转换之后的NeoMap
     */
    public static NeoMap from(Object object, NamingChg namingChg) {
        return from(object, namingChg, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * 对象转换为NeoMap
     *
     * @param object 待转换对象
     * @param columns 对象的属性名列表
     * @param userDefineNaming 用户自定义的转换，结构为{@code Map<String, String>}
     * @return 转换之后的NeoMap
     */
    public static NeoMap from(Object object, Columns columns, NeoMap userDefineNaming) {
        return from(object, userDefineNaming, new ArrayList<>(columns.getFieldSets()), new ArrayList<>());
    }

    /**
     * 对象转换为NeoMap
     *
     * @param object 待转换对象
     * @param userDefineNaming 用户自定义的转换，结构为{@code Map<String, String>}，key为实体的属性名，value为DB中的列名
     * @return 转换之后的NeoMap
     */
    public static NeoMap from(Object object, NeoMap userDefineNaming) {
        return from(object, userDefineNaming, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * 指定包括的属性进行对象转换为NeoMap
     *
     * @param object 待转换对象
     * @param fields 需要的属性
     * @return 转换之后的NeoMap
     */
    public static NeoMap fromInclude(Object object, String... fields) {
        return from(object, NamingChg.DEFAULT, Arrays.asList(fields), new ArrayList<>());
    }

    /**
     * 指定排除的属性进行对象转换为NeoMap
     *
     * @param object 待转换对象
     * @param fields 排除的属性
     * @return 转换之后的NeoMap
     */
    public static NeoMap fromExclude(Object object, String... fields) {
        return from(object, NamingChg.DEFAULT, new ArrayList<>(), Arrays.asList(fields));
    }

    /**
     * 通过用户自定义的方式，将对象转换为NeoMap
     *
     * @param object 待转换的对象
     * @param userNaming 用户自定义命名转换方式
     * @param inFieldList 包括的属性
     * @param exFieldList 排除的属性
     * @return 转换之后的NeoMap
     */
    public static NeoMap from(Object object, NeoMap userNaming, List<String> inFieldList, List<String> exFieldList) {
        NeoMap neoMap = NeoMap.of();
        if (null == object) {
            return neoMap;
        }
        neoMap.setUserDefineNaming(userNaming);
        return innerFrom(neoMap, object, inFieldList, exFieldList);
    }

    /**
     * 对象转换为NeoMap
     *
     * @param object 待转换的对象
     * @param naming 转换方式
     * @param inFieldList 包括的属性
     * @param exFieldList 排除的属性
     * @return 转换之后的NeoMap
     */
    @SuppressWarnings("unchecked")
    public static NeoMap from(Object object, NamingChg naming, List<String> inFieldList, List<String> exFieldList) {
        NeoMap neoMap = NeoMap.of();
        if (null == object) {
            return neoMap;
        }

        String valueResult = valueCheck(object);
        if (null != valueResult) {
            throw new NeoMapChgException("object 不是普通对象，转换失败：" + valueResult);
        }

        if (Map.class.isAssignableFrom(object.getClass())) {
            return neoMap.append(Map.class.cast(object));
        }
        neoMap.setLocalNaming(naming);
        return innerFrom(neoMap, object, inFieldList, exFieldList);
    }

    public static NeoMap fromMap(NeoMap sourceMap, NamingChg namingChg) {
        NeoMap targetMap = NeoMap.of();
        sourceMap.stream().forEach(c -> {
            targetMap.putIfAbsent(namingChg.smallCamelToOther(c.getKey()), c.getValue());
        });
        return targetMap;
    }

    public static NeoMap table(String tableName){
        return NeoMap.of().setKeyPreTableName(tableName);
    }

    /**
     * 判断对象是否是普通对象，若是枚举、数组、集合和Map则返回具体的文案，否则返回null
     *
     * @param value 待检查的值
     * @return null：普通对象；文本：非普通对象
     */
    @SuppressWarnings("unchecked")
    private static String valueCheck(Object value){
        Class vClass = value.getClass();
        if(vClass.isEnum()){
            return "对象是枚举类型";
        }

        if(vClass.isArray()){
            return "对象是数组类型";
        }

        if (Collection.class.isAssignableFrom(vClass)) {
            return "对象是集合类型";
        }

        return null;
    }

    private static NeoMap innerFrom(NeoMap neoMap, Object object, List<String> inFieldList, List<String> exFieldList) {
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields.length != 0) {
            Stream.of(fields).filter(f -> {
                if (!inFieldList.isEmpty()) {
                    return inFieldList.contains(f.getName());
                }
                if (!exFieldList.isEmpty()) {
                    return !exFieldList.contains(f.getName());
                }
                return true;
            }).forEach(f -> {
                f.setAccessible(true);
                try {
                    Object value = f.getType().cast(f.get(object));
                    if (null != value) {
                        neoMap.putIfAbsent(neoMap.namingChg(f.getName()), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return neoMap;
    }

    public static <T> List<T> asArray(List<NeoMap> neoMaps, Class<T> tClass) {
        if (null == neoMaps || neoMaps.isEmpty()) {
            return new ArrayList<>();
        }

        return neoMaps.stream().map(m -> m.as(tClass)).collect(Collectors.toList());
    }

    public static <T> List<T> asArray(List<NeoMap> neoMaps, Class<T> tClass, NamingChg namingChg) {
        if (null == neoMaps || neoMaps.isEmpty()) {
            return new ArrayList<>();
        }

        return neoMaps.stream().map(m -> m.as(tClass, namingChg)).collect(Collectors.toList());
    }

    public static <T> List<NeoMap> fromArray(List<T> dataList, Columns columns, NamingChg namingChg) {
        if (null == dataList || dataList.isEmpty()) {
            return new ArrayList<>();
        }
        return dataList.stream().map(m -> NeoMap.from(m, columns, namingChg)).collect(Collectors.toList());
    }

    public static <T> List<NeoMap> fromArray(List<T> dataList, Columns columns) {
        return fromArray(dataList, columns, null);
    }

    public static <T> List<NeoMap> fromArray(List<T> dataList, NamingChg namingChg) {
        if (null == dataList || dataList.isEmpty()) {
            return new ArrayList<>();
        }
        return dataList.stream().map(m -> NeoMap.from(m, namingChg)).collect(Collectors.toList());
    }

    public static <T> List<NeoMap> fromArray(List<T> dataList) {
        return fromArray(dataList, null, null);
    }

    /**
     * 将数据库字符转换为对应的属性变量规范字符
     *
     * @param source 源字符串
     * @param namingChg 转换规则
     * @return 转换之后的字符
     */
    public static String dbToJavaStr(String source, NamingChg namingChg) {
        return namingChg.otherToSmallCamel(source);
    }

    /**
     * 默认全局转换
     *
     * @param source 源字符
     * @return 转换后的字符
     */
    public static String dbToJavaStr(String source) {
        return globalNaming.smallCamelToOther(source);
    }

    public static boolean isEmpty(NeoMap neoMap) {
        return neoMap == null || neoMap.isEmpty();
    }

    public static boolean isEmpty(Collection<NeoMap> neoMaps) {
        return neoMaps == null || neoMaps.isEmpty() || neoMaps.stream().allMatch(Map::isEmpty);
    }

    /**
     * 拼接表的多个列名columns和对应的值
     *
     * @param kvs key-value-key-value-...
     * @return 拼接字段之后的数据
     */
    public NeoMap cs(Object... kvs) {
        if (kvs.length % 2 != 0) {
            throw new NumberOfValueException("参数请使用：key,value,key,value...这种参数格式");
        }

        String orderByStr = "order by";
        for (int i = 0; i < kvs.length; i += 2) {
            if (null == kvs[i]) {
                throw new ParameterNullException("NeoMap.of()中的参数不可为null");
            }
            String key = (String) kvs[i];
            Object valueObj = kvs[i + 1];
            if (key.trim().equals(orderByStr) && valueObj instanceof String) {
                String filterValue = toOrderByValueStr(String.class.cast(valueObj));
                // 不包含order by对应的value，则设置转换的
                if (!containsKey(key)) {
                    put(key, filterValue);
                } else {
                    // 已经包含过，则合并新的
                    String orderByValue = String.class.cast(get(key));
                    put(key, orderByValue + ", " + filterValue);
                }
            } else {
                put(toColumnStr((String) kvs[i]), kvs[i + 1]);
            }
        }
        return this;
    }

    public NeoMap and(String tableName){
        return setKeyPreTableName(tableName);
    }

    /**
     * 将字段转变为sql中的字段名，即添加前后的引号``
     * @param key 表的列名
     * @return sql中对应的表的字段名
     */
    private String toColumnStr(String key){
        if (null != keyPreTableName && !"".equals(keyPreTableName)) {
            return keyPreTableName + "." + key + "";
        }
        return "`" + key + "`";
    }

    /**
     * 对order by后面的字段添加表前缀，比如：{@code name desc --> table1.name desc}
     * {@code name desc, group asc --> table.name desc, table.group asc}
     *
     * @param value order by 后面的字段
     * @return 添加表前缀
     */
    private String toOrderByValueStr(String value){
        String comma = ",";
        String dom = ".";
        if(value.contains(comma)){
            return String.join(comma + " ", Stream.of(value.split(comma)).map(v->{
                v = v.trim();
                if (!v.startsWith(keyPreTableName) && !v.contains(dom)) {
                    return keyPreTableName + "." + v;
                }
                return v;
            }).collect(Collectors.toList()));
        }
        if (!value.startsWith(keyPreTableName) && !value.contains(dom)) {
            return keyPreTableName + "." + value;
        }
        return value;
    }

    /**
     * key的命名风格从其他转到小驼峰
     *
     * @param namingChg 转换类型
     * @return 转换后的类型
     */
    public NeoMap keyChgToSmallCamelFrom(NamingChg namingChg){
        NeoMap neoMap = NeoMap.of();
        stream().forEach(e -> neoMap.put(namingChg.otherToSmallCamel(e.getKey()), e.getValue()));
        return neoMap;
    }

    /**
     * key的命名风格从小驼峰转到其他
     *
     * @param namingChg 转换类型
     * @return 转换后的类型
     */
    public NeoMap keyChgFromSmallCamelTo(NamingChg namingChg){
        NeoMap neoMap = NeoMap.of();
        stream().forEach(e -> neoMap.put(namingChg.smallCamelToOther(e.getKey()), e.getValue()));
        return neoMap;
    }

    /**
     * 给所有的key设置前缀
     *
     * @param preFix 前缀
     * @return 所有的key替换之后的NeoMap
     */
    public NeoMap setKeyPre(String preFix) {
        NeoMap neoMap = NeoMap.of();
        stream().forEach(e -> neoMap.put(preFix + e.getKey(), e.getValue()));
        return neoMap;
    }

    /**
     * NeoMap 转化为实体数据，其中key就是对应的属性
     *
     * @param tClass 目标类的Class
     * @param <T> 目标类的类型
     * @return 目标类的实体对象
     */
    public <T> T as(Class<T> tClass) {
        return as(tClass, localNaming);
    }

    /**
     * NeoMap 转化为实体数据，其中key就是对应的属性
     *
     * @param tClass 目标类的Class
     * @param naming 属性名的转换
     * @param <T> 目标类的类型
     * @return 目标类的实体对象
     */
    @SuppressWarnings("unchecked")
    public <T> T as(Class<T> tClass, NamingChg naming) {
        if (dataMap.isEmpty()) {
            return null;
        }

        if (NeoMap.class.isAssignableFrom(tClass)) {
            return (T) this;
        }
        T t = null;
        try {
            t = tClass.newInstance();
            Field[] fields = tClass.getDeclaredFields();
            if (fields.length != 0) {
                T finalT = t;
                setLocalNaming(naming);
                Stream.of(fields).forEach(f -> {
                    f.setAccessible(true);
                    try {
                        f.set(finalT, dataMap.get(namingChg(f.getName())));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 只要固定的几个列值
     *
     * @param columns 具体的列
     * @return 新的map结构
     */
    public NeoMap assign(Columns columns) {
        Set<String> fields = columns.getFieldSets();
        if (null == fields || fields.isEmpty()) {
            return this;
        }
        NeoMap neoMap = NeoMap.of();
        fields.forEach(f -> {
            if (containsKey(f)) {
                neoMap.put(f, get(f));
            }
        });
        return neoMap;
    }

    /**
     * 对NeoMap中的key进行转换
     *
     * @param keys 新旧key的映射，key-value-key-value...的形式，比如：a, a1, b, b1
     * @return key转换之后的NeoMap，比如：{@code a=ok, b=name} 到 {@code a1=ok, b1=name}
     */
    @SuppressWarnings("confusing")
    public NeoMap keyConvert(String... keys) {
        NeoMap keyChgMap = NeoMap.of(Arrays.asList(keys).toArray());
        NeoMap neoMap = NeoMap.fromMap(this, NamingChg.DEFAULT);
        keyChgMap.stream().forEach(e -> {
            String oldKey = e.getKey();
            String newKey = (String) e.getValue();
            if (neoMap.containsKey(oldKey)) {
                neoMap.put(newKey, neoMap.get(oldKey));
                neoMap.remove(oldKey);
            }
        });
        return neoMap;
    }

    public NeoMap append(Map<String, ?> neoMap) {
        this.putAll(neoMap);
        return this;
    }

    public NeoMap append(NeoMap neoMap) {
        this.putAll(neoMap.getDataMap());
        return this;
    }

    public NeoMap append(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public Stream<Entry<String, Object>> stream() {
        return dataMap.entrySet().stream();
    }

    public Stream<String> keyStream() {
        return dataMap.keySet().stream();
    }

    public Stream<Object> valueStream() {
        return dataMap.values().stream();
    }

    public NeoMap putAll(NeoMap sourceMap, NamingChg namingChg) {
        this.putAll(NeoMap.fromMap(sourceMap, namingChg));
        return this;
    }

    /**
     * 根据传入的类进行对应类型的获取
     *
     * @param tClass 目标值的class
     * @param key map的key
     * @param <T> 目标值的类型
     * @return 目标值
     */
    public <T> T get(Class<T> tClass, String key){
        return ObjectUtil.cast(tClass, get(key));
    }

    /**
     * 根据传入的类进行对应类型的获取，若没有获取到，则返回默认值
     *
     * @param tClass 目标的class
     * @param key map的key
     * @param defaultValue 默认值
     * @param <T> 目标的类型
     * @return 目标值
     */
    public <T> T get(Class<T> tClass, String key, T defaultValue){
        T result = get(tClass, key);
        return (null == result) ? defaultValue : result;
    }

    /**
     * 获取字符
     *
     * @param key map的key
     * @return 字符，如果是字符串，则返回字符串的第一个字符
     */
    public Character getCharacter(String key){
        return ObjectUtil.toChar(get(key));
    }

    public Character getCharacter(String key, Character defaultValue) {
        Character result = getCharacter(key);
        return (null == result) ? defaultValue : result;
    }

    /**
     * 返回值为String类型的值
     *
     * @param key map的key
     * @return String类型的值
     */
    public String getStr(String key){
        return ObjectUtil.toStr(get(key));
    }

    public String getStr(String key, String defaultValue){
        String result = getStr(key);
        return (null == result) ? defaultValue : result;
    }

    /**
     * 获取值类型为Boolean的值
     * @param key map中对应的key
     * @return 若值为true或者TRUE，则返回true，否则其他任何值都返回false，包括false和null
     */
    public Boolean getBoolean(String key) {
        return ObjectUtil.toBoolean(get(key));
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        Boolean result = getBoolean(key);
        return (null == result) ? defaultValue : result;
    }

    public Byte getByte(String key){
        return ObjectUtil.toByte(get(key));
    }

    public Byte getByte(String key, Byte defaultValue) {
        Byte result = getByte(key);
        return (null == result) ? defaultValue : result;
    }

    public Short getShort(String key){
        return ObjectUtil.toShort(get(key));
    }

    public Short getShort(String key, Short defaultValue) {
        Short result = getShort(key);
        return (null == result) ? defaultValue : result;
    }

    public Integer getInteger(String key){
        return ObjectUtil.toInt(get(key));
    }

    public Integer getInteger(String key, Integer defaultValue) {
        Integer result = getInteger(key);
        return (null == result) ? defaultValue : result;
    }

    public Long getLong(String key){
        return ObjectUtil.toLong(get(key));
    }

    public Long getLong(String key, Long defaultValue) {
        Long result = getLong(key);
        return (null == result) ? defaultValue : result;
    }

    public Double getDouble(String key){
        return ObjectUtil.toDouble(get(key));
    }

    public Double getDouble(String key, Double defaultValue) {
        Double result = getDouble(key);
        return (null == result) ? defaultValue : result;
    }

    public Float getFloat(String key){
        return ObjectUtil.toFloat(get(key));
    }

    public Float getFloat(String key, Float defaultValue) {
        Float result = getFloat(key);
        return (null == result) ? defaultValue : result;
    }

    /**
     * 获取value并转换为指定的类型的list
     *
     * @param tClass 目标对象的类，对于有些类型不是这个类的，只要能转换到这个类也是可以的，比如原先存的是{@code List<String>}，取的时候只要String可以转为Integer，那么这个tClass可以为Integer.class
     * @param key 目标值对应NeoMap中的key
     * @param <T> 目标对象类型
     * @return 集合类型
     */
    public <T> List<T> getList(Class<T> tClass, String key){
        return ObjectUtil.toList(get(key)).stream().map(r -> ObjectUtil.cast(tClass, r)).collect(Collectors.toList());
    }

    public <T> Set<T> getSet(Class<T> tClass, String key){
        return ObjectUtil.toSet(get(key)).stream().map(r -> ObjectUtil.cast(tClass, r)).collect(Collectors.toSet());
    }

    /**
     * 将值获取为NeoMap，对于value为普通对象的也可以用该方法
     *
     * 注意：只有value为普通对象的时候才会获取成功，对于枚举，集合，数组这些都会转换失败
     * @param key key值
     * @return 返回转换的NeoMap
     */
    public NeoMap getNeoMap(String key){
        return ObjectUtil.toNeoMap(get(key));
    }

    /**
     * 这里根据命名订阅，如果用户自定义表中存在映射，则使用该映射，否则用设置的命名映射规则
     */
    private String namingChg(String name) {
        if (null != userDefineNaming) {
            String chgName = (String) userDefineNaming.get(name);
            if (null != chgName) {
                return chgName;
            }
        }
        return ((null != localNaming && !localNaming.equals(NamingChg.DEFAULT)) ? localNaming : globalNaming)
            .smallCamelToOther(name);
    }

    public enum NamingChg {
        /**
         * 不转换
         */
        DEFAULT(t -> t, t -> t),
        /**
         * 小驼峰到大驼峰 {@code dataBaseUser <------> DateBaseUser }
         */
        BIGCAMEL(StringConverter::bigCamel, StringConverter::bigCamelToSmallCamel),
        /**
         * 小驼峰到下划线 {@code dataBaseUser <------> data_base_user }
         */
        UNDERLINE(StringConverter::underLine, StringConverter::underLineToSmallCamel),
        /**
         * 小驼峰到前下划线 {@code dataBaseUser <------> _data_base_user }
         */
        PREUNDER(StringConverter::preUnder, StringConverter::underLineToSmallCamel),
        /**
         * 小驼峰到后下划线 {@code dataBaseUser <------> data_base_user_ }
         */
        POSTUNDER(StringConverter::postUnder, StringConverter::underLineToSmallCamel),
        /**
         * 小驼峰到前后下划线 {@code dataBaseUser <------> _data_base_user_ }
         */
        PREPOSTUNDER(StringConverter::prePostUnder, StringConverter::underLineToSmallCamel),
        /**
         * 小驼峰到中划线 {@code dataBaseUser <------> data-base-user }
         */
        MIDDLELINE(StringConverter::middleLine, StringConverter::middleLineToSmallCamel),
        /**
         * 小驼峰到大写下划线 {@code dataBaseUser <------> DATA_BASE_USER }
         */
        UPPERUNER(StringConverter::upperUnder, StringConverter::upperUnderToSmallCamel),
        /**
         * 小驼峰到大写中划线 {@code dataBaseUser <------> DATA-BASE-USER }
         */
        UPPERMIDDLE(StringConverter::upperUnderMiddle, StringConverter::upperUnderMiddleToSmallCamel);

        /**
         * 用于名字的转换
         */
        private Function<String, String> smallCamelToOther;
        private Function<String, String> otherToSmallCamel;

        NamingChg(Function<String, String> smallCamelToOther, Function<String, String> otherToSmallCamel) {
            this.smallCamelToOther = smallCamelToOther;
            this.otherToSmallCamel = otherToSmallCamel;
        }

        /**
         * 小驼峰类型到其他类型的转换
         *
         * @param data 源数据
         * @return 转换后的数据
         */
        public String smallCamelToOther(String data) {
            return smallCamelToOther.apply(data);
        }

        /**
         * 其他类型到小驼峰转换
         *
         * @param data 源数据
         * @return 转换后的数据
         */
        public String otherToSmallCamel(String data) {
            return otherToSmallCamel.apply(data);
        }
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    @Override
    public int size() {
        return dataMap.size();
    }

    @Override
    public boolean isEmpty() {
        return dataMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return dataMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return dataMap.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return dataMap.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        if (null != value) {
            dataMap.put(key, value);
        }
        return value;
    }

    @Override
    public Object remove(Object key) {
        return dataMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        dataMap.putAll(m);
    }

    @Override
    public void clear() {
        dataMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return dataMap.keySet();
    }

    @Override
    public Collection<Object> values() {
        return dataMap.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return dataMap.entrySet();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof NeoMap) {
            return dataMap.equals(NeoMap.class.cast(object).getDataMap());
        }
        return false;
    }

    @Override
    public String toString() {
        return dataMap.toString();
    }
}
