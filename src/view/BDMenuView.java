/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import util.base.Preferences;

import org.fxmisc.richtext.CodeArea;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BDParameters;

/**
 *
 * @author gsh
 */
public class BDMenuView extends HBox
{
    public BDSearchWindow searchWindow      = new BDSearchWindow();     // 搜索窗口
    public BDLibWindow libWindow            = new BDLibWindow();        // 添加库窗口
    public BDComWindow comWindow            = new BDComWindow();        // 串口通讯窗口
    public BDPreSettingWindow psw           = new BDPreSettingWindow(); // 预设置窗口
    public BDExampleWindow expWindow        = new BDExampleWindow();    // 例程窗口
    public BDConsoleWindow consoleWindow    = new BDConsoleWindow();    // 编译信息窗口
    public BDPluginWindow pluginWindow      = new BDPluginWindow();     // 工具插件窗口
    public BDAboutWindow aboutWindow        = new BDAboutWindow();      // 关于我们窗口
    
    public CodeArea consloeArea				= null;
    public SplitPane splitPanel 			= null;
    public Stage primaryStage				= null;
    
    public BDHintDialogWindow hintDialogWindow = new BDHintDialogWindow("  提示", "请先确定计算机已经连接开发板！");
    
    public Button menuOpenBtn      = new Button(); // 打开按钮
    public Button menuNewBtn       = new Button(); // 新建按钮
    public Button menuSaveBtn      = new Button(); // 保存按钮
    public Button menuSaveAsBtn    = new Button(); // 另存为按钮
    
    public Button menuExampleBtn   = new Button(); // 例程按钮
        
    public Button menuUndoBtn      = new Button(); // 恢复按钮
    public Button menuRedoBtn      = new Button(); // 重做按钮
    public Button menuSearchBtn    = new Button(); // 搜索按钮
        
    public Button menuLibBtn       = new Button(); // 添加库按钮
    public Button menuVerifyBtn    = new Button(); // 编译按钮
    public Button menuUploadBtn    = new Button(); // 编译上传按钮
    public Button menuComBtn       = new Button(); // 串口通讯按钮
    public Button menuSettingBtn   = new Button(); // 设置按钮
    public Button menuAboutBtn     = new Button(); // 社区按钮
    
    public Button menuPreSetBtn    = new Button(); // 预设置按钮
    
    public HBox forumPanel = new HBox();
    public HBox settingPanel = new HBox();
    
    public Label lbBoard   = new Label();
    public Label lbCom     = new Label();
    
    public VBox rightLables;
        
