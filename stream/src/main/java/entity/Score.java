package entity;

import lombok.Data;

/**
 * Created by zhuchunliu on 2017/8/29.
 */
@Data
public class Score {
    private int index;
    private String name;

    public Score(int index,String name){
        this.index = index;
        this.name =name ;
    }

    public String toString(){
        return " name: "+name+"  index: "+index;
    }
}
