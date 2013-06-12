include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= insts/stk/stklib/Brass.cpp insts/stk/stklib/DelayA.cpp insts/stk/stklib/BiQuad.cpp insts/stk/stklib/Filter.cpp insts/stk/stklib/Stk.cpp insts/stk/stklib/PoleZero.cpp insts/stk/stklib/Instrmnt.cpp insts/stk/stklib/Delay.cpp insts/stk/stklib/DelayL.cpp insts/stk/stklib/Noise.cpp insts/stk/stklib/BowTabl.cpp insts/stk/stklib/BandedWG.cpp insts/stk/stklib/JetTabl.cpp insts/stk/stklib/BlowBotl.cpp insts/stk/stklib/ReedTabl.cpp insts/stk/stklib/OneZero.cpp insts/stk/stklib/BlowHole.cpp insts/stk/stklib/OnePole.cpp insts/stk/stklib/Bowed.cpp insts/stk/stklib/Clarinet.cpp insts/stk/stklib/Flute.cpp insts/stk/stklib/Modal.cpp insts/stk/stklib/ModalBar.cpp insts/stk/stklib/Mesh2D.cpp insts/stk/stklib/Saxofony.cpp insts/stk/stklib/Shakers.cpp insts/stk/stklib/Sitar.cpp

LOCAL_MODULE    := stk
LOCAL_CFLAGS	:= -Werror -fexceptions
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix

include $(BUILD_STATIC_LIBRARY)
