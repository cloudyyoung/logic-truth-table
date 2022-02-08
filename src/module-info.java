module LogicTruthTable {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.junit.jupiter.api;
	requires junit;
	requires javafx.graphics;
	requires javafx.base;
	requires java.base;
	
	
	opens application;
	opens model;
	opens connective;
	opens view;
	
}
