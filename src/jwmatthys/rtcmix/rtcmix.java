/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package jwmatthys.rtcmix;

public class rtcmix {
  public static SWIGTYPE_p_int new_intArray(int nelements) {
    long cPtr = rtcmixJNI.new_intArray(nelements);
    return (cPtr == 0) ? null : new SWIGTYPE_p_int(cPtr, false);
  }

  public static void delete_intArray(SWIGTYPE_p_int ary) {
    rtcmixJNI.delete_intArray(SWIGTYPE_p_int.getCPtr(ary));
  }

  public static int intArray_getitem(SWIGTYPE_p_int ary, int index) {
    return rtcmixJNI.intArray_getitem(SWIGTYPE_p_int.getCPtr(ary), index);
  }

  public static void intArray_setitem(SWIGTYPE_p_int ary, int index, int value) {
    rtcmixJNI.intArray_setitem(SWIGTYPE_p_int.getCPtr(ary), index, value);
  }

  public static SWIGTYPE_p_float new_floatArray(int nelements) {
    long cPtr = rtcmixJNI.new_floatArray(nelements);
    return (cPtr == 0) ? null : new SWIGTYPE_p_float(cPtr, false);
  }

  public static void delete_floatArray(SWIGTYPE_p_float ary) {
    rtcmixJNI.delete_floatArray(SWIGTYPE_p_float.getCPtr(ary));
  }

  public static float floatArray_getitem(SWIGTYPE_p_float ary, int index) {
    return rtcmixJNI.floatArray_getitem(SWIGTYPE_p_float.getCPtr(ary), index);
  }

  public static void floatArray_setitem(SWIGTYPE_p_float ary, int index, float value) {
    rtcmixJNI.floatArray_setitem(SWIGTYPE_p_float.getCPtr(ary), index, value);
  }

  public static int rtcmixmain() {
    return rtcmixJNI.rtcmixmain();
  }

  public static int droid_rtsetparams(float sr, int nchans, int vecsize) {
    return rtcmixJNI.droid_rtsetparams(sr, nchans, vecsize);
  }

  public static int parse_score(String thebuf, int buflen) {
    return rtcmixJNI.parse_score(thebuf, buflen);
  }

  public static float[] pullTraverse(float[] inbuf) {
    return rtcmixJNI.pullTraverse(inbuf);
  }

  public static int check_bang() {
    return rtcmixJNI.check_bang();
  }

  public static int check_vals(float[] thevals) {
    return rtcmixJNI.check_vals(thevals);
  }

  public static int parse_dispatch(String funcname, SWIGTYPE_p_Arg arglist, int n_args, SWIGTYPE_p_Arg return_val) {
    return rtcmixJNI.parse_dispatch(funcname, SWIGTYPE_p_Arg.getCPtr(arglist), n_args, SWIGTYPE_p_Arg.getCPtr(return_val));
  }

  public static int check_error() {
    return rtcmixJNI.check_error();
  }

  public static void pfield_set(int inlet, float pval) {
    rtcmixJNI.pfield_set(inlet, pval);
  }

  public static void buffer_set(String bufname, float[] bufstart, int nframes, int nchans, int modtime) {
    rtcmixJNI.buffer_set(bufname, bufstart, nframes, nchans, modtime);
  }

  public static void flush_sched() {
    rtcmixJNI.flush_sched();
  }

  public static float[] new_floatp() {
    return rtcmixJNI.new_floatp();
  }

  public static float[] copy_floatp(float value) {
    return rtcmixJNI.copy_floatp(value);
  }

  public static void delete_floatp(float[] self) {
    rtcmixJNI.delete_floatp(self);
  }

  public static void floatp_assign(float[] self, float value) {
    rtcmixJNI.floatp_assign(self, value);
  }

  public static float floatp_value(float[] self) {
    return rtcmixJNI.floatp_value(self);
  }

}
