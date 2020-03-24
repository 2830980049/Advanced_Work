package com.edu.Servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/CheckCode_Servlet")
public class CheckCode_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 120;
        int height = 30;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0,0,width,height);
        graphics.setColor(Color.white);
        graphics.drawRect(0,0,width-1,height-1);
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setFont(new Font("楷体",Font.BOLD,20));
        String words = "QWERTYUIOPALSKDJFHGZMXCNVBqpweorituylaksdjfghzmxncbv0123456789";
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        int x = 10;
        for(int i = 0; i < 4; i++){
            g2d.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
            int index = random.nextInt(words.length());
            char ch = words.charAt(index);
            str.append(ch);
            int angle = random.nextInt(60) - 30;
            double radian = angle * Math.PI / 180;
            g2d.rotate(radian,x,20);
            g2d.drawString(String.valueOf(ch),x,20);
            g2d.rotate(-radian,x,20);
            x += 30;
        }
        req.getSession().setAttribute("checkCode",str.toString());
        graphics.setColor(getRandColor(160,200));
        int x1,x2,y1,y2;
        for (int i = 0; i < 30; i++){
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1,y1,x1 + x2,x2 + y2);
        }
        graphics.dispose();
        g2d.dispose();
        System.out.println(str.toString());
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());
    }

        private Color getRandColor(int x,int y){
            Random random = new Random();
            if(x > 255)
                x = 255;
            if(y > 255)
                y = 255;
            int r = x + random.nextInt(y-x);
            int g = x + random.nextInt(y - x);
            int b = x + random.nextInt(y - x);
            return new Color(r,g,b);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
