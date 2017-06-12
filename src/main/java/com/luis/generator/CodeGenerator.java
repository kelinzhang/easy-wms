package com.luis.generator;


import com.luis.wms.domain.StockOutcomeBill;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

//代码生成器类
public class CodeGenerator {
    private static Configuration cfg;

    static {
        try {
            cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File("templates"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("生成开始");
//        generated();
        System.out.println("生成结束");
    }

    private static void generated()throws Exception{
        ClassInfo classInfo = new ClassInfo(StockOutcomeBill.class);
        //生成DAO组件
        createFile(classInfo, "IDAO.ftl", "src/main/java/{0}/dao/I{1}DAO.java");
        createFile(classInfo, "DAOImpl.ftl", "src/main/java/{0}/dao/impl/{1}DAOImpl.java");
        //生成service组件
        createFile(classInfo, "IService.ftl", "src/main/java/{0}/service/I{1}Service.java");
        createFile(classInfo, "ServiceImpl.ftl", "src/main/java/{0}/service/impl/{1}ServiceImpl.java");
        //生成Action和Query对象
        createFile(classInfo, "QueryObject.ftl", "src/main/java/{0}/query/{1}QueryObject.java");
        createFile(classInfo, "Action.ftl", "src/main/java/{0}/web/action/{1}Action.java");
        //生成映射文件和JSP文件
        createFile(classInfo, "hbm_xml.ftl", "src/main/resources/{0}/domain/{1}.hbm.xml");
        createFile(classInfo, "list_jsp.ftl", "src/main/webapp/WEB-INF/views/{2}/orderChart.jsp");
        createFile(classInfo, "input_jsp.ftl", "src/main/webapp/WEB-INF/views/{2}/input.jsp");
        //追加dao,service,action的配置
        appendXml(classInfo,"dao_xml.ftl","src/main/resources/applicationContext-daos.xml");
        appendXml(classInfo,"service_xml.ftl","src/main/resources/applicationContext-services.xml");
        appendXml(classInfo,"action_xml.ftl","src/main/resources/applicationContext-actions.xml");
    }

    //在XML配置之后追加一段配置
    private static void appendXml(ClassInfo classInfo, String templateFile, String targetFile) throws Exception {
        Template template = cfg.getTemplate(templateFile);
        StringWriter sWriter = new StringWriter();//字符串输出流
        template.process(classInfo,sWriter);
        //追加
        XmlUtil.mergeXML(new File(targetFile),sWriter.toString());
    }

    //生成文件
    private static void createFile(ClassInfo classInfo, String templateFile, String targetFile) throws Exception {
        Template template = cfg.getTemplate(templateFile);
        targetFile = MessageFormat.format(targetFile,
                classInfo.getBasePkg().replace('.', '/')//设置{0}位置
                , classInfo.getClassName()//设置{1}
                , classInfo.getObjectName());//设置{2}


        File f = new File(targetFile);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        template.process(classInfo, new FileWriter(targetFile));
    }

}
