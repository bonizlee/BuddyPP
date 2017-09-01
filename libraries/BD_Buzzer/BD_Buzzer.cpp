#include<Arduino.h>
#include<BD_Buzzer.h>

void BD_Buzzer::Init(uint8_t inPin)
{
	buzzerPin = inPin;
	
	pinMode(buzzerPin, OUTPUT);
	
	digitalWrite(buzzerPin, 0);
}

void BD_Buzzer::Init(uint8_t buzzerArray[])
{
	len = sizeof(buzzerArray) / sizeof(buzzerArray[0]);
	
	buzzers = new uint8_t[len];
	
	for(uint8_t i = 0; i <= len; i++)
	{
		buzzers[i] = buzzerArray[i];
		
		digitalWrite(buzzers[i], 0);
	}
}

void BD_Buzzer::Sound(bool flag)
{
	digitalWrite(buzzerPin, flag);
}

void BD_Buzzer::SoundBuzzers(bool flag)
{
	for(uint8_t i = 0; i <= len; i++)
	{
		digitalWrite(buzzers[i], flag);
	}
}

void BD_Buzzer::Alert(int interval)
{
	digitalWrite(buzzerPin, 1);
	delay(interval);
	digitalWrite(buzzerPin, 0);
	delay(interval);
}

void BD_Buzzer::FadeIn(uint8_t interval)
{
	for (int a = 0; a <= 255; a++)
	{
		analogWrite(buzzerPin, a);
		delay(interval);
	}
}

void BD_Buzzer::FadeInBuzzers(uint8_t interval)
{
	for (int a = 0; a <= 255; a++)
	{
		for(uint8_t i = 0; i <= len; i++)
		{
			analogWrite(buzzers[i], a);	
		}
		
		delay(interval);
	}
}

void BD_Buzzer::FadeOut(uint8_t interval)
{
	for (int a = 255; a >= 0; a--)
	{
		analogWrite(buzzerPin, a);
		delay(interval);
	}
}

void BD_Buzzer::Music(int tune[], float durt[], int length)
{	
	// ÿѭ��һ�β���һ������
	for(int x = 0; x < length; x++)
	{
		// tone(pin, frequency)
		// tone�����ɲ�����ͬƵ����ͬռ�ձ�(50%)�Ĳ��Ρ�
		// pin��Ҫ�������������
		// frequency�����Ƶ��
		tone(buzzerPin, tune[x]);
	
		delay(500 * durt[x]);
	
		// ��ͣ����
		noTone(buzzerPin);
    
	}
}