package seu.util.filterInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FilterInfoUtil {
	//kmp�㷨��ȡ�Ӵ��Ƿ������ܴ�
	public int kmp(StringBuffer str, String dest,int[] next){//str�ı���  dest ģʽ��
        for(int i = 0, j = 0; i < str.length(); i++){
            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;
            }
        }
        return -1;
    }
    public int[] kmpnext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for(int i = 1,j = 0; i < dest.length(); i++){
            while(j > 0 && dest.charAt(j) != dest.charAt(i)){
                j = next[j - 1];
            }
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    //��ȡ��ǰĿ¼��badkey.txt�ļ����ݻ�ȡString��
    public String getBadkeysFromFile() throws IOException{
    	char[] cbuf=new char[80];
        File file = new File(FilterInfoUtil.class.getResource("badkey.txt").getPath().substring(1).replace("%20", " ").replace("/", "\\").toString());
		BufferedReader br = new BufferedReader(new FileReader(file));
		br.read(cbuf);
		String badkeys = String.valueOf(cbuf);
		return badkeys;
    }
    //�ָ��ļ����� ����arraylist��
    public ArrayList<String> spiltBadkeys(String badkeys){
    	ArrayList<String> badkey = new ArrayList<>();
    	String []keyString=badkeys.split(";");
    	for(String b:keyString){
    		System.out.println(b);
    		badkey.add(b);
    	}
    	System.out.println(badkey.toString());
    	return badkey;
    }
    //���˲�����Ϣ
    public StringBuffer filterBadInfo(StringBuffer str) throws IOException{
    	String badkeys=getBadkeysFromFile();
    	ArrayList<String> badkey = new ArrayList<>();
    	badkey = spiltBadkeys(badkeys);
        for(String s:badkey){
        	int[] next = kmpnext(s);
            int res = kmp(str, s,next);
            if((res!=-1)&&(res<str.length())){
            	System.out.println("�������д�!");
            	StringBuffer c = new StringBuffer("");
            	for(int i = 0; i<s.length();i++){
            		c.append("*");
            	}
            	str.replace(res, res+s.length(), new String(c));
            }
        }
        System.out.println(str);
        return str;
    }
}
