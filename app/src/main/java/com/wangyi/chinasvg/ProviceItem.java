package com.wangyi.chinasvg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

public class ProviceItem {
    private Path path;

    /**
     * 绘制颜色
     */
    private  int drawColor;
    public ProviceItem(Path path) {
        this.path = path;
    }

    public void setDrawColor(int drawColor) {
        this.drawColor = drawColor;
    }
    void drawItem(Canvas canvas, Paint paint, boolean isSelect) {
        if(isSelect){
//            绘制内部的颜色
            paint.clearShadowLayer();
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(drawColor);
            canvas.drawPath(path, paint);
//            绘制边界

            paint.setStyle(Paint.Style.STROKE);
            int strokeColor = 0xFFD0E8F4;
            paint.setColor(strokeColor);
            canvas.drawPath(path, paint);
        }else {

            paint.setStrokeWidth(2);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setShadowLayer(8,0,0,0xffffff);
            canvas.drawPath(path,paint);

//            绘制边界
            paint.clearShadowLayer();
            paint.setColor(drawColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(2);
            canvas.drawPath(path, paint);
        }


    }

    public boolean isTouch(float x, float y) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);//todo 计算path所在区域，并将结果写入rect，如果整个path只包含0或1个点，将返回(0,0,0,0):
        Region region = new Region();//todo 该类的一个判断点是否在矩形区域内的函数
        region.setPath(path, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        return region.contains((int)x, (int)y);
    }
}
