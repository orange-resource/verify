package com.orange.verify.builder.config;

import cn.hutool.core.util.StrUtil;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

/**
 * 生成编辑类
 */
public class MySQLCommentGenerator extends EmptyCommentGenerator {

    private static final Logger log = LoggerFactory.getLogger(MySQLCommentGenerator.class);

    private Properties properties;

    public static String targetProject;
    public static String targetPackage;

    public MySQLCommentGenerator() {
        properties = new Properties();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        // 获取自定义的 properties
        this.properties.putAll(properties);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = introspectedTable.getContext().getJavaModelGeneratorConfiguration();
        targetProject = javaModelGeneratorConfiguration.getTargetProject();
        targetPackage = javaModelGeneratorConfiguration.getTargetPackage();

        String author = properties.getProperty("author", "Orange");
        String email = properties.getProperty("email", "x946xzgbv@mozmail.com");
        String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();

        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(new FullyQualifiedJavaType("long"));
        field.setStatic(true);
        field.setFinal(true);
        field.setName("serialVersionUID");
        field.setInitializationString("1L");
        topLevelClass.addField(field);

        FullyQualifiedJavaType iSerializable = new FullyQualifiedJavaType("Serializable");
        FullyQualifiedJavaType serializable = new FullyQualifiedJavaType("java.io.Serializable");
        FullyQualifiedJavaType mybatisPlus = new FullyQualifiedJavaType("com.baomidou.mybatisplus.annotation.*");
        topLevelClass.addSuperInterface(iSerializable);
        topLevelClass.addImportedType(serializable);
        topLevelClass.addImportedType(mybatisPlus);

        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" * " + fullyQualifiedTable.getIntrospectedTableName());
//        topLevelClass.addJavaDocLine(" * @author " + author + " <"+ email +">");
//        topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine(" */");
        topLevelClass.addJavaDocLine("@TableName(\""+ fullyQualifiedTable.getIntrospectedTableName() +"\")");
        topLevelClass.addJavaDocLine("@KeySequence(\"SEQ_TEST\")");

        TableNameList.add(fullyQualifiedTable.getIntrospectedTableName());

        log.info("数据库表：" + fullyQualifiedTable.getIntrospectedTableName() + " 生成...");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        // 获取列注释
        String remarks = introspectedColumn.getRemarks();

        List<IntrospectedColumn> primaryKeyColumnList = introspectedTable.getPrimaryKeyColumns();
        if (null != primaryKeyColumnList) {
            for (IntrospectedColumn introspectedColumn1 :primaryKeyColumnList) {
                boolean equals = introspectedColumn.getJavaProperty().equals(introspectedColumn1.getJavaProperty());
                if (!equals) {
                    break;
                }

                String idType = "ASSIGN_ID";
                if (!StrUtil.isBlank(remarks)) {
                    if (remarks.equalsIgnoreCase("uuid")) {
                        idType = "ASSIGN_UUID";
                    } else if (remarks.equalsIgnoreCase("snowflake")) {
                        idType = "ASSIGN_ID";
                    } else if (remarks.equalsIgnoreCase("none")) {
                        idType = "NONE";
                    } else if (remarks.equalsIgnoreCase("input")) {
                        idType = "INPUT";
                    }
                }
                field.addJavaDocLine("@TableId(value = \""+ introspectedColumn1.getActualColumnName() +"\", type = IdType."+ idType +")");
                return;
            }
        }

        if (introspectedColumn.getJavaProperty().equals("delFlag")) {
            field.addJavaDocLine("@TableLogic");
        }
        else if (introspectedColumn.getJavaProperty().equals("createAt")) {
            field.addJavaDocLine("@TableField(value = \""+ introspectedColumn.getActualColumnName() +"\", fill = FieldFill.INSERT)");
        }
        else if (introspectedColumn.getJavaProperty().equals("updateAt")) {
            field.addJavaDocLine("@TableField(value = \""+ introspectedColumn.getActualColumnName() +"\", fill = FieldFill.UPDATE)");
        }
        else if (StrUtil.contains(remarks, "乐观锁")) {
            field.addJavaDocLine("@Version");
        }
        else {
            field.addJavaDocLine("/**");
            if (!StrUtil.isBlank(remarks)) {
                field.addJavaDocLine(" * " + remarks);
            }
            field.addJavaDocLine(" * " + introspectedColumn.getActualColumnName());
            field.addJavaDocLine(" */");
        }

    }

}
