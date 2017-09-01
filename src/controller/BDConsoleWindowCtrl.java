/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mongcj.util.debug.BDProgressStatusEvent;
import com.mongcj.util.debug.BDProgressStatusListener;
import com.mongcj.util.debug.BDProgressType;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import view.BDConsoleWindow;

/**
 *
 * @author gsh
 */
public class BDConsoleWindowCtrl implements BDProgressStatusListener {

	BDConsoleWindow consoleWindow;

	public BDConsoleWindowCtrl(BDConsoleWindow consoleWindow) {
		this.consoleWindow = consoleWindow;

		// =====已过时
		consoleWindow.icon_btn.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				consoleWindow.icon_btn.setImage(consoleWindow.icon_msg_press_img);
			}
		});

		consoleWindow.icon_btn.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				consoleWindow.icon_btn.setImage(consoleWindow.icon_msg_detail_img);
				consoleWindow.msgWindow.show();

				// Close the console window.

				consoleWindow.close();
			}
		});
		// ======================
		consoleWindow.detailBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				consoleWindow.msgWindow.show();

				// 更新编译对话框，移除操作按钮
				removeBtns();
				// Close the console window.
				consoleWindow.close();
			}
		});

		consoleWindow.okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 更新编译对话框，移除操作按钮
				removeBtns();

				// Close the console window.
				consoleWindow.msgWindow.clearText();
				consoleWindow.close();
			}
		});

		consoleWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				// 更新编译对话框，移除操作按钮
				removeBtns();
				consoleWindow.msgWindow.clearText();
			}
		});

		consoleWindow.msgWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				consoleWindow.msgWindow.clearText();
			}
		});

	}

	public BDConsoleWindow getView() {

		return this.consoleWindow;
	}

	public void setUploadStyle() {
		consoleWindow.progressBarDebug.setProgress(0.0);
	}

	public void removeBtns() {
		try {
			// 更新编译对话框，移除操作按钮
			consoleWindow.contain.getChildren().add(consoleWindow.progressBarDebug);
			consoleWindow.contain.getChildren().remove(consoleWindow.bottomPanel);
		} catch (Exception ex) {
		}
	}

	public void addBtns() {
		try {
			// 更新编译对话框，弹出操作按钮
			consoleWindow.contain.getChildren().add(consoleWindow.bottomPanel);
			consoleWindow.contain.getChildren().remove(consoleWindow.progressBarDebug);
		} catch (Exception ex) {
		}
	}

	/*
	 * public void setUploadProgressVisable(boolean b){
	 * consoleWindow.progressBarUpload.setVisible(b); }
	 */
	@Override
	public void ProgressEventHandler(BDProgressStatusEvent event) {
		// 在FX线程中，处理Swing线程的数据，必须使用Platform.runLater方法
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				int num = event.getProgressNumber();

				String msg = null;

				// MsgBar default is hidden.
				consoleWindow.msgBtnBar.setVisible(false);

				if (event.getProgressType() == BDProgressType.Compile) {
					consoleWindow.progressBarDebug.setProgress(num / 100.0);

					if (num >= 100) {
						msg = "编译完成";

						// Change the error icon.
						consoleWindow.icon_btn.setImage(consoleWindow.icon_msg_detail_img);

						// Show the message bar.
						consoleWindow.msgBtnBar.setVisible(true);

						// 更新编译进度对话框，隐藏进度条显示操作按钮
						addBtns();

					} else {
						msg = String.format("程序编译中...%d %% ", num);
					}

					consoleWindow.lbl.setText(msg);
				}

				if (event.getProgressType() == BDProgressType.Upload) {
					consoleWindow.progressBarDebug.setProgress(num / 100.0);

					if (num >= 100) {
						msg = "上传完成";

						// Change the error icon.
						consoleWindow.icon_btn.setImage(consoleWindow.icon_msg_detail_img);

						// Show the message bar.
						consoleWindow.msgBtnBar.setVisible(true);

						// 更新编译进度对话框，隐藏进度条显示操作按钮
						addBtns();
					} else {
						msg = String.format("程序上传中...%d %%", num);
					}
					consoleWindow.lbl.setText(msg);
				}
			}
		});
	}
}
