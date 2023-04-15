module cz.vse.planner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.fx.countries;
    requires eu.hansolo.fx.heatmap;
    requires eu.hansolo.toolboxfx;
    requires eu.hansolo.toolbox;

    opens cz.vse.planner.main to javafx.fxml, javafx.graphics;
    exports cz.vse.planner.main to javafx.fxml, javafx.graphics;
    opens cz.vse.planner to javafx.fxml;
    exports cz.vse.planner;
}
