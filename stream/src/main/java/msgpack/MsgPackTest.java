package msgpack;

import entity.Teacher;
import org.msgpack.MessagePack;
import org.msgpack.template.CollectionTemplate;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuchunliu on 2017/8/24.
 */
public class MsgPackTest {
    public void convertObj() throws Exception{
        Teacher teacher = new Teacher("张老师",123);
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(teacher);
        Teacher teacherCon = messagePack.read(bytes,Teacher.class);
        System.err.println(teacherCon.toString());
    }

    public void convertList() throws Exception{

        List<String> list = new ArrayList<String>();
        list.add("abcd");
        list.add("123");

        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(list);
        List<String> listCon =
                messagePack.read(bytes, Templates.tList(Templates.TString));
        System.err.println(listCon.get(0)+"  "+list.get(1));

    }

    public static void main(String[] args) throws Exception{
        MsgPackTest test =  new MsgPackTest();
        test.convertObj();
        test.convertList();
    }



}
