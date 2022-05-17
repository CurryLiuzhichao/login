package com.lzc.login;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.Scanner;

public class CodeGenerator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/lzc_login?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8", "root", "19971003qaz").globalConfig(builder -> {
                    builder.author("lzc")
                            .commentDate("yyyy-MM-dd hh:mm:ss")
                            .dateType(DateType.TIME_PACK)
                            .enableSwagger()
                            .fileOverride()
                            //E:\git\辰图项目\src\main\java
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.lzc.login") // 设置父包名
                            .controller("controller") //生成controller层
                            .entity("entity") //生成实体层
                            .service("service") //生成服务层
                            .mapper("mapper"); //生成mapper层
                    // .moduleName("mybatisplus");
                })//策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("")// 设置过滤表前缀
                            .serviceBuilder() //开启service策略配置
                            .formatServiceFileName("%sService") //取消Service前的I
                            .controllerBuilder() //开启controller策略配置
                            .enableRestStyle() //配置restful风格
                            .enableHyphenStyle() //url中驼峰转连字符
                            .entityBuilder() //开启实体类配置
                            .enableLombok() //开启lombok
                            .enableChainModel(); //开启lombok链式操作

                })
                //模板配置
                .templateEngine(new FreemarkerTemplateEngine())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                //执行
                .execute();
    }

}
