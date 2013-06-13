include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= src/control/maxmsp/RTInletPField.cpp src/control/maxmsp/inletglue.cpp
LOCAL_MODULE    := inlet
LOCAL_CFLAGS	:= -O3
LOCAL_CPPFLAGS	:= $(LOCAL_CFLAGS)
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix

include $(BUILD_STATIC_LIBRARY)
