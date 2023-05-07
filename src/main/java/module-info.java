module cz.vse.planner {
    /*** JavaFX ***/
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jdk.httpserver;
    /* JBCRYPT */
    requires jbcrypt;
    /*** JavaFX ***/
    /*** SPRINGBOOT ***/
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter.thymeleaf;
    requires spring.boot.starter;
    requires lombok;
    requires spring.boot.starter.web;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.beans;
    requires spring.core;
    requires spring.web;
    requires spring.webmvc;
    requires spring.tx;
    requires spring.data.commons;
    requires spring.boot.starter.data.jpa;
    requires spring.boot.starter.jdbc;
    requires spring.context.support;
    /* Spring Mailer */
    requires spring.boot.starter.mail;
    /* JPA - Jakarta - SpringToSQL api */
    requires jakarta.persistence;
    requires jakarta.annotation;
    requires jakarta.transaction;
    requires jakarta.cdi;
    /* JPA communicator */
    requires eclipselink;
    /* JAXB */
    requires java.xml.bind;
    /*** SPRINGBOOT ***/

    opens cz.vse.planner.main to javafx.fxml, javafx.graphics, spring.beans, spring.context, spring.core, spring.boot.autoconfigure;
    exports cz.vse.planner.main to javafx.fxml, javafx.graphics, spring.beans, spring.context, spring.core, spring.boot.autoconfigure;

    exports cz.vse.planner.utils;
    opens cz.vse.planner.utils to javafx.fxml;

    exports cz.vse.planner.controls to javafx.fxml, javafx.graphics, spring.beans, spring.context, spring.core;
    opens cz.vse.planner.controls to javafx.fxml, javafx.graphics, spring.beans, spring.context, spring.core;

    opens cz.vse.planner.entity to javafx.fxml, javafx.graphics, spring.beans, spring.boot.autoconfigure, spring.context, spring.core, spring.data.jpa, eclipselink;
    exports cz.vse.planner.entity to javafx.fxml, javafx.graphics, spring.beans, spring.boot.autoconfigure, spring.context, spring.core, spring.data.jpa, eclipselink;
}
