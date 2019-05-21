
/* 開始日時:2019/4/17 13:00 */
/* 機能:デスクトップ上の入力データの数値を呼び出し、連結リストを作成する。*/

/*再利用:class node, class linkedlist*/
/*導入:java.io.File, java.io.FileReader, java.io.BufferedReader, java.io.FileNotFoundException, java.io.IOException*/
/*構成:class node, class linkedkist, class Main*/

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
/*目的:引数の数字をnodeのnumに設定する*/

	public void set_num(int num)
	{
		this.num = num;
	}

/*method void set_data(double data)*/
/*目的:引数の数字をnodeのdataに設定する*/

	public void set_data(double data)
	{
		this.data = data;
	}

/*method void set_next(node next)*/
/*目的:引数のnodeをnodeのnextに設定する*/

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
/*目的:リストの初めに引数のdataが入ったnodeを挿入する*/

	public void insert_node(double data)
	{
		/*if(Head.next != null) リストの中身が空かどうかの判定*/
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
			/*while(seach.next != null) リストの最後かどうかの判定*/
			while(seach.next != null)
			{
				seach.num = i;
				i++;
				seach = seach.next;
			}
	}

/*method double cal_sum()*/
/*目的:リスト内のdataの合計値を計算する*/

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
/*目的:リスト内の引数のaveを用いて、標準偏差を出力する*/

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
/*目的:リストの大きさを出力する*/

	public int list_size()
	{
		return size;
	}

/*void show_list()*/
/*目的:リストの中身を表示する*/

	public void show_list()
	{
		/*if(Head.next != null) リストの中身が空かどうかの判定*/
		if(Head.next == null)
		{
			System.out.println("リストが空です");
		}else{
			node show = Head.next;
				/*while(seach.next != null) リストの最後かどうかの判定*/
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
				File file = new File("c:\\Users\\Panasonic\\Desktop\\入力データ.txt");
  				BufferedReader br = new BufferedReader(new FileReader(file));
  				String str;
					/*while((str = br.readLine()) != null) 読み込んだデータが空白がどうかの判定*/
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
		System.out.println("平均："+ String.format("%.3f",list.cal_sum()/list.list_size()) +",標準偏差:"+ String.format("%.3f",list.cal_dev((list.cal_sum()/list.list_size()))));
	}
}



