/* File : rtcmix.i */
%module rtcmix
%include arrays_java.i
%include carrays.i
%array_functions(int, intArray);
%array_functions(float, floatArray);
%include cpointer.i

%apply float[] {float *};
%apply int[] {int *};

%inline %{
  extern int rtcmixmain();
  extern int droid_rtsetparams(float sr, int nchans, int vecsize);
  extern int parse_score(char *thebuf, int buflen);
  extern float* pullTraverse(float* inbuf);
  extern int check_bang();
  extern int check_vals(float thevals[]);
  extern int parse_dispatch(const char *funcname, const Arg arglist[], const int n_args, Arg *return_val);
  extern int check_error();
  extern void pfield_set(int inlet, float pval);
  extern void buffer_set(char *bufname, float *bufstart, int nframes, int nchans, int modtime);
  extern void flush_sched();
%}

%pointer_functions(float, floatp);
