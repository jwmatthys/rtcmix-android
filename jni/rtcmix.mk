# build shared library

include $(CLEAR_VARS)

LOCAL_MODULE    := rtcmix
LOCAL_CFLAGS	:= -O3
LOCAL_CPPFLAGS	:= $(LOCAL_CFLAGS)
LOCAL_LDFLAGS	:= -Wl,--export-dynamic
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/conf
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix/gen
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix/heap
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/insts/jg/objlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/insts/stk/stklib
LOCAL_STATIC_LIBRARIES := snd gen obj rtaudio stk conf inlet pfbus minc rtheap
LOCAL_SRC_FILES := src/rtcmix/addcheckfunc.cpp \
src/rtcmix/addrtInst.cpp \
src/rtcmix/buffers.cpp \
src/rtcmix/bus_config.cpp \
src/rtcmix/checkInsts.cpp \
src/rtcmix/dispatch.cpp \
src/rtcmix/filter.cpp \
src/rtcmix/Instrument.cpp \
src/rtcmix/intraverse.cpp \
src/rtcmix/loader.cpp \
src/rtcmix/lfo.cpp \
src/rtcmix/minc_info.cpp \
src/rtcmix/DataFile.cpp \
src/rtcmix/mm_rtsetparams.cpp \
src/rtcmix/Option.cpp \
src/rtcmix/PField.cpp \
src/rtcmix/PFieldSet.cpp \
src/rtcmix/Random.cpp \
src/rtcmix/RefCounted.cpp \
src/rtcmix/RTcmix.cpp \
src/rtcmix/rtcmix_types.cpp \
src/rtcmix/rtcmix_wrappers.cpp \
src/rtcmix/rtgetin.cpp \
src/rtcmix/rtgetsamps.cpp \
src/rtcmix/rtinput.cpp \
src/rtcmix/rtoutput.cpp \
src/rtcmix/rtprofile.cpp \
src/rtcmix/rtsendsamps.cpp \
src/rtcmix/rtsetinput.cpp \
src/rtcmix/rtsetoutput.cpp \
src/rtcmix/rtsetparams.cpp \
src/rtcmix/rtwritesamps.cpp \
src/rtcmix/set_option.cpp \
src/rtcmix/table.cpp \
src/rtcmix/tableutils.cpp \
src/rtcmix/modtable.cpp \
src/rtcmix/ops.cpp \
src/rtcmix/utils.cpp \
src/rtcmix/rt_ug_intro.cpp \
src/rtcmix/connection.cpp \
src/rtcmix/converter.cpp \
src/rtcmix/monitor.cpp \
src/rtcmix/check_byte_order.c \
src/rtcmix/command_line.c \
src/rtcmix/funderflow.c \
src/rtcmix/getsample.c \
src/rtcmix/infile.c \
src/rtcmix/io.c \
src/rtcmix/load_utils.c \
src/rtcmix/merror.c \
src/rtcmix/message.c \
src/rtcmix/minc_functions.c \
src/rtcmix/minout.c \
src/rtcmix/m_system.c \
src/rtcmix/printsf.c \
src/rtcmix/profile.c \
src/rtcmix/resetamp.c \
src/rtcmix/sampfunc.c \
src/rtcmix/sfcopy.c \
src/rtcmix/sfprint.c \
src/rtcmix/sfstats.c \
src/rtcmix/sound.c \
src/rtcmix/soundio.c \
src/rtcmix/tempo.c \
src/rtcmix/ug_intro.c \
src/rtcmix/gen/fdump.c \
src/rtcmix/gen/floc.c \
src/rtcmix/gen/fnscl.c \
src/rtcmix/gen/fplot.c \
src/rtcmix/gen/fsize.c \
src/rtcmix/gen/gen10.c \
src/rtcmix/gen/gen17.c \
src/rtcmix/gen/gen18.c \
src/rtcmix/gen/gen1.c \
src/rtcmix/gen/gen20.c \
src/rtcmix/gen/gen24.c \
src/rtcmix/gen/gen25.c \
src/rtcmix/gen/gen2.c \
src/rtcmix/gen/gen3.c \
src/rtcmix/gen/gen4.c \
src/rtcmix/gen/gen5.c \
src/rtcmix/gen/gen6.c \
src/rtcmix/gen/gen7.c \
src/rtcmix/gen/gen9.c \
src/rtcmix/gen/makegen.c \
src/rtcmix/gen/modgens.c \
insts/base/MIX/MIX.cpp \
insts/base/WAVETABLE/WAVETABLE.cpp \
insts/std/AM/AM.cpp \
insts/std/AMINST/AMINST.cpp \
insts/std/CLAR/CLAR.cpp \
insts/std/CLAR/cfuncs.c \
insts/std/COMBIT/COMBIT.cpp \
insts/std/DEL1/DEL1.cpp \
insts/std/DELAY/DELAY.cpp \
insts/std/FIR/FIR.cpp \
insts/std/FMINST/FMINST.cpp \
insts/std/HOLO/HOLO.cpp \
insts/std/IIR/BUZZ.cpp \
insts/std/IIR/INPUTSIG.cpp \
insts/std/IIR/NOISE.cpp \
insts/std/IIR/PULSE.cpp \
insts/std/IIR/cfuncs.c \
insts/std/LPCPLAY/Complex.cpp \
insts/std/LPCPLAY/DataSet.cpp \
insts/std/LPCPLAY/LPCPLAY.cpp \
insts/std/LPCPLAY/bmultf.c \
insts/std/LPCPLAY/buzz.c \
insts/std/LPCPLAY/lpcheader.c \
insts/std/LPCPLAY/rand.c \
insts/std/LPCPLAY/setup.cpp \
insts/std/LPCPLAY/shift.c \
insts/std/LPCPLAY/stabilize.cpp \
insts/std/MARAGRAN/SGRANR.cpp \
insts/std/MARAGRAN/STGRANR.cpp \
insts/std/METAFLUTE/BSFLUTE.cpp \
insts/std/METAFLUTE/LSFLUTE.cpp \
insts/std/METAFLUTE/SFLUTE.cpp \
insts/std/METAFLUTE/VSFLUTE.cpp \
insts/std/MOVE/BASE.cpp \
insts/std/MOVE/MOVE.cpp \
insts/std/MOVE/PLACE.cpp \
insts/std/MOVE/cmixfuns.c \
insts/std/MOVE/common.cpp \
insts/std/MOVE/path.c \
insts/std/MOVE/setup.c \
insts/std/MOCKBEND/MOCKBEND.cpp \
insts/std/MULTICOMB/MULTICOMB.cpp \
insts/std/PANECHO/PANECHO.cpp \
insts/std/PHASER/PHASER.cpp \
insts/std/PVOC/PVFilter.cpp \
insts/std/PVOC/PVOC.cpp \
insts/std/PVOC/fft.c \
insts/std/PVOC/fold.c \
insts/std/PVOC/lpa.c \
insts/std/PVOC/lpamp.c \
insts/std/PVOC/makewindows.c \
insts/std/PVOC/overlapadd.c \
insts/std/PVOC/setup.cpp \
insts/std/REVMIX/REVMIX.cpp \
insts/std/SCRUB/SCRUB.cpp \
insts/std/SCULPT/SCULPT.cpp \
insts/std/STEREO/STEREO.cpp \
insts/std/STRUM/BEND.cpp \
insts/std/STRUM/BEND1.cpp \
insts/std/STRUM/FRET.cpp \
insts/std/STRUM/FRET1.cpp \
insts/std/STRUM/START.cpp \
insts/std/STRUM/START1.cpp \
insts/std/STRUM/VFRET1.cpp \
insts/std/STRUM/VSTART1.cpp \
insts/std/STRUM/delay.c \
insts/std/STRUM/delayclean.c \
insts/std/STRUM/delayset.c \
insts/std/STRUM/dist.c \
insts/std/STRUM/randfill.c \
insts/std/STRUM/squisher.c \
insts/std/STRUM/sset.c \
insts/std/STRUM/strum.c \
insts/std/STRUM2/STRUM2.cpp \
insts/std/STRUMFB/STRUMFB.cpp \
insts/std/TRANS/TRANS.cpp \
insts/std/TRANS/TRANS3.cpp \
insts/std/TRANSBEND/TRANSBEND.cpp \
insts/std/WAVESHAPE/WAVESHAPE.cpp \
insts/jg/BUTTER/BUTTER.cpp \
insts/jg/COMPLIMIT/COMPLIMIT.cpp \
insts/jg/CONVOLVE1/CONVOLVE1.cpp \
insts/jg/DCBLOCK/DCBLOCK.cpp \
insts/jg/DECIMATE/DECIMATE.cpp \
insts/jg/DISTORT/DISTORT.cpp \
insts/jg/ELL/ELL.cpp \
insts/jg/ELL/ellipse.c \
insts/jg/ELL/setell.c \
insts/jg/EQ/EQ.cpp \
insts/jg/FILTERBANK/FILTERBANK.cpp \
insts/jg/FILTSWEEP/FILTSWEEP.cpp \
insts/jg/FLANGE/FLANGE.cpp \
insts/jg/FOLLOWER/FOLLOWBUTTER.cpp \
insts/jg/FOLLOWER/FOLLOWER.cpp \
insts/jg/FOLLOWER/FOLLOWER_BASE.cpp \
insts/jg/FOLLOWER/FOLLOWGATE.cpp \
insts/jg/FREEVERB/FREEVERB.cpp \
insts/jg/FREEVERB/allpass.cpp \
insts/jg/FREEVERB/comb.cpp \
insts/jg/FREEVERB/delay.cpp \
insts/jg/FREEVERB/revmodel.cpp \
insts/jg/GRANSYNTH/GRANSYNTH.cpp \
insts/jg/GRANSYNTH/synthgrainstream.cpp \
insts/jg/GRANSYNTH/synthgrainvoice.cpp \
insts/jg/JCHOR/JCHOR.cpp \
insts/jg/JDELAY/JDELAY.cpp \
insts/jg/JFIR/JFIR.cpp \
insts/jg/JGRAN/JGRAN.cpp \
insts/jg/MOOGVCF/MOOGVCF.cpp \
insts/jg/MROOM/MROOM.cpp \
insts/jg/MROOM/timeset.c \
insts/jg/MULTEQ/MULTEQ.cpp \
insts/jg/MULTIWAVE/MULTIWAVE.cpp \
insts/jg/PAN/PAN.cpp \
insts/jg/REV/REV.cpp \
insts/jg/REVERBIT/REVERBIT.cpp \
insts/jg/ROOM/ROOM.cpp \
insts/jg/ROOM/roomset.c \
insts/jg/SHAPE/SHAPE.cpp \
insts/jg/SPECTACLE/SPECTACLE.cpp \
insts/jg/SPECTACLE/SPECTACLE_BASE.cpp \
insts/jg/SPECTACLE/SPECTEQ.cpp \
insts/jg/SPECTACLE/TVSPECTACLE.cpp \
insts/jg/SPECTACLE/fft.c \
insts/jg/SPECTACLE2/SPECTACLE2.cpp \
insts/jg/SPECTACLE2/SPECTACLE2_BASE.cpp \
insts/jg/SPECTACLE2/SPECTEQ2.cpp \
insts/jg/SPLITTER/SPLITTER.cpp \
insts/jg/VOCODE2/VOCODE2.cpp \
insts/jg/VOCODE3/VOCODE3.cpp \
insts/jg/VOCODESYNTH/VOCODESYNTH.cpp \
insts/jg/WAVY/WAVY.cpp \
insts/jg/WAVY/fparser27/fparser.cc \
insts/jg/WIGGLE/WIGGLE.cpp \
insts/stk/MBANDEDWG/MBANDEDWG.cpp \
insts/stk/MBLOWBOTL/MBLOWBOTL.cpp \
insts/stk/MBLOWHOLE/MBLOWHOLE.cpp \
insts/stk/MBOWED/MBOWED.cpp \
insts/stk/MBRASS/MBRASS.cpp \
insts/stk/MCLAR/MCLAR.cpp \
insts/stk/MMESH2D/MMESH2D.cpp \
insts/stk/MMODALBAR/MMODALBAR.cpp \
insts/stk/MSAXOFONY/MSAXOFONY.cpp \
insts/stk/MSHAKERS/MSHAKERS.cpp \
insts/stk/MSITAR/MSITAR.cpp \
insts/maxmsp/MAXBANG/MAXBANG.cpp \
insts/maxmsp/MAXMESSAGE/MAXMESSAGE.cpp \
insts/bgg/GVERB/GVERB.cpp \
insts/bgg/GVERB/gverbdsp.cpp \
insts/bgg/HALFWAVE/HALFWAVE.cpp \
insts/bgg/PFSCHED/PFSCHED.cpp \
insts/bgg/SYNC/SYNC.cpp \
insts/bgg/VWAVE/VWAVE.cpp \
insts/joel/jfuncs/jfuncs.c \
insts/joel/tuning/tuning.c \
src/audio/audio_devices.cpp \
src/rtcmix/RTcmixMain.cpp \
src/rtcmix/main.cpp \
src/parser/parse_with_minc.c \
rtcmix_wrap.c \
native-audio-jni.c # OpenSLES code

LOCAL_LDLIBS    := -lOpenSLES
# for native asset manager
LOCAL_LDLIBS    += -landroid

include $(BUILD_SHARED_LIBRARY)
