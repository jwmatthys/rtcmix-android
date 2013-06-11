include $(CLEAR_VARS)
LOCAL_MODULE    := AM
LOCAL_CFLAGS	:= -Werror
LOCAL_C_INCLUDES:= $(LOCAL_PATH)/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/genlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix/heap
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/rtcmix/gen
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/conf
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/audio
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/sndlib
LOCAL_C_INCLUDES += $(LOCAL_PATH)/src/include
LOCAL_STATIC_LIBRARIES := gen
LOCAL_SRC_FILES := src/rtcmix/RTcmix.cpp \
	src/rtcmix/Instrument.cpp \
	insts/std/AM/AM.cpp
include $(BUILD_SHARED_LIBRARY)
