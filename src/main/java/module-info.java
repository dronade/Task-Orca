module dev.dronade.taskorca {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens dev.dronade.taskorca to javafx.fxml;
    exports dev.dronade.taskorca;
    exports dev.dronade.taskorca.controller;
    opens dev.dronade.taskorca.controller to javafx.fxml;
    exports dev.dronade.taskorca.model;
    opens dev.dronade.taskorca.model to javafx.fxml;
    exports dev.dronade.taskorca.database;
    opens dev.dronade.taskorca.database to javafx.fxml;
}