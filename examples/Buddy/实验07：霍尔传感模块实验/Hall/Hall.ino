/**
 ***************************************
 *
 * @file Hall.ino
 * @brief 霍尔传感器模块实验
 * 
 * 霍尔传感模块通过霍尔传感器检测磁性事件，当磁体靠近霍尔传感器，
 * SIG引脚将会输出一个低电平信号，通过使用螺丝刀调整模块上的电位
 * 器可以适当调整传感器的灵敏程度，测试程序演示了如何通过霍尔传
 * 感模块检测磁体靠近事件。
 * 
 * @author gsh
 * @version 1.0
 * @date 2015.12.1
 *
 ***************************************
 */

int hallPin = 3;	// 定义变量shockPin指向数字端口D3
int buffer  = 0;	// 定义变量buffer用于暂存霍尔传感器读数

void setup()
{
	// 设定串口波特率为9600
	Serial.begin(9600);
}

void loop()
{
	// 读取霍尔传感器数据
	buffer = digitalRead(hallPin);
	
	// 显示霍尔传感器的读数（每0.5秒更新一次）
	Serial.print("value = ");
	Serial.println(buffer);
  
	// 延时0.5秒
	delay(500);
}

