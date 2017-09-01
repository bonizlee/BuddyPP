#ifndef Function_h
#define Function_h
#include <Arduino.h>
#include "Servo.h"
#include "ColSensor.h"
#include "Track.h"
#include "Car.h"
#include "ColQueue.h"
#define N 12
/*�洢��⵽��12��λ�÷������ɫ����һ��Ԫ��
Memory[0]��ֵ��Ч���±��ӦС����12��·��*/
int Memory[N + 1];
ColQueue Red(N), Green(N), Blue(N);
//С�����ֵ��ٶ�
int SPEED1 = 150;
//С�����ֵ��ٶ�
int SPEED2 = 150 - 10;
//С��ת��ʱ���ٶ�
int TURN = 180;
//����С�������ƽ����ʱ
int TIME = 1;
//С��ת��ʱ����ʱ
int DELAY = 800;
//С��ֹͣʱ����ʱ
int STOP = 250;
//С������ʱ����ʱ
int BACK = 200;
//��¼С�����ڵ�·�ڣ��ֱ�ȡֵΪ1��12
int num = 0;
Car mycar(8, 9, 10, 11, 5, 6);
//ѭ��ģ��Ϊ��ɫ�ͺŵ��û���Ҫ�ڲ����б�������һ��false
Track mytrack(A5, A4, A3, A2, A1, false);
ColSensor mysensor(0, 1, 3, 4, 2, 10);
/*������ã�1��ʾ���Ƽ��ӵĶ����2��ʾ���ƻ�е
�۰ڶ��Ķ����3��ʾ���ƻ�е�۵�����ת�Ķ��*/
Servo myservo1, myservo2, myservo3;
/********************С��ת�亯��********************/
//С��ֱ����ת
void TurnL(){
	mycar.Move(0, 0, 5, STOP);
	mycar.Move(TURN, TURN, 4, DELAY / 2);
	do{
		mycar.Move(TURN, TURN, 4, TIME);
	} while (mytrack.Digital_in(2));
	mycar.Move(0, 0, 5, 1);
}
//С��ֱ����ת
void TurnR(){
	mycar.Move(0, 0, 5, STOP);
	mycar.Move(TURN, TURN, 6, DELAY / 2);
	do{
		mycar.Move(TURN, TURN, 6, TIME);
	} while (mytrack.Digital_in(2));
	mycar.Move(0, 0, 5, 1);
}
//С��ԭ��ת180��
void TurnC(){
	mycar.Move(0, 0, 5, STOP);
	mycar.Move(TURN, TURN, 4, DELAY);
	do{
		mycar.Move(TURN, TURN, 4, TIME);
	} while (mytrack.Digital_in(2));
	mycar.Move(0, 0, 5, 1);
}
/********************���书����غ���********************/
void Counter(){
	mysensor.g_count++;
}
/*��ⷽ����ɫ��������һ��ֵ��1��ʾ��ɫ��2��ʾ��ɫ��3��ʾ��ɫ��0��ʾû�м�⵽��
ɫ�����ǵ���ɫ����������Ч����Ƚ϶̣�Ϊ����߼������׼ȷ�ʣ��������ڲ��ò�ֵ��
�ϵķ�������ԭ���ǣ����ҳ�RGBֵ�е����ֵ��ֻҪ������������ֵ�е�ĳһ�����100
�����ϣ����⵽����ɫ�����ֵ����Ӧ����ɫȷ������������Ϊû�м�⵽����Ҫ�����ɫ*/
int Color(){
	int i, min1, min2;
	mysensor.Get();
	if (mysensor.g_array[0]>mysensor.g_array[1]){
		i = 0; min1 = mysensor.g_array[1];
	}
	else{
		i = 1; min1 = mysensor.g_array[0];
	}
	if (mysensor.g_array[i]>mysensor.g_array[2])
		min2 = mysensor.g_array[2];
	else {
		min2 = mysensor.g_array[i]; i = 2;
	}
	if ((mysensor.g_array[i] - min1)>100 ||
		(mysensor.g_array[i] - min2)>100)return i + 1;
	return 0;
}
void Push(int i){
	switch (Memory[i]){
	case 1:Red.Enter(i); break;
	case 2:Blue.Enter(i); break;
	case 3:Green.Enter(i); break;
	default:break;
	}
}
int Check(int i){
	int flag;
	mycar.Move(0, 0, 5, STOP);
	//������Σ���Сʧ�����
	for (int i = 0; i<3; i++)
	if (flag = Color()){
		digitalWrite(13, HIGH); delay(500);
		digitalWrite(13, LOW); delay(500);
		break;
	}
	return Memory[i] = flag;
}
/********************���ת������********************/
//���ƶ����ת��
void ServoMove(int a, int b, int c){
	Servo p; int t;
	switch (c){
	case 1:p = myservo1; t = 2; break;
	case 2:p = myservo2; t = 2; break;
	case 3:p = myservo3; t = 2; break;
	default:break;
	}
	//���ǵ�����ľ�ȷ�ȣ��˺���������ֵ�����п���
	a = map(a, 0, 180, 544, 2400);
	b = map(b, 0, 180, 544, 2400);
	if (a>b)
	for (int i = a; i>b; i--){
		p.writeMicroseconds(i); delay(t);
	}
	else
	for (int i = a; i<b; i++){
		p.writeMicroseconds(i); delay(t);
	}
}
//�����ٶȣ��������ѹ������������ٶȼ�С
void Plus(){
	SPEED1++; SPEED2++; TURN++;
}
//����ɲ��
void Stoping(int& a){
	if (a > 2){
		mycar.Move(SPEED1, SPEED2, 2, BACK / (6.0 / a));
		mycar.Move(0, 0, 5, 1);
	}
	a = 0;
}
void Tracking(int a, int b, int temp = 11011){
	int t = 1;
	while (t){
		t = mytrack.Digital_in();
		Serial.println(t);
		if (t == 100 || t == 1110 || t == 10101 || t == 11111)t = temp;
		switch (t){

		case 11110: case 11100: case 11101:
		case 1100: case 1101: case 10100:
			mycar.Move(a, b, 6, TIME); temp = t; break;

		case 1111: case 110: case 101:
		case 10110: case 10111: case 111:
			mycar.Move(a, b, 4, TIME); temp = t; break;

		case 11000: case 1000: case 1001: case 11010:
			mycar.Move(a, b, 6, TIME); temp = t; break;

		case 11: case 10: case 10010: case 1011:
			mycar.Move(a, b, 4, TIME); temp = t; break;

		case 11011: case 1010: case 11001: case 10011:
			mycar.Move(a, b, 8, TIME); temp = t; break;

		case 0: case 10001: case 1: case 10000: 
			/*ʹС��Խ�����ߣ���ֹ�����ظ����ͬһ��������жϴ�
			�󣬵���������˺����Ҷ˾���⵽��ɫʱ�˳���ѭ��*/
			while (mytrack.Digital_in(1) || mytrack.Digital_in(3)){
				mycar.Move(a, b, 8, 1);
			}t = 0; break;

		default:break;
		}
	}
	mycar.Move(0, 0, 5, 1);
}
void In(){
	TurnL();
	Tracking(SPEED1, SPEED2, 1);
	mycar.Move(SPEED1, SPEED2, 2, BACK);
	mycar.Move(0, 0, 5, 1);

}
void Out(){
	TurnC();
	Tracking(SPEED1, SPEED2, 1);
	mycar.Move(SPEED1, SPEED2, 2, BACK / 3);
	mycar.Move(0, 0, 5, 1);
	TurnL();
}
/********************У������********************/
//���ڰ�ƽ��
void Ready(){
	pinMode(13, OUTPUT);
	attachInterrupt(0, Counter, RISING);
	delay(1000);
	mysensor.Test();
	for (int i = 0; i<1000 / 200; i++){
		digitalWrite(13, HIGH); delay(100);
		digitalWrite(13, LOW); delay(100);
	}
	for (int i = 0; i<1;){
		int flag = Color();
		if (flag){
			digitalWrite(13, HIGH); delay(500);
			digitalWrite(13, LOW); delay(500);
			i++;
		}
	}
	for (int i = 0; i<3000 / 200; i++){
		digitalWrite(13, HIGH); delay(100);
		digitalWrite(13, LOW); delay(100);
	}
}
//�Զ�������ֵ
void Modify(){
	int test1[5] = { 0, 0, 0, 0, 0 };
	int test2[5] = { 0, 0, 0, 0, 0 };
	int t[5] = { 0, 0, 0, 0, 0 };
	pinMode(13, OUTPUT);
	//��ָʾ��ֹͣ��˸ǰ��Ӧ������ȫ����׼���߲��ַ���
	for (int i = 0; i<3000 / 200; i++){
		digitalWrite(13, HIGH); delay(100);
		digitalWrite(13, LOW); delay(100);
	}
	for (int i = 0; i<20; i++){
		mytrack.Test();
		for (int i = 0; i<5; i++){
			test1[i] += mytrack.value[i];
		}
		delay(10);
	}
	//��ָʾ��ֹͣ��˸ǰ��Ӧ������ȫ����׼��ɫ���ַ���
	for (int i = 0; i<3000 / 1000; i++){
		digitalWrite(13, HIGH); delay(500);
		digitalWrite(13, LOW); delay(500);
	}
	for (int i = 0; i<20; i++){
		mytrack.Test();
		for (int i = 0; i<5; i++){
			test2[i] += mytrack.value[i];
		}
		delay(10);
	}
	for (int i = 0; i<5; i++){
		test1[i] = test1[i] / 20;
		test2[i] = test2[i] / 20;
		t[i] = (test1[i] + test2[i]) / 2;
	}
	mytrack.Set(t[0], t[1], t[2], t[3], t[4]);
	for (int i = 0; i<3000 / 200; i++){
		digitalWrite(13, HIGH); delay(100);
		digitalWrite(13, LOW); delay(100);
	}
}
#endif