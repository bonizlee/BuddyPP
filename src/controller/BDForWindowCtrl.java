/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;
import model.BDCodeAgent;
import model.BDParameters;
import view.BDForWindow;

/**
 *
 * @author gsh
 */
public class BDForWindowCtrl 
{
    public BDForWindowCtrl(BDForWindow forWindow, BDWorkspaceCtrl workspaceCtrl)
    {
       forWindow.group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
       { 
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle)   
            { 
                if (forWindow.group.getSelectedToggle() != null)  
                {
                    if(forWindow.norRB.isSelected() == true)
                    {
                        // 简单模式
                        forWindow.norPanel.setDisable(false);
                        forWindow.advPanel.setDisable(true);
                    }
                    else
                    {
                        // 高级模式
                        forWindow.norPanel.setDisable(true);
                        forWindow.advPanel.setDisable(false);
                    }
                } 
            } 
       });  
       
        // 循环次数只能输入数字
        forWindow.countTxt.lengthProperty().addListener(new ChangeListener<Number>()
        {
             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
             {              
                   if(newValue.intValue() > oldValue.intValue())
                   {
                       char ch = forWindow.countTxt.getText().charAt(oldValue.intValue());
                       //System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);                   

                       //Check if the new character is the number or other's
                       if(!(ch >= '0' && ch <= '9' ))
                       {       
                            //if it's not number then just setText to previous one
                            forWindow.countTxt.setText(forWindow.countTxt.getText().substring(0,forWindow.countTxt.getText().length()-1)); 
                       }
                  }
             }
        });
        
        // 初始化条件只能输入数字
        forWindow.initValueTxt.lengthProperty().addListener(new ChangeListener<Number>()
        {
             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
             {              
                   if(newValue.intValue() > oldValue.intValue())
                   {
                       char ch = forWindow.initValueTxt.getText().charAt(oldValue.intValue());
                       //System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);                   

                       //Check if the new character is the number or other's
                       if(!(ch >= '0' && ch <= '9' ))
                       {       
                            //if it's not number then just setText to previous one
                            forWindow.initValueTxt.setText(forWindow.initValueTxt.getText().substring(0,forWindow.initValueTxt.getText().length()-1)); 
                       }
                  }
             }
        });
        
        // 结束条件只能输入数字
        forWindow.limitValueTxt.lengthProperty().addListener(new ChangeListener<Number>()
        {
             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
             {              
                   if(newValue.intValue() > oldValue.intValue())
                   {
                       char ch = forWindow.limitValueTxt.getText().charAt(oldValue.intValue());
                       //System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);                   

                       //Check if the new character is the number or other's
                       if(!(ch >= '0' && ch <= '9' ))
                       {       
                            //if it's not number then just setText to previous one
                            forWindow.limitValueTxt.setText(forWindow.limitValueTxt.getText().substring(0,forWindow.limitValueTxt.getText().length()-1)); 
                       }
                  }
             }
        });
        
        // 步长只能输入数字
        forWindow.stepValueTxt.lengthProperty().addListener(new ChangeListener<Number>()
        {
             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
             {              
                   if(newValue.intValue() > oldValue.intValue())
                   {
                       char ch = forWindow.stepValueTxt.getText().charAt(oldValue.intValue());
                       //System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);                   

                       //Check if the new character is the number or other's
                       if(!(ch >= '0' && ch <= '9' ))
                       {
                            //if it's not number then just setText to previous one
                            forWindow.stepValueTxt.setText(forWindow.stepValueTxt.getText().substring(0,forWindow.stepValueTxt.getText().length()-1)); 
                       }
                  }
             }
        });

        forWindow.submitBtn.setOnAction(new EventHandler<ActionEvent>() 
        {    
            @Override
            public void handle(ActionEvent event) 
            {
                BDCodeAgent codeAgent = new BDCodeAgent(workspaceCtrl.workspaceView.workspaceModel.curTab);
                
                // 获取当前行数
                //int lineNum = workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretLineNumber();
                int lineNum = codeAgent.getCaretLineNumber();
                
                // 获取词位
                int tabCount = 0;
                
                try
                {
                    //tabCount = workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getTokenListForLine(lineNum).getLexeme().length();
                    tabCount = codeAgent.getTabCount();
                }
                catch(Exception e)
                {
                    tabCount = 0;
                }
                
                // 生成语句
                String code = "";
                String vID = "i" + BDParameters.variableId;
                
                BDParameters.variableId++;
                
                if(forWindow.norRB.isSelected() == true)
                {
                    // 简单模式
                    
                    // 缺少输入返回
                    if(forWindow.countTxt.getText().equals(""))
                    {
                        // 设为默认值
                        forWindow.countTxt.setText("10");
                        
                        return;
                    }
                        
                    code = "for(int " + vID + " = 0; " + vID + " < " + forWindow.countTxt.getText() + "; " + vID + "++) \n";
                    
                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += "\t";
                    }
                    
                    code += "{\n";
                    
                    // 添加缩进
                    for(int i = 0; i <= tabCount; i++)
                    {
                        code += "\t";
                    }
                    
                    code += "\n";
                    
                    // 添加缩进
                    for(int i = 0; i < tabCount; i++)
                    {
                        code += "\t";
                    }
                    
                    //code += "}\n";
                    code += "}";
                }
                else
                {
                    // 高级模式
                    if(forWindow.initValueTxt.getText().equals("") || forWindow.limitValueTxt.getText().equals("") || forWindow.stepValueTxt.getText().equals(""))
                    {
                        // 缺少输入返回
                        if(forWindow.initValueTxt.getText().equals(""))
                        {
                            // 设为默认值
                            forWindow.initValueTxt.setText("0");
                        }
                        
                        if(forWindow.limitValueTxt.getText().equals(""))
                        {
                            // 设为默认值
                            forWindow.limitValueTxt.setText("10");
                        }
                        
                        if(forWindow.stepValueTxt.getText().equals(""))
                        {
                            // 设为默认值
                            forWindow.stepValueTxt.setText("1");
                        }
                        
                        return;
                    }
                    
                    String opt = "";
                    
                    if(forWindow.opera2CmbBox.getValue() == "自增")
                    {
                        opt = "+=";
                    }
                    else if(forWindow.opera2CmbBox.getValue() == "自减")
                    {
                        opt = "-=";
                    }                    
                    
                   // 高级模式
                   code = "for(int " + vID + " = " +  forWindow.initValueTxt.getText() + "; " + vID + " " + forWindow.operaCmbBox.getValue() + " "  + forWindow.limitValueTxt.getText() + "; " + vID + " " + opt + " " + forWindow.stepValueTxt.getText() + ")";
                   code += "\n";
                   
                   // 添加缩进
                   for(int i = 0; i < tabCount; i++)
                   {
                       code += "\t";
                   }
                   
                   code += "{\n";
                   
                   // 添加缩进
                   for(int i = 0; i <= tabCount; i++)
                   {
                       code += "\t";
                   }
                   
                   code += "\n";
                   
                   // 添加缩进
                   for(int i = 0; i < tabCount; i++)
                   {
                       code += "\t";
                   }
                   
                  //code += "}\n";
                   code += "}";
                }

                //System.out.println(workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getTokenListForLine(lineNum));
                //System.out.println(workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getTokenListForLine(lineNum).getLexeme().length());
               //System.out.println(workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getTokenListForLine(lineNum).getTextArray().length);
                //System.out.println("offset:" + workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretOffsetFromLineStart());
                //System.out.println("offset:" + workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getli);
                /*
                try {
                    System.out.println("offset:" + workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getLineStartOffset(lineNum));
                } catch (BadLocationException ex) {
                    Logger.getLogger(BDForWindowCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
                        */
                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getTabSize();
                //System.out.println("line: " + workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretLineNumber());
                
                //workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.insert(code, workspaceCtrl.workspaceView.workspaceModel.curTab.textArea.getCaretPosition());

                // 插入代码
                
                
                codeAgent.insert(code);
                
                // 关闭窗口
                forWindow.close();
            }
        });
    }
}
