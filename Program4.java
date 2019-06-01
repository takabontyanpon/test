/* 開始日時:2019/5/28 14:26 */
/* 機能:デスクトップ上のデータから、正規分布に従ったプロキシサイズを表示する*/

/*再利用:class linkedlist*/
/*導入:java.io.File, java.io.FileReader, java.io.BufferedReader, java.io.FileNotFoundException, java.io.IOException*/
/*構成:class node, class linkedkist, class calculate, class readfile, class Main*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/*class node*/
/*add 2LOC*/
class node
{
	public node next;
	public String class_name;
	public double data;
	public double methods;
}

/*class linkedlist*/
class linkedlist
{
	node Head;
	int size = 0;

	public linkedlist()
	{
		Head = new node();
	}

/*method void insert_node(node node1)*/
/*目的:リストの初めにnode1を挿入する*/

	public void insert_node(node Node)
	{
		/*if(Head.next != null) リストの中身が空かどうかの判定*/
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
}

/*class calculate*/
class calculate
{
	public double cal_ave(linkedlist list)
	{
		double sum = 0.0;
		/*for(node p = list.Head.next; p!=null; p=p.next) リストの最後までの繰り返し*/
			for(node p = list.Head.next; p!=null; p=p.next)
			{
				sum = sum + Math.log(p.data/p.methods);
			}
		return sum/list.size;
	}

	public double sum_square(linkedlist list)
	{
		double sum = 0.0;
		/*for(node p = list.Head.next; p!=null; p=p.next) リストの最後までの繰り返し*/
			for(node p = list.Head.next; p!=null; p=p.next)
			{
				sum = sum + p.data*p.data;
			}
		return sum;
	}

	/*method double cal_sigma(linkedlist list)*/
	/*目的:リストの標準偏差を計算する*/
	public double cal_sigma(linkedlist list)
	{
		double sigma2 = 0.0;
		/*for(node p = list.Head.next; p!=null; p=p.next) リストの最後までの繰り返し*/
			for(node p = list.Head.next; p!=null; p=p.next)
			{
				sigma2 = sigma2 + (Math.log(p.data/p.methods)*Math.log(p.data/p.methods) - 2*this.cal_ave(list)*Math.log(p.data/p.methods) + this.cal_ave(list)*this.cal_ave(list));
			}
		return Math.sqrt(sigma2/(list.size - 1));	
	}

	/*method double[] cal_range(linkedlist list)*/
	/*目的:リストのデータから正規分布に従ったプロキシサイズを算出する*/
	public double[] cal_range(linkedlist list)
	{
		double proxy_size[] = new double[5];
		proxy_size[0] = Math.exp(this.cal_ave(list) - 2*this.cal_sigma(list));
		proxy_size[1] = Math.exp(this.cal_ave(list) - this.cal_sigma(list));
		proxy_size[2] = Math.exp(this.cal_ave(list)); 
		proxy_size[3] = Math.exp(this.cal_ave(list) + this.cal_sigma(list));
		proxy_size[4] = Math.exp(this.cal_ave(list) + 2*this.cal_sigma(list));
		return proxy_size;
	}
}

/*class readfile*/
class readfile
{
	/*method linkedlist read_file(String name)*/
	/*目的:ファイルを読み込んで、データをリストに挿入する*/
	public linkedlist read_file(String file_name)
	{
		linkedlist list = new linkedlist();
		try
		{
			File file = new File("c:\\Users\\Panasonic\\Desktop\\"+file_name);
  			BufferedReader br = new BufferedReader(new FileReader(file));
  			String str;
			/*while((str = br.readLine()) != null) 読み込んだ行がファイルの終わりかどうかの判定*/
  			while((str = br.readLine()) != null)
				{
					String[] Array = str.split(" ");
					String class_name = Array[0];
					double data1 = Double.parseDouble(Array[1]);
					double data2;
					if(Array.length == 2)
					{
						data2 = 1.0;
					}else{
						data2 = Double.parseDouble(Array[2]);
					}
    					node Node = this.make_node(data1, data2);
					Node.class_name = class_name;
					list.insert_node(Node);
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
		return list;
	}

	/*method node make_node(double data1, double data2)*/
	/*目的:data1とdata2の値が入ったnodeを作成する*/
	public node make_node(double data1 ,double data2)
	{
		node Node = new node();
		Node.data = data1;
		Node.methods = data2;
		return Node;
	}
}

/*class Main*/
class Main
{
	public static void main(String[] args)
	{

		linkedlist list = new linkedlist();
		calculate calculater = new calculate();
		readfile readfile = new readfile();
		double[] proxy_size = new double[5];
		list = readfile.read_file(args[0]);
		proxy_size = calculater.cal_range(list);
		for(int i = 0; i < proxy_size.length ; i++)
		{
			System.out.print(String.format("%.5f",proxy_size[i])+" ");
		}
	}
}