include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= src/conf/conf.tab.cpp src/conf/Config.cpp
LOCAL_MODULE    := conf
LOCAL_CFLAGS	:= -O3
LOCAL_CPPFLAGS	:= $(LOCAL_CFLAGS)
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix

include $(BUILD_STATIC_LIBRARY)
