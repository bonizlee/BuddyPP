/**
 ***************************************
 *
 * @file Db.ino
 * @brief 分贝传感器模块实验
 * 
 * 分贝传感模块可以检测环境声音大小，SIG引脚将会输出一个模拟值，通过
 * 使用螺丝刀调整模块上的电位器可以适当调整传感器的灵敏程度，测试程序
 * 演示了如何通过分贝模块检测环境声音大小。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 ***************************************
 */

#define DbPin A5	// 使用字符串ADpin替代字符串A5

int buffer = 0;		// 定义变量buffer用于分贝传感器读数

// 初始化
void setup()
{
	// 设定串口波特率为9600
	Serial.begin(9600);
}

// 主循环
void loop()
{
	// 读取声音强弱
	buffer = analogRead(DbPin);
  
	// 显示声音强弱读数（每0.5秒更新一次）
	Serial.print("Vloume = ");
	Serial.println(buffer);
  
	// 延时0.5秒
	delay(500);
}
