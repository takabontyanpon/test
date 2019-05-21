/* �J�n����:2019/5/15 14:00 */
/* �@�\:�f�X�N�g�b�v��̓��̓f�[�^�̐��l���Ăяo���A�A�����X�g���쐬����B*/

/*�ė��p:class linerregression*/
/*����:java.io.File, java.io.FileReader, java.io.BufferedReader, java.io.FileNotFoundException, java.io.IOException*/
/*�\��:class node, class linkedkist, class linerregression, class Main*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/*class node*/
class node
{
	public node next;
	public double data;
}

/*class linkedlist*/
/*del:2LOC*/
class linkedlist
{
	node Head;
	int size = 0;

	public linkedlist()
	{
		Head = new node();
	}

/*method void insert_node(node node1)*/
/*�ړI:���X�g�̏��߂�node1��}������*/
/*del:12LOC*/
/*mod:3LOC*/

	public void insert_node(node Node)
	{
		/*if(Head.next != null) ���X�g�̒��g���󂩂ǂ����̔���*/
		if(Head.next != null)
		{
			node a = new node();
			a.next = Head.next;
			Head.next = Node;
			Node.next = a.next;
			size = size + 1;
		}else{
			Head.next = Node;
			size = size + 1;
		}
	}

/*double cal_sum()*/
/*�ړI:���X�g����data�̍��v�l���v�Z����*/
/*del:9LOC*/

/*double cal_dev(daouble ave)*/
/*�ړI:���X�g���̈�����ave��p���āA�W���΍����o�͂���*/
/*del:12LOC*/

/*int list_size()*/
/*�ړI:���X�g�̑傫�����o�͂���*/
/*del:4LOC*/

/*void show_list()*/
/*�ړI:���X�g�̒��g��\������*/
/*del:14LOC*/

}

/*class linerregression*/
class linerregression
{
	
	/*method double cal_ave(linkedlist list)*/
	/*�ړI:���X�g�̒��g�̕��ϒl�����߂�*/
	public double cal_ave(linkedlist list)
	{
		double sum = 0.0;
		/*for(node p = list.Head.next; p!=null; p=p.next) ���X�g�̍Ō�܂ł̌J��Ԃ�*/
		for(node p = list.Head.next; p!=null; p=p.next)
		{
			sum = sum + p.data;
		}
		return sum/list.size;
	}

	/*method double sum_mul(linkedlist list1,linkedlist list2)*/
	/*�ړI:list1��list2�̃f�[�^�������āA�������킹�����ʂ����߂�*/
	public double sum_mul(linkedlist list1,linkedlist list2)
	{
		double sum = 0.0;
		if(list1.size == list2.size)
		{
			node q = list2.Head.next;
			/*for(node p = list1.Head.next; p!=null; p=p.next) ���X�g�̍Ō�܂ł̌J��Ԃ�*/
			for(node p = list1.Head.next; p!=null; p=p.next)
			{
				sum = sum + (p.data * q.data);
				q = q.next;
			}
			return sum;
		}
		return 0;
	}

	/*method double sum_square(linkedlist list)*/
	/*�ړI:list�f�[�^���悵�A�������킹�����ʂ����߂�*/
	public double sum_square(linkedlist list)
	{
		double sum = 0.0;
		/*for(node p = list.Head.next; p!=null; p=p.next) ���X�g�̍Ō�܂ł̌J��Ԃ�*/
		for(node p = list.Head.next; p!=null; p=p.next)
		{
			sum = sum + (p.data * p.data);
		}
		return sum;
	}

