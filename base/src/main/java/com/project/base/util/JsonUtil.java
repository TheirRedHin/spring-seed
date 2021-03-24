package com.project.base.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.project.base.exception.BaseException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * JSON工具类
 */
public class JsonUtil {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    // 忽略未知属性
    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //设置JSON时间格式
    SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormat));
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormat));
    javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormat));

    javaTimeModule
        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormat));
    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormat));
    javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormat));
    MAPPER.registerModule(javaTimeModule);
    MAPPER.setDateFormat(myDateFormat);
  }

  /**
   * 获取ObjectMapper
   *
   * @return ObjectMapper
   */
  public static ObjectMapper getMapper() {
    return MAPPER;
  }

  /**
   * 字符串转对象
   *
   * @param json json格式字符串
   * @param cls  对象类型
   * @return 对象
   * @throws IOException
   * @throws JsonMappingException
   * @throws JsonParseException
   */
  public static <C> C toBean(String json, Class<C> cls)
      throws JsonParseException, JsonMappingException, IOException {
    return MAPPER.readValue(json, cls);
  }


  /**
   * 字符串转JsonNode
   *
   * @param json 字符串
   * @return JsonNode
   * @throws IOException
   */
  public static JsonNode toJsonNode(String json) throws IOException {
    return MAPPER.readTree(json);
  }

  /**
   * 字符串转对象列表
   *
   * @param json    json格式字符串
   * @param typeRef 对象类型引用
   * @return 对象列表
   * @throws JsonParseException
   * @throws JsonMappingException
   * @throws IOException
   */
  public static <C> C toBean(String json, TypeReference<C> typeRef)
      throws JsonParseException, JsonMappingException, IOException {
    C list = MAPPER.readValue(json, typeRef);
    return list;
  }

  /**
   * 对象转字符串
   *
   * @param obj 对象
   * @return json格式字符串
   * @throws IOException
   */
  public static String toJson(Object obj) throws IOException {
    return MAPPER.writeValueAsString(obj);
  }

  /**
   * 对象转为字符串
   *
   * @param obj
   * @return
   */
  public static String toJsonString(Object obj) {
    if (obj != null && obj instanceof String) {
      return obj.toString();
    }
    try {
      return MAPPER.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new BaseException("1", e.getMessage());
    }
  }

  /**
   * 字符串转Map
   *
   * @param json json格式字符串
   * @return Map
   * @throws IOException
   */


  @SuppressWarnings("unchecked")
  public static <T> Map<String, T> toMap(String json) throws IOException {
    Map<String, T> map = (Map<String, T>) MAPPER.readValue(json, Map.class);
    return map;
  }

  /**
   * 根据键获取值。
   *
   * @param obj
   * @param key
   * @param defaultValue
   * @return String
   * @throws IOException
   */
  public static String getString(JsonNode obj, String key, String defaultValue) {
    if (!isContainsKey(obj, key)) {
      return defaultValue;
    }
    try {
      if (obj.get(key).isObject() || obj.get(key).isArray()) {
        return toJson(obj.get(key));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    JsonNode jsonNode = obj.get(key);
    if (jsonNode.isNull()) {
      return defaultValue;
    }
    return jsonNode.asText();
  }

  /**
   * 根据键获取值。
   *
   * @param obj
   * @param key
   * @return String
   * @throws IOException
   */
  public static String getString(JsonNode obj, String key) {
    return getString(obj, key, "");
  }

  /**
   * 根据键获取int值。
   *
   * @param obj
   * @param key
   * @return int
   */
  public static int getInt(ObjectNode obj, String key) {
    if (!isContainsKey(obj, key)) {
      return 0;
    }
    return obj.get(key).asInt();
  }

  /**
   * 根据键获取int值。
   *
   * @param obj
   * @param key
   * @param defaultValue
   * @return int
   */
  public static int getInt(ObjectNode obj, String key, int defaultValue) {
    if (!isContainsKey(obj, key)) {
      return defaultValue;
    }
    return obj.get(key).asInt();
  }

  public static boolean getBoolean(ObjectNode obj, String key) {
    if (!isContainsKey(obj, key)) {
      return false;
    }
    return obj.get(key).asBoolean();
  }

  /**
   * 根据键获取boolean值。
   *
   * @param obj
   * @param key
   * @param defaultValue
   * @return boolean
   */
  public static boolean getBoolean(ObjectNode obj, String key, boolean defaultValue) {

    if (!isContainsKey(obj, key)) {
      return defaultValue;
    }
    return obj.get(key).asBoolean();
  }


  /**
   * json对象中是否包含某属性
   *
   * @param obj
   * @param key
   * @return
   */
  public static boolean isContainsKey(JsonNode obj, String key) {
    if (obj != null && key != null) {
      for (Iterator<String> iterator = obj.fieldNames(); iterator.hasNext(); ) {
        String name = (String) iterator.next();
        if (key.equals(name)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 替换掉包含富文本的json 字符串中特殊的字符
   *
   * @param str
   * @return
   */
  public static String escapeSpecialChar(String str) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < str.length(); i++) {

      char c = str.charAt(i);
      switch (c) {
        case '\"':
          sb.append("\\\"");
          break;
        case '\\':
          sb.append("\\\\");
          break;
        case '/':
          sb.append("\\/");
          break;
        case '\b':
          sb.append("\\b");
          break;
        case '\f':
          sb.append("\\f");
          break;
        case '\n':
          sb.append("\\n");
          break;
        case '\r':
          sb.append("\\r");
          break;
        case '\t':
          sb.append("\\t");
          break;
        default:
          sb.append(c);
      }
    }

    return sb.toString();
  }

  /**
   * 删除的空项，主要controller请求返回的时候 如果数据有{a:null}换转换失败
   *
   * @param jsonObject void
   * @throws
   * @since 1.0.0
   */
  public static void removeNull(ObjectNode jsonObject) {
    Iterator<Entry<String, JsonNode>> newSet = jsonObject.fields();
    while (newSet.hasNext()) {
      Entry<String, JsonNode> ent = newSet.next();
      JsonNode val = jsonObject.get(ent.getKey());
      if (val instanceof NullNode) {
        (jsonObject).put(ent.getKey(), "");
      }
    }
  }

  /**
   * 删除的空项，主要controller请求返回的时候 如果数据有{a:null}换转换失败
   *
   * @param jsonArray void
   * @throws
   * @since 1.0.0
   */
  public static void removeNull(ArrayNode jsonArray) {
    for (int i = 0; i < jsonArray.size(); i++) {
      removeNull((ObjectNode) jsonArray.get(i));
    }
  }


  /**
   * <pre>
   * 把jsonObject 转到jsonArray,通常用于以下这种情况
   * 为了保证jsonArray里的某个值是唯一的所以先用jsonObject来保存着
   * eg:
   * {a:{id:1,name:a},b:{id:2,name:b}}
   * 转成：[{id:1,name:a},{id:2,name:b}]
   * </pre>
   *
   * @param jsonObject
   * @return JSONArray
   * @throws
   * @since 1.0.0
   */
  public static ArrayNode objectToArray(JsonNode jsonObject) {
    ArrayNode jsonArray = getMapper().createArrayNode();
    Iterator<Entry<String, JsonNode>> newSet = jsonObject.fields();
    while (newSet.hasNext()) {
      Entry<String, JsonNode> ent = newSet.next();
      jsonArray.add(jsonObject.get(ent.getKey()));
    }
    return jsonArray;
  }

  /**
   * 判断JSON是否为空
   *
   * @param obj
   * @return
   */
  public static boolean isEmpty(Object obj) {
    if (obj == null) {
      return true;
    }
    if (obj instanceof ObjectNode) {
      return ((ObjectNode) obj).isObject();
    }
    if (obj instanceof ArrayNode) {
      return ((ArrayNode) obj).isArray();
    }
    return NullNode.getInstance().equals(obj);
  }


  public static ObjectNode createObjectNode() {
    return MAPPER.createObjectNode();
  }

  public static ArrayNode createArrayNode() {
    return MAPPER.createArrayNode();
  }


}
