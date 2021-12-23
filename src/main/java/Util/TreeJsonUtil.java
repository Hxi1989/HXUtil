package Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import io.fuse.cms.admin.vo.DepartmentTreeVo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TreeJsonUtil {

    public static void analysisJson(Object objJson, List<Long> longList) {
        //如果objJson为json数组
        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                analysisJson(objArray.get(i), longList);
            }
        } else if (objJson instanceof JSONObject) { //如果objJson为json对象
            JSONObject jsonObject = (JSONObject) objJson;
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                Object value = jsonObject.get(key); //value
                if (value instanceof JSONArray) { //如果value是数组
                    JSONArray objArray = (JSONArray) value;
                    analysisJson(objArray, longList);
                } else if (value instanceof JSONObject) { //如果value是json对象
                    analysisJson((JSONObject) value, longList);
                } else { //如果value是基本类型
//                    System.out.println("["+key+"]:"+(value==null?"":value.toString())+" ");
                    if (key.equals("id")) {
//                        sb.append(value==null?"":value.toString()+",");
                        longList.add(Long.valueOf(value == null ? "0" : value.toString()));
                    }
                }
            }
        } else { //objJson为基本类型
        }
    }

    /*public static DepartmentTreeVo recursion(Map<Long, List<DepartmentTreeVo>> maps, DepartmentTreeVo tree) {
        if (tree.getChildren() == null) {
            if (maps.get(tree.getId()) != null) {
                tree.setChildren(maps.get(tree.getId()));
                maps.remove(tree.getId());
                if (maps.size() > 0) {
                    recursion(maps, tree);
                }
            }
        } else {
            List<DepartmentTreeVo> metaTypeList = tree.getChildren();
            if (metaTypeList != null && metaTypeList.size() > 0) {
                for (DepartmentTreeVo meta : metaTypeList) {
                    recursion(maps, meta);
                }
            }
        }
        return tree;
    }*/

    /*public static List<DepartmentTreeVo> tree2list(List<DepartmentTreeVo> list) {
        List<DepartmentTreeVo> result = new ArrayList<>();
        for (DepartmentTreeVo test : list) {
            List<DepartmentTreeVo> c = test.getChildren();
            result.add(test);
            if (c!=null) {
                result.addAll(tree2list(c));
                test.setChildren(null);//
            }
        }
        return result;
    }*/

}
