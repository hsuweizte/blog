# micro blog

> 作者：徐瑋澤

**使用的技術：**

- 後端：Spring Boot + JPA + thymeleaf
- 資料庫：MySQL
- 前端 UI：Semantic UI 框架

**工具與環境：**

- IDEA
- Maven 3
- JDK 20

**內容模組：**

- 需求分析與功能規劃
- 頁面設計與開發
- 技術框架搭建
- 後端管理功能實現
- 前端管理功能實現

## 1、需求與功能

### 1.1 用戶角度

用戶角度是敏捷框架中的一種開發方法。可以説明開發者轉換視角，以用戶的角度更好的把握需求，從而實現具有商業價值的功能。

> 使用者角度最好是用戶團隊編寫

**用戶故事範本**：

- As a (role of user), I want (some feature) so that (some business value).
- 作為一個(角色) 使用者，我可以做(某個功能) 事情，如此可以有(某個商業價值) 的好處

**關鍵點**：角色、功能、商業價值

**舉例**：

- 作為一個招聘網站**註冊用戶**，我想**查看最近 3 天發佈的招聘資訊**，以便於**瞭解最新的招聘資訊**。
- 作為公司，可以張貼新工作。

個人 blog 的用戶角度：

角色：**普通訪客**，**管理員（我）**
- 訪客，可以分頁查看所有的日誌
- 訪客，可以快速查看日誌數最多的 6 個分類
- 訪客，可以查看所有的分類
- 訪客，可以查看某個分類下的日誌列表
- 訪客，可以快速查看標記日誌最多的 10 個標籤
- 訪客，可以查看所有的標籤
- 訪客，可以查看某個標籤下的日誌列表
- 訪客，可以根據年度時間線查看日誌列表
- 訪客，可以快速查看最新的推薦日誌
- 訪客，可以用關鍵字全域搜索日誌
- 訪客，可以查看單個日誌內容
- 訪客，可以對日誌內容進行評論
- 訪客，可以讚賞日誌內容
- 訪客，可以使用手機 QR Code 閱讀日誌內容
- 訪客，可以在首頁掃描 QR CODE 關注我
- 我，可以帳號和密碼登錄後臺管理
- 我，可以管理日誌
  - 我，可以發佈新日誌
  - 我，可以對日誌進行分類
  - 我，可以對日誌打標籤
  - 我，可以修改日誌
  - 我，可以刪除日誌
  - 我，可以根據標題，分類，標籤查詢日誌
- 我，可以管理日誌分類
  - 我，可以新增一個分類
  - 我，可以修改一個分類
  - 我，可以刪除一個分類
  - 我，可以根據分類名稱查詢分類
- 我，可以管理標籤
  - 我，可以新增一個標籤
  - 我，可以修改一個標籤
  - 我，可以刪除一個標籤
  - 我，可以根據名稱查詢標籤

### 1.2 功能規劃

