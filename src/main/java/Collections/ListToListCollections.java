package Collections;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author hx
 * @version 1.0
 * @date 2022/12/16 11:01
 */
public class ListToListCollections {
    //根据list1排序顺序，对list2进行排序

    public List<Object> setListOrder(List<Object> list1,List<Object> list2){

        Collections.sort(list2,(((o1, o2) -> {
            int i=0;
            try {
                int io1 = list1.indexOf(o1.getClass().getDeclaredConstructor());
                int io2 = list1.indexOf(o2.getClass().getDeclaredConstructor());

                if(io1!=-1){
                    io1 = list2.size()-io1;
                }
                if(io2!=-1){
                    io2 = list2.size()-io2;
                }
                i = io1-io2;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return i;
        })));

        return list2;
    }
}
