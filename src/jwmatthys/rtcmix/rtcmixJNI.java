/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package jwmatthys.rtcmix;

public class rtcmixJNI {
  public final static native long new_intArray(int jarg1);
  public final static native void delete_intArray(long jarg1);
  public final static native int intArray_getitem(long jarg1, int jarg2);
  public final static native void intArray_setitem(long jarg1, int jarg2, int jarg3);
  public final static native long new_floatArray(int jarg1);
  public final static native void delete_floatArray(long jarg1);
  public final static native float floatArray_getitem(long jarg1, int jarg2);
  public final static native void floatArray_setitem(long jarg1, int jarg2, float jarg3);
  public final static native int rtcmixmain();
  public final static native int droid_rtsetparams(float jarg1, int jarg2, int jarg3);
  public final static native int parse_score(String jarg1, int jarg2);
  public final static native float[] pullTraverse(float[] jarg1);
  public final static native int check_bang();
  public final static native int check_vals(float[] jarg1);
  public final static native int parse_dispatch(String jarg1, long jarg2, int jarg3, long jarg4);
  public final static native int check_error();
  public final static native void pfield_set(int jarg1, float jarg2);
  public final static native void buffer_set(String jarg1, float[] jarg2, int jarg3, int jarg4, int jarg5);
  public final static native void flush_sched();
  public final static native float[] new_floatp();
  public final static native float[] copy_floatp(float jarg1);
  public final static native void delete_floatp(float[] jarg1);
  public final static native void floatp_assign(float[] jarg1, float jarg2);
  public final static native float floatp_value(float[] jarg1);
}
