package com.example.kingsoft.simpleexcel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kingsoft on 2017/11/17.
 *
 * 绘制表格
 */

public class FormView extends View {

    private int firstX = 0;//起始点x
    private int firstY = 0;//起始点y
    private int secondX = 80;//第二点x
    private int secondY = 50;//第二点y
    private int row = 1024;//行数
    private int col = 256;//列数
    private int firstColX = 80;//第一列的宽度
    private int secondColX = 150;//其它列的宽度
    private int rowY = 50;//每一行的高度

    public FormView(Context context) {
        super(context);
    }

    public FormView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //计算FormView的大小
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(firstColX+(col-1)*secondColX,row*rowY);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        drawForm(canvas);
    }

    //画表
    private void drawForm(Canvas canvas){
        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        int cellX = 0,cellY = 0,cellBX = 0,cellBY = 0;//单元格的位置
        for(int i = 0;i < col;i++){
            for(int j = 0;j < row;j++){
                //这里设置第一列的宽度和其他列的不一样并设置数字
                if(i == 0){
                    cellX = firstX + i * firstColX;
                    cellY = firstY + j * rowY;
                    cellBX = firstX + (i+1) * firstColX;
                    cellBY = firstY + (j+1) * rowY;
                    if(j > 0){
                        setText(canvas,cellX,cellY,cellBX,cellBY,j);
                    }
                }else{
                    cellX = secondX + (i-1) * secondColX;
                    cellY = secondY + (j-1) * rowY;
                    cellBX = secondX + i * secondColX;
                    cellBY = secondY + j * rowY;
                    if(j == 0 && i > 0){
                        setText(canvas,cellX,cellY,cellBX,cellBY,i);
                    }
                }
                canvas.drawRect(cellX,cellY,cellBX,cellBY,paint);
            }
        }
    }

    //设置第一行、第一列的数字
    private void setText(Canvas canvas,int cellX,int cellY,int cellBX,int cellBY,int j){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        int textSize = (cellBY - cellY) / 2;
        paint.setTextSize(textSize);
        int x = cellX + (cellBX - cellX) / 10;
        int y = cellBY - (cellBY - cellY) / 3;
        canvas.drawText(""+j,x,y,paint);
    }

    /*
    //点击事件
    public boolean onTouchEvent(MotionEvent event){
        float touchX = event.getX();
        float touchY = event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touchThePanel(touchX,touchY);
        }
        return super.onTouchEvent(event);
    }

    //判断点击事件落在哪一个格子
    public boolean touchThePanel(float x,float y){
        //第一行和第一列不能编辑
        if(x>secondX && y>secondY
                &&)
    }
    */
}
