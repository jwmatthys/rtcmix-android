/* File : rtcmix.i */
%module rtcmix
%include arrays_java.i

%apply float[] {float *};
%apply int[] {int *};

%inline %{
  extern int rtcmixmain();
  extern int pd_rtsetparams(float sr, int nchans, int vecsize, float *mm_inbuf, float *mm_outbuf, char *mm_errbuf);
  extern int parse_score(char *thebuf, int buflen);
  extern void pullTraverse();
  extern int check_bang();
  extern int check_vals(float thevals[]);
  extern int parse_dispatch(const char *funcname, const Arg arglist[], const int n_args, Arg *return_val);
  extern int check_error();
  extern void pfield_set(int inlet, float pval);
  extern void buffer_set(char *bufname, float *bufstart, int nframes, int nchans, int modtime);
  extern void flush_sched();
%}
