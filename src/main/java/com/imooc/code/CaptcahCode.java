package com.imooc.code;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CaptcahCode {

    /**
     * 验证码的生成方法
     * @param response
     * @return
     */
    public static String drawImage(HttpServletResponse response){
        StringBuilder builder = new StringBuilder();
        //得到4位的随机码
        for(int i = 0; i<4; i++){
            builder.append(randomChar());
        }
        String code = builder.toString();

        int width = 70;
        int height = 25;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = bufferedImage.createGraphics();
        Font font = new Font("微软雅黑", Font.PLAIN, 20);
        Color color = new Color(0,0,0);
        g.setFont(font);
        g.setColor(color);
        g.setBackground(new Color(226,226,240));
        g.clearRect(0,0,width,height);

        FontMetrics fontMetrics = g.getFontMetrics();
        int x = (width-fontMetrics.stringWidth(code))/2;
        int y = (height - fontMetrics.getHeight())/2+fontMetrics.getAscent();

        g.drawString(code,x,y);
        g.dispose();

        try {
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }


    /**
     * 此方法用于生成随机字母和数字
     * @return
     */
    private static char randomChar(){
        String string = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789";
        Random random = new Random();
        return string.charAt(random.nextInt(string.length()));
    }
}
