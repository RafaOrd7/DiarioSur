
import java.util.Comparator;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Garri
 */
public class ValueComparator implements Comparator {

    Map _base;

    public ValueComparator(Map base) {
        _base = base;
    }

    public int compare(Object a, Object b) {
       if((Integer)_base.get(a)>(Integer)_base.get(b)){
           return 1;
       }else if((Integer)_base.get(a)<(Integer)_base.get(b)){
           return -1;
       }else{
           return 0;
       }
    }
}