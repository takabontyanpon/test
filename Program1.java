
/* �J�n����:2019/4/17 13:00 */
/* �@�\:�f�X�N�g�b�v��̓��̓f�[�^�̐��l���Ăяo���A�A�����X�g���쐬����B*/

/*�ė��p:class node, class linkedlist*/
/*����:java.io.File, java.io.FileReader, java.io.BufferedReader, java.io.FileNotFoundException, java.io.IOException*/
/*�\��:class node, class linkedkist, class Main*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/*class node*/
class node
{
	public node next;
	public int num;
	public double data;

/*method void set_num(int num)*/
/*�ړI:�����̐�����node��num�ɐݒ肷��*/

	public void set_num(int num)
	{
		this.num = num;
	}

/*method void set_data(double data)*/
/*�ړI:�����̐�����node��data�ɐݒ肷��*/

	public void set_data(double data)
	{
		this.data = data;
	}

/*method void set_next(node next)*/
/*�ړI:������node��node��next�ɐݒ肷��*/

	public void set_next(node next)
	{
		this.next = next;
	}
}

/*class linkedlist*/
class linkedlist
{
	node Head;
	int size = 0;

	public linkedlist()
	{
		Head = new node();
		Head.set_num(0);
		Head.set_data(0.0);
	}

/*method void insert_node(double data)*/
/*�ړI:���X�g�̏��߂Ɉ�����data��������node��}������*/

	public void insert_node(double data)
	{
		/*if(Head.next != null) ���X�g�̒��g���󂩂ǂ����̔���*/
		if(Head.next != null)
		{
			node newNode = new node();
			node a = new node();
			a.next = Head.next;
			Head.next = newNode;
			newNode.data = data;
			newNode.next = a.next;
			size = size + 1;
		}else{
			node newNode = new node();
			newNode.data = data;
			Head.next = newNode;
			size = size + 1;
		}
		node seach = Head.next;
		int i = 1;
			/*while(seach.next != null) ���X�g�̍Ōォ�ǂ����̔���*/
			while(seach.next != null)
			{
				seach.num = i;
				i++;
				seach = seach.next;
			}
	}

/*method double cal_sum()*/
/*�ړI:���X�g����data�̍��v�l���v�Z����*/

	public double cal_sum()
	{
		double sum = 0.0;
		for(node p = Head.next; p!=null; p=p.next)
		{
			sum = sum + p.data;
		}
		return sum;
	}

/*method double cal_dev(daouble ave)*/
/*�ړI:���X�g���̈�����ave��p���āA�W���΍����o�͂���*/

	public double cal_dev(double ave)
	{
		double dev = 0.0;
		double gap = 0.0;
		int n = 0;
		for(node p = Head.next; p!=null; p=p.next)
		{
			gap = gap + (p.data - ave)*(p.data - ave);
			n++;
		}

		return Math.sqrt(gap/(n-1));
	}

/*method int list_size()*/
/*�ړI:���X�g�̑傫�����o�͂���*/

	public int list_size()
	{
		return size;
	}

/*void show_list()*/
/*�ړI:���X�g�̒��g��\������*/

	public void show_list()
	{
		/*if(Head.next != null) ���X�g�̒��g���󂩂ǂ����̔���*/
		if(Head.next == null)
		{
			System.out.println("���X�g����ł�");
		}else{
			node show = Head.next;
				/*while(seach.next != null) ���X�g�̍Ōォ�ǂ����̔���*/
				while(show.next != null)
				{
					System.out.println(show.data);
					show = show.next;
				}
		}
	}

}

/*class Main*/
class Main
{
	public static void main(String[] args)
	{
		linkedlist list = new linkedlist();
			try
			{
				File file = new File("c:\\Users\\Panasonic\\Desktop\\���̓f�[�^.txt");
  				BufferedReader br = new BufferedReader(new FileReader(file));
  				String str;
					/*while((str = br.readLine()) != null) �ǂݍ��񂾃f�[�^���󔒂��ǂ����̔���*/
  					while((str = br.readLine()) != null)
					{
							double data = Double.parseDouble(str);
    							list.insert_node(data);
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
		System.out.println("���ρF"+ String.format("%.3f",list.cal_sum()/list.list_size()) +",�W���΍�:"+ String.format("%.3f",list.cal_dev((list.cal_sum()/list.list_size()))));
	}
}



