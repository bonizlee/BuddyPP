/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

/**
 *
 * @author gsh
 */
public class BDSwitchWindow extends BDWindow
{
    @SuppressWarnings("rawtypes")
	public ComboBox value1CmbBox = new ComboBox();
    @SuppressWarnings("rawtypes")
	public ComboBox value2CmbBox = new ComboBox();
    
    public Button submitBtn = new Button("确定");
    
    private HBox topPanel   = new HBox();
    public VBox bottomPanel = new VBox();
    public VBox buttonPanel = new VBox();
    
    @SuppressWarnings("rawtypes")
	public ObservableList<ComboBox> valuesList = FXCollections.observableArrayList();
    
    @SuppressWarnings("unchecked")
	public BDSwitchWindow()
    {
        // 窗口初始化
    	super.init(480, 250);
        
        // 总在最前方
    	this.setAlwaysOnTop(true);
       
    	// 只有关闭按钮的窗口
    	this.initStyle(StageStyle.UTILITY);
    	this.setResizable(false);
       
    	this.setTitle("  Switch-Case语句");
    	this.setScene(scene);

    	VBox contain  = new VBox();
       
    	//HBox topPanel = new HBox();
       
    	topPanel.getChildren().add(new Label("表达式："));
       
    	value1CmbBox.setEditable(true);
       
    	value1CmbBox.setPromptText("整形表达式");
       
    	value2CmbBox.getItems().add("1");
    	value2CmbBox.getItems().add("2");
    	value2CmbBox.getItems().add("3");
    	value2CmbBox.getItems().add("4");
    	value2CmbBox.getItems().add("5");
    	value2CmbBox.getItems().add("6");
    	value2CmbBox.getItems().add("7");
    	value2CmbBox.getItems().add("8");
    	value2CmbBox.getItems().add("9");
    	value2CmbBox.getItems().add("10");
       
    	// 默认为三个条件
    	value2CmbBox.getSelectionModel().select(2);
       
    	topPanel.getChildren().add(value1CmbBox);
    	topPanel.getChildren().add(new Label("数量："));
    	topPanel.getChildren().add(value2CmbBox);
       
    	submitBtn.setPrefSize(80, 30);
    	
    	value1CmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
    	value2CmbBox.setStyle("-fx-background-radius: 0, 0;-fx-font-size: 15;");
    	submitBtn.setStyle("-fx-background-radius: 0, 0;");
       
    	buttonPanel.getChildren().add(submitBtn);
       
    	contain.getChildren().add(topPanel);
    	contain.getChildren().add(bottomPanel);
    	contain.getChildren().add(buttonPanel);
       
    	topPanel.setPadding(new Insets(15, 15, 15, 15));  	// 设置边距
    	topPanel.setSpacing(10);                          	// 设置间距
    	topPanel.setAlignment(Pos.CENTER_LEFT);           	// 居中排列
       
    	bottomPanel.setPadding(new Insets(-15, 15, 15, 73)); // 设置边距
    	bottomPanel.setSpacing(10);                          // 设置间距
    	//bottomPanel.setAlignment(Pos.CENTER_LEFT);         // 居中排列
       
    	buttonPanel.setPadding(new Insets(0, 0, 0, 295));  	// 设置边距
       
    	contain.setPadding(new Insets(15, 15, 10, 15));  	// 设置边距
    	contain.setSpacing(10);                          	// 设置间距
    	contain.setAlignment(Pos.CENTER_RIGHT);           	// 居中排列

    	rootPanel.getChildren().add(contain);
    }
}
