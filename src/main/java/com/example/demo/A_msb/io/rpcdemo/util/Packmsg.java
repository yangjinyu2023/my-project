package com.example.demo.A_msb.io.rpcdemo.util;

import com.example.demo.A_msb.io.rpcdemo.rpc.protocol.MyContent;
import com.example.demo.A_msb.io.rpcdemo.rpc.protocol.Myheader;

/**
 * @author: 马士兵教育
 * @create: 2020-07-19 20:47
 */
public class Packmsg {

    Myheader header;
    MyContent content;

    public Myheader getHeader() {
        return header;
    }

    public void setHeader(Myheader header) {
        this.header = header;
    }

    public MyContent getContent() {
        return content;
    }

    public void setContent(MyContent content) {
        this.content = content;
    }

    public Packmsg(Myheader header, MyContent content) {
        this.header = header;
        this.content = content;
    }
}
