include $(CLEAR_VARS)

LOCAL_SRC_FILES	:= src/control/maxmsp/PFBusField.cpp src/control/maxmsp/pfbusglue.cpp
LOCAL_MODULE    := pdbus
#LOCAL_CFLAGS	:= -Werror # -fexceptions
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix

include $(BUILD_STATIC_LIBRARY)
