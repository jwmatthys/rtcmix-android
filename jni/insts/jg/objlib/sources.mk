include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= insts/jg/objlib/JGFilter.cpp insts/jg/objlib/JGOnePole.cpp insts/jg/objlib/TwoPole.cpp insts/jg/objlib/JGOneZero.cpp insts/jg/objlib/TwoZero.cpp insts/jg/objlib/NZero.cpp insts/jg/objlib/JGBiQuad.cpp insts/jg/objlib/Butter.cpp insts/jg/objlib/DCBlock.cpp insts/jg/objlib/RMS.cpp insts/jg/objlib/Balance.cpp insts/jg/objlib/DLineN.cpp insts/jg/objlib/DLineL.cpp insts/jg/objlib/DLineA.cpp insts/jg/objlib/Reverb.cpp insts/jg/objlib/PRCRev.cpp insts/jg/objlib/JCRev.cpp insts/jg/objlib/NRev.cpp insts/jg/objlib/Comb.cpp insts/jg/objlib/ZComb.cpp insts/jg/objlib/Notch.cpp insts/jg/objlib/ZNotch.cpp insts/jg/objlib/Allpass.cpp insts/jg/objlib/Envelope.cpp insts/jg/objlib/ADSR.cpp insts/jg/objlib/Oscil.cpp insts/jg/objlib/OscilN.cpp insts/jg/objlib/OscilL.cpp insts/jg/objlib/KOscilN.cpp insts/jg/objlib/TableN.cpp insts/jg/objlib/TableL.cpp insts/jg/objlib/JGNoise.cpp insts/jg/objlib/SubNoise.cpp insts/jg/objlib/SubNoiseL.cpp insts/jg/objlib/WavShape.cpp insts/jg/objlib/ZAllpass.cpp insts/jg/objlib/Equalizer.cpp

LOCAL_MODULE    := obj
LOCAL_CFLAGS	:= -Werror
#LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix

include $(BUILD_STATIC_LIBRARY)
