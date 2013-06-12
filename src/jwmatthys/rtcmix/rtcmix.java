/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package jwmatthys.rtcmix;

public class rtcmix {
  public static int rtcmixmain() {
    return rtcmixJNI.rtcmixmain();
  }

  public static int pd_rtsetparams(float sr, int nchans, int vecsize, SWIGTYPE_p_float mm_inbuf, SWIGTYPE_p_float mm_outbuf, String mm_errbuf) {
    return rtcmixJNI.pd_rtsetparams(sr, nchans, vecsize, SWIGTYPE_p_float.getCPtr(mm_inbuf), SWIGTYPE_p_float.getCPtr(mm_outbuf), mm_errbuf);
  }

  public static int parse_score(String thebuf, int buflen) {
    return rtcmixJNI.parse_score(thebuf, buflen);
  }

  public static void pullTraverse() {
    rtcmixJNI.pullTraverse();
  }

  public static int check_bang() {
    return rtcmixJNI.check_bang();
  }

  public static int check_vals(SWIGTYPE_p_float thevals) {
    return rtcmixJNI.check_vals(SWIGTYPE_p_float.getCPtr(thevals));
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

  public static void buffer_set(String bufname, SWIGTYPE_p_float bufstart, int nframes, int nchans, int modtime) {
    rtcmixJNI.buffer_set(bufname, SWIGTYPE_p_float.getCPtr(bufstart), nframes, nchans, modtime);
  }

  public static void flush_sched() {
    rtcmixJNI.flush_sched();
  }

}