![](https://ws2.sinaimg.cn/large/006tKfTcgy1fk7m27hbn4j31ds0ycdnp.jpg)

## 2、頁面設計與開發

### 2.1 設計

**頁面規劃：**

前端展示：首頁、詳情頁、分類、標籤、歸檔、關於我

後臺管理：範本頁

### 2.2 頁面開發

[Semantic UI 官網](https://semantic-ui.com/)

[Semantic UI 中文官網](http://www.semantic-ui.cn/)

[背景圖片資源](https://www.toptal.com/designers/subtlepatterns/)

### 2.3 外掛程式集成

[編輯器 Markdown](https://pandao.github.io/editor.md/)

[內容排版 typo.css](https://github.com/sofish/typo.css)

[動畫 animate.css](https://daneden.github.io/animate.css/)

[代碼亮顯 prism](https://github.com/PrismJS/prism)

[目錄生成 Tocbot](https://tscanlin.github.io/tocbot/)

[滾動偵測 waypoints](http://imakewebthings.com/waypoints/)

[平滑滾動 jquery.scrollTo](https://github.com/flesler/jquery.scrollTo)

[QR CODE 生成 qrcode.js](https://davidshimjs.github.io/qrcodejs/)

## 3、IDE

> [IDEA 下載 https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)

### 3.1 構建與配置

**1、引入 Spring Boot 模組：**

- web
- Thymeleaf
- JPA
- MySQL
- Aspects
- DevTools

**2、application.yml 配置**

- 使用 thymeleaf 3

  pom.xml:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

-application.yml:
```yaml
spring:
  thymeleaf:
    mode: HTML
```

- 資料庫連接配置

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```
- 資料庫匯入

```yaml
source %path%/blog.sql
```

- logback 配置
- application.yml:

```yaml
logging:
  level:
    root: info
    com.imcoding: debug
  file: log/imcoding.log
```

logback-spring.xml：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration>
    <!--包含Spring boot對logback日誌的預設配置-->
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <property name="LOG_FILE" value="${LOG_FILE:-${LOG_PATH:-${LOG_TEMP:-${java.io.tmpdir:-/tmp}}}/spring.log}"/>
    <include resource="org/springframework/boot/logging/logback/console-appender.xml" />

    <!--重寫了Spring Boot框架 org/springframework/boot/logging/logback/file-appender.xml 配置-->
    <appender name="TIME_FILE"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
        <file>${LOG_FILE}</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_FILE}.%d{yyyy-MM-dd}.%i</fileNamePattern>
            <!--保留歷史日誌一個月的時間-->
            <maxHistory>30</maxHistory>
            <!--
            Spring Boot預設情況下，日誌檔10M時，會切分日誌檔,這樣設置日誌檔會在100M時切分日誌
            -->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>

        </rollingPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
        <appender-ref ref="TIME_FILE" />
    </root>

</configuration>
<!--
    1、繼承Spring boot logback設置（可以在appliaction.yml或者application.properties設置logging.*屬性）
    2、重寫了預設配置，設置日誌檔大小在100MB時，按日期切分日誌，切分後目錄：
-->
```

- 生產環境與開發環境配置
  - application-dev.yml
  - application-pro.yml

### 3.2 異常處理

**1、定義錯誤頁面**

- 404
- 500
- error

**2、全域處理異常**

統一處理異常：

```java
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    /**
     * 異常處理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(HttpServletRequest request, Exception e) throws Exception {

        logger.error("Request URL : {} , Exception : {}", request.getRequestURL(), e);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", e);
        mav.setViewName("error/error");

        return mav;
    }
}
```

錯誤頁面異常資訊顯示處理：

```html
<div>
  <div th:utext="'&lt;!--'" th:remove="tag"></div>
  <div th:utext="'Failed Request URL : ' + ${url}" th:remove="tag"></div>
  <div
    th:utext="'Exception message : ' + ${exception.message}"
    th:remove="tag"
  ></div>
  <ul th:remove="tag">
    <li th:each="st : ${exception.stackTrace}" th:remove="tag">
      <span th:utext="${st}" th:remove="tag"></span>
    </li>
  </ul>
  <div th:utext="'--&gt;'" th:remove="tag"></div>
</div>
```

**3、資源找不到異常**

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExcepiton extends RuntimeException {

    public NotFoundExcepiton() {
    }

    public NotFoundExcepiton(String message) {
        super(message);
    }

    public NotFoundExcepiton(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### 3.3 日誌處理

**1、記錄日誌內容**

- 請求 url
- 訪問者 ip
- 調用方法 classMethod
- 參數 args
- 返回內容

**2、記錄日誌類：**

### 3.4 頁面處理

**1、靜態頁面導入 project**

**2、thymeleaf 佈局**

- 定義 fragment
- 使用 fragment 佈局

**3、錯誤頁面美化**

4、設計與規範

### 4.1 實體設計

**實體類：**

- 日誌 Blog
- 日誌分類 Type
- 日誌標籤 Tag
- 日誌評論 Comment
- 用戶 User

**實體關係：**

**評論類自關聯關係：**

**Blog 類：**

**Type 類：**

**Tag 類：**

**Comment 類：**

**User 類：**

### 4.2 應用分層

### 4.3 命名約定

**Service/DAO 層命名約定：**

- 獲取單個物件的方法用 get 做首碼。
- 獲取多個物件的方法用 list 做首碼。
- 獲取統計值的方法用 count 做首碼。
- 插入的方法用 save(推薦)或 insert 做首碼。
- 刪除的方法用 remove(推薦)或 delete 做首碼。
- 修改的方法用 update 做首碼。

## 5、後臺管理功能實現

### 5.1 登錄

**1、構建登錄頁面和後臺管理首頁**

**2、UserService 和 UserRepository**

**3、LoginController 實現登錄**

**4、MD5 加密**

**5、登錄攔截器**

### 5.2 分類管理

**1、分類管理頁面**

**2、分類列表分頁**

**3、分類新增、修改、刪除**

### 5.3 標籤管理

### 5.4 日誌管理

**1、日誌分頁查詢**

**2、日誌新增**

**3、日誌修改**

**4、日誌刪除**

## 6、前端展示功能實現

### 6.1 首頁展示

**1、日誌列表**

**2、top 分類**

**3、top 標籤**

**4、最新日誌推薦**

**5、日誌詳情**

**1、Markdown 轉換 HTML**

- [commonmark-java https://github.com/atlassian/commonmark-java](https://github.com/atlassian/commonmark-java)
- pom.xml 引用 commonmark 和擴展外掛程式

```xml
<dependency>
    <groupId>org.commonmark</groupId>
    <artifactId>commonmark</artifactId>
    <version>0.21.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.commonmark/commonmark-ext-heading-anchor -->
<dependency>
    <groupId>org.commonmark</groupId>
    <artifactId>commonmark-ext-heading-anchor</artifactId>
    <version>0.21.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.commonmark/commonmark-ext-gfm-tables -->
<dependency>
    <groupId>org.commonmark</groupId>
    <artifactId>commonmark-ext-gfm-tables</artifactId>
    <version>0.18.0</version>
</dependency>
```

**2、評論功能**

- 評論資訊提交與回復功能
- 評論資訊清單展示功能
- 管理員回復評論功能

### 6.2 分類頁

### 6.3 標籤頁

### 6.4 歸檔頁

### 6.5 關於我
