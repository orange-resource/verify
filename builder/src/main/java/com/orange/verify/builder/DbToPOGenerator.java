package com.orange.verify.builder;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.orange.verify.builder.config.MySQLCommentGenerator;
import com.orange.verify.builder.config.TableNameList;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * 生成Po类
 */
public class DbToPOGenerator {

    public static void main( String[] args ) throws Exception {
//        System.out.println(System.getProperty("user.dir"));
        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        String path = resource.getPath();
        path = StrUtil.subBefore(path, "/target", true);
        System.out.println(path);
        String resourcesPath = path + "/src/main/resources/genconfig";
        System.out.println(resourcesPath);

        File[] files = FileUtil.ls(resourcesPath);
        Map<Integer, String> map = new LinkedHashMap<>();
        int i = 0;
        System.out.println("请输入要生成的配置xml对应序号");
        for (File file : files) {
            i+=1;
            map.put(i, file.getName());
            System.out.println("序号: " + i + " 配置名: " + file.getName());
        }
        String generator = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("请输入: ");
            int index = scanner.nextInt();
            generator = map.get(index);
            if (StrUtil.isBlank(generator)) {
                System.out.println("输入序号错误, 请重新输入对应序号...");
            } else {
                break;
            }
        }
        scanner.close();

        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("genconfig/" + generator);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        generatePOTableName();
    }

    private static void generatePOTableName() throws IOException {
        String className = "BaseTableName";
        String fileName = System.getProperty("user.dir") + "/" +
                          MySQLCommentGenerator.targetProject + "/" +
                          MySQLCommentGenerator.targetPackage.replace(".", "/") + "/" + className +".java";
        System.out.println(fileName);
        StringBuilder builder = new StringBuilder();
        builder.append("package " + MySQLCommentGenerator.targetPackage + ";");
        builder.append("\n");
        builder.append("\n");
        builder.append("/**" + "\n");
        builder.append(" * 自动生成" + "\n");
        builder.append(" */" + "\n");
        builder.append("public interface " + className + " {");
        builder.append("\n");
        builder.append("\n");
        for (String tableName : TableNameList.getList()) {
            builder.append("    String " + tableName + " = " + "\"" + tableName + "\"" + ";");
            builder.append("\n");
            builder.append("    String " + tableName + "_space = " + "\" " + tableName + " \"" + ";");
            builder.append("\n");
            builder.append("\n");
        }
        builder.append("}");

        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        try {
            fileWriter.write(builder.toString());
            fileWriter.flush();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
