
/* 開始日時: 2019/04/26 13:30*/
/* 機能:Java言語仕様で記述されたプログラムの行数や構成クラスのメソッド数をカウントし、表示する。*/

/*再利用:class node, class linkedlist*/
/*導入:java.io.File, java.io.FileReader, java.io.BufferedReader, java.io.FileNotFoundException, java.io.IOException*/
/*構成:class node, class linkedkist, class counter, class Main*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/*class node*/
class node
{
	public node next;
	public int lines; 
	public int methods; 
	public String class_name; 

/*method void set_lines(int lines)*/
/*目的:引数の数字をnodeのlinesに設定する*/

	public void set_lines(int lines)
	{
		this.lines = lines; 
	}

/*method void set_methods(int methods)*/
/*目的:引数の数字をnodeのmethodsに設定する*/

	public void set_methods(int methods)
	{
		this.methods = methods; 
	}

/*method void set_class_name(string class_name)*/
/*目的:引数の数字をnodeのclass_nameに設定する*/

	public void set_class_name(String class_name)
	{
		this.class_name = class_name;
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
	node Head = new node();
	int size = 0;

/*method void insert_node(node c)*/
/*目的:リストの初めにnodeを挿入する*/

	public void insert_node(node c)
	{
		if(Head.next != null)
		{
			node a = new node();
			a.next = Head.next;
			Head.next = c;
			c.next = a.next;
			size = size + 1;
		}else{
			Head.next = c;
			size = size + 1;
		}
		node seach = Head.next;
			while(seach.next != null)
			{
				seach = seach.next;
			}
	}

/*method int est_all_lines()*/
/*目的:リスト内のlinesの合計値を計算する*/

	public int est_all_lines()
	{
		int all_lines = 0;
		for(node p = Head.next; p!=null; p=p.next)
		{
			all_lines = all_lines + p.lines;
		}
		return all_lines;
	}

/*method void show_list()*/
/*目的:リストの中身を表示する*/

	public void show_list()
	{
		/*if(Head.next != null) リストの中身が空かどうかの判定*/
		if(Head.next == null)
		{
			System.out.println("リストが空です");
		}else{
			node show = Head.next;
			int all_lines = 0;
				/*for(int i = 0; i < size; i++) リストの最後かどうかの判定*/
				for(int i = 0; i < size; i++)
				{
					System.out.println(show.class_name + "      " + show.methods + "      " + show.lines+"LOC");
					all_lines = all_lines + show.lines;
					show = show.next;
				}
			System.out.println("合計       " + all_lines+"LOC");
		}
	}

	public void set_lines(int node_count, int lines)
	{
		node p = Head.next;
		for(int i = 0; i < node_count; i++)
		{
			p = p.next;
		}
		p.lines = lines;
	}

	public void set_methods(int node_count, int methods)
	{
		node p = Head.next;
		for(int i = 0; i < node_count; i++)
		{
			p = p.next;
		}
		p.methods = methods;
	}
}

/*class counter*/
class counter
{
	int number_class = 0;
	int methods = 0;
	int lines = 0;

/*method void count_lines(int line_belong, linkedlist list)*/
/*目的:nodeの中身のパラメータを変更する*/

	public void count_lines(int line_belong, linkedlist list)
	{
		switch(line_belong)
		{
		case 0:
			break;
		case 1:
			number_class++;
			list.set_lines(list.size - number_class, lines);
			list.set_methods(list.size - number_class, methods);
			methods = 0;
			lines = 0;
			break;
		case 2:
			methods++;
			break;
		case 3:
			lines++;
			break;
		case 4:
			lines++;
			break;
		case 5:
			number_class++;
			list.set_lines(list.size - number_class, lines);
			list.set_methods(list.size - number_class, methods);
			break;
		default:
			
		}
	}

/*method int judge_line(String line)*/
/*目的:入力された行の分別をする*/

	public int judge_line(String line)
	{
		String[] keyword = {"public","private","protected","while","if","for","else","try","catch","import","class","switch","case","default"};
		String[] end_symbol = {"{","}",";"};
		String comment_method = "/*method";
		String comment_class = "/*class";
		String comment = "/*";
		/*if(line.length() == 0) 空白がどうかの判定*/
		if(line.length() == 0)return 0;
		/*if(line.startsWith(comment_class) != false) クラスに関するコメントがあるかの判定*/
		if(line.startsWith(comment_class) != false)return 1;
		/*if(line.indexOf(comment_method) != -1) メソッドに関するコメントがあるかの判定*/
		if(line.indexOf(comment_method) != -1)return 2;
		/*for(int i = 0; i < keyword.length; i++) キーワードが含まれているかの判定*/
		for(int i = 0; i < keyword.length; i++)
		{
			/*if(line.indexOf(keyword[i]) != -1) キーワードが含まれているかの判定*/
			if(line.indexOf(keyword[i]) != -1)
			{
				/*if(line.indexOf(comment) != -1) コメント化された行にキーワードが含まれているかの判定*/
				if(line.indexOf(comment) != -1)
				{
					return 6;
				}
				return 3;
			}
		}
		/*for(int j = 0; j < end_symbol.length; j++) 中括弧等のエンドシンボルが含まれているかの判定*/
		for(int j = 0; j < end_symbol.length; j++)
		{
			/*if(line.indexOf(end_symbol[j]) != -1) エンドシンボルが含まれているかの判定*/
			if(line.indexOf(end_symbol[j]) != -1)return 4;
		}
		/*if(line == null) ファイルの終わりの行かの判定*/
		if(line == null)return 5;
		return 6;
	}

/*method void make_class_node(String line, linkedlist list)*/
/*目的:クラス名を抜き出して、nodeを作成と挿入する*/

	public void make_class_node(String line, linkedlist list)
	{
		if(list.size == 0)
		{
			node first = new node();
			first.class_name = "first";
			list.insert_node(first);
		}
		String class_name;
		node class_node = new node();
		String str = line.substring(8);
		String str2 = str.replace("*/","");
		class_node.class_name = str2;
		list.insert_node(class_node);
	}
}

/*class Main*/
class Main
{
	public static void main(String[] args)
	{
		linkedlist list = new linkedlist();
		counter counter = new counter();
			try
			{
				File file = new File("c:\\Users\\Panasonic\\Desktop\\"+args[0]+".java");
  				BufferedReader br = new BufferedReader(new FileReader(file));
  				String str;
					/*while((str = br.readLine()) != null) 読み込んだ行がファイルの終わりかどうかの判定*/
  					while((str = br.readLine()) != null)
					{
						int kind_line = counter.judge_line(str);
    						if(kind_line == 1)
						{
							counter.make_class_node(str, list);
						}
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

			try
			{
				File file = new File("c:\\Users\\Panasonic\\Desktop\\"+args[0]+".java");
  				BufferedReader br = new BufferedReader(new FileReader(file));
  				String str;
					/*while((str = br.readLine()) != null) 読み込んだ行がファイルの終わりかどうかの判定*/
  					while((str = br.readLine()) != null)
					{
						int kind_line = counter.judge_line(str);
    						counter.count_lines(kind_line,list);
					}
				int kind_line = 5;
    				counter.count_lines(kind_line,list);
				System.out.println("class name     method     size");
				System.out.println("===============================");
				list.show_list();
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
	}
}