    public BDMenuView()
    {
        this.setStyle("-fx-background-color: #42b2e4;");
        
        // 设置菜单栏位置
        this.setPrefHeight(60);
        this.setPadding(new Insets(0, 0, 0, 25));
        //this.setPadding(new Insets(5, 0, 0, 15));
        
        this.setAlignment(Pos.CENTER_LEFT);          // 居中排列
        
        
        //HBox settingPanel = new HBox();
        
        //Image image = new Image("/images/settingPanel.png");
        //ImageView iv = new ImageView(image);
        
        Image image = new Image("/images/sp.png");
        ImageView sp01 = new ImageView(image);
        ImageView sp02 = new ImageView(image);
        ImageView sp03 = new ImageView(image);
        ImageView sp04 = new ImageView(image);
        
        Tooltip menuPreSetBtnTip = new Tooltip("预设值");
        
        menuPreSetBtnTip.getStyleClass().add("tip");

        menuPreSetBtn.setPrefSize(50, 50);
        menuPreSetBtn.setTooltip(menuPreSetBtnTip);
        menuPreSetBtn.getStyleClass().add("preSetBtn");
        
        settingPanel.getChildren().add(menuPreSetBtn);
        
        rightLables = new VBox();
        
        //rightLables.setStyle("-fx-padding: 12,0,0,0;");
        //rightLables.setPadding(new Insets(0, 0, 0, 0));
        
        
        //Label lbBoard   = new Label();
        //Label lbCom     = new Label();
        
        lbBoard.setText("当前板型：Buddy Board LEO");
        
        if(BDParameters.serialports.isEmpty())
        {
            lbCom.setText("当前串口：未连接");
        }
        else
        {
            lbCom.setText("当前串口："+Preferences.get("serial.port"));
        }
        
        
        lbBoard.setStyle("-fx-text-fill: #ffffff;-fx-font-size:13px;"); 
        lbCom.setStyle("-fx-text-fill: #ffffff;-fx-font-size:13px;"); 
        
        rightLables.getChildren().add(lbBoard);
        rightLables.getChildren().add(lbCom);
        rightLables.setAlignment(Pos.CENTER_LEFT);
        rightLables.setPadding(new Insets(0, 0, 0, 12));
        
        settingPanel.setAlignment(Pos.CENTER_LEFT);
        
        //settingPanel.getChildren().add(rightLables);
        
        // 设定设置面板的宽度
        //settingPanel.setPrefWidth(315);
        //settingPanel.setPrefWidth(265);
        
        //settingPanel.setPrefWidth(250);
        rightLables.setPrefWidth(300);
        
        /*
        Button menuOpenBtn      = new Button(); // 打开按钮
        Button menuNewBtn       = new Button(); // 新建按钮
        Button menuSaveBtn      = new Button(); // 保存按钮
        Button menuSaveAsBtn    = new Button(); // 另存为按钮
        
        Button menuUndoBtn      = new Button(); // 恢复按钮
        Button menuRedoBtn      = new Button(); // 重做按钮
        Button menuSearchBtn    = new Button(); // 搜索按钮
        
        Button menuLibBtn       = new Button(); // 添加哭按钮
        Button menuUploadBtn    = new Button(); // 编译上传按钮
        Button menuComBtn       = new Button(); // 串口通讯按钮
        Button menuSettingBtn   = new Button(); // 设置按钮
        Button menuForumBtn     = new Button(); // 社区按钮
        */

        menuOpenBtn.setPrefSize(50, 50);
        menuNewBtn.setPrefSize(50, 50);
        menuSaveBtn.setPrefSize(50, 50);
        menuSaveAsBtn.setPrefSize(50, 50);
        menuExampleBtn.setPrefSize(50, 50);
        menuUndoBtn.setPrefSize(50, 50);
        menuRedoBtn.setPrefSize(50, 50);
        menuSearchBtn.setPrefSize(50, 50);
        menuLibBtn.setPrefSize(50, 50);
        menuVerifyBtn.setPrefSize(50, 50);
        menuUploadBtn.setPrefSize(50, 50);
        menuComBtn.setPrefSize(50, 50);
        menuSettingBtn.setPrefSize(50, 50);
        menuAboutBtn.setPrefSize(50, 50);
        
        Tooltip menuOpenBtnTip = new Tooltip("打开");
        Tooltip menuNewBtnTip = new Tooltip("新建");
        Tooltip menuSaveBtnTip = new Tooltip("保存");
        Tooltip menuSaveAsBtnTip = new Tooltip("另存为");
        Tooltip menuExampleBtnTip = new Tooltip("例子");
        Tooltip menuUndoBtnTip = new Tooltip("恢复");
        Tooltip menuRedoBtnTip = new Tooltip("重做");
        Tooltip menuSearchBtnTip = new Tooltip("搜索");
        Tooltip menuLibBtnTip = new Tooltip("添加库");
        Tooltip menuVerifyBtnTip = new Tooltip("编译");
        Tooltip menuUploadBtnTip = new Tooltip("烧录");
        Tooltip menuComBtnTip = new Tooltip("串口通讯");
        Tooltip menuSettingBtnTip = new Tooltip("工具");
        Tooltip menuForumBtnTip = new Tooltip("关于");
        //Tooltip menuSettingBtnTip = new Tooltip("设置");
        //Tooltip menuForumBtnTip = new Tooltip("社区");

        menuOpenBtnTip.getStyleClass().add("tip");
        menuNewBtnTip.getStyleClass().add("tip");
        menuSaveBtnTip.getStyleClass().add("tip");
        menuSaveAsBtnTip.getStyleClass().add("tip");
        menuExampleBtnTip.getStyleClass().add("tip");
        menuUndoBtnTip.getStyleClass().add("tip");
        menuRedoBtnTip.getStyleClass().add("tip");
        menuSearchBtnTip.getStyleClass().add("tip");
        menuLibBtnTip.getStyleClass().add("tip");
        menuVerifyBtnTip.getStyleClass().add("tip");
        menuUploadBtnTip.getStyleClass().add("tip");
        menuComBtnTip.getStyleClass().add("tip");
        menuSettingBtnTip.getStyleClass().add("tip");
        menuForumBtnTip.getStyleClass().add("tip");

        menuOpenBtn.setTooltip(menuOpenBtnTip);
        menuNewBtn.setTooltip(menuNewBtnTip);
        menuSaveBtn.setTooltip(menuSaveBtnTip);
        menuSaveAsBtn.setTooltip(menuSaveAsBtnTip);
        menuExampleBtn.setTooltip(menuExampleBtnTip);
        menuUndoBtn.setTooltip(menuUndoBtnTip);
        menuRedoBtn.setTooltip(menuRedoBtnTip);
        menuSearchBtn.setTooltip(menuSearchBtnTip);
        menuLibBtn.setTooltip(menuLibBtnTip);
        menuVerifyBtn.setTooltip(menuVerifyBtnTip);
        menuUploadBtn.setTooltip(menuUploadBtnTip);
        menuComBtn.setTooltip(menuComBtnTip);
        menuSettingBtn.setTooltip(menuSettingBtnTip);
        menuAboutBtn.setTooltip(menuForumBtnTip);
        
        /*
        menuOpenBtn.setTooltip(new Tooltip("打开"));
        menuNewBtn.setTooltip(new Tooltip("新建"));
        menuSaveBtn.setTooltip(new Tooltip("保存"));
        menuSaveAsBtn.setTooltip(new Tooltip("另存为"));
        menuUndoBtn.setTooltip(new Tooltip("恢复"));
        menuRedoBtn.setTooltip(new Tooltip("重做"));
        menuSearchBtn.setTooltip(new Tooltip("搜索"));
        menuLibBtn.setTooltip(new Tooltip("添加库"));
        menuUploadBtn.setTooltip(new Tooltip("烧录"));
        menuComBtn.setTooltip(new Tooltip("串口调试"));
        menuSettingBtn.setTooltip(new Tooltip("设置"));
        menuForumBtn.setTooltip(new Tooltip("社区"));
        */
        
        menuOpenBtn.getStyleClass().add("openBtn");
        menuNewBtn.getStyleClass().add("newBtn");
        menuSaveBtn.getStyleClass().add("saveBtn");
        menuSaveAsBtn.getStyleClass().add("saveAsBtn");
        menuExampleBtn.getStyleClass().add("exampleBtn");
        menuUndoBtn.getStyleClass().add("undoBtn");
        menuRedoBtn.getStyleClass().add("redoBtn");
        menuSearchBtn.getStyleClass().add("searchBtn");
        menuLibBtn.getStyleClass().add("libBtn");
        menuVerifyBtn.getStyleClass().add("verifyBtn");
        menuUploadBtn.getStyleClass().add("uploadBtn");
        menuComBtn.getStyleClass().add("comBtn");
        menuSettingBtn.getStyleClass().add("settingBtn");
        menuAboutBtn.getStyleClass().add("forumBtn");
        
        this.getChildren().add(menuNewBtn);
        this.getChildren().add(menuOpenBtn);
        this.getChildren().add(menuSaveBtn);
        this.getChildren().add(menuSaveAsBtn);
        this.getChildren().add(menuExampleBtn);
        this.getChildren().add(sp01);
        this.getChildren().add(menuUndoBtn);
        this.getChildren().add(menuRedoBtn);
        this.getChildren().add(menuSearchBtn);
        this.getChildren().add(sp02);
        this.getChildren().add(menuLibBtn);
        this.getChildren().add(menuComBtn);
        this.getChildren().add(menuVerifyBtn);
        this.getChildren().add(menuUploadBtn);
        //this.getChildren().add(menuSettingBtn);
        //this.getChildren().add(menuForumBtn);

        this.getChildren().add(settingPanel);
        this.getChildren().add(rightLables);
        //this.getChildren().add(menuAboutBtn);
        
        //HBox forumPanel = new HBox();
        
        // 暂时屏蔽
        //forumPanel.getChildren().add(menuSettingBtn);
        //forumPanel.getChildren().add(menuAboutBtn);
        
        //forumPanel.setPadding(new Insets(0, 0, 0, 150));
        
        forumPanel.setAlignment(Pos.CENTER_LEFT);
        
        this.getChildren().add(forumPanel);
    }
}
