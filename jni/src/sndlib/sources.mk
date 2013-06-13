include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= src/sndlib/headers.c src/sndlib/io.c src/sndlib/extra.c
LOCAL_MODULE    := snd
LOCAL_CFLAGS	:= -O3
LOCAL_CPPFLAGS	:= $(LOCAL_CFLAGS)
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix

include $(BUILD_STATIC_LIBRARY)
