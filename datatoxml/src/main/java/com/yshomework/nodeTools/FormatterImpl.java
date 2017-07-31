package com.yshomework.nodeTools;

import com.yshomework.Models.Node;
import com.yshomework.nodeTools.toolesInterface.Formatter;
import com.yshomework.nodeTools.toolesInterface.NodeFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

/**
 * Created by lubeeplant on 17-7-31.
 */
public class FormatterImpl implements Formatter {
    @Override
    public String format(Node node) {
        try {
        JAXBContext context = JAXBContext.newInstance(Node.class);    // 获取上下文对象
        Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进
//      marshaller.marshal(getSimpleDepartment(),System.out);   // 打印到控制台
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String xmlObj = new String(baos.toByteArray());         // 生成XML字符串
        marshaller.marshal(node, baos);
        return xmlObj;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
