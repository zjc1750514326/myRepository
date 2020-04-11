package com.zjc.concurrent.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author - zjc
 * @Description -Nio客户端
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/9 19:47
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));

        while (!socketChannel.finishConnect()){
            /** 没有连接则一直等待 */
            Thread.yield();
        }

        // 获取键盘输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String msg = scanner.nextLine();
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        while (byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }

        // 读取响应
        System.out.println("收到服务端响应:");
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);

        while (socketChannel.isConnected() && socketChannel.read(requestBuffer) != -1){
            // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
            if (requestBuffer.position() > 0) break;
        }

        // 设置为读取模式
        requestBuffer.flip();

        // 创建读取的容器字节数组，长度为buff的限制长度
        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        System.out.println(new String(content));
        scanner.close();
        socketChannel.close();
    }
}
