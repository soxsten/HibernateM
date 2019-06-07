package com;

import com.entity.CondenserMicrophone;
import com.entity.DynamicMicrophone;
import com.entity.Microphone;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;

public class SchemaGenerator {
    private static final String SCRIPT_FILE = "exportScript.sql";
    private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";

    private static SchemaExport getSchemaExport() {
        SchemaExport export = new SchemaExport();
        File outputFile = new File(SCRIPT_FILE);
        String outputFilePath = outputFile.getAbsolutePath();
        System.out.println("Export file: " + outputFilePath);

        export.setDelimiter(";");
        export.setOutputFile(outputFilePath);
        export.setHaltOnError(false);
        return export;
    }

    private static void dropDataBase(SchemaExport export, Metadata metadata) {
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
        export.drop(targetTypes, metadata);
    }

    private static void createDataBase(SchemaExport export, Metadata metadata) {
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
        SchemaExport.Action action = SchemaExport.Action.CREATE;
        export.execute(targetTypes, action, metadata);
        System.out.println("Export OK");
    }

    private static void generateDataValue() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                DynamicMicrophone microphone = new DynamicMicrophone();
                microphone.setName("Child dynamic " + i);
                microphone.setWeight(String.valueOf(i * 10));
                session.save(microphone);
            }
            if (i % 3 == 0) {
                Microphone microphone = new Microphone();
                microphone.setName("Parent mic " + i);
                session.save(microphone);
            }
            if (i % 4 == 0) {
                CondenserMicrophone microphone = new CondenserMicrophone();
                microphone.setName("Child condenser " + i);
                microphone.setColor("Color #" + i);
                session.save(microphone);
            }
        }
        session.close();
    }

    public static void main(String[] args) {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure(HIBERNATE_CFG_XML)
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder()
                .build();
        SchemaExport export = getSchemaExport();
        System.out.println("Drop Database...");
        dropDataBase(export, metadata);
        System.out.println("Create Database...");
        createDataBase(export, metadata);
        generateDataValue();
    }
}
