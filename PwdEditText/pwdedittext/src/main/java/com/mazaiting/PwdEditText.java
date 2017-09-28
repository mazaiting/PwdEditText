package com.mazaiting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 可自定义长度的密码输入框
 * Created by mazaiting on 2017/9/26.
 */

public class PwdEditText extends EditText {
  /**默认的内容边缘边距*/
  private static final int mDefaultContMargin = 5;
  /**默认的分割线宽度*/
  private static final int mDefaultSplitLineWidth = 3;
  /**默认的边框颜色*/
  private int mBorderColor = 0xFFFFFFFF;
  /**默认的边框宽度*/
  private float mBorderWidth = 1.0f;
  /**默认的边缘叫圆滑程度*/
  private float mBorderRadius = 10.0f;
  /**默认的密码长度*/
  private  int mPasswordLength = 6;
  /**默认的密码颜色*/
  private int mPasswordColor = 0xFFEF5350;
  /**默认的密码宽度*/
  private float mPasswordWidth = 6.0f;
  /**默认的密码半径*/
  private float mPasswordRadius = 10.0f;
  /**密码画笔*/
  private Paint mPaintPassword = new Paint(Paint.ANTI_ALIAS_FLAG);
  /**边框画笔*/
  private Paint mPaintBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
  /**输入文本长度*/
  private int mTextLength;
  public PwdEditText(Context context) {
    this(context, null);
  }

  public PwdEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
    setDefault();
  }

  /**
   * 初始化各数值
   * @param context 上下文
   * @param attrs 属性
   */
  private void init(Context context, AttributeSet attrs) {
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PwdEditText);
    for (int i=0,indexCount = typedArray.getIndexCount();i< indexCount; i++){
      int index = typedArray.getIndex(i);
      if (index == R.styleable.PwdEditText_petBorderColor) {
        mBorderColor = typedArray.getColor(index, mBorderColor);
      } else if (index == R.styleable.PwdEditText_petBorderWidth) {
        mBorderWidth = typedArray.getDimension(index, mBorderWidth);
      } else if (index == R.styleable.PwdEditText_petBorderRadius) {
        mBorderRadius = typedArray.getDimension(index, mBorderRadius);
      } else if (index == R.styleable.PwdEditText_petPasswordLength) {
        mPasswordLength = typedArray.getInt(index, mPasswordLength);
      } else if (index == R.styleable.PwdEditText_petPasswordColor) {
        mPasswordColor = typedArray.getColor(index, mPasswordColor);
      } else if (index == R.styleable.PwdEditText_petPasswordWidth) {
        mPasswordWidth = typedArray.getDimension(index, mPasswordWidth);
      } else if (index == R.styleable.PwdEditText_petPasswordRadius) {
        mPasswordRadius = typedArray.getDimension(index, mPasswordRadius);
      }
    }
    typedArray.recycle();
  }

  /**
   * 设置默认的初始值
   */
  private void setDefault() {
    this.setBackgroundColor(Color.TRANSPARENT);
    mPaintBorder.setStrokeWidth(mBorderWidth);
    mPaintBorder.setColor(mBorderColor);

    mPaintPassword.setStrokeWidth(mPasswordWidth);
    mPaintPassword.setStyle(Paint.Style.FILL);
    mPaintPassword.setColor(mPasswordColor);

    // 设置输入的文本不可见--密码
    //this.setTransformationMethod(PasswordTransformationMethod.getInstance());
    // 设置输入的字符为透明
    this.setTextColor(Color.TRANSPARENT);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int width = getWidth();
    int height = getHeight();
    mPaintBorder.setStyle(Paint.Style.STROKE);
    // 外边框
    RectF rectF = new RectF(0, 0, width, height);
    //mPaintBorder.setColor(mBorderColor);
    //canvas.drawRoundRect(rectF, mBorderRadius, mBorderRadius, mPaintBorder);

    // 内容区
    RectF rectFIn = new RectF(rectF.left + mDefaultContMargin, rectF.top+mDefaultContMargin,
        rectF.right - mDefaultContMargin, rectF.bottom-mDefaultContMargin);
    mPaintBorder.setColor(Color.WHITE);
    canvas.drawRoundRect(rectFIn, mBorderRadius, mBorderRadius, mPaintBorder);

    // 分割线
    mPaintBorder.setColor(mBorderColor);
    mPaintBorder.setStrokeWidth(mDefaultSplitLineWidth);
    for (int i=1; i< mPasswordLength; i++){
      float x = width * i / mPasswordLength;
      canvas.drawLine(x, 2, x, height-2, mPaintBorder);
    }

    // 密码
    float cx, cy = height / 2;
    float half = width / mPasswordLength / 2;
    for (int i=0;i < mTextLength;i++){
      cx = width * i/ mPasswordLength + half;
      canvas.drawCircle(cx, cy, mPasswordWidth, mPaintPassword);
    }
  }

  @Override
  protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
    super.onTextChanged(text, start, lengthBefore, lengthAfter);
    this.mTextLength = text.toString().length();
    invalidate();
  }
  /**
   * 获取边框颜色
   * @return 获取边框颜色
   */
  public int getBorderColor() {
    return mBorderColor;
  }

  /**
   * 设置边框颜色
   * @param borderColor 边框颜色
   */
  public void setBorderColor(int borderColor) {
    this.mBorderColor = borderColor;
    mPaintBorder.setColor(borderColor);
    invalidate();
  }

  /**
   * 获取边框宽度
   * @return 获取边框宽度
   */
  public float getBorderWidth() {
    return mBorderWidth;
  }

  /**
   * 设置边框宽度
   * @param borderWidth 边框宽度
   */
  public void setBorderWidth(float borderWidth) {
    this.mBorderWidth = borderWidth;
    mPaintBorder.setStrokeWidth(borderWidth);
    invalidate();
  }

  /**
   * 获取边框半径
   * @return 边框半径
   */
  public float getBorderRadius() {
    return mBorderRadius;
  }

  /**
   * 设置边框半径
   * @param borderRadius 边框半径
   */
  public void setBorderRadius(float borderRadius) {
    this.mBorderRadius = borderRadius;
    invalidate();
  }

  /**
   * 获取密码长度
   * @return 密码长度
   */
  public int getPasswordLength() {
    return mPasswordLength;
  }

  /**
   * 获取密码长度
   * @param passwordLength 密码长度
   */
  public void setPasswordLength(int passwordLength) {
    this.mPasswordLength = passwordLength;
    invalidate();
  }

  /**
   * 获取密码颜色
   * @return 密码颜色
   */
  public int getPasswordColor() {
    return mPasswordColor;
  }

  /**
   * 设置密码颜色
   * @param passwordColor 密码颜色
   */
  public void setPasswordColor(int passwordColor) {
    this.mPasswordColor = passwordColor;
    mPaintPassword.setColor(passwordColor);
    invalidate();
  }

  /**
   * 设置密码宽度
   * @return 密码款都
   */
  public float getPasswordWidth() {
    return mPasswordWidth;
  }

  /**
   * 设置密码宽度
   * @param passwordWidth 密码宽度
   */
  public void setPasswordWidth(float passwordWidth) {
    this.mPasswordWidth = passwordWidth;
    mPaintPassword.setStrokeWidth(passwordWidth);
    invalidate();
  }

  /**
   * 设置密码半径
   * @return 密码半径
   */
  public float getPasswordRadius() {
    return mPasswordRadius;
  }

  /**
   * 设置密码半径
   * @param passwordRadius 密码半径
   */
  public void setPasswordRadius(float passwordRadius) {
    this.mPasswordRadius = passwordRadius;
    invalidate();
  }
}
