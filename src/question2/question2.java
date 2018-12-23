package question2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class question2 {
    public static  void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil();
        Student[] users = fileUtil.parseStudents("D:\\Game\\kaoheproject\\src\\students.txt");
        int len = users.length;
        question2 hha = new question2();
        hha.search(users,len);

    }
public void  search(Student[] users, int len){
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < len; i++) {
        if (map.containsKey(users[i].getName())) {
            int tempCount = map.get(users[i].getName());
            map.put(users[i].getName(), ++tempCount);
        } else {
            map.put(users[i].getName(), 1);
        }
    }
    Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
    int count = 0;
    String maxCountWord = users[0].getName();
    while (it.hasNext()) {
        Map.Entry<String, Integer> en = it.next();
        int tempCount = en.getValue();
        if (tempCount > count) {
            count = tempCount;
            maxCountWord = en.getKey();
        }
    }
    System.out.println("最大众化的名字出现啦：" + maxCountWord + "出现" + count + "次！");

}



}



