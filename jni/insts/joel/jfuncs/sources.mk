include $(CLEAR_VARS)
LOCAL_MODULE    := jfuncs
LOCAL_CFLAGS	:= -Werror
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
LOCAL_STATIC_LIBRARIES := gen obj stk rtaudio snd conf inlet pfbus
LOCAL_SRC_FILES := insts/joel/jfuncs/jfuncs.c
include $(BUILD_SHARED_LIBRARY)
