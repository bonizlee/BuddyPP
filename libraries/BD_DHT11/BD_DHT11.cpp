
#include<BD_DHT11.h>

void BD_DHT11::Init(uint8_t pin)
{
	dht11Pin = pin;
	
	Serial.println("DHT11 TEST PROGRAM ");
	Serial.print("LIBRARY VERSION: ");
	Serial.println(DHT11LIB_VERSION);
	Serial.println();
}

void BD_DHT11::Read()
{
	// ��ȡ����������
	chk = DHT11.read(dht11Pin);
}

// �����¶ȶ�ת��Ϊ�����¶�
double BD_DHT11::Fahrenheit(double celsius) 
{
	return 1.8 * celsius + 32;
}

// �����¶�ת��Ϊ�����¶�
double BD_DHT11::Kelvin(double celsius)
{
	return celsius + 273.15;
}     

// ¶�㣨���ڴ��¶�ʱ���������Ͳ�����¶�飩
double BD_DHT11::DewPoint(double celsius, double humidity)
{
	double A0= 373.15/(273.15 + celsius);
	double SUM = -7.90298 * (A0-1);
	
	SUM += 5.02808 * log10(A0);
	SUM += -1.3816e-7 * (pow(10, (11.344*(1-1/A0)))-1) ;
	SUM += 8.1328e-3 * (pow(10,(-3.49149*(A0-1)))-1) ;
	SUM += log10(1013.246);
	
	double VP = pow(10, SUM-3) * humidity;
	double T = log(VP/0.61078);   // temp var
	
	return (241.88 * T) / (17.558-T);
}

// ���ټ���¶�㣬�ٶ���5��dewPoint()
double BD_DHT11::DewPointFast(double celsius, double humidity)
{
	double a = 17.271;
	double b = 237.7;
	double temp = (a * celsius) / (b + celsius) + log(humidity/100);
	double Td = (b * temp) / (a - temp);
	
	return Td;
}

// ��ȡʪ��
double BD_DHT11::GetHumidity()
{

	if(chk == DHTLIB_OK)
	{
		return (double)DHT11.humidity;
	}
	
}

// ��ȡ�����¶�
double BD_DHT11::GetTemperature()
{

	if(chk == DHTLIB_OK)
	{
		return (double)DHT11.temperature;
	}
	
}

// ��ȡ�����¶�
double BD_DHT11::GetFahrenheit()
{

	if(chk == DHTLIB_OK)
	{
		return (double)(Fahrenheit(DHT11.temperature));
	}
	
}

// ��ȡ�����¶�
double BD_DHT11::GetKelvin()
{

	if(chk == DHTLIB_OK)
	{
		return (double)(Kelvin(DHT11.temperature));
	}
	
}

// ��ȡ¶�㣨���϶ȣ�
double BD_DHT11::GetDewPoint()
{

	if(chk == DHTLIB_OK)
	{
		return (double)(DewPoint(DHT11.temperature, DHT11.humidity));
	}
	
}

// ��ȡ¶����٣����϶ȣ�
double BD_DHT11::GetDewFast()
{

	if(chk == DHTLIB_OK)
	{
		return (double)(DewPointFast(DHT11.temperature, DHT11.humidity));
	}
	
}

// ��ʾ��������ȡ����Ϣ
void BD_DHT11::Display()
{
	Serial.println("\n");

	// ��ȡ����������
	//int chk = DHT11.read(dht11Pin);

	Serial.print("Read sensor: ");
	
	// �жϴ�����״̬
	switch (chk)
	{
		case DHTLIB_OK:
		
			Serial.println("OK"); 
                
			break;
                
		case DHTLIB_ERROR_CHECKSUM:
		
			Serial.println("Checksum error");
                 
			break;
                
		case DHTLIB_ERROR_TIMEOUT: 
		
			Serial.println("Time out error"); 
                
			break;
                
		default: 
		
			Serial.println("Unknown error"); 
                
			break;
	}

	// ��ʾ������
	
	Serial.print("Humidity (%): ");
	Serial.println((float)DHT11.humidity, 2);

	Serial.print("Temperature (oC): ");
	Serial.println((float)DHT11.temperature, 2);

	Serial.print("Temperature (oF): ");
	Serial.println(Fahrenheit(DHT11.temperature), 2);

	Serial.print("Temperature (K): ");
	Serial.println(Kelvin(DHT11.temperature), 2);

	Serial.print("Dew Point (oC): ");
	Serial.println(DewPoint(DHT11.temperature, DHT11.humidity));

	Serial.print("Dew PointFast (oC): ");
	Serial.println(DewPointFast(DHT11.temperature, DHT11.humidity));

	// ��ʱ2��
	delay(2000);
}