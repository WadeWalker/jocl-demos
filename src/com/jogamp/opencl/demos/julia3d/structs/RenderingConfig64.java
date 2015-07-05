/* !---- DO NOT EDIT: This file autogenerated by com/sun/gluegen/JavaEmitter.java on Tue Feb 09 18:20:26 CET 2010 ----! */


package com.jogamp.opencl.demos.julia3d.structs;


class RenderingConfig64 extends RenderingConfig {

  private final Camera camera;

  public static int size() {
    return 116;
  }

  RenderingConfig64(java.nio.ByteBuffer buf) {
    super(buf);
    camera = Camera.create(accessor.slice(56, 60));
  }


  public RenderingConfig setWidth(int val) {
    accessor.setIntAt(4*0, val);
    return this;
  }

  public int getWidth() {
    return accessor.getIntAt(4*0);
  }

  public RenderingConfig setHeight(int val) {
    accessor.setIntAt(4*1, val);
    return this;
  }

  public int getHeight() {
    return accessor.getIntAt(4*1);
  }

  public RenderingConfig setSuperSamplingSize(int val) {
    accessor.setIntAt(4*2, val);
    return this;
  }

  public int getSuperSamplingSize() {
    return accessor.getIntAt(4*2);
  }

  public RenderingConfig setActvateFastRendering(int val) {
    accessor.setIntAt(4*3, val);
    return this;
  }

  public int getActvateFastRendering() {
    return accessor.getIntAt(4*3);
  }

  public RenderingConfig setEnableShadow(int val) {
    accessor.setIntAt(4*4, val);
    return this;
  }

  public int getEnableShadow() {
    return accessor.getIntAt(4*4);
  }

  public RenderingConfig setMaxIterations(int val) {
    accessor.setIntAt(4*5, val);
    return this;
  }

  public int getMaxIterations() {
    return accessor.getIntAt(4*5);
  }

  public RenderingConfig setEpsilon(float val) {
    accessor.setFloatAt(4*6, val);
    return this;
  }

  public float getEpsilon() {
    return accessor.getFloatAt(4*6);
  }

  public RenderingConfig setMu(float[] val) {
    accessor.setFloatsAt(4*7, val);
    return this;
  }

  public float[] getMu() {
    return accessor.getFloatsAt(4*7, new float[4]);
  }

  public RenderingConfig setLight(float[] val) {
    accessor.setFloatsAt(4*11, val);
    return this;
  }

  public float[] getLight() {
    return accessor.getFloatsAt(4*11, new float[3]);
  }

  public Camera getCamera() {
    return camera;
  }
}
