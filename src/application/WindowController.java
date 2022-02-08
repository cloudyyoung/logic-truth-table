package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import model.Statement;
import model.*;

public class WindowController {

	@FXML
	void initialize() {
		//Sets description text on textLabel
		textLabel.setText("This application can create truth table by entering a logic statement formula.\n"
				+ "You can enter logical operators in differnet formats.\n"
				+ "For example, you formula is (A \u2227 \u00ACB) \u2192 C, then could be written as (A /\\ (-B)) -> C,  \n"
				+ "or as (A & (~B)) > C.");
		promptLabel.setVisible(false); // Hides promptLabel
		truthTable.setEditable(false); // Disables editing table

	}

	@FXML
	private TextField input;

	@FXML
	private TableView<Row> truthTable;

	@FXML
	private Label textLabel; // Descriptive text about application

	@FXML
	private Label promptLabel; // Descriptive text

	
	/**
	 * The method will be called when user every time typed
	 * and show the table for valid statement.
	 * 
	 * @param event 	Takes action from event in textField.
	 */
	@FXML
	void keyTyped(KeyEvent event) {

		this.truthTable.setVisible(false); // Hide table
		this.promptLabel.setVisible(false); // Hide prompt label
		this.truthTable.getColumns().clear(); // Clear table
		
		// The input is blank, not parse
		if(this.input.getText().isBlank()) {
			return;
		}

		Statement statement = null;
		try {
			// gets user input
			statement = StatementParser.parse(this.input.getText());

		} catch (InvalidStatementException e) {
			// If statement invalid, throw error, display prompt label
			promptLabel.setVisible(true);
			promptLabel.setPrefHeight(200);
			this.promptLabel.setText(e.getMessage() + "\n" + "Please try again...");
			return;
		}

		// Show table if statement is valid.
		promptLabel.setPrefHeight(0);
		showTable(statement);

	}

	
	/** 
	 * Sets whole tableView about column header, table values and display in tableView.
	 * 
	 * @param statement the statement object to build the table
	 */

	public void showTable(Statement statement) {
		Table table = new Table(statement);	//Creates new table for results


		//Gets amount of columns
		int variableSize = table.getVariables().size();
		int columnsTotal = variableSize + table.getRoot().getTruthValues().size() + 1;
		TableColumn<Row, String>[] columnsHeader = new TableColumn[columnsTotal];

		//Creates new empty columns
		columnsHeader[variableSize] = new TableColumn<Row, String>("");
		columnsHeader[variableSize].setPrefWidth(35);

		//Sets left side (variables) column header
		for (int t = 0; t < variableSize; t++) {
			columnsHeader[t] = new TableColumn<Row, String>(table.getVariables().get(t).toString());
			columnsHeader[t].setPrefWidth(35);
		}

		//Sets right side (statement) column header
		for (int t = variableSize + 1; t < columnsTotal; t++) {
			String value = table.getRoot().getStringArray().get(t - variableSize - 1);
			columnsHeader[t] = new TableColumn<Row, String>(value);

			if (value.equals("(") || value.equals(")")) {
				columnsHeader[t].setPrefWidth(17);
			} else {
				columnsHeader[t].setPrefWidth(35);
			}

		}

		for (int t = 0; t < columnsTotal; t++) {
			TableColumn<Row, String> tb = columnsHeader[t];
			final int k = t;

			tb.setSortable(false); //Disables user sort by columns
			tb.setResizable(false); //Disables user resize columns
			tb.setReorderable(false); //Disables user reorder columns

			// sets cell type for table
			if (t < variableSize) {
				tb.setCellValueFactory(new Callback<CellDataFeatures<Row, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Row, String> cell) {
						boolean value = cell.getValue().getStatement().getVariables().get(k).getValue();
						return new SimpleStringProperty("" + BooleanUtil.getBooleanCap(value));
					}
				});
			} else if (t > variableSize) {
				tb.setCellValueFactory(new Callback<CellDataFeatures<Row, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Row, String> cell) {
						Boolean value = cell.getValue().getStatement().getTruthValues().get(k - variableSize - 1);
						if (value != null) {
							return new SimpleStringProperty("" + BooleanUtil.getBooleanCap(value));
						}

						return new SimpleStringProperty("");
					}
				});
			}
		}

		this.truthTable.getColumns().clear();	//Clear whole table 
		this.truthTable.getColumns().setAll(columnsHeader); // sets columns header in tableView
		this.truthTable.setVisible(true); // sets tableView visible

		// for each row, get truth values in string array and add to table
		ObservableList<Row> observableList = FXCollections.observableArrayList();
		observableList.addAll(table.getRows());
		this.truthTable.setItems(observableList); // sets all values in tableView

	}
}
