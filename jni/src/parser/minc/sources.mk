include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= src/parser/minc/y.tab.c src/parser/minc/builtin.c src/parser/minc/callextfunc.cpp src/parser/minc/error.c src/parser/minc/sym.c src/parser/minc/trees.c src/parser/minc/utils.c src/parser/minc/handle.c
LOCAL_MODULE    := minc
LOCAL_CFLAGS	:= -O3
LOCAL_CPPFLAGS	:= $(LOCAL_CFLAGS)
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/include

include $(BUILD_STATIC_LIBRARY)
