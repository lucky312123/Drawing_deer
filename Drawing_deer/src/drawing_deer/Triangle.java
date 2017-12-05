
package drawing_deer;

import java.awt.image.BufferedImage;

public class Triangle {
    
     public static BufferedImage line_v2(int x_0, int y_0, int x_1, int y_1, BufferedImage img){
        int driffX,driffY;

        if(x_0 > x_1 || y_0 > y_1)
        {
            int cache = x_0;
            x_0 = x_1;
            x_1 = cache;
            
            cache = y_0;
            y_0 = y_1;
            y_1 = cache;
        }
        driffX = x_1 - x_0;
        driffY = y_1 - y_0;
        
        
        if(driffX > driffY){
        for(int x = x_0; x <= x_1 ; x++)
            {
                float t = (float)(x-x_0)/(float)(x_1-x_0);
                int y =   (int) Math.round(y_0 + (y_1 - y_0)*t);
                img.setRGB(x, y, 0xffffffff);
            }
        }
        else{
            for(int y = y_0; y <= y_1 ; y++)
            {
                float t = (float)(y-y_0)/(float)(y_1-y_0);
                int x =   (int) Math.round(x_0 + (x_1 - x_0)*t);
                img.setRGB(x, y, 0xffffffff);
            }
        }
       
        return img;
    }
    
    public  BufferedImage tricnycle(int ax, int ay, int bx, int by, int cx, int cy, BufferedImage img){
        line_v2(ax, ay, bx, by, img);
        line_v2(bx, by, cx, cy, img);
        line_v2(ax, ay, cx, cy, img);        
        return img;
    }
    
}