	/*method double cal_beta1(linkedlist list1,linkedlist list2)*/
	/*�ړI:�ŏ����@��p���āAlist1��list2�̃f�[�^�Ԃ̋ߎ����̃p�����[�^�����߂�*/
	public double cal_beta1(linkedlist list1, linkedlist list2)
	{
		double beta = 0.0, mul, x_ave, y_ave, square;
		/*if(list1.size == list2.size) �f�[�^�̐������������m�F����*/
		if(list1.size == list2.size)
		{
			mul = this.sum_mul(list1,list2);
			x_ave = this.cal_ave(list1);
			y_ave = this.cal_ave(list2);
			square = this.sum_square(list1);
			beta = ((mul)-(list1.size*x_ave*y_ave))/((square)-(list1.size*x_ave*x_ave));
		}
		return beta;
	}

	/*method double cal_beta0(linkedlist list1,linkedlist list2)*/
	/*�ړI:�ŏ����@��p���āAlist1��list2�̃f�[�^�Ԃ̋ߎ����̃p�����[�^�����߂�*/
	public double cal_beta0(linkedlist list1,linkedlist list2)
	{
		double beta1, beta0 = 0.0, x_ave, y_ave;
		/*if(list1.size == list2.size) �f�[�^�̐������������m�F����*/
		if(list1.size == list2.size)
		{
			x_ave = this.cal_ave(list1);
			y_ave = this.cal_ave(list2);
			beta1 = this.cal_beta1(list1,list2);
			beta0 = y_ave - beta1*x_ave;
		}
		return beta0;
	}

	/*method double cal_beta1(linkedlist list1,linkedlist list2)*/
	/*�ړI:list1��list2�̃f�[�^�Ԃ̑��֌W�������߂�*/
	public double cal_r(linkedlist list1,linkedlist list2)
	{
		double mul, sum_x, sum_y, square_x, square_y, r = 0.0;
		/*if(list1.size == list2.size) �f�[�^�̐������������m�F����*/
		if(list1.size == list2.size)
		{
			mul = this.sum_mul(list1,list2);
			sum_x = this.cal_ave(list1)*list1.size;
			sum_y = this.cal_ave(list2)*list2.size;
			square_x = this.sum_square(list1);
			square_y = this.sum_square(list2);
			r = ((list1.size*mul)-(sum_x*sum_y))/Math.sqrt(((list1.size*square_x)-(sum_x*sum_x))*((list1.size*square_y)-(sum_y*sum_y)));
		}
		return r;
	}
}

/*class Main*/
class Main
{
	public static void main(String[] args)
	{
		int i = 0;
		double x;
		linerregression liner_reg = new linerregression();
		linkedlist list[] = new linkedlist[2];
		try
		{
			/*while(i < 2) �t�@�C�������ǂ����̔���*/
			while(i < 2)
				{
					list[i] = new linkedlist();
					File file = new File("c:\\Users\\Panasonic\\Desktop\\"+args[i]);
  					BufferedReader br = new BufferedReader(new FileReader(file));
  					String str;
					/*while((str = br.readLine()) != null) �ǂݍ��񂾍s���t�@�C���̏I��肩�ǂ����̔���*/
  					while((str = br.readLine()) != null)
					{
						double data = Double.parseDouble(str);
    						node Node = new node();
						Node.data = data;
						list[i].insert_node(Node);
					}
					i++;
				}
		}
		catch(FileNotFoundException e)
		{
      			System.out.println(e);
    		}
		catch(IOException e)
		{
      			System.out.println(e);
    		}
		catch(NullPointerException e)
		{
			System.out.println(e);
		}
		x = Double.parseDouble(args[i]);
	System.out.println(String.format("%.5f",liner_reg.cal_beta0(list[0],list[1]))+"    "+String.format("%.5f",liner_reg.cal_beta1(list[0],list[1]))+"    "+String.format("%.5f",liner_reg.cal_r(list[0],list[1]))+"    "+String.format("%.5f",liner_reg.cal_r(list[0],list[1])*liner_reg.cal_r(list[0],list[1]))+"    "+String.format("%.5f",(liner_reg.cal_beta1(list[0],list[1])*x+liner_reg.cal_beta0(list[0],list[1]))));
	}
}
					