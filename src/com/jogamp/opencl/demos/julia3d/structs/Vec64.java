/* !---- DO NOT EDIT: This file autogenerated by com/sun/gluegen/JavaEmitter.java on Tue Feb 09 18:20:26 CET 2010 ----! */


package com.jogamp.opencl.demos.julia3d.structs;


class Vec64 extends Vec {

  public static int size() {
    return 12;
  }

  Vec64(java.nio.ByteBuffer buf) {
    super(buf);
  }


  public Vec setX(float val) {
    accessor.setFloatAt(4*0, val);
    return this;
  }

  public float getX() {
    return accessor.getFloatAt(4*0);
  }

  public Vec setY(float val) {
    accessor.setFloatAt(4*1, val);
    return this;
  }

  public float getY() {
    return accessor.getFloatAt(4*1);
  }

  public Vec setZ(float val) {
    accessor.setFloatAt(4*2, val);
    return this;
  }

  public float getZ() {
    return accessor.getFloatAt(4*2);
  }
}
