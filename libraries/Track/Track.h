#ifndef Track_h
#define Track_h
class Track{
private:
	//�������ţ��±�0��4��Ӧ�������������
	int Light[5];
	//���巧ֵ���������ֺںͰ�
	int NUM[5];
	//��Ϊtrue���������ֵʱ�ǰ�ɫ����Ϊfalse���������ֵʱ�Ǻ�ɫ��Ĭ��Ϊtrue
	bool flag;

public:
	//���ڴ洢��ȡ��ģ����
	int value[5];
	Track(int a, int b, int c, int d, int e, bool f = true);
	void Mode();
	void Set(int a, int b, int c, int d, int e);
	void Test();
	int Digital_in(int a = 5);
};
#endif