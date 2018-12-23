package question7;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


class SearchServlet {
    public String getURLContent(String urlStr){
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String temp = "";
                while((temp = reader.readLine())!=null){
                    sb.append(temp+'\n');
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    public static void main(String[] args) throws Exception{
        SearchServlet test = new SearchServlet();
        String result = test.getURLContent("http://jwzx.cqupt.edu.cn/data/json_StudentSearch.php?searchKey="+request.getparameter("class"));
        JSONObject obj = JSONObject.fromObject(result);
        JSONArray obj2 = JSONArray.fromObject(obj.get("returnData"));
        Map<String, Object> map = new HashMap<>();
        for (int i=0;i<obj2.size();i++)
        {
            JSONObject json  = (JSONObject) obj2.get(i);
            Set<String> keys =  json.keySet();
            for (String key:keys){
                Object value = json.get(key);
                map.put(key,value);
            }
            Connection con = JDBCUtil.getConnection();
            SQLInsert main = new SQLInsert(con);
            main.insert(map.get("xh"),map.get("xm"),
                    map.get("xmEn"),map.get("xb"),
                    map.get("bj"),map.get("zyh"),
                    map.get("zym"),map.get("yxh"),
                    map.get("yxm"),
                    map.get("nj"),map.get("csrq"),
                    map.get("xjzt"),map.get("rxrq"),
                    map.get("yxmen"),map.get("zymen"),map.get("xz"),map.get("mz"));
            System.out.println(map);
        }

//        String result2= obj2.toString().replaceAll("[\\[]","\\}").replaceAll("[\\]]","\\}");
//        System.out.println(result2);
//        JSONObject obj3 = JSONObject.fromObject(result2);
//        System.out.println(obj3);
//        System.out.println(obj3.get("xmEn"));

    }
